package com.thrive.regression;

import com.thrive.logger.LoggerConfig;
import com.thrive.modules.LinkStatusCode;
import com.thrive.utils.ExcelUtils;

public class test1 {

	public static void main(String[] args) {
//		for(int i=1;i<7;i++) {
//			System.out.println(ExcelUtils.getData("login").get(i));
//		}
		System.out.println(LinkStatusCode.connection("https://www.google.com"));
	}

}
