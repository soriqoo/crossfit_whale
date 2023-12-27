package com.crossfit.whale

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WhaleApplication

fun main(args: Array<String>) {
    runApplication<WhaleApplication>(*args)
}
