package com.springboot.claude.models;

import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	@NotBlank(message="This field is required and cannot be blank. Name must be between 2 and 200 characters long!")
	@Size(min=2, max=200)
	private String firstName;
	@Column
	@NotBlank(message="This field is required and cannot be blank. Name must be between 2 and 200 characters long!")
	@Size(min=2, max=200)
	private String lastName;
	@Email(message="Invalid entry") 
	private String email;
	@Size(min=8, message="Password must be at least 8 characters.")
	private String password;
	@Transient
	@NotBlank(message="This field cannot be blank")
	private String passwordConfirm;
	@Column(updatable=false)
	@DateTimeFormat(pattern="MM/dd/yyyy HH:mm:ss")
	private Date createdAt;
	@Column
	@DateTimeFormat(pattern="MM/dd/yyyy HH:mm:ss")
	private Date updatedAt;
	
		@OneToMany(mappedBy="user", fetch=FetchType.LAZY)
	private List<Idea> ideas;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name="likedIdeas",
			joinColumns=@JoinColumn(name="user_id"),
			inverseJoinColumns=@JoinColumn(name="idea_id")
			)
	private List<Idea> ideaWithLikes;
	
	public User() {}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public List<Idea> getIdeas() {
		return ideas;
	}

	public void setIdeas(List<Idea> ideas) {
		this.ideas = ideas;
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
	public String getPassConfirm() {
		return passwordConfirm;
	}
	public void setPassConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}		
	public List<Idea> getMyIdeas() {
		return ideas;
	}
	public void setMyIdeas(List<Idea> myIdeas) {
		this.ideas = myIdeas;
	}
	public List<Idea> getLikedIdeas() {
		return ideas;
	}
	public void setLikedIdeas(List<Idea> likedIdeas) {
		this.ideas = likedIdeas;
	}
	
	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
    
}