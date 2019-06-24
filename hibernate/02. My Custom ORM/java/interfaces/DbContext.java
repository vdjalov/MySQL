package interfaces;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface DbContext<E> {

	boolean persist(E entity) throws IllegalArgumentException, IllegalAccessException, SQLException;
	
	Iterable<E> find(Class<E> table) throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException;
	
	Iterable<E> find(Class<E> table, String where) throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException;
	
	E findFirst(Class<E> table) throws InstantiationException, IllegalAccessException, SQLException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException;
	
	E findFirst(Class<E> table, String where) 
			throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException;
	
	public void createDB(String dbName) throws SQLException;	
	public <E> void createAtableInDatabase(String databaseName, Class entity) throws SQLException; 
	
}
