package com.example.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "app_user")
public class User {
	
	@Id
	@Column(name = "user_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer user_id;

	@Column(name = "user_name", nullable = false)
	private String user_name;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "status", nullable = false)
	private Integer status;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@Column(name = "created_date")
	private Date created_date;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@Column(name = "updated_date")
	private Date updated_date;

	@Column(name = "note")
	private String note;
	
	@Column(name = "full_name")
	private String full_name;
	
	@Column(name = "mobile_phone")
	private String mobile_phone;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "isadmin")
	private Integer isadmin;
	
	@Column(name = "enable")
	private Integer enable;
	
	@Column(name = "functions")
	private Integer functions;
	
	@Column(name = "is_first_login")
	private Integer is_first_login;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@Column(name = "expired_password")
	private Date expired_password;
	
	@Column(name = "is_login_count")
	private Integer is_login_count;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@Column(name = "time_to_lock")
	private Date time_to_lock;

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public Date getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getMobile_phone() {
		return mobile_phone;
	}

	public void setMobile_phone(String mobile_phone) {
		this.mobile_phone = mobile_phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getIsadmin() {
		return isadmin;
	}

	public void setIsadmin(Integer isadmin) {
		this.isadmin = isadmin;
	}

	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	public Integer getFunctions() {
		return functions;
	}

	public void setFunctions(Integer functions) {
		this.functions = functions;
	}

	public Integer getIs_first_login() {
		return is_first_login;
	}

	public void setIs_first_login(Integer is_first_login) {
		this.is_first_login = is_first_login;
	}

	public Date getExpired_password() {
		return expired_password;
	}

	public void setExpired_password(Date expired_password) {
		this.expired_password = expired_password;
	}

	public Integer getIs_login_count() {
		return is_login_count;
	}

	public void setIs_login_count(Integer is_login_count) {
		this.is_login_count = is_login_count;
	}

	public Date getTime_to_lock() {
		return time_to_lock;
	}

	public void setTime_to_lock(Date time_to_lock) {
		this.time_to_lock = time_to_lock;
	}
	
	
	
	
}
