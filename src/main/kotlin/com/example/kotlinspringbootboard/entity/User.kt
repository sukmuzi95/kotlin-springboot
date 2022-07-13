package com.example.kotlinspringbootboard.entity

import javax.persistence.*
import java.io.Serializable

@Entity
@Table(name = "TB_USER")
data class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(nullable = false)
    var password: String = "",

    @Column(nullable = false)
    var email: String = "",

    @Column(nullable = false)
    var nickname: String = "",

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var role: Role? = null
) : BaseTimeEntity(), Serializable {
}