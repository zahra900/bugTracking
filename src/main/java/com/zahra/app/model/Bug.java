package com.zahra.app.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;



@Entity
@Table(name = "BUG")
@TypeDef(name = "priority", typeClass = PostgreSQLEnumType.class)
@TypeDef(name = "status", typeClass = PostgreSQLEnumType.class)
public class Bug implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "TITLE")
	private String title;
	
	@Column(name = "BUG_DESCRIPTION")
	private String bugDescription;
	
	@Column(name = "RESOLUTION_SUMMARY")
	private String resolutionSummary;
	
	@Basic(optional = false)
	@CreationTimestamp
	@Column(name = "CREATED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate = new Date();
	
	@Enumerated(EnumType.STRING)
	@Type(type = "status")
	@Column(name = "STATUS")
	private Status status;
	
	@Enumerated(EnumType.STRING)
	@Type(type = "priority")
	@Column(name = "PRIORITY")
	private Priority priority;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "assignee_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	private User assignee;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "assigner_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	private User assigner;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	//@JsonBackReference
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "bugs"})
    private Project project;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "bug", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private Set<Comment> comments = new HashSet<>();
	
	public void setComments(Set<Comment> comments) {
        this.comments.clear();
        if (comments != null) {
            this.comments.addAll(comments);
        }
    }
	
	public Set<Comment> getComments() {
		return comments;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBugDescription() {
		return bugDescription;
	}

	public void setBugDescription(String bugDescription) {
		this.bugDescription = bugDescription;
	}

	public String getResolutionSummary() {
		return resolutionSummary;
	}

	public void setResolutionSummary(String resolutionSummary) {
		this.resolutionSummary = resolutionSummary;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public User getAssignee() {
		return assignee;
	}

	public void setAssignee(User assignee) {
		this.assignee = assignee;
	}

	public User getAssigner() {
		return assigner;
	}

	public void setAssigner(User assigner) {
		this.assigner = assigner;
	}


	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
}
