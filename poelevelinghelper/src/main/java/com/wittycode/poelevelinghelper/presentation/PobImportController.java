package com.wittycode.poelevelinghelper.presentation;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.wittycode.poelevelinghelper.buildimport.model.PoBContent;
import com.wittycode.poelevelinghelper.buildimport.services.domain.PobToXmlConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class PobImportController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PobToXmlConverter pobToXmlConverter;

    private final Logger logger = LoggerFactory.getLogger(PobImportController.class);

    @RequestMapping("/import/pob")
    public PoBContent importPoBFromPastebin(
        @RequestParam("pastebinurl") String pasteBinUrl
    ) throws JsonParseException, JsonMappingException, IOException {
        logger.info("Importing from Url {}", pasteBinUrl);
        final String rawPasteBinContent = restTemplate.getForObject(pasteBinUrl, String.class);

        logger.info("Import finished, decompressing");
        final String decompressedXmlFromPasteBin = pobToXmlConverter.convertPobRawToXmlString(rawPasteBinContent);

        logger.info("Decompression finished, mapping XML");
        XmlMapper xmlMapper = new XmlMapper();
        
        final PoBContent pobContent = xmlMapper.readValue(decompressedXmlFromPasteBin, PoBContent.class);
        
        return pobContent;
    }

}