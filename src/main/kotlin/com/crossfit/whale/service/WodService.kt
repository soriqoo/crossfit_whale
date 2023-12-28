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
    fun delete(id: UUID) {
        wodRepository.deleteById(id)
    }

    fun getTodayWods(wodDate: Date) : List<Wod>? {
        return wodRepository.findAllByWodDate(wodDate)
    }

}

data class WodCreationCommand(
    val name: String,
    val type: Char,
    val category: String,
    val content: String?,
    val date: String?
) {
    fun toEntity() = Wod(name, type, category, content, Date.valueOf(date))
}