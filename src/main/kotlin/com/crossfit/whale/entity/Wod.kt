package com.crossfit.whale.entity

import com.crossfit.whale.service.WodCreationCommand
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.sql.Date

@Entity
@Table(name = "wod")
class Wod (
    name: String,
    type: Char,
    category: String,
    content: String?,
    date: Date
): PrimaryKeyEntity() {

    @Column(nullable = false)
    var wodName: String = name
        protected set

    @Column(nullable = false)
    var wodType: Char = type

    @Column(nullable = false)
    var wodCategory: String = category

    @Column(nullable = true)
    var wodContent: String? = content

    @Column(nullable = false)
    var wodDate: Date = date

    // for Update
    fun updateWod(command: WodCreationCommand) {
        this.wodName = command.wodName
        this.wodType = command.wodType
        this.wodCategory = command.wodCategory
        this.wodContent = command.wodContent
        this.wodDate = Date.valueOf(command.wodDate)
    }
}