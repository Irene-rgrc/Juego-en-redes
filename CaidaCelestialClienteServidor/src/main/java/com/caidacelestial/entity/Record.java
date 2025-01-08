package com.caidacelestial.entity;

import java.io.Serializable;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Record implements Serializable { // VIGILAR
	private long id;
	private String names;
	private long points;

	public String getNames() {
		return names;
	}
	
	public long getId() {
		return id;
	}
	
	public long getPoints() {
		return points;
	}
	
	public void setName(String n) {
		this.names = n;
	}
	
	public void setId(long idN) {
		this.id = idN;
	}
	
	public void setPoints(long newRecord) {
		this.points = newRecord;
	}
}
