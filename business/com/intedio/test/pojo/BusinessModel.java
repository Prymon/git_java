package com.intedio.test.pojo;

import java.util.HashMap;
import java.util.Map;

import com.intedio.test.util.OPERATION;

public class BusinessModel {

	/* 一级字段 */
	private String carId = "";
	private String type = "business";
	private String operation = OPERATION.intesv.toString();

	/* detailMap */
	private String Score;

	/* basicMap */
	private String CarPlateType = "2";
	private String CarPlateType2 = "2";
	private String CarPlateNumber = "蒙G56939";
	private String CarPlateNumber2 = "蒙G56939";
	private String SF_CarStyleCode = "01F216A5BF0E411394E1791950327210"; // jeep-牧马人-20082014
	private String SF_CarBrand = "6";
	private String SF_CarKindCode = "1"; // 只有稽查布控用
	private String SF_CarPattern = "1";
	private String SF_CarFamily = "-59";
	private String SF_CarColorCode = "10"; // 只有稽查布控用
	private String sunNum = "0";
	private String tagNum = "0"; // 未使用
	private String dropNum = "0"; // 未使用
	private String paperNum = "0"; // 未使用
	private String sunScore = "100";
	private String TollgateDeviceId = "00004";
	private String SnapShotTime = "1434511790500";

	private static BusinessModel model;

	private BusinessModel(OPERATION operation) {
		setOperation(this.operation.toString());
	}
	
	/**
	 * use demo
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(BusinessModel.getInstance(OPERATION.intesv).setSunNum("2").setScore("100").setCarPlateType("2").getMap());
	}

	/**
	 * 将model转成map
	 * 
	 * @return
	 */
	public static Map<String, Object> getMap() {
		Map<String, Object> textDataMap = new HashMap<String, Object>();
		Map<String, Object> maps_detail = new HashMap<String, Object>();
		Map<String, Object> maps_basic = new HashMap<String, Object>();
		Map<String, Object> maps_business = new HashMap<String, Object>();
		Map<String, Object> RunRes = new HashMap<String, Object>();
		/** detail */
		maps_detail.put("Score", model.getScore());
		maps_detail.put("CarBodyBox", ""); // 弃用
		maps_detail.put("CarFaceBox", ""); // 弃用
		/** basic */
		maps_basic.put("sunNum", model.getSunNum()); // 遮阳板报警则让此值为1
		maps_basic.put("dropNum", model.getDropNum());
		maps_basic.put("tagNum", model.getTagNum());
		maps_basic.put("paperNum", model.getPaperNum());
		maps_basic.put("sunScore", model.getSunScore());
		// 一牌多车，假套牌，过滤等
		maps_basic.put("CarPlateNumber", model.getCarPlateNumber());
		maps_basic.put("CarPlateNumber2", model.getCarPlateNumber2());
		maps_basic.put("SF_CarColorCode", model.getSF_CarColorCode()); // 范围0-9,数据库已经有10这条，0-9即为不同的车
		maps_basic.put("SF_CarKindCode", model.getSF_CarKindCode());
		maps_basic.put("SF_CarFamily", model.getSF_CarFamily());
		// 其他信息
		maps_basic.put("TollgateDeviceId", model.getTollgateDeviceId());
		maps_basic.put("CarPlateType", model.getCarPlateType());
		maps_basic.put("CarPlateType2", model.getCarPlateType2());
		maps_basic.put("SnapShotTime", model.getSnapShotTime());
		maps_basic.put("SF_CarBrand", model.getSF_CarBrand());
		maps_basic.put("SF_CarPattern", model.getSF_CarPattern());
		maps_basic.put("SF_CarStyleCode", model.getSF_CarStyleCode());
		/** business*/
		RunRes.put("plate", "9");
		RunRes.put("detection", "9");
		RunRes.put("color", "9");
		RunRes.put("kind", "9");
		RunRes.put("style", "9");
		maps_business.put("RunRes", RunRes);
		maps_business.put("AutoVerifyRes", "");
		/** necessary param*/
		textDataMap.put("operation", model.getOperation());
//		int random = (int) (Math.random() * 9000000);
//		textDataMap.put("carId", "3761916123" + random);
		textDataMap.put("carId", model.getCarId());
		textDataMap.put("type", model.getType());
		
		textDataMap.put("business", maps_business);
		textDataMap.put("basic", maps_basic);
		textDataMap.put("detail", maps_detail);
		return textDataMap;
	}

