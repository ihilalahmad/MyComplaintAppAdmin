package com.example.mycomplaintadmin.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mycomplaintadmin.databinding.ComplaintsItemBinding
import com.example.mycomplaintadmin.interfaces.ComplaintClickListener
import com.example.mycomplaintadmin.model.ComplaintsModel

class ComplaintsAdapter(
    private val complaintList: List<ComplaintsModel>,
    private val complaintClickListener: ComplaintClickListener
): RecyclerView.Adapter<ComplaintsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ComplaintsItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            tvUserName.text = complaintList[position].userName
            tvContactNumber.text = complaintList[position].userContact
            tvComplaintTitle.text = complaintList[position].subject
            tvComplaintDetails.text = complaintList[position].message

            tvOpenComplaint.setOnClickListener {
                complaintClickListener.onComplaintClick(complaintList[position])
            }
        }
    }

    override fun getItemCount() = complaintList.size

    inner class ViewHolder(val binding: ComplaintsItemBinding): RecyclerView.ViewHolder(binding.root)
}