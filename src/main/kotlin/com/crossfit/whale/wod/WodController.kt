package com.crossfit.whale.wod

import com.crossfit.whale.model.CommonResponse
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
import java.util.UUID

@RestController
@RequestMapping("/wod")
class WodController (
    private val wodService: WodService
){

    private val log = LoggerFactory.getLogger(WodController::class.java)

    // WOD 등록
    @PostMapping("/wods")
    fun createWod(@RequestBody @Valid command: WodCreationCommand): WodDto =
        WodDto(wodService.create(command))


    // 오늘의 WOD 가져오기. WOD 등록 시점에 따라 None, Crossfit, Dietfit, C/Dfit
    @GetMapping("/today")
    fun getTodayWodList(): List<Wod>? =
        wodService.getWods(Date.valueOf(LocalDate.now()))


    // 특정 날짜 WOD 가져오기
    @GetMapping("/{wodDate}")
    fun getWodList(@PathVariable wodDate: LocalDate): List<Wod>? =
        wodService.getWods(Date.valueOf(wodDate))



    /**
        - WOD 삭제
        P : wodDate, wodType

        1. 삭제 권한 있는 사용자인지 확인?
        2. 삭제 가능한 WOD인지 확인? - 상태 추가 필요?
        3. DB에서 WOD 삭제

        R : 성공/실패 Code
     **/
    @DeleteMapping("/{wodDate}/{wodType}")
    fun deleteWod(@PathVariable wodDate: LocalDate, @PathVariable wodType: Char): CommonResponse {
        wodService.deleteWod(Date.valueOf(wodDate), wodType)
        return CommonResponse(200, "OK")
    }

}

//@Serializable
data class WodDto(
    val wodName: String,
    val wodType: Char,
    val wodCategory: String,
    val wodContent: String?,
    val wodDate: String?
) {
    constructor(entity: Wod) : this(entity.wodName, entity.wodType, entity.wodCategory, entity.wodContent, entity.wodDate.toString())
}