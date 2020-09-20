package com.example.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "app_action_object")
public class UserPermission implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9020863703906041360L;
	
	@Id
	@Column(name = "action_name",nullable = false)
	private String action_name;
	

	@Id
	@Column(name = "object_id", nullable = false)
	private Integer object_id;
	
	@Column(name ="allow", nullable = false)
	private Integer allow;
	
	@Column(name ="created_by")
	private Integer created_by;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@Column(name="created_time")
	private Date created_time;
	
	@Column(name="last_updated_by", nullable= false)
	private Integer last_updated_by;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@Column(name="last_updated_time")
	private Date last_updated_time;

	public String getAction_name() {
		return action_name;
	}

	public void setAction_name(String action_name) {
		this.action_name = action_name;
	}

	public Integer getObject_id() {
		return object_id;
	}

	public void setObject_id(Integer object_id) {
		this.object_id = object_id;
	}

	public Integer getAllow() {
		return allow;
	}

	public void setAllow(Integer allow) {
		this.allow = allow;
	}

	public Integer getCreated_by() {
		return created_by;
	}

	public void setCreated_by(Integer created_by) {
		this.created_by = created_by;
	}

	public Date getCreated_time() {
		return created_time;
	}

	public void setCreated_time(Date created_time) {
		this.created_time = created_time;
	}

	public Integer getLast_updated_by() {
		return last_updated_by;
	}

	public void setLast_updated_by(Integer last_updated_by) {
		this.last_updated_by = last_updated_by;
	}

	public Date getLast_updated_time() {
		return last_updated_time;
	}

	public void setLast_updated_time(Date last_updated_time) {
		this.last_updated_time = last_updated_time;
	}
	
	
	
	
}
