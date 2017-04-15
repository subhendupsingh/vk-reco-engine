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
public class SdLogisticsRateCard implements Serializable{

	private static final long serialVersionUID = -4546694285708602628L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private Double fromWieght;
	private Double toWeight;
	private Double differenceUnit;
	private Double sd;
	private Double sdPlus;
	
	@ManyToOne
	@JoinColumn(name="setting_id")
	private SnapdealSettings settingId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Double getFromWieght() {
		return fromWieght;
	}

	public void setFromWieght(Double fromWieght) {
		this.fromWieght = fromWieght;
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

	public Double getSd() {
		return sd;
	}

	public void setSd(Double sd) {
		this.sd = sd;
	}

	public Double getSdPlus() {
		return sdPlus;
	}

	public void setSdPlus(Double sdPlus) {
		this.sdPlus = sdPlus;
	}

	public SnapdealSettings getSettingId() {
		return settingId;
	}

	public void setSettingId(SnapdealSettings settingId) {
		this.settingId = settingId;
	}
	
	

}