	/**
	 * 获取实例
	 * 
	 * @param operation
	 * @return
	 */
	public static BusinessModel getInstance(OPERATION operation) {
		model = new BusinessModel(operation);
		return model;
	}

	public String getCarId() {
		return carId;
	}

	public BusinessModel setCarId(String carId) {
		this.carId = carId;
		return this;
	}

	public String getType() {
		return type;
	}

	public BusinessModel setType(String type) {
		this.type = type;
		return this;
	}

	public String getOperation() {
		return operation;
	}

	public BusinessModel setOperation(String operation) {
		this.operation = operation;
		return this;
	}

	public String getScore() {
		return Score;
	}

	public BusinessModel setScore(String score) {
		Score = score;
		return this;
	}

	public String getCarPlateType() {
		return CarPlateType;
	}

	public BusinessModel setCarPlateType(String carPlateType) {
		CarPlateType = carPlateType;
		return this;
	}

	public String getCarPlateType2() {
		return CarPlateType2;
	}

	public BusinessModel setCarPlateType2(String carPlateType2) {
		CarPlateType2 = carPlateType2;
		return this;
	}

	public String getCarPlateNumber() {
		return CarPlateNumber;
	}

	public BusinessModel setCarPlateNumber(String carPlateNumber) {
		CarPlateNumber = carPlateNumber;
		return this;
	}

	public String getCarPlateNumber2() {
		return CarPlateNumber2;
	}

	public BusinessModel setCarPlateNumber2(String carPlateNumber2) {
		CarPlateNumber2 = carPlateNumber2;
		return this;
	}

	public String getSF_CarStyleCode() {
		return SF_CarStyleCode;
	}

	public BusinessModel setSF_CarStyleCode(String sF_CarStyleCode) {
		SF_CarStyleCode = sF_CarStyleCode;
		return this;
	}

	public String getSF_CarBrand() {
		return SF_CarBrand;
	}

	public BusinessModel setSF_CarBrand(String sF_CarBrand) {
		SF_CarBrand = sF_CarBrand;
		return this;
	}

	public String getSF_CarKindCode() {
		return SF_CarKindCode;
	}

	public BusinessModel setSF_CarKindCode(String sF_CarKindCode) {
		SF_CarKindCode = sF_CarKindCode;
		return this;
	}

	public String getSF_CarPattern() {
		return SF_CarPattern;
	}

	public BusinessModel setSF_CarPattern(String sF_CarPattern) {
		SF_CarPattern = sF_CarPattern;
		return this;
	}

	public String getSF_CarFamily() {
		return SF_CarFamily;
	}

	public BusinessModel setSF_CarFamily(String sF_CarFamily) {
		SF_CarFamily = sF_CarFamily;
		return this;
	}

	public String getSF_CarColorCode() {
		return SF_CarColorCode;
	}

	public BusinessModel setSF_CarColorCode(String sF_CarColorCode) {
		SF_CarColorCode = sF_CarColorCode;
		return this;
	}

	public String getSunNum() {
		return sunNum;
	}

	public BusinessModel setSunNum(String sunNum) {
		this.sunNum = sunNum;
		return this;
	}

	public String getTagNum() {
		return tagNum;
	}

	public BusinessModel setTagNum(String tagNum) {
		this.tagNum = tagNum;
		return this;
	}

	public String getDropNum() {
		return dropNum;
	}

	public BusinessModel setDropNum(String dropNum) {
		this.dropNum = dropNum;
		return this;
	}

	public String getPaperNum() {
		return paperNum;
	}

	public BusinessModel setPaperNum(String paperNum) {
		this.paperNum = paperNum;
		return this;
	}

	public String getSunScore() {
		return sunScore;
	}

	public BusinessModel setSunScore(String sunScore) {
		this.sunScore = sunScore;
		return this;
	}

	public String getTollgateDeviceId() {
		return TollgateDeviceId;
	}

	public BusinessModel setTollgateDeviceId(String tollgateDeviceId) {
		TollgateDeviceId = tollgateDeviceId;
		return this;
	}

	public String getSnapShotTime() {
		return SnapShotTime;
	}

	public BusinessModel setSnapShotTime(String snapShotTime) {
		SnapShotTime = snapShotTime;
		return this;
	}

	/* businessMap */
	// abandonded

}
