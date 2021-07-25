package com.example.mycomplaintadmin.model

import java.io.Serializable

data class ComplaintsModel(
    val userId: String = "",
    val userName: String = "",
    val userContact: String = "",
    val deptName: String = "",
    val complaintSubject: String = "",
    val complaintDetails: String = ""
): Serializable