package nl.vu.datalayer.sail;

import java.io.File;

import org.openrdf.model.ValueFactory;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.sail.SailRepository;
import org.openrdf.repository.sail.SailRepositoryConnection;
import org.openrdf.sail.Sail;

public class HBaseRepository extends SailRepository {

	public HBaseRepository(Sail sail) {
		super(sail);
		// TODO Auto-generated constructor stub
	}

	@Override
	public HBaseRepositoryConnection getConnection() throws RepositoryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File getDataDir() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValueFactory getValueFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initialize() throws RepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isWritable() throws RepositoryException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setDataDir(File arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shutDown() throws RepositoryException {
		// TODO Auto-generated method stub
		
	}

}
