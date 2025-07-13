package it.unisa.model.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import it.unisa.model.beans.BeanMarker;

public interface BeanDaoInterface<T extends BeanMarker> {
	public void doSave(T entity) throws SQLException;

	public boolean doDelete(String key) throws SQLException;

	public T doRetrieveByKey(String key) throws SQLException;
	
	public ArrayList<T> doRetrieveAll(String order) throws SQLException;
}
