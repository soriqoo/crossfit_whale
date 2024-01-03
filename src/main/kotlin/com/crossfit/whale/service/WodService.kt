package com.crossfit.whale.service

import com.crossfit.whale.entity.Wod
import com.crossfit.whale.repository.WodRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.sql.Date

@Service
class WodService(
    private val wodRepository: WodRepository
) {

    @Transactional
    fun createWod(command: WodCreationCommand) : Wod {
        return wodRepository.save(command.toEntity())
    }

    @Transactional
    fun updateWod(wodDate: Date, wodType: Char, command: WodCreationCommand) : Wod {
        val findWod = wodRepository.findByWodDateAndWodType(wodDate, wodType)
        findWod.updateWod(command)

        return findWod
    }

    @Transactional
    fun deleteWod(wodDate: Date, wodType: Char) {
        wodRepository.deleteByWodDateAndWodType(wodDate, wodType)
    }

    fun getWodList(wodDate: Date) : List<Wod>? {
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