package com.doctor.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")
public class Patient {

	private String id;

	private String name;

	private String surname;

	private String personal_number;

	private String blood;

	private String oxygen;

	private String mainDisease;

	private String specifiedDisease;

	private String lastLocated;

	private String timeLastLocated;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPersonal_number() {
		return personal_number;
	}

	public void setPersonal_number(String personal_number) {
		this.personal_number = personal_number;
	}

	public String getBlood() {
		return blood;
	}

	public void setBlood(String blood) {
		this.blood = blood;
	}

	public String getOxygen() {
		return oxygen;
	}

	public void setOxygen(String oxygen) {
		this.oxygen = oxygen;
	}

	public String getMainDisease() {
		return mainDisease;
	}

	public void setMainDisease(String mainDisease) {
		this.mainDisease = mainDisease;
	}

	public String getSpecifiedDisease() {
		return specifiedDisease;
	}

	public void setSpecifiedDisease(String specifiedDisease) {
		this.specifiedDisease = specifiedDisease;
	}

	public String getLastLocated() {
		return lastLocated;
	}

	public void setLastLocated(String lastLocated) {
		this.lastLocated = lastLocated;
	}

	public String getTimeLastLocated() {
		return timeLastLocated;
	}

	public void setTimeLastLocated(String timeLastLocated) {
		this.timeLastLocated = timeLastLocated;
	}
}
