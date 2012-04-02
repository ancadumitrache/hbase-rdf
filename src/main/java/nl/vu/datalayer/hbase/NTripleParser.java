package nl.vu.datalayer.hbase;



import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.io.FilenameUtils;

import org.openrdf.model.Resource;
import org.openrdf.model.Literal;
import org.openrdf.model.URI;
import org.openrdf.model.Statement;
import org.openrdf.rio.RDFHandlerException;
import org.openrdf.rio.RDFParseException;
import org.openrdf.rio.RDFParser;
import org.openrdf.rio.helpers.StatementCollector;
import org.openrdf.rio.turtle.TurtleParser;

import java.io.IOException;


public class NTripleParser {
	
	String file;
	String confPath;
	
	public NTripleParser(String f, String cp) {
//		file = FilenameUtils.removeExtension(FilenameUtils.getName(f));
		file = f;
		confPath = cp;
	}
	
	public void parse() throws IOException {
		
		try {
			HBaseUtil util = new HBaseUtil(confPath);
			FileInputStream is = new FileInputStream(file);
			RDFParser rdfParser = new TurtleParser();
			
			ArrayList<Statement> myList = new ArrayList<Statement>();
			StatementCollector collector = new StatementCollector(myList);
			rdfParser.setRDFHandler(collector);
			
			try {
			   rdfParser.parse(is, "");
			} 
			catch (IOException e) {
			  // handle IO problems (e.g. the file could not be read)
			}
			catch (RDFParseException e) {
			  // handle unrecoverable parse error
			}
			catch (RDFHandlerException e) {
			  // handle a problem encountered by the RDFHandler
			}
			
			String tableName = FilenameUtils.removeExtension(FilenameUtils.getName(file));
			
			// create table column families
			ArrayList<String> predicatesHash = new ArrayList<String>();
			for (Iterator<Statement> iter = myList.iterator(); iter.hasNext();) {
				Statement s = iter.next();
				
//				System.out.println("PREDICATE: " + s.getPredicate().stringValue()
//						+ " = " + pred);
				
				predicatesHash.add(s.getPredicate().stringValue());
			}
			util.createTableStruct(tableName, predicatesHash);
			util.cachePredicates();
			
			// populate table
			for (Iterator<Statement> iter = myList.iterator(); iter.hasNext();) {
				Statement s = iter.next();
				if (s.getObject() instanceof Resource){
					util.addRow(tableName, s.getSubject().stringValue(), 
							s.getPredicate().stringValue() ,"resource",
							s.getObject().stringValue());
				}
				else {
					util.addRow(tableName, s.getSubject().stringValue(), 
							s.getPredicate().stringValue() ,"literal",
							s.getObject().stringValue());
//					System.out.println(tableName + " - " + s.getSubject().toString() +
//							" - " + s.getPredicate().toString() + " - " + "literal" + " - " + 
//							s.getObject().toString());
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			// FileInputStream exception
		}
	}
	
	public static void main(String[] args) {
		try {
			NTripleParser ntp = new NTripleParser("data/tbl-card.nt", null);
			ntp.parse();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
