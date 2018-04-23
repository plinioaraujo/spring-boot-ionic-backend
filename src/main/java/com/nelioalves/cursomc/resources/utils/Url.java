package com.nelioalves.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class Url {

	
	public static List<Integer>DecodeIntList(String s){
		String[] vet = s.split(",");
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < vet.length; i++) {
			list.add(Integer.parseInt(vet[i]));
		}
		return list;
		///Realiza a mesma operação de cima
		//return Arrays.asList(s.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
	}
	
	public static String decodeParam(String s ){
		try {
			return URLDecoder.decode(s,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			
			return "";
		}
	}
}
