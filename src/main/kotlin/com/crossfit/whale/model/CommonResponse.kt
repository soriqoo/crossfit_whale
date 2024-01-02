package com.crossfit.whale.model

import jakarta.servlet.http.HttpServletResponse

open class CommonResponse (
    val resultCode: Int,
    val message: String
)
{

    fun defaultCommonResponse(): CommonResponse{
        return CommonResponse(HttpServletResponse.SC_OK, "OK")  // default 200, "OK"
    }
}