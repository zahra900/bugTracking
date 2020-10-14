package com.zahra.app.model;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="PROJECT")
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "PROJECT_NAME")
	private String projectName;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	//@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "project", orphanRemoval = true)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "project"})
	private Set<Bug> bugs = new HashSet<>();
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(name = "PROJECTS_USERS", joinColumns = @JoinColumn(name = "PROJECT_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"))
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Set<User> users  = new HashSet<>();

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	
	public Set<Bug> getBugs() {
		return bugs;
	}

	public void setBugs(Set<Bug> bugs) {
		if (bugs != null) {
        	this.bugs.clear();
        	this.bugs.addAll(bugs);
     }
	}

}
	
	
	
	
	


