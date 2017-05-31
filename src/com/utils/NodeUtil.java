package com.utils;

import java.util.HashMap;
import java.util.Map;

public class NodeUtil {
	public static Map<String, String> nodeMap = setNodeMap();

	public static String getNodeName(String node) {
		return nodeMap.get(node);
	}

	public static Map<String, String> setNodeMap() {
		nodeMap = new HashMap<String, String>();
		nodeMap.put("020", "����");
		nodeMap.put("0755", "����");
		nodeMap.put("0769", "��ݸ");
		nodeMap.put("0757", "��ɽ");
		nodeMap.put("0754", "��ͷ");
		nodeMap.put("0760", "��ɽ");
		nodeMap.put("0752", "����");
		nodeMap.put("0750", "����");
		nodeMap.put("0663", "����");
		nodeMap.put("0759", "տ��");
		nodeMap.put("0756", "�麣");
		nodeMap.put("0668", "ï��");
		nodeMap.put("0758", "����");
		nodeMap.put("0753", "÷��");
		nodeMap.put("0768", "����");
		nodeMap.put("0751", "�ع�");
		nodeMap.put("0763", "��Զ");
		nodeMap.put("0660", "��β");
		nodeMap.put("0662", "����");
		nodeMap.put("0762", "��Դ");
		nodeMap.put("0766", "�Ƹ�");
		return nodeMap;
	}
}
