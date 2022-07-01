package com.example.kotlinspringbootboard.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "TB_BOARD")
data class Board(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var boardNo: Long? = null,

    var boardTitle: String? = null,
    var boardContent: String? = null,
    var readCount: Int? = 0,
    var registerId: String? = null

) : BaseTimeEntity()