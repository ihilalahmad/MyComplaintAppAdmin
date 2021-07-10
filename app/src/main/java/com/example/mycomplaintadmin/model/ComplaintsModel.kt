package com.example.mycomplaintadmin.model

data class ComplaintsModel(
    val userId: String = "",
    val userName: String = "",
    val userContact: String = "",
    val subject: String = "",
    val message: String = ""
)