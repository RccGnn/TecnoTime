package it.unisa.model.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import it.unisa.model.beans.BeanMarker;

public interface BeanDaoInterfaceInt<T extends BeanMarker> {
	public void doSave(T entity) throws SQLException;

	public boolean doDelete(int key) throws SQLException;

	public T doRetrieveByKey(int key) throws SQLException;
	
	public ArrayList<T> doRetrieveAll(String order) throws SQLException;
}