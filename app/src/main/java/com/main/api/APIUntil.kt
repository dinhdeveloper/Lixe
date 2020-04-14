package com.dinh.service

import com.dinh.service.APIClient.getApiClientLSP

object APIUntil {
    private const val baseURL =
        "https://appmusic-test.herokuapp.com/" // https://mobishops.herokuapp.com/ http://vtnshop.herokuapp.com/

    val server: APIService
        get() = getApiClientLSP(baseURL)?.create(APIService::class.java)!!
}