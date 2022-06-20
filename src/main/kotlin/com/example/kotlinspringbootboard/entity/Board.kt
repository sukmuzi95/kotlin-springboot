package com.example.kotlinspringbootboard.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Board(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var title: String? = null,
    var content: String? = null,
    var readCnt: Int? = null,
    var registerId: String? = null

) : BaseTimeEntity() {

}