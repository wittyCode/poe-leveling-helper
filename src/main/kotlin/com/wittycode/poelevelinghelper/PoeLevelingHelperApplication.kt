package com.wittycode.poelevelinghelper

import com.wittycode.poelevelinghelper.gemimport.services.GemImporter
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate

@SpringBootApplication
class PoeLevelingHelperApplication(private val gemImporter: GemImporter): CommandLineRunner {

	companion object {
		@JvmStatic
		fun main(args: Array<String>) {
			val app = SpringApplication(PoeLevelingHelperApplication::class.java)
			app.run(*args)
		}
	}

	@Bean
	fun restTemplate(): RestTemplate? {
		return RestTemplate()
	}

	@Throws(Exception::class)
	override fun run(vararg args: String) {
		gemImporter.importGemInfo()
	}
}