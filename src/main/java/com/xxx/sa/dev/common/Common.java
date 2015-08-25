package com.xxx.sa.dev.common;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Common {

	private final static String symbol="$$";
	
	public static String GetSymbol(){
		return symbol;
	}
	
	public static String GetHostName(){
		try {
			InetAddress addr = InetAddress.getLocalHost();
			return addr.getHostName().toString();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}
	public static String GetMemSize(){
		int kb = 1024;
		long totalMemory = Runtime.getRuntime().totalMemory() / kb;
		// 剩余内存
		long freeMemory = Runtime.getRuntime().freeMemory() / kb;
		// 最大可使用内存
		long maxMemory = Runtime.getRuntime().maxMemory() / kb;
		return "totalMemory="+totalMemory+"|freeMemory="+freeMemory+"|maxMemory="+maxMemory;
	}

}
