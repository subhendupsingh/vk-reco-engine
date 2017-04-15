package com.vk.recoengine.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
public class SkuSettings implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7701496039863742234L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int setting_id;
	private String sku;
	private String title;
	@Column(length=500)
	private String imageURL;
	private Double length;
	private Double width;
	private Double height;
	private Double weight;
	private int wieghtSlab;
	private int deadWieghtSlab;
	private int chargedWeightslab;
	private Date fromDate;
	private Date toDate;
	
	@ManyToOne
	@JoinColumn(name="cat_id")
	@JsonIgnore
	private ProductCategories category;

	
	public int getSetting_id() {
		return setting_id;
	}

	public void setSetting_id(int setting_id) {
		this.setting_id = setting_id;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public Double getLength() {
		return length;
	}

	public void setLength(Double length) {
		this.length = length;
	}

	public Double getWidth() {
		return width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public int getWieghtSlab() {
		return wieghtSlab;
	}

	public void setWieghtSlab(int wieghtSlab) {
		this.wieghtSlab = wieghtSlab;
	}

	public ProductCategories getCategory() {
		return category;
	}

	public void setCategory(ProductCategories category) {
		this.category = category;
	}

	public int getDeadWieghtSlab() {
		return deadWieghtSlab;
	}

	public void setDeadWieghtSlab(int deadWieghtSlab) {
		this.deadWieghtSlab = deadWieghtSlab;
	}

	public int getChargedWeightslab() {
		return chargedWeightslab;
	}

	public void setChargedWeightslab(int chargedWeightslab) {
		this.chargedWeightslab = chargedWeightslab;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
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
