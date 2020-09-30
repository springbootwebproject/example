package com.example.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "story_content")
public class StoryEntity {
	
	
	@Id
	@Column(name = "story_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer story_id;
	
	@OneToOne
	private Category category;
	
	@Column(name = "story_name", nullable = false)
	private String story_name;
	
	@Column(name = "status", nullable = false)
	private String status;
	
	@DateTimeFormat(pattern ="dd-MM-yyyy HH:mm:ss")
	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date created_at;
	
	@Column(name= "image_path")
	private String image_path;
	
	@Column(name = "title_path")
	private String title_path;
	
	
	@DateTimeFormat(pattern ="dd-MM-yyyy HH:mm:ss")
	@Column(name = "publish_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date publish_time;
	
	@ManyToOne
	private User author;
	
	@Column(name = "path",nullable = false)
	private String path;
	
	@Column(name = "summary_path")
	private String summary_path;

	public Integer getStory_id() {
		return story_id;
	}

	public void setStory_id(Integer story_id) {
		this.story_id = story_id;
	}
	
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	

	public String getStory_name() {
		return story_name;
	}

	public void setStory_name(String story_name) {
		this.story_name = story_name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public String getImage_path() {
		return image_path;
	}

	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}

	public String getTitle_path() {
		return title_path;
	}

	public void setTitle_path(String title_path) {
		this.title_path = title_path;
	}

	public Date getPublish_time() {
		return publish_time;
	}

	public void setPublish_time(Date publish_time) {
		this.publish_time = publish_time;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getSummary_path() {
		return summary_path;
	}

	public void setSummary_path(String summary_path) {
		this.summary_path = summary_path;
	}
	
	
}
