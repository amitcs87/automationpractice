/**
 * This class has utility functions
 * 
 * @since Oct 25, 2020
 */
package com.automationpractice.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Utilities {

	/**
	 * This Function will sort map in ascending order by value
	 * 
	 * @param hm
	 *            - HashMap - map to be sort
	 * 
	 * @return sorted_map - Linkedhashmap - return sorted map
	 */
	public static HashMap<String, Double> sort_map_by_value(HashMap<String, Double> hm) {
		List<Map.Entry<String, Double>> list = new LinkedList<Map.Entry<String, Double>>(hm.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
			public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});
		HashMap<String, Double> sorted_map = new LinkedHashMap<String, Double>();
		for (Map.Entry<String, Double> entry : list) {
			sorted_map.put(entry.getKey(), entry.getValue());
		}
		return sorted_map;
	}
}
