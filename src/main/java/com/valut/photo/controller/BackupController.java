package com.valut.photo.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.valut.photo.util.RestoreBackup;

/**
 * 
 * @author praveen.saini
 *
 */
@RestController
@RequestMapping(path = "/v1")
public class BackupController {

	@Autowired
	public RestoreBackup restoreBackup;

	@PostMapping("/upload")
	public String fileUploadEncode(@RequestParam("files") MultipartFile[] files,
			@RequestParam("uploadPath") String uploadPath) {

		Arrays.asList(files).forEach(file -> {
			System.out.println(file.getName());
			System.out.println(file.getSize());

			restoreBackup.uploadDocumentEncode(file, uploadPath);
		});

		return "upload success";
	}

	@GetMapping("/decode")
	public String fileDecode(@RequestParam("sourceFolder") String sourceFolder,
			@RequestParam("destFolder") String destFolder) {
		restoreBackup.documentDecode(sourceFolder, destFolder);
		return "Decode success";
	}
}
