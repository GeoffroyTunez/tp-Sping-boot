package com.aplose.digihello.dto;

public class TownDto {
	private String townName;
	private Integer townNbInhabitants;
	private String departmentCode;
	private String departmentName;
	public String getTownName() {
		return townName;
	}
	public void setTownName(String townName) {
		this.townName = townName;
	}
	public Integer getTownNbInhabitants() {
		return townNbInhabitants;
	}
	public void setTownNbInhabitants(Integer townNbInhabitants) {
		this.townNbInhabitants = townNbInhabitants;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
}
