package com.springboot.claude.models;

import java.util.Date;
import java.util.List;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="ideas")
public class Idea {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column
	private String ideaName;
	@Column(updatable=false)
	@DateTimeFormat(pattern="MM/dd/yyyy HH:mm:ss")
	private Date createdAt;
	@Column
	@DateTimeFormat(pattern="MM/dd/yyyy HH:mm:ss")
	private Date updatedAt;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinTable(name="user_id")
	private User user;
	
	@OneToMany(mappedBy="ideaLiked",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<Like> likes;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name="liked_ideas",
			joinColumns=@JoinColumn(name="idea_id"),
			inverseJoinColumns=@JoinColumn(name="user_id")
			)
	private List<User> usersAlreadyLike;
	
	public Idea() {}
	
	public Idea(String idea) {
		this.ideaName = idea;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdea() {
		return ideaName;
	}

	public void setIdea(String idea) {
		this.ideaName = idea;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Like> getLikes() {
		return likes;
	}

	public void setLikes(List<Like> likes) {
		this.likes = likes;
	}
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

	public List<User> getUsersAlreadyLike() {
		return usersAlreadyLike;
	}
	
	public void setUsersAlreadyLike(List<User> usersAlreadyLike) {
		this.usersAlreadyLike = usersAlreadyLike;
	}
}
