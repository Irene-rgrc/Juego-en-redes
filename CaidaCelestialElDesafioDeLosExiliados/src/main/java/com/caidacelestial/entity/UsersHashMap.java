package com.caidacelestial.entity;

import java.util.HashMap;

public class UsersHashMap {
	
	public static void main(String[] args) {
		HashMap<String, String> hash = new HashMap<>();
		
		hash.put(null, null);
		hash.remove("Pepe");
		hash.containsKey("Pepe");
		
		for(String indice : hash.keySet()) {
			System.out.println(hash.get(indice));
		}
	}

}
