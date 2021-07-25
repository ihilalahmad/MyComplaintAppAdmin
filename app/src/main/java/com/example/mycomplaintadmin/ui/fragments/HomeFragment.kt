package com.example.mycomplaintadmin.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mycomplaintadmin.R
import com.example.mycomplaintadmin.databinding.FragmentHomeBinding
import com.example.mycomplaintadmin.interfaces.ComplaintClickListener
import com.example.mycomplaintadmin.model.ComplaintsModel
import com.example.mycomplaintadmin.ui.adapters.ComplaintsAdapter
import com.example.mycomplaintadmin.utils.AppPreferences
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HomeFragment : Fragment(), ComplaintClickListener {

    private lateinit var binding: FragmentHomeBinding
    private val complaintsList = ArrayList<ComplaintsModel>()
    private lateinit var deptName: String
    private lateinit var adapter: ComplaintsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        val isUserLoggedIn = AppPreferences.getLoginStatus(requireContext())
        if (!isUserLoggedIn) {
            findNavController().navigate(R.id.navigate_home_to_login)
        } else {
            deptName = AppPreferences.getDeptName(requireContext())
            setupRecyclerView()
            getComplaintsFromFirebase()
        }
        return binding.root
    }

    private fun setupRecyclerView() {

        binding.complaintsRecyclerView.also {
            it.layoutManager = LinearLayoutManager(context)
            it.setHasFixedSize(true)
        }
    }

    private fun getComplaintsFromFirebase() {

        binding.progressBar.visibility = View.VISIBLE

        FirebaseDatabase.getInstance()
            .getReference("Complaints")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    complaintsList.clear()

                    for (complaintsSnap in snapshot.children) {

                        val complaints: ComplaintsModel =
                            complaintsSnap.getValue(ComplaintsModel::class.java)!!

                        if (complaints.deptName == deptName) {
                            complaintsList.add(complaints)
                        }
                    }
                    adapter = ComplaintsAdapter(complaintsList, this@HomeFragment, deptName)
                    binding.complaintsRecyclerView.adapter = adapter
                    binding.progressBar.visibility = View.GONE
                }

                override fun onCancelled(error: DatabaseError) {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(context, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
                }

            })
    }

    override fun onComplaintClick(complaintsModel: ComplaintsModel) {
        val bundle = Bundle().apply {
            putSerializable("complaint", complaintsModel)
            putSerializable("deptName", deptName)
        }
        findNavController().navigate(R.id.navigate_home_to_chat, bundle)
    }
}