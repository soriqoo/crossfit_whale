package com.crossfit.whale.entity

import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.sql.Date

@Entity
@Table(name = "wod_note")
class WodNote (
    userName: String,

    content: String?,
    date: Date
): PrimaryKeyEntity() {

}