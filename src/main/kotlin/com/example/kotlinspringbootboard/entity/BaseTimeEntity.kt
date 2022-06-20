package com.example.kotlinspringbootboard.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
open class BaseTimeEntity {

    @CreatedDate
    var registerTime: LocalDateTime? = null

    @LastModifiedBy
    var updateTime: LocalDateTime? = null
}