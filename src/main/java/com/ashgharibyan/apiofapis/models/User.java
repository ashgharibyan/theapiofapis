package com.ashgharibyan.apiofapis.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

    
@Entity
@Table(name="users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message="Username is required!")
    @Size(min=3, max=30, message="Username must be between 3 and 30 characters")
    private String userName;
    
    @NotEmpty(message="Email is required!")
    @Email(message="Please enter a valid email!")
    private String email;
    
    @NotEmpty(message="Password is required!")
    @Size(min=8, max=128, message="Password must be between 8 and 128 characters")
    private String password;
    
    @Transient
    private String confirm;
    
    // LIST OF APIs(Models)
    @Column(columnDefinition="BLOB")
    private ArrayList<String> allAPIs;
    
    @Column(columnDefinition="BLOB")
    private ArrayList<HashMap<String,String>> allAPILinks;

    
	@OneToMany(mappedBy="user", fetch=FetchType.LAZY)
	private List<Template> templates;
  
    public User() {
    	allAPIs = new ArrayList<String>();
    	allAPILinks = new ArrayList<HashMap<String,String>>();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public ArrayList<String> getAllAPIs() {
		return allAPIs;
	}

	public void setAllAPIs(ArrayList<String> allAPIs) {
		this.allAPIs = allAPIs;
	}

	public List<Template> getTemplates() {
		return templates;
	}

	public void setTemplates(List<Template> templates) {
		this.templates = templates;
	}

	public ArrayList<HashMap<String,String>> getAllAPILinks() {
		return allAPILinks;
	}

	public void setAllAPILinks(ArrayList<HashMap<String,String>> allAPILinks) {
		this.allAPILinks = allAPILinks;
	}


	
    
    
  
}
    
