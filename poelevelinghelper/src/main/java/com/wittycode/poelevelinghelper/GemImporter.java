package com.wittycode.poelevelinghelper;


import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;


@Service
public class GemImporter {

	@Autowired
	private ResourceLoader resourceLoader;

    private final Logger logger = LoggerFactory.getLogger(PoeLevelingApplication.class);

    public void importGemInfo() {
        
		Resource resource = resourceLoader.getResource("classpath:gems.csv");
		try {
			InputStream inputStream = resource.getInputStream();
			byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
			String data = new String(bdata, StandardCharsets.UTF_8);
			logger.info(data);
		} catch (IOException e) {
			logger.error("IOException", e);
		}
    }
}