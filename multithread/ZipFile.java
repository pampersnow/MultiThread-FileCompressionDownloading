package com.multithread;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author JYB
 * @date  2018.11
 */
public class ZipFile implements Runnable{
	public String sFile;
	public String dFile;
 
	public ZipFile(String srcFile, String descFile) {
		this.sFile = srcFile;
		this.dFile = descFile;
	}
	public void run() {
		// TODO Auto-generated method stub
		ZipFileFunc();
	}
	public synchronized void ZipFileFunc() {
		ZipOutputStream zipOut = null;
		FileOutputStream out = null;
		FileInputStream in = null;
		try {
			long start = System.currentTimeMillis();
			GetFile gf = GetFile.getFile();
 
			// ��������ļ�
			File srcFile = gf.getFilePath(sFile);
			File destFile = gf.getFilePath(dFile);
 
			// �����ļ�����������
			in = new FileInputStream(srcFile);
			// �����ļ����������(zipѹ���ļ�)
			out = new FileOutputStream(destFile);
			// ����ZIP�������������
			zipOut = new ZipOutputStream(out);
			// �õ��ļ�����
			String fileName = sFile.substring(sFile.lastIndexOf('/') + 1, sFile.length());
 
			// ����ָ��ѹ��ԭʼ�ļ������
			ZipEntry entry = new ZipEntry(fileName);
			zipOut.putNextEntry(entry);
 
			// ��ѹ���ļ����������
			int number = 0;
			byte[] buffer = new byte[1024];
			while ((number = in.read(buffer)) != -1) {
				zipOut.write(buffer, 0, number);
			}
			long end = System.currentTimeMillis();
			System.out.println("ѹ����ʱ��" + (end - start));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				zipOut.close();
				out.close();
				in.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
}
