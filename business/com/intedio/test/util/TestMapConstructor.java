package com.intedio.test.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 装配测试数据
 * 
 * @author Deamon
 */
public class TestMapConstructor {

	public Map<String, Object> getMap() {
		Map<String, Object> textDataMap = new HashMap<String, Object>();
		this.setOperation(OPERATION.intesv, textDataMap);
		this.setBasic(textDataMap);
		this.setBusiness(textDataMap);
		this.setCarId(textDataMap);
		this.setDetail(95, textDataMap);
		this.setType(textDataMap);
		return textDataMap;
	}

	/* 操作类型 */
	public void setOperation(OPERATION operation, Map<String, Object> textDataMap) {
		textDataMap.put("operation", operation.name());
	}

	/* 汽车ID */
	public void setCarId(Map<String, Object> textDataMap) {
		int random = (int) (Math.random() * 9000000);
		textDataMap.put("carId", "3761916123" + random);
	}

	public void setType(Map<String, Object> textDataMap) {
		textDataMap.put("type", "business");
	}

	/* detail字段，score为可信度，【大于90时，且触发了报警条件】-》才会报警 */
	public void setDetail(int score, Map<String, Object> textDataMap) {
		if (!(score >= 0 && score <= 100))
			score = 0;
		Map<String, Object> maps_detail = new HashMap<String, Object>();
		maps_detail.put("Score", score + "");
		maps_detail.put("CarBodyBox", ""); // 弃用
		maps_detail.put("CarFaceBox", ""); // 弃用
		textDataMap.put("detail", maps_detail);
	}

	/* 最重要的basic字段 */
	public void setBasic(Map<String, Object> textDataMap) {
		Map<String, Object> maps_basic = new HashMap<String, Object>();
		// 遮阳板报警
		maps_basic.put("sunNum", 0); // 遮阳板报警则让此值为1
		maps_basic.put("dropNum", 0);
		maps_basic.put("tagNum", 0);
		maps_basic.put("paperNum", 0);
		maps_basic.put("sunScore", 95);
		// 一牌多车，假套牌，过滤等
		maps_basic.put("CarPlateNumber", "蒙G56939");
		maps_basic.put("CarPlateNumber2", "蒙G56939");
		maps_basic.put("SF_CarColorCode", 10); // 范围0-9,数据库已经有10这条，0-9即为不同的车
		maps_basic.put("SF_CarKindCode", 5);
		maps_basic.put("SF_CarFamily", "90");
		// 其他信息
		maps_basic.put("TollgateDeviceId", "00004");
		maps_basic.put("CarPlateType", 2);
		maps_basic.put("CarPlateType2", 2);
		maps_basic.put("SnapShotTime", System.currentTimeMillis());
		maps_basic.put("SF_CarBrand", "5");
		maps_basic.put("SF_CarPattern", "1");
		maps_basic.put("SF_CarStyleCode", "LS5A3BBDA5974a86B8D1575BFF82ECCE");
		textDataMap.put("basic", maps_basic);
	}

	public void setBusiness(Map<String, Object> textDataMap) {
		Map<String, Object> maps_business = new HashMap<String, Object>();
		Map<String, Object> RunRes = new HashMap<String, Object>();
		RunRes.put("plate", "9");
		RunRes.put("detection", "9");
		RunRes.put("color", "9");
		RunRes.put("kind", "9");
		RunRes.put("style", "9");
		maps_business.put("RunRes", RunRes);
		maps_business.put("AutoVerifyRes", "");
		textDataMap.put("business", maps_business);
	}
}
