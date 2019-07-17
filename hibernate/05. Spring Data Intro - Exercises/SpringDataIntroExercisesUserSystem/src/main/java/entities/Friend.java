package entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "friends")
public class Friend {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	
	@ManyToMany(mappedBy = "allFriends")
	private Set<User>friends;


	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}
	
	
}
