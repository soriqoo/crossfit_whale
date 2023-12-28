package com.crossfit.whale.repository

import com.crossfit.whale.entity.Wod
import org.springframework.data.jpa.repository.JpaRepository
import java.sql.Date
import java.util.*

public interface WodRepository : JpaRepository<Wod?, UUID?> {   // Spring Data Envers(by Hibernate)

    fun findAllByWodDate(wodDate: Date): List<Wod>?

}