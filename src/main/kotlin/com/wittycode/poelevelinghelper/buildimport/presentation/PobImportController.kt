package com.wittycode.poelevelinghelper.buildimport.presentation

import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.JsonMappingException
import com.wittycode.poelevelinghelper.buildimport.model.PoBContent
import com.wittycode.poelevelinghelper.buildimport.services.PobBuildAccessor
import com.wittycode.poelevelinghelper.leveling.services.LevelingGuideAccessor
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.io.IOException
import javax.ws.rs.core.MediaType


@RestController
class PobImportController(
        private val pobBuildAccessor: PobBuildAccessor,
        private val levelingGuideAccessor: LevelingGuideAccessor
) {

    private val logger = LoggerFactory.getLogger(PobImportController::class.java)

    @RequestMapping("/import/pob", produces = [MediaType.APPLICATION_JSON])
    @Throws(JsonParseException::class, JsonMappingException::class, IOException::class)
    fun importPoBFromPastebin(
            @RequestParam("pastebinurl") pasteBinUrl: String
    ): PoBContent {
        // TODO move to separate class to better encapsulate business logic
        if (!pasteBinUrl.contains("/raw/")) {
            logger.info("provided url was not raw format, replacing path to raw pastebin")
            val rawPasteBinUrl = pasteBinUrl.replace(".com/", ".com/raw/")
            pobBuildAccessor.loadPobBuild(rawPasteBinUrl)
        } else {
            pobBuildAccessor.loadPobBuild(pasteBinUrl);
        }
        levelingGuideAccessor.enrichLevelingStepsWithGems()
        return pobBuildAccessor.poBContent
    }
}