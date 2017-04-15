package com.vk.recoengine.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

@Entity
public class Portals{

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	@Lob
	@Column
	private String logo;
	
	@OneToOne
	private SnapdealPortal sdPortal;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public SnapdealPortal getSdPortal() {
		return sdPortal;
	}
	public void setSdPortal(SnapdealPortal sdPortal) {
		this.sdPortal = sdPortal;
	}
	
	
}
