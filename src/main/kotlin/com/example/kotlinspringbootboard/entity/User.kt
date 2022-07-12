package com.example.kotlinspringbootboard.entity

import javax.persistence.*
import java.io.Serializable

@Entity
@Table(name = "TB_USER")
data class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var userNo: Long = 0,

    @Column(nullable = false)
    var userPw: String = "",

    @Column(nullable = false)
    var userName: String = "",

    @Column(nullable = false)
    var userEmail: String = "",

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var role: Role? = null
) : BaseTimeEntity(), Serializable {
}