package com.example.kotlinspringbootboard.entity

import javax.persistence.*
import java.io.Serializable

@Entity
@Table(name = "TB_USER")
data class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var userNo: Long? = 0,

    @Column(nullable = false, unique = true)
    var userId: String? = null,

    @Column(nullable = false)
    var userPw: String? = null,

    @Column(nullable = false)
    var userName: String? = null,

    @Column(nullable = false)
    var userEmail: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var role: Role? = null
) : BaseTimeEntity(), Serializable {
}