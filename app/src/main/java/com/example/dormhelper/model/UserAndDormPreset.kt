package com.example.dormhelper.model

import androidx.room.Embedded
import androidx.room.Relation

data class UserAndDormPreset (
    @Embedded
    val user: User,
    @Relation(
        parentColumn = "username",
        entityColumn = "nameOfUser"
    )
    val Dorm: DormPreset
)