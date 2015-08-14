package com.intedio.test.util;

import java.util.HashMap;
import java.util.Map;

/**
 * װ���������
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

	/* �������� */
	public void setOperation(OPERATION operation, Map<String, Object> textDataMap) {
		textDataMap.put("operation", operation.name());
	}

	/* ����ID */
	public void setCarId(Map<String, Object> textDataMap) {
		int random = (int) (Math.random() * 9000000);
		textDataMap.put("carId", "3761916123" + random);
	}

	public void setType(Map<String, Object> textDataMap) {
		textDataMap.put("type", "business");
	}

	/* detail�ֶΣ�scoreΪ���Ŷȣ�������90ʱ���Ҵ����˱���������-���Żᱨ�� */
	public void setDetail(int score, Map<String, Object> textDataMap) {
		if (!(score >= 0 && score <= 100))
			score = 0;
		Map<String, Object> maps_detail = new HashMap<String, Object>();
		maps_detail.put("Score", score + "");
		maps_detail.put("CarBodyBox", ""); // ����
		maps_detail.put("CarFaceBox", ""); // ����
		textDataMap.put("detail", maps_detail);
	}

	/* ����Ҫ��basic�ֶ� */
	public void setBasic(Map<String, Object> textDataMap) {
		Map<String, Object> maps_basic = new HashMap<String, Object>();
		// �����屨��
		maps_basic.put("sunNum", 0); // �����屨�����ô�ֵΪ1
		maps_basic.put("dropNum", 0);
		maps_basic.put("tagNum", 0);
		maps_basic.put("paperNum", 0);
		maps_basic.put("sunScore", 95);
		// һ�ƶ೵�������ƣ����˵�
		maps_basic.put("CarPlateNumber", "��G56939");
		maps_basic.put("CarPlateNumber2", "��G56939");
		maps_basic.put("SF_CarColorCode", 10); // ��Χ0-9,���ݿ��Ѿ���10������0-9��Ϊ��ͬ�ĳ�
		maps_basic.put("SF_CarKindCode", 5);
		maps_basic.put("SF_CarFamily", "90");
		// ������Ϣ
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
