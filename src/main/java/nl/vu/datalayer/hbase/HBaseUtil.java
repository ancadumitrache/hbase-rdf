package nl.vu.datalayer.hbase;

import java.util.ArrayList;
import java.util.Iterator;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.filter.ValueFilter;
import org.apache.hadoop.hbase.filter.WritableByteArrayComparable;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.HConstants;
import org.openrdf.model.URI;
import org.openrdf.model.impl.URIImpl;

import com.sun.appserv.util.cache.Constants;


public class HBaseUtil {	
	
	public static HBaseAdmin hbase = null;
	public static Configuration conf = null;
	
	public static String encodePred(String pred) {
		String encodedPred = Integer.toString(pred.hashCode());
		return encodedPred.replace("-", "0");
	}
	
	public HBaseUtil(String configFilePath) throws Exception
	{
		conf = HBaseConfiguration.create();
	    
		if (configFilePath == null) {
			System.out.println("got here");
			conf.set("hbase.master","localhost:60000");
	    }
		else {
			System.out.println("Yeah!!!");
			
		}
		
		System.out.println(conf);
		hbase = new HBaseAdmin(conf);
		
		
	}
	
	public void cachePredicates()  throws IOException {
	
	    
	    HTableDescriptor desc;
	    if (hbase.tableExists("predicates") == false) {
	    	desc = new HTableDescriptor("predicates");
	    }
	    else {
		     desc = hbase.getTableDescriptor("predicates".getBytes());
	    }
	    
	    HColumnDescriptor c = new HColumnDescriptor("URI".getBytes());
	    desc.addFamily(c);

	    if (hbase.tableExists("predicates") == false) {
	    	hbase.createTable(desc);
	    }
	    
	    hbase.enableTable("predicates");
	}
	
	public void createTableStruct(String table, ArrayList<String> columns)  throws IOException {

	    HTableDescriptor desc;
	    if (hbase.tableExists(table) == false) {
	    	desc = new HTableDescriptor(table);
//	    	HColumnDescriptor literal = new HColumnDescriptor("literal".getBytes());
//	    	desc.addFamily(literal);
	    }
	    else {
		     desc = hbase.getTableDescriptor(table.getBytes());
	    }
	    
		for (Iterator<String> iter = columns.iterator(); iter.hasNext();) {
			String columnName = encodePred(iter.next());
//			System.out.println("COLUMN: " + columnName);
			
			HColumnDescriptor c = new HColumnDescriptor(columnName.getBytes());
			if (desc.hasFamily(columnName.getBytes()) == false) {
				desc.addFamily(c);
			}
		}

	    if (hbase.tableExists(table) == false) {
	    	hbase.createTable(desc);
	    }
	    
	    hbase.enableTable(table);
	}
	
	public  void addRow(String tableName, String key, String columnFam, String columnName, String val) throws IOException {

//	    System.out.println("PRED ENTRY: " + columnFam);
	    // add triples to HBase
		String hashPred = encodePred(columnFam);
		
	    HTable table = new HTable(conf, tableName);
	    Put row = new Put(Bytes.toBytes(key));
	    row.add(Bytes.toBytes(hashPred), Bytes.toBytes(columnName), Bytes.toBytes(val));
	    table.put(row);
	    

	    table = new HTable(conf, "predicates");
	    row = new Put(Bytes.toBytes(hashPred)); 
	    row.add(Bytes.toBytes("URI"), Bytes.toBytes(""), Bytes.toBytes(columnFam));
	    table.put(row);
	}
	
	public ArrayList<ArrayList<String>> getRow(String URI, String tableName)  throws IOException {

	    HTable table = new HTable(conf, tableName);
	    System.out.println("Fetching from TABLE: " + tableName + ", URI:" + URI);
	    
		Get g = new Get(Bytes.toBytes(URI));
	    Result r = table.get(g);
	    
	    ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
	    List<KeyValue> rawList = r.list();
	    
	    for (Iterator<KeyValue> it = rawList.iterator(); it.hasNext();) {
	    	KeyValue k = (KeyValue)it.next();
	    	ArrayList<String> triple = new ArrayList();
	    	
	    	String pred = Bytes.toString(k.getFamily());
	    	triple.add(pred);
	    	
	    	String objType = Bytes.toString(k.getQualifier());
	    	triple.add(objType);
	    	
	    	String val = Bytes.toString(k.getValue());
	    	triple.add(val);
	    	
	    	list.add(triple);
	    }
	    
	    return list;
	}
	
	public String getPredicate(String pred) throws IOException {
		String URI = "";
		
	    HTable table = new HTable(conf, "predicates");
	    
	    System.out.println("ENCODED PRED: " + encodePred(pred));
	    
		Get g = new Get(Bytes.toBytes(pred));
		
//		Filter f = new SingleColumnValueFilter(Bytes.toBytes("URI"),
//				Bytes.toBytes(""), CompareFilter.CompareOp.EQUAL, Bytes.toBytes(pred));
//		g.setFilter(f);
	    Result r = table.get(g);
	    
	    List<KeyValue> rawList = r.list();
	    
	    for (Iterator<KeyValue> it = rawList.iterator(); it.hasNext();) {
	    	KeyValue k = (KeyValue)it.next();
	    	URI = Bytes.toString(k.getValue());
	    }
		
		return URI;
	}
	
	public ArrayList<ArrayList<String>> getValue(String URI, String tableName)  throws IOException {
		HTable table = new HTable(conf, tableName);
	    
		Get g = new Get();
		ValueFilter vf = new ValueFilter(CompareFilter.CompareOp.EQUAL, new BinaryComparator(Bytes.toBytes(URI)));
	    Result r = table.get(g);
	    
	    ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
	    List<KeyValue> rawList = r.list();
	    
	    for (Iterator<KeyValue> it = rawList.iterator(); it.hasNext();) {
	    	KeyValue k = (KeyValue)it.next();
	    	ArrayList<String> triple = new ArrayList();
	    	
	    	String pred = Bytes.toString(k.getFamily());
	    	triple.add(pred);
	    	
	    	String objType = Bytes.toString(k.getQualifier());
	    	triple.add(objType);
	    	
	    	String val = Bytes.toString(k.getValue());
	    	triple.add(val);
	    	
	    	list.add(triple);
	    }
	    
	    return list;
	}
}
