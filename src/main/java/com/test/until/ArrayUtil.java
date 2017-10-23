package com.test.until;

public class ArrayUtil {

	// 使用循环判断
	public static boolean useLoop(String[] arr, String targetValue) {
		if(null ==arr||targetValue ==null){
			return false;
		}
		for (String s : arr) {
			if (s.equals(targetValue))
				return true;
		}
		return false;
	}
}
