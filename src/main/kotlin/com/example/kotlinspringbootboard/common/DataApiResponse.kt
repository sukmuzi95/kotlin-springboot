package com.example.kotlinspringbootboard.common

class DataApiResponse<T>(token: T) : ApiResponse() {
    var result: T? = token
}