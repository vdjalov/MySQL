package shampooCompany.labels;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import shampooCompany.interfaces.Label;
import shampooCompany.shampoos.BasicShampoo;

@Entity
@Table(name = "labels")
public class BasicLabel implements Label {

	@Id
	@GeneratedValue
	private long id;
	private String title;
	private String subtitle;
	
	@OneToOne(targetEntity = BasicShampoo.class, mappedBy = "label", 
									fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private BasicShampoo basicShampoo;
	
	
	public BasicLabel(){};
	
	public BasicLabel(String title, String subtitle) {
		this.title = title;
		this.subtitle = subtitle;
	}
	
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return this.subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

}
