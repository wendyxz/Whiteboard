package com.cs346.user

import org.springframework.data.annotation.PersistenceConstructor
import org.springframework.data.annotation.PersistenceCreator
import java.io.Serializable
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id


data class User @PersistenceCreator constructor(
    val id: Int?,

    var name: String?,

    var password: String?
) : Serializable