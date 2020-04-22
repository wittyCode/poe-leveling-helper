package com.wittycode.poelevelinghelper.buildimport.model;

import javax.xml.bind.annotation.XmlAttribute;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Data;

@JacksonXmlRootElement(localName = "Gem")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Gem {

    @XmlAttribute
    private String skillId;
}