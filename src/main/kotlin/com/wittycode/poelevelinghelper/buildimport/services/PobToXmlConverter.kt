package com.wittycode.poelevelinghelper.buildimport.services

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.util.*
import java.util.zip.DataFormatException
import java.util.zip.Inflater

@Service
class PobToXmlConverter {

    private val logger = LoggerFactory.getLogger(PobToXmlConverter::class.java)

    fun convertPobRawToXmlString(pobRaw: String?): String? {
        val decodedPastbin = Base64.getUrlDecoder().decode(pobRaw)
        val inflater = Inflater()
        inflater.setInput(decodedPastbin)
        val buffer = ByteArray(1024)
        var decompressedXmlFromPasteBin: String? = ""
        try {
            ByteArrayOutputStream(decodedPastbin.size).use { outputStream ->
                while (!inflater.finished()) {
                    val count = inflater.inflate(buffer)
                    outputStream.write(buffer, 0, count)
                }
                decompressedXmlFromPasteBin = String(outputStream.toByteArray())
            }
        } catch (exception: IOException) {
            logger.error(exception.message)
        } catch (exception: DataFormatException) {
            logger.error(exception.message)
        }
        return decompressedXmlFromPasteBin
    }
}