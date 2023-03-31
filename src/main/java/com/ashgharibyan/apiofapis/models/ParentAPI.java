//package com.ashgharibyan.apiofapis.models;
//
//import java.util.Date;
//import java.util.List;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.PrePersist;
//import javax.persistence.PreUpdate;
//import javax.persistence.Table;
//
//import org.springframework.format.annotation.DateTimeFormat;
//
//@Entity
//@Table(name="parentAPIs")
//public class ParentAPI {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//	
//	private List<Object> userNames; 
//	
//	
//	@Column(updatable = false)
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	private Date createdAt;
//	
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	private Date updatedAt;
//	
//	public ParentAPI(){
//	}
//	
//	@PrePersist
//	protected void onCreate() {
//		this.createdAt = new Date();
//	}
//
//	@PreUpdate
//	protected void onUpdate() {
//		this.updatedAt = new Date();
//	}
//	
//	public Date getCreatedAt() {
//		return createdAt;
//	}
//
//	public Date getUpdatedAt() {
//		return updatedAt;
//	}
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public List<Object> getUserNames() {
//		return userNames;
//	}
//
//	public void setUserNames(List<Object> userNames) {
//		this.userNames = userNames;
//	}
//
//	
//
//	
//}
