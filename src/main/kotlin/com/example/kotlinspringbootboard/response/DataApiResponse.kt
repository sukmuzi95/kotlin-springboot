package com.example.kotlinspringbootboard.response

class DataApiResponse<T>(token: T) : ApiResponse() {
    var result: T? = token
}