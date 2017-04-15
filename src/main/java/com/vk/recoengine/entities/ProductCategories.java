package com.vk.recoengine.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class ProductCategories implements Serializable{

	private static final long serialVersionUID = 6998633244304477289L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="cat_id")
	private int id;
	private String categoryName;
	@ManyToOne
	@JoinColumn(name="sd_portal_id")
	@JsonBackReference
	private SnapdealPortal sdPortal;
	private Date created;
	
	@OneToMany(mappedBy="category",fetch=FetchType.EAGER)
	private Set<ProductCategoryCommissions> commissions;
	
	@OneToMany(mappedBy="category",fetch=FetchType.EAGER)
	private Set<SkuSettings> skus;

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public SnapdealPortal getSdPortal() {
		return sdPortal;
	}
	public void setScPortal(SnapdealPortal sdPortal) {
		this.sdPortal = sdPortal;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Set<ProductCategoryCommissions> getCommissions() {
		return commissions;
	}
	public void setCommissions(Set<ProductCategoryCommissions> commissions) {
		this.commissions = commissions;
	}
	public Set<SkuSettings> getSkus() {
		return skus;
	}
	public void setSkus(Set<SkuSettings> skus) {
		this.skus = skus;
	}
	
	
	
}
