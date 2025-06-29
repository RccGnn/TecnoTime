package it.unisa.model.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import it.unisa.model.beans.BeanMarker;

public interface BeanDaoInterfaceArray<T extends BeanMarker> {
	public void doSave(T entity) throws SQLException;

	public boolean doDelete(ArrayList<?> key) throws SQLException;

	public T doRetrieveByKey(ArrayList<?> key) throws SQLException;
	
	public ArrayList<T> doRetrieveAll(String order) throws SQLException;
}