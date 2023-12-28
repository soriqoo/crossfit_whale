package com.crossfit.whale.wod

import com.crossfit.whale.entity.Wod
import com.crossfit.whale.service.WodCreationCommand
import com.crossfit.whale.service.WodService
import jakarta.validation.Valid
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
import java.sql.Date
import java.time.LocalDate

@RestController
@RequestMapping("/wod")
class WodController (
    private val wodService: WodService
){

    private val log = LoggerFactory.getLogger(WodController::class.java)

    @PostMapping("/wods")
    fun createWod(@RequestBody @Valid command: WodCreationCommand): WodDto = WodDto(wodService.create(command))

    @GetMapping("/today")
    fun getTodayWodList() {
        wodService.getTodayWods(Date.valueOf(LocalDate.now()))
    }

    @PostMapping("/test")
    fun test(@RequestBody body: String): String? {
        log.info("==================== just test ======================")
        log.info(body)
        val wodDto = Json.decodeFromString<WodDto>(body)

        log.info("==================== Json String to WodDto Object ======================")
        log.info(wodDto.toString())

        return "OK"
    }

    @PostMapping("/test2")
    fun test2(@RequestBody body: WodDto): String? {
        log.info("==================== just test 2 ======================")
        log.info(body.toString())
        log.info("-------------------------------------------------------")
        log.info("wodName : ${body.wodName}")
        log.info("wodType : ${body.wodType}")
        log.info("wodCategory : ${body.wodCategory}")
        log.info("wodContent : ${body.wodContent}")
        log.info("wodDate : ${body.wodDate}")


        return "OK"
    }
}

@Serializable
data class WodDto(
//    val id: UUID,
    val wodName: String,
    val wodType: Char,
    val wodCategory: String,
    val wodContent: String?,
    val wodDate: String?
) {
//    constructor(entity: Wod) : this(entity.id, entity.wodName, entity.wodType, entity.wodCategory, entity.wodContent, entity.wodDate)
    constructor(entity: Wod) : this(entity.wodName, entity.wodType, entity.wodCategory, entity.wodContent, entity.wodDate.toString())
}