package com.example.mycomplaintadmin.interfaces

import com.example.mycomplaintadmin.model.ComplaintsModel

interface ComplaintClickListener {
    fun onComplaintClick(complaintsModel: ComplaintsModel)
}