package com.multithread;

import java.io.IOException;

/**
 * @author JYB
 * @date  2018.11
 */
public class MultiThread {	
		
		public static String srcFilePath = "E://testMultiThread//info.xml";
		public static String destFilePath = "E://testMultiThread//test//";
		public static String zipFilePath = "E://testMultiThread//test//";
		
		public static void main(String[] args) throws IOException{
			destFilePath = destFilePath + "test_" + System.currentTimeMillis() + ".xml";
			zipFilePath = zipFilePath + "test_" + System.currentTimeMillis() + ".zip";
			UpLoadFile uf = new UpLoadFile(srcFilePath,destFilePath);
			ZipFile zf = new ZipFile(srcFilePath,zipFilePath);
			Thread th1 = new Thread(uf);
			Thread th2 = new Thread(zf);
			th1.start();
			th2.start();
		}
		
	
}
