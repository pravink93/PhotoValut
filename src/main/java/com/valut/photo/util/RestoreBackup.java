package com.valut.photo.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/*
 * Author
 * Pravin Saini
 * 
 */
@Component
public class RestoreBackup {

	public void uploadDocumentEncode(MultipartFile file, String basePath) {

		String filePath = basePath + "/" + System.currentTimeMillis();

		// give input to "is" one by one run 15 time's
		InputStream is;
		try {
			is = new BufferedInputStream(file.getInputStream());

			// give another file name for other file
			OutputStream os = new BufferedOutputStream(new FileOutputStream(new File(filePath + ".FILE")));

			int read = 0;
			System.out.println("Restoring....");
			while ((read = is.read()) != -1) {
				os.write(read);
			}
			System.out.println("Done!");

			is.close();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void documentDecode(String sourceFolder, String destFolder) {

		File folder = new File(sourceFolder);
		for (File fileEntry : folder.listFiles()) {
			if (!fileEntry.isDirectory()) {
				InputStream is;
				try {
					is = new BufferedInputStream(new FileInputStream(fileEntry));

					// give another file name for other file
					OutputStream os = new BufferedOutputStream(
							new FileOutputStream(new File(destFolder + File.separator +  fileEntry.getName() + ".jpg")));

					int read = 0;
					System.out.println("Restoring....");
					while ((read = is.read()) != -1) {
						os.write(read);
					}
					System.out.println("Done!");

					is.close();
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
