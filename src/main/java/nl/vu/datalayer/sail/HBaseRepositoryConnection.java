package nl.vu.datalayer.sail;

import info.aduna.iteration.Iteration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;

import org.openrdf.model.Namespace;
import org.openrdf.model.Resource;
import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.Value;
import org.openrdf.model.ValueFactory;
import org.openrdf.query.BooleanQuery;
import org.openrdf.query.GraphQuery;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.Query;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.RepositoryResult;
import org.openrdf.repository.sail.SailBooleanQuery;
import org.openrdf.repository.sail.SailGraphQuery;
import org.openrdf.repository.sail.SailQuery;
import org.openrdf.repository.sail.SailRepository;
import org.openrdf.repository.sail.SailRepositoryConnection;
import org.openrdf.repository.sail.SailTupleQuery;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFHandler;
import org.openrdf.rio.RDFHandlerException;
import org.openrdf.rio.RDFParseException;
import org.openrdf.sail.SailConnection;

public class HBaseRepositoryConnection extends SailRepositoryConnection {

	protected HBaseRepositoryConnection(SailRepository repository,
			SailConnection sailConnection) {
		super(repository, sailConnection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void add(Statement arg0, Resource... arg1)
			throws RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(Iterable<? extends Statement> arg0, Resource... arg1)
			throws RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <E extends Exception> void add(
			Iteration<? extends Statement, E> arg0, Resource... arg1)
			throws RepositoryException, E {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(InputStream arg0, String arg1, RDFFormat arg2,
			Resource... arg3) throws IOException, RDFParseException,
			RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(Reader arg0, String arg1, RDFFormat arg2, Resource... arg3)
			throws IOException, RDFParseException, RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(URL arg0, String arg1, RDFFormat arg2, Resource... arg3)
			throws IOException, RDFParseException, RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(File arg0, String arg1, RDFFormat arg2, Resource... arg3)
			throws IOException, RDFParseException, RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(Resource arg0, URI arg1, Value arg2, Resource... arg3)
			throws RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clear(Resource... arg0) throws RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearNamespaces() throws RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() throws RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void commit() throws RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void export(RDFHandler arg0, Resource... arg1)
			throws RepositoryException, RDFHandlerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exportStatements(Resource arg0, URI arg1, Value arg2,
			boolean arg3, RDFHandler arg4, Resource... arg5)
			throws RepositoryException, RDFHandlerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RepositoryResult<Resource> getContextIDs()
			throws RepositoryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNamespace(String arg0) throws RepositoryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RepositoryResult<Namespace> getNamespaces()
			throws RepositoryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Repository getRepository() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RepositoryResult<Statement> getStatements(Resource arg0, URI arg1,
			Value arg2, boolean arg3, Resource... arg4)
			throws RepositoryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValueFactory getValueFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasStatement(Statement arg0, boolean arg1, Resource... arg2)
			throws RepositoryException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasStatement(Resource arg0, URI arg1, Value arg2,
			boolean arg3, Resource... arg4) throws RepositoryException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAutoCommit() throws RepositoryException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() throws RepositoryException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isOpen() throws RepositoryException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BooleanQuery prepareBooleanQuery(QueryLanguage arg0, String arg1)
			throws RepositoryException, MalformedQueryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SailBooleanQuery prepareBooleanQuery(QueryLanguage arg0, String arg1,
			String arg2) throws MalformedQueryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GraphQuery prepareGraphQuery(QueryLanguage arg0, String arg1)
			throws RepositoryException, MalformedQueryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SailGraphQuery prepareGraphQuery(QueryLanguage arg0, String arg1,
			String arg2) throws MalformedQueryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query prepareQuery(QueryLanguage arg0, String arg1)
			throws RepositoryException, MalformedQueryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SailQuery prepareQuery(QueryLanguage arg0, String arg1, String arg2)
			throws MalformedQueryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TupleQuery prepareTupleQuery(QueryLanguage arg0, String arg1)
			throws RepositoryException, MalformedQueryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SailTupleQuery prepareTupleQuery(QueryLanguage arg0, String arg1,
			String arg2) throws MalformedQueryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Statement arg0, Resource... arg1)
			throws RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Iterable<? extends Statement> arg0, Resource... arg1)
			throws RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <E extends Exception> void remove(
			Iteration<? extends Statement, E> arg0, Resource... arg1)
			throws RepositoryException, E {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Resource arg0, URI arg1, Value arg2, Resource... arg3)
			throws RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeNamespace(String arg0) throws RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rollback() throws RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAutoCommit(boolean arg0) throws RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setNamespace(String arg0, String arg1)
			throws RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long size(Resource... arg0) throws RepositoryException {
		// TODO Auto-generated method stub
		return 0;
	}

}
