package com.vk.recoengine.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class SdPackingCharges implements Serializable{

	
	private static final long serialVersionUID = 2150035738015587590L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private Double fromWeight;
	private Double toWeight;
	private Double differenceUnit;
	private Double rate;
	@ManyToOne
	@JoinColumn(name="setting_id")
	private SnapdealSettings settingId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Double getFromWeight() {
		return fromWeight;
	}
	public void setFromWeight(Double fromWeight) {
		this.fromWeight = fromWeight;
	}
	public Double getToWeight() {
		return toWeight;
	}
	public void setToWeight(Double toWeight) {
		this.toWeight = toWeight;
	}
	public Double getDifferenceUnit() {
		return differenceUnit;
	}
	public void setDifferenceUnit(Double differenceUnit) {
		this.differenceUnit = differenceUnit;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public SnapdealSettings getSettingId() {
		return settingId;
	}
	public void setSettingId(SnapdealSettings settingId) {
		this.settingId = settingId;
	}
	
	
	
}
