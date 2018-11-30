package com.multithread;

import java.io.File;

/**
 * @author JYB
 * @date  2018.11
 *
 */
public class GetFile {
	public static GetFile gf = null;

	private GetFile() {
	}

	public static GetFile getFile() {
		if (gf == null) {
			gf = new GetFile();
		}
		return gf;
	}

	public File getFilePath(String filePath) {
		File f = new File(filePath);
		return f;
	}
}
