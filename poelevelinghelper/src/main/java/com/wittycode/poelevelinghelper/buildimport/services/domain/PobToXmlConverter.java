package com.wittycode.poelevelinghelper.buildimport.services.domain;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

import org.springframework.stereotype.Service;

/**
 * converts given content from PoB "raw" pastebin to XmlTree represented as String
 */
@Service
public class PobToXmlConverter {

    public String convertPobRawToXmlString(final String pobRaw) {
        final byte[] decodedPastbin = Base64.getUrlDecoder().decode(pobRaw);

        final Inflater inflater = new Inflater();
        inflater.setInput(decodedPastbin);

        byte[] buffer = new byte[1024];

        String decompressedXmlFromPasteBin = "";

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream(decodedPastbin.length)) { 
            while (!inflater.finished()) {  
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            decompressedXmlFromPasteBin = new String(outputStream.toByteArray());
        } catch (final IOException | DataFormatException exception) {
            System.err.println(exception.getMessage());
        }

        return decompressedXmlFromPasteBin;
    }
}