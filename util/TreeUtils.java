package com.eb.dianlianbao_server.util;

import java.util.ArrayList;
import java.util.List;

import com.eb.dianlianbao_server.pojo.Node;

public class TreeUtils {

	/**
	 * 递归处理 数据库树结构数据->树形json
	 *
	 * @param nodeId
	 * @param nodes
	 * @return List<Map<String, Object>>
	 */
	public static List<Node> getNodeJson(String nodeId, List<Node> nodes) {
		// 当前层级当前点下的所有子节点
		List<Node> childList = getChildNodes(nodeId, nodes);
		List<Node> childTree = new ArrayList<>();
		for (Node node : childList) {
			List<Node> childs = getNodeJson(node.getId(), nodes); // 递归调用该方法
			node.setChildren(childs);
			childTree.add(node);
		}
		return childTree;
	}

	/**
	 * 获取当前节点的所有子节点
	 *
	 * @param nodeId
	 * @param nodes
	 * @return List<Node>
	 */
	public static List<Node> getChildNodes(String nodeId, List<Node> nodes) {
		List<Node> list = new ArrayList<>();
		for (Node node : nodes) {
			if (node.getParentId().equals(nodeId)) {
				list.add(node);
			}
		}
		return list;
	}


}
