package com.wittycode.poelevelinghelper.gemimport.presentation

import com.wittycode.poelevelinghelper.gemimport.model.GemInfo
import com.wittycode.poelevelinghelper.gemimport.services.GemInfoAccessor
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.ws.rs.core.MediaType

@RestController
class GemInfoController(private val gemInfoAccessor: GemInfoAccessor) {

    private val logger = LoggerFactory.getLogger(GemInfoController::class.java)

    @RequestMapping("/gems", produces = [MediaType.APPLICATION_JSON])
    fun fetchGemInfos(): List<GemInfo> {
        logger.debug("Returning gem info list.")
        return gemInfoAccessor.getAll()
    }

    @RequestMapping("/gems/single", produces = [MediaType.APPLICATION_JSON])
    fun fetchGemInfoByGemName(
            @RequestParam("gemName") gemName: String
    ): GemInfo {
        logger.debug("Returning gem info list.")
        return gemInfoAccessor.getGemInfoByGemName(gemName)
    }

    @RequestMapping("/gems/class", produces = [MediaType.APPLICATION_JSON])
    fun fetchGemInfoByClassName(
            @RequestParam("className") className: String
    ): Set<GemInfo> {
        logger.debug("Returning gem info list.")
        return gemInfoAccessor.getGemInfoByClassName(className)
    }
}