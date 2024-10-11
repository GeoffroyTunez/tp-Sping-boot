package com.aplose.digihello.dto;

public class DepartmentDto {
	private String departmentCode;
	private String deparmtentName;
	private Integer totalNbInhabitants;
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getDeparmtentName() {
		return deparmtentName;
	}
	public void setDeparmtentName(String deparmtentName) {
		this.deparmtentName = deparmtentName;
	}
	public Integer getTotalNbInhabitants() {
		return totalNbInhabitants;
	}
	public void setTotalNbInhabitants(Integer totalNbInhabitants) {
		this.totalNbInhabitants = totalNbInhabitants;
	}	
}
