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
 
			// 输入输出文件
			File srcFile = gf.getFilePath(sFile);
			File destFile = gf.getFilePath(dFile);
 
			// 创建文件输入流对象
			in = new FileInputStream(srcFile);
			// 创建文件输出流对象(zip压缩文件)
			out = new FileOutputStream(destFile);
			// 创建ZIP数据输出流对象
			zipOut = new ZipOutputStream(out);
			// 得到文件名称
			String fileName = sFile.substring(sFile.lastIndexOf('/') + 1, sFile.length());
 
			// 创建指向压缩原始文件的入口
			ZipEntry entry = new ZipEntry(fileName);
			zipOut.putNextEntry(entry);
 
			// 向压缩文件中输出数据
			int number = 0;
			byte[] buffer = new byte[1024];
			while ((number = in.read(buffer)) != -1) {
				zipOut.write(buffer, 0, number);
			}
			long end = System.currentTimeMillis();
			System.out.println("压缩耗时：" + (end - start));
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
