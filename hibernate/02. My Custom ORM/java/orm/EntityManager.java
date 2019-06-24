package orm;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;



import annotations.Column;
import annotations.Entity;
import annotations.Id;
import interfaces.DbContext;

public class EntityManager<E> implements DbContext<E> {
	
	private static final String INSERT_STATEMENT = "insert into %s(%s) values(%s)";
	private static final String UPDATE_STATEMENT = "update %s set %s where %s";
	private static final String SELECT_FIRST_WITH_WHERE = "select * from %s %s limit 1";
	private static final String SELECT_ALL_WITH_WHERE = "select * from %s %s";
	private static final String CREATE_DATABASE = "create schema %s";
	private static final String USE_DATABASE = "use %s";
	private static final String CREATE_TABLE = "create table %s(%s)";
	private Connection connection;
	
	public EntityManager(Connection connection) {
		this.connection = connection;
	}
	
	//Create a table by a given entity using annotations
	public <E> void createAtableInDatabase(String databaseName, Class entity) throws SQLException {
		String useDbQuery = String.format(USE_DATABASE, databaseName);
		Statement ps = this.connection.createStatement();
		ps.executeUpdate(useDbQuery);
		
		String tableName = entity.getDeclaredAnnotation(Entity.class).toString().split("[(=)]")[2];;
		
		String id[] = new String[2];
		List<String> params = new ArrayList<String>();
		List<String> finalParams = new ArrayList<String>();
		
		Field fields[] = entity.getDeclaredFields(); // can go in a separate method
			Arrays.stream(fields).forEach(f -> {
					if(f.isAnnotationPresent(Id.class)) {
						id[0] = f.getName();
						id[1] = f.getType().getSimpleName();
					} else if(f.isAnnotationPresent(Column.class)) {
						params.add(f.getDeclaredAnnotation(Column.class).name() + " " + f.getType().getSimpleName());
					}
			});
			
			finalParams.add(id[0] + " " + id[1] + " primary key");
			params.forEach(p -> {  // can go in a separate method
				if(p.contains("String")){
					String replace = p.replace("String", "varchar(55)");
					finalParams.add(replace);
				}else if (p.contains("Integer")) {
					String replace = p.replace("Integer", "int");
					finalParams.add(replace);
				} else {
					finalParams.add(p);
				}
			});
	
			
			
			ps.executeUpdate(String.format(CREATE_TABLE,
					tableName,  finalParams.toString().substring(1, finalParams.toString().length() - 1)));
		
	}
	
	// Create a db
	public void createDB(String dbName) throws SQLException {
		String query = String.format(CREATE_DATABASE, dbName);
		connection.prepareStatement(query).execute();
	}
	
	
	
	// Part ONE BELOW
	public boolean persist(E entity) throws IllegalArgumentException, IllegalAccessException, SQLException {
		Field idField = this.getId(entity.getClass());
		idField.setAccessible(true);
		Object idValue = idField.get(entity);

			if(idValue == null || (int)idValue <= 0) { // not ok works only for id with integer values!
				return doInsert(entity, idField);
			}
		return doUpdate(entity, idField);
	}

	
	// Inserting into the DB
	private boolean doInsert(E entity, Field idField) throws SQLException {
		String tableName = this.getTableName(entity); 
		String tableFields[] =  this.getTableFields(entity);
		String tableValues[] =  this.getTableValues(entity);
				
		String query = String.format(INSERT_STATEMENT, 
				tableName,
				String.join(", ", tableFields),
				String.join(", ", tableValues));
		
		return this.connection.prepareStatement(query).execute();
	}


	// Updating the DB
	private boolean doUpdate(E entity, Field idField) throws SQLException, IllegalArgumentException, IllegalAccessException {
		String tableName = this.getTableName(entity); 
		String tableFields[] =  this.getTableFields(entity);
		String tableValues[] =  this.getTableValues(entity);
		
		List<String> updateList = new ArrayList<String>();
		for(int i = 0; i < tableFields.length; i++) {
			String temp = tableFields[i]  + " = " + tableValues[i];
			updateList.add(temp);
		}
		
	String whereId = "`" + idField.getName() + "` = " + idField.get(entity);  
	
	String query = String.format(UPDATE_STATEMENT, 
			tableName,
			String.join(", ", updateList),
			whereId);
	
		return this.connection.prepareStatement(query).execute();
	}
	
	
	// Select all with or without where clause and return a collection
	public Iterable<E> find(Class<E> table) throws SQLException, InstantiationException, 
		IllegalAccessException, IllegalArgumentException, InvocationTargetException, 
				NoSuchMethodException, SecurityException {
		
		return this.find(table, null);
	}

