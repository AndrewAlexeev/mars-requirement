package ru.mokb.mars.requirements.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Slf4j
@Service
public class StorageServiceImpl implements StorageService {



	@Override
	public void store(MultipartFile file, String savePath) {

		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				BufferedOutputStream stream =
						new BufferedOutputStream(new FileOutputStream(new File(savePath)));
				stream.write(bytes);
				stream.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
