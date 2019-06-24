import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import entities.User;
import orm.Connector;
import orm.EntityManager;

public class Main {

	public static void main(String[] args) throws SQLException, IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException, SecurityException {
		
//      PART ONE 
//		Connector.createConnectionToSpecificDb("root", "12345", "my_orm");
//		Connection conn = Connector.getConnection();
//		User user = new User("Grigor Dimitrov", "12345", 39, Date.valueOf("2019-11-06"));
//		user.setId(5);
//		EntityManager<User> userManager = new EntityManager<User>(conn);
//		userManager.persist(user);
//		User found = userManager.findFirst(User.class, "username = 'Grigor Dimitrov'");
//		System.out.println(found.getUsername());
		
		
//		List<User> allUsers = (List<User>) userManager.find(User.class, "age=35");
//		allUsers.forEach(au -> System.out.println(au.getRegistrationDate()));
		
		
		// PART TWO 
		Connector.createConnectionToLocalHost("root", "12345");
		Connection conn = Connector.getConnection();
		EntityManager manager = new EntityManager(conn);
		manager.createDB("my_orm");
		manager.createAtableInDatabase("my_orm", User.class);
		
	}
}
