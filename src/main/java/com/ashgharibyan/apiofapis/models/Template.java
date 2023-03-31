package com.ashgharibyan.apiofapis.models;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="templates")
public class Template {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	//Class Name
	@NotEmpty(message = "Class name is required!") // validation for strings
	private String className;
	
	//Table name - lowercase and make plural of className
	
	//String Attribute
	@NotEmpty(message = "String Attribute name is required!") // validation for strings
	private String stringAttribute1;
	
	//Integer Attribute
	@NotEmpty(message = "Integer Attribute name is required!") // validation for strings
	private String integerAttribute1;
	
	
	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;
	
    //APPLICATION TO ADD ONE TO MANY RELATIONSHIPS LATER




	@OneToMany(mappedBy="template", fetch=FetchType.LAZY)
	private List<Cat> cats;



	@OneToMany(mappedBy="template", fetch=FetchType.LAZY)
	private List<Dog> dogs;



	@OneToMany(mappedBy="template", fetch=FetchType.LAZY)
	private List<Zoom> zooms;



	@OneToMany(mappedBy="template", fetch=FetchType.LAZY)
	private List<Ninja> ninjas;



	@OneToMany(mappedBy="template", fetch=FetchType.LAZY)
	private List<Bank> banks;

	
	@OneToMany(mappedBy="template", fetch=FetchType.LAZY)
	private List<Burger> burgers;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    
	public Template(){
	}
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getStringAttribute1() {
		return stringAttribute1;
	}

	public void setStringAttribute1(String stringAttribute1) {
		this.stringAttribute1 = stringAttribute1;
	}

	public String getIntegerAttribute1() {
		return integerAttribute1;
	}

	public void setIntegerAttribute1(String integerAttribute1) {
		this.integerAttribute1 = integerAttribute1;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}


	
}
