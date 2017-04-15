package com.vk.recoengine.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
public class ProductCategoryCommissions implements Serializable{

	private static final long serialVersionUID = -3934833207259247043L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="cat_id")
	@JsonIgnore
	private ProductCategories category;
	
	private Double commissionPercent;
	private Double alternateCommissionPercent;
	private Date fromDate;
	private Date toDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ProductCategories getCategory() {
		return category;
	}
	public void setCategory(ProductCategories category) {
		this.category = category;
	}
	public Double getCommissionPercent() {
		return commissionPercent;
	}
	public void setCommissionPercent(Double commissionPercent) {
		this.commissionPercent = commissionPercent;
	}
	public Double getAlternateCommissionPercent() {
		return alternateCommissionPercent;
	}
	public void setAlternateCommissionPercent(Double alternateCommissionPercent) {
		this.alternateCommissionPercent = alternateCommissionPercent;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	
	

}
