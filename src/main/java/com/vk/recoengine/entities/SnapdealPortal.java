package com.vk.recoengine.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.codehaus.jackson.annotate.JsonManagedReference;

@Entity
public class SnapdealPortal{
	
	@Id
	@Column(name="sd_portal_id")
	private int id;
	
	private String username;
	private String password;
	private String url;
	private String usernameField;
	private String passwordField;
	private String loginButtonId;
	private Double paymentCollectionPercent;
	private Double paymentCollectionRate;
	
	
	@OneToMany(mappedBy="sdPortal",fetch=FetchType.EAGER)
	@JsonManagedReference
	private Set<ProductCategories> productCategories;
	
	@OneToMany(mappedBy="sdPortal",fetch=FetchType.EAGER)
	@JsonManagedReference
	private Set<SnapdealSettings> settings;
	
	@OneToMany(mappedBy="sdPortal",fetch=FetchType.EAGER)
	@JsonManagedReference
	private Set<SdRules> sdRules;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsernameField() {
		return usernameField;
	}

	public void setUsernameField(String usernameField) {
		this.usernameField = usernameField;
	}

	public String getPasswordField() {
		return passwordField;
	}

	public void setPasswordField(String passwordField) {
		this.passwordField = passwordField;
	}

	public String getLoginButtonId() {
		return loginButtonId;
	}

	public void setLoginButtonId(String loginButtonId) {
		this.loginButtonId = loginButtonId;
	}

	public Set<ProductCategories> getProductCategories() {
		return productCategories;
	}

	public void setProductCategories(Set<ProductCategories> productCategories) {
		this.productCategories = productCategories;
	}

	public Set<SnapdealSettings> getSettings() {
		return settings;
	}

	public void setSettings(Set<SnapdealSettings> settings) {
		this.settings = settings;
	}

	public Set<SdRules> getSdRules() {
		return sdRules;
	}

	public void setSdRules(Set<SdRules> sdRules) {
		this.sdRules = sdRules;
	}

	public Double getPaymentCollectionPercent() {
		return paymentCollectionPercent;
	}

	public void setPaymentCollectionPercent(Double paymentCollectionPercent) {
		this.paymentCollectionPercent = paymentCollectionPercent;
	}

	public Double getPaymentCollectionRate() {
		return paymentCollectionRate;
	}

	public void setPaymentCollectionRate(Double paymentCollectionRate) {
		this.paymentCollectionRate = paymentCollectionRate;
	}

	
	
}
