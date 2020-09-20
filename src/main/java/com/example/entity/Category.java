package com.example.entity;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "app_category")
public class Category {
	
	@Id
	@Column(name = "category_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer category_id;
	
	@Column(name = "category_name", nullable = false)
	private String category_name;
	
	@Column(name = "status", nullable = false)
	private Integer status;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date created_date;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@Column(name = "updated_dated")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated_dated;
	
	@Column(name = "updated_by")
	private Integer updated_by;
	
	@OneToOne
	private CategoryParent parent_category_id;
	
	
	public Date getUpdated_dated() {
		return updated_dated;
	}

	public void setUpdated_dated(Date updated_dated) {
		this.updated_dated = updated_dated;
	}

	public Integer getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(Integer updated_by) {
		this.updated_by = updated_by;
	}

	public CategoryParent getParent_category_id() {
		return parent_category_id;
	}

	public void setParent_category_id(CategoryParent parent_category_id) {
		this.parent_category_id = parent_category_id;
	}

	public Integer getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
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
	
	
	
}
