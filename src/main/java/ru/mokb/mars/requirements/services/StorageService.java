package ru.mokb.mars.requirements.services;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
	void store(MultipartFile file, String pathName);
}
