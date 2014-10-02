package com.vguang;

import java.util.Iterator;
import java.util.Properties;

import swing.VguangSample;

public class VguangApi {
	public static final int DEVICE_VALID = 1;  //设备有效
	public static final int DEVICE_INVALID = 2; //设备无效
	static {
		
		Properties ps = System.getProperties();
		
		for(Object key: ps.keySet()) {
			System.out.println("K: "+key+"\n  V: "+ps.get(key));
		}
		
		
		String arch = System.getProperty("os.arch"); 
		String os= System.getProperty("os.name").toUpperCase(); 
		
		System.out.println("Architecture: " + arch);
		System.out.println("OS: "+os);
		
		if(arch.indexOf("64") != -1) {
			// x64
			System.loadLibrary("x64/win7/dll_vguang_java");
		}else if(os.indexOf("XP") != -1) {
			// x86 WinXP
			System.loadLibrary("x32/xp/dll_vguang_java");
		}else {
			// x86 Other
			System.loadLibrary("x32/win7/dll_vguang_java");
		}
	}

	// 设置QR引擎状态，true时qr引擎开启；false时qr引擎关闭
	public native static void setQRable(boolean bqr);
	// 设置DM引擎状态，true时dm引擎开启；false时dm引擎关闭
	public native static void setDMable(boolean bdm);
	// 设置一维码引擎状态，true时一维码引擎开启；false时一维码引擎关闭
	public native static void setBarcode(boolean bbarcode);
	// 设置自动休眠状态，true时自动休眠开启；false时自动休眠关闭
	public native static void setAI(boolean bai);
	// 设置自动休眠灵敏度，1~64，缺省10
	public native static void setAISensitivity(int aiLimit);
	// 设置自动休眠响应时间，单位秒
	public native static void setAIResponseTime(int responseTime);
	// 设置解码间隔时间，单位毫秒
	public native static void setDeodeIntervalTime(int intervalTime);
	// 设置扬声器状态，true时扬声器(缺省声音)开启；false时扬声器(缺省声音)关闭
	public native static void setBeepable(boolean bbeep);
	// 扬声器声音，times取值为1,2,3
	public native static void beep(int times);
	// 开灯
	public native static void lightOn();
	// 关灯
	public native static void lightOff();
	// 打开设备
	public native static void openDevice();
	// 关闭设备
	public native static void closeDevice();
	
	/**
	 * 扫码回调函数）   需要根据实现情况修改实现
	 * @param decodeStrBytes 解码字符串对应的byte数组（缺省是GBK编码）
	 */
	public static void decodeCallBack(byte[] decodeStrBytes) {
		String str = new String(decodeStrBytes);
		VguangSample.vguangSample.setResultString(str);
		return;
	}
	/**
	 * 设备状态变化回调函数            需要根据实现情况修改
	 * @param status 设备状态,DEVICE_VALID(1)-设备有效,DEVICE_INVALID(2)-设备无效 
	 */
	public static void deviceStatusCallBack(int status) {
		VguangSample.vguangSample.setDeviceStatus(status);
		return;
	}
}
