package com.crossfit.whale.repository

import com.crossfit.whale.entity.Wod
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional
import java.sql.Date
import java.util.*

public interface WodRepository : JpaRepository<Wod?, UUID?> {   // Spring Data Envers(by Hibernate)

    fun findAllByWodDate(wodDate: Date): List<Wod>?

    @Query("select w from Wod w where w.wodDate = :wodDate and w.wodType = :wodType")
    fun findByWodDateAndWodType(wodDate: Date, wodType: Char): Wod


    @Transactional
    @Modifying
    @Query("delete from Wod w where w.wodDate = :wodDate and w.wodType = :wodType")
    fun deleteByWodDateAndWodType(wodDate: Date, wodType: Char): Int
}