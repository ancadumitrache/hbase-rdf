package nl.vu.datalayer.sail;

import nl.vu.datalayer.hbase.HBaseUtil;

import org.openrdf.model.ValueFactory;
import org.openrdf.model.impl.ValueFactoryImpl;
import org.openrdf.sail.NotifyingSailConnection;
import org.openrdf.sail.SailException;
import org.openrdf.sail.helpers.NotifyingSailBase;

public class HBaseSail extends NotifyingSailBase {

	private HBaseUtil hbase;
	
	private ValueFactory mValueFactory = new ValueFactoryImpl();
	
	public HBaseSail() {
		try {
			hbase=new HBaseUtil(null);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public ValueFactory getValueFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWritable() throws SailException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected NotifyingSailConnection getConnectionInternal()
			throws SailException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void shutDownInternal() throws SailException {
		// TODO Auto-generated method stub

	}

}
