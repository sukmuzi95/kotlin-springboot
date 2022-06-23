package com.example.kotlinspringbootboard.entity

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.Collections
import javax.persistence.*

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
) : BaseTimeEntity(), java.io.Serializable {
}