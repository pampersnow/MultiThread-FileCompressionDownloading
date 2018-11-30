package com.multithread;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * @author JYB
 * @date  2018.11
 */
public class UpLoadFile implements Runnable {
	public String sFile;
	public String dFile;
 
	public UpLoadFile(String srcFile, String descFile) {
		this.sFile = srcFile;
		this.dFile = descFile;
	}

	public void run() {
		// TODO Auto-generated method stub
		UpLoadFileFunc();
	}
	public synchronized void UpLoadFileFunc() {
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedWriter bw = null;
		try {
			GetFile gf = GetFile.getFile();
			// ��������ļ�
			File srcFile = gf.getFilePath(sFile);
			File destFile = gf.getFilePath(dFile);
			long start = System.currentTimeMillis();
			// �ֽ��ַ�������
			fis = new FileInputStream(srcFile);
			isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			// �ֽ��ַ������
			FileOutputStream fos = new FileOutputStream(destFile);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			bw = new BufferedWriter(osw);
			String s = br.readLine();
			while (s != null) {
				bw.write(s);
				bw.newLine();
				s = br.readLine();
			}
			long end = System.currentTimeMillis();
			System.out.println("���ݺ�ʱ��" + (end - start));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
				isr.close();
				if (bw != null) {
					bw.flush();
					bw.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
	}
}
