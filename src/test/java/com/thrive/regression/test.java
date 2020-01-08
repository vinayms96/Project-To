package com.thrive.regression;

import com.thrive.utils.excelUtils;

public class test {

	public static void main(String[] args) {
		for(int i=1;i<7;i++) {
			System.out.println(excelUtils.getData("login").get(i));
		}
	}

}
