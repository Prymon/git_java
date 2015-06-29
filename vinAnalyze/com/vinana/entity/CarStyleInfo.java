package com.vinana.entity;

public class CarStyleInfo {
	private int countVin;	//已有VIN的个数
	private String SF_CarStyleCode;		
	private String SF_CarStyleName;
	private String CarStyleType;
	private String CarKind;
	private String SF_CarFamilyName;
	private String SF_CarBrandName;

	public int getCountVin() {
		return countVin;
	}

	public void setCountVin(int countVin) {
		this.countVin = countVin;
	}

	public String getSF_CarStyleCode() {
		return SF_CarStyleCode;
	}

	public void setSF_CarStyleCode(String sF_CarStyleCode) {
		SF_CarStyleCode = sF_CarStyleCode;
	}

	public String getSF_CarStyleName() {
		return SF_CarStyleName;
	}

	public void setSF_CarStyleName(String sF_CarStyleName) {
		SF_CarStyleName = sF_CarStyleName;
	}

	public String getCarStyleType() {
		return CarStyleType;
	}

	public void setCarStyleType(String carStyleType) {
		CarStyleType = carStyleType;
	}

	public String getCarKind() {
		return CarKind;
	}

	public void setCarKind(String carKind) {
		CarKind = carKind;
	}

	public String getSF_CarFamilyName() {
		return SF_CarFamilyName;
	}

	public void setSF_CarFamilyName(String sF_CarFamilyName) {
		SF_CarFamilyName = sF_CarFamilyName;
	}

	public String getSF_CarBrandName() {
		return SF_CarBrandName;
	}

	public void setSF_CarBrandName(String sF_CarBrandName) {
		SF_CarBrandName = sF_CarBrandName;
	}

}
