package com.finance.config;

import android.os.Environment;


public class Consts {

	/**
	 * 请求超时
	 */
	public static int TIME_OUT = 30000;
	/**
	 * 3秒后自动进入其他页面
	 */
	public final static int SPLASH_DISPLAY_LENGHT = 3000; //

	public static String IMAGEPATH = Environment.getExternalStorageDirectory() + "/XCI/DTRM/IMAGES/";
	public static String IMAGEURL = "http://113.140.71.254:7010/";

	/**
	 * page
	 */
	public final static int PAGE = 1; //

	/**
	 * 
	 * 
	 * 访问地址
	 */
	public final static String URL = "http://192.168.198.18:8080/FinancialService/";
	
	public final static String URL_IMAGE = "http://192.168.198.18:8080/FinancialService/upload/";

	/**
	 * 分页加载 默认每次加载10�?
	 */
	public final static int PAGE_SIZE = 10;

	public final static int USERTYPE = 1;


	public static class APP {

		public static final String RegisterAction = "servlet/RegisterAction";
		public static final String MoneyAction = "servlet/MoneyAction";
		public static final String NewsAction = "servlet/NewsAction";
		

	}

	public static class actionId {
		public static final int resultFlag = 1;
		public static final int resultCode = 2;
		public static final int resultState = 3;
		public static final int resultSs = 4;
	}
}
