package com.crossfit.whale

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@EnableTransactionManagement
class WhaleApplication

fun main(args: Array<String>) {
    runApplication<WhaleApplication>(*args)
}
