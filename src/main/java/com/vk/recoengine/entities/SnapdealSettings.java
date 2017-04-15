package com.vk.recoengine.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonBackReference;

@Entity
public class SnapdealSettings implements Serializable{

	private static final long serialVersionUID = -2831426814711782597L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="setting_id")
	private int setting_id;
	
	private String name;
	private Date fromDate;
	private Date toDate;
	@ManyToOne
	@JoinColumn(name="id")
	@JsonBackReference
	private SnapdealPortal sdPortal;
	
	@OneToMany(mappedBy="settingId")
	private Set<SdLogisticsRateCard> sdLogisticsRateCard;
	
	@OneToMany(mappedBy="settingId")
	private Set<SdPackingCharges> sdPackingCharges;
	
	
	public int getSetting_id() {
		return setting_id;
	}
	public void setSetting_id(int setting_id) {
		this.setting_id = setting_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public SnapdealPortal getSdPortal() {
		return sdPortal;
	}
	public void setSdPortal(SnapdealPortal sdPortal) {
		this.sdPortal = sdPortal;
	}
	public Set<SdLogisticsRateCard> getSdLogisticsRateCard() {
		return sdLogisticsRateCard;
	}
	public void setSdLogisticsRateCard(Set<SdLogisticsRateCard> sdLogisticsRateCard) {
		this.sdLogisticsRateCard = sdLogisticsRateCard;
	}
	public Set<SdPackingCharges> getSdPackingCharges() {
		return sdPackingCharges;
	}
	public void setSdPackingCharges(Set<SdPackingCharges> sdPackingCharges) {
		this.sdPackingCharges = sdPackingCharges;
	}
	
	

}
