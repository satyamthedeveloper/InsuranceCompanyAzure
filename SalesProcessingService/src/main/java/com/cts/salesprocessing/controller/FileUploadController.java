package com.cts.salesprocessing.controller;

import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class FileUploadController {

	private String filePath;

	@Autowired
	private Environment environment;

	@GetMapping("/")
	public String index() {
		return "upload";
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/upload")
	public String singleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
			return "redirect:uploadStatus";
		}

		filePath = environment.getProperty("file.upload.path");

		BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(filePath).buildClient();
		// Create a unique name for the container
		String containerName = environment.getProperty("container.name");
		// Create the container and return a container client object
		BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);

		try {

			String fileName = file.getOriginalFilename();
			BlobClient blobClient = containerClient.getBlobClient(fileName);
			log.debug("\nUploading to Blob storage as blob:\n\t" + blobClient.getBlobUrl());
			blobClient.upload(file.getInputStream(), file.getSize(), true);

			redirectAttributes.addFlashAttribute("message",
					"You successfully uploaded '" + file.getOriginalFilename() + "'");

		} catch (IOException e) {
			e.printStackTrace();
		}

		return "redirect:/uploadStatus";
	}
}
