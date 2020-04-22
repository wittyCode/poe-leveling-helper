package com.wittycode.poelevelinghelper.presentation;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.wittycode.poelevelinghelper.buildimport.model.PoBContent;
import com.wittycode.poelevelinghelper.buildimport.model.Skills;
import com.wittycode.poelevelinghelper.buildimport.services.domain.PobToXmlConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class PobImportController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PobToXmlConverter pobToXmlConverter;

    @RequestMapping("/import/pob")
    public PoBContent test() throws JsonParseException, JsonMappingException, IOException {
        final String rawPasteBinContent = restTemplate.getForObject("https://pastebin.com/raw/1BvxfCLS/", String.class);

        final String decompressedXmlFromPasteBin = pobToXmlConverter.convertPobRawToXmlString(rawPasteBinContent);

        System.out.println(decompressedXmlFromPasteBin);
        XmlMapper xmlMapper = new XmlMapper();
        
        final PoBContent pobContent = xmlMapper.readValue(decompressedXmlFromPasteBin, PoBContent.class);
        
        return pobContent;
    }

}