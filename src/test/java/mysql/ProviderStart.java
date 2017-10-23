package mysql;

import mysql.util.ProviderCode;

public class ProviderStart {

	public static void main(String[] args) {
		try {
			ProviderCode.fileList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
