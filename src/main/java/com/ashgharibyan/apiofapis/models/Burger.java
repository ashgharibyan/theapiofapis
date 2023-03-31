package com.ashgharibyan.apiofapis.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "burgers")
public class Burger {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // generates an auto incrementing
	private Long id;

	// String Attribute
	@NotEmpty(message = "Name name is required!") // validation for strings
	private String name;

	// Integer Attribute
	@NotEmpty(message = "Ingridient name is required!") // validation for strings
	private String ingridient;

	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="template_id")
    private Template template;	public Burger() {

	}

	public Burger(String name, String ingridient){
		this.name = name;
		this.ingridient = ingridient;
	}

	@PrePersist // adds the created at date and time to sql on creation
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate // add the updated at date and time when being updated
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIngridient() {
		return ingridient;
	}

	public void setIngridient(String ingridient) {
		this.ingridient = ingridient;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

}
