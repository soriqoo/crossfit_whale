package com.crossfit.whale.service

import com.crossfit.whale.entity.Wod
import com.crossfit.whale.repository.WodRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.sql.Date
import java.util.UUID

@Service
class WodService(
    private val wodRepository: WodRepository
) {

    @Transactional
    fun create(command: WodCreationCommand) : Wod {
        return wodRepository.save(command.toEntity())
    }

    @Transactional
    fun deleteWod(wodDate: Date, wodType: Char) {
        wodRepository.deleteByWodDateAndWodType(wodDate, wodType)
    }

    fun getWods(wodDate: Date) : List<Wod>? {
        return wodRepository.findAllByWodDate(wodDate)
    }

}

data class WodCreationCommand(
    val wodName: String,
    val wodType: Char,
    val wodCategory: String,
    val wodContent: String?,
    val wodDate: String?
) {
    fun toEntity() = Wod(wodName, wodType, wodCategory, wodContent, Date.valueOf(wodDate))
}