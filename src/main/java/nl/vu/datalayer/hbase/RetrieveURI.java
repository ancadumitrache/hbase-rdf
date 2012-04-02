package nl.vu.datalayer.hbase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.io.FilenameUtils;
import org.openrdf.model.Literal;
import org.openrdf.model.Resource;
import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.Value;
import org.openrdf.model.impl.LiteralImpl;
import org.openrdf.model.impl.StatementImpl;
import org.openrdf.model.impl.URIImpl;


public class RetrieveURI {
	
	String table;
	
	public RetrieveURI(String t) {
		table = FilenameUtils.removeExtension(FilenameUtils.getName(t));
	}
	
	public ArrayList<Statement> retrieveSubject(String uri) {
		ArrayList<Statement> list = new ArrayList();
		
		try {
			HBaseUtil util = new HBaseUtil(null);
			
			ArrayList<ArrayList<String>> triples = util.getRow(uri, table);
			
			for (Iterator<ArrayList<String>> it = triples.iterator(); it.hasNext();) {
				
				ArrayList<String> triple = (ArrayList<String>)it.next();
				int index = 0;
				
//				out.write("<" + URI + "> ");
				Resource subj = new URIImpl(uri);
				
				URI pred = null;
				Value obj = null;
				for (Iterator<String> jt = triple.iterator(); jt.hasNext();) {
					String res = (String)jt.next();
					index++;
					
					if (index == 1) {
						res = util.getPredicate(res);
						pred = new URIImpl(res);
					}
					else if (index == 2) {
//						out.write("<" + res + "> ");
						if (res.compareTo("resource") == 0) {
							String o = (String)jt.next();
							obj = new URIImpl(o);
						}
						else if (res.compareTo("literal") == 0) {
							String o = (String)jt.next();
							obj = new LiteralImpl(o);
						}
					}
				}
//				out.write(".\n");

				Statement s = new StatementImpl(subj, pred, obj);
				list.add(s);
			}
			
//			out.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public void printURIInfo(String URI) {
		try {
			ArrayList<Statement> list = retrieveSubject(URI);
			Iterator it= list.iterator();
			while (it.hasNext()) {
				Statement s = (Statement)it.next();
				System.out.print("<" + s.getSubject().stringValue() + "> <" + 
									s.getPredicate().stringValue() + "> ");
				if (s.getObject() instanceof Literal) {
					System.out.print("\"" + s.getObject().stringValue() + "\"" + " .\n");
				}
				else {
					System.out.print("<" + s.getObject().stringValue() + "> .\n");
				}
			}
		}
		catch (Exception e) {
		}
	}
	
	public void retrieveFile(String inFile, String outFile) {
		try {
			  FileInputStream ifstream = new FileInputStream(inFile);
			  DataInputStream in = new DataInputStream(ifstream);
			  BufferedReader br = new BufferedReader(new InputStreamReader(in));
			  
			  FileWriter ofstream = new FileWriter(outFile);
			  BufferedWriter out = new BufferedWriter(ofstream);
			  
			  String strLine;
			  while ((strLine = br.readLine()) != null)   {
				  ArrayList<Statement> list = retrieveSubject(strLine);
				  
				  Iterator it= list.iterator();
				  while (it.hasNext()) {
						Statement s = (Statement)it.next();
						out.write("<" + s.getSubject().stringValue() + "> <" + 
											s.getPredicate().stringValue() + "> ");
						if (s.getObject() instanceof Literal) {
							out.write(s.getObject().stringValue() + " .\n");
						}
						else {
							out.write("<" + s.getObject().stringValue() + "> .\n");
						}
				  }
			  }
			  
			  in.close();
			  out.close();
		}
		catch (Exception e) {
			  System.err.println("Error: " + e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		RetrieveURI ruri = new RetrieveURI("tbl-card");
		ruri.printURIInfo("http://www.w3.org/data#W3C");
		
		//retrieveFile("/home/anca/Documents/OPS/trials/URIlist", "/home/anca/Documents/OPS/trials/out.ttl", "excerpt");
	}
}