	public Iterable<E> find(Class<E> table, String where) throws SQLException, InstantiationException, 
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, 
					NoSuchMethodException, SecurityException {
		
		Statement stm = this.connection.createStatement();
		String tableName = this.getTableName(table.getConstructor().newInstance());
		where = where != null ? "where " + where : "";
		String query = String.format(SELECT_ALL_WITH_WHERE
												, tableName, where);
		ResultSet result = stm.executeQuery(query);	
		
		return getEntityCollection(result, table);
	}

	private Iterable<E> getEntityCollection(ResultSet result, Class<E> table) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, SQLException {
		List<E> collection = new ArrayList<E>();
		
			while(result.next()) {
			E entity = this.mapResultToEntity(result, table);	
					collection.add(entity);
			}
		
		return collection;
	}

	// Find the first elements with and without where clause
	public E findFirst(Class<E> table) throws InstantiationException, IllegalAccessException, SQLException, 
	IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		
		return this.findFirst(table, null);
	}

	public E findFirst(Class<E> table, String where) throws SQLException, InstantiationException, 
		IllegalAccessException, IllegalArgumentException, InvocationTargetException, 
			NoSuchMethodException, SecurityException {
		
		Statement stm = this.connection.createStatement();
		String tableName = this.getTableName(table.getConstructor().newInstance());
		where = where != null ? "where " + where : "";
		String query = String.format(SELECT_FIRST_WITH_WHERE
												, tableName, where);
		ResultSet result = stm.executeQuery(query);
		result.next();
		
		return this.mapResultToEntity(result, table);
	}
	
	
	private E mapResultToEntity(ResultSet result, Class<E> table) 
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, 
				InvocationTargetException, NoSuchMethodException, SecurityException {
		E entity = table.newInstance();
		
		Arrays.stream(table.getDeclaredFields())
			  .forEach(f -> {
				  f.setAccessible(true);
				  String name = f.getName();
				  Object value = null;
				try {
					if(f.getType() == int.class || f.getType() == Integer.class) {
						value = result.getInt(name);
					} else if(f.getType() == long.class || f.getType() == Long.class) {
						value = result.getLong(name);
					} else if(f.getType() == String.class) {
						value = result.getString(name);
					} else if (f.getType() == Date.class) {
						value = result.getDate(name);
					}
					
					f.set(entity, value);
				} catch (Exception e1) { return;}
			  });
			return entity;
	}

	private Field getId(Class<?> entity) {
	
		Field[] fields = entity.getDeclaredFields();
		return Arrays.stream(fields).filter(f -> f.isAnnotationPresent(Id.class))
		.findFirst()
		.orElseThrow(() ->
				new UnsupportedOperationException("Entity does not have primary key"));
	}

	// Getting the table name
	private String getTableName(E entity) {
		
		//return entity.getClass().getSimpleName().toLowerCase().concat("s");
		return entity.getClass().getDeclaredAnnotation(Entity.class).name();
	}
	
	// Get any table0(entity) fields 
	private String[] getTableFields(E entity ) {
		return Arrays.stream(entity.getClass().getDeclaredFields())
	     .filter(f -> f.isAnnotationPresent(Column.class))
	     .map(f -> {
	    	 f.setAccessible(true);
	    	 return f.getName();
	    	 }
	     )
	     .toArray(String[]:: new);
	}
	
	// Getting any table(entity) values
	private String[] getTableValues(E entity) {
		
		return Arrays.stream(entity.getClass().getDeclaredFields())
			     .filter(f -> f.isAnnotationPresent(Column.class))
			     .map(f -> {
			    	 f.setAccessible(true);
			    	 Object result = "";
			    	 try {
						result = "'" + (Object) f.get(entity) + "'";
					} catch (Exception e) {}
					return result;
			    	
			    	}
			     )
			     .toArray(String[]::new);
	}
}
































