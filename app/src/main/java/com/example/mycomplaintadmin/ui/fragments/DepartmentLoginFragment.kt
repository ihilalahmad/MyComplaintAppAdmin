package com.example.mycomplaintadmin.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.mycomplaintadmin.R
import com.example.mycomplaintadmin.databinding.FragmentDepartmentLoginBinding
import com.example.mycomplaintadmin.model.DeptModel
import com.google.firebase.database.*


class DepartmentLoginFragment : Fragment() {

    private lateinit var binding: FragmentDepartmentLoginBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDepartmentLoginBinding.inflate(
            inflater,
            container,
            false
        )
        initFirebase()
        binding.btnLogin.setOnClickListener {
            loginDepartmentFromFirebase()
        }

        return binding.root
    }

    private fun initFirebase() {
        databaseReference = FirebaseDatabase.getInstance().getReference("Departments")
    }

    private fun loginDepartmentFromFirebase() {

        val deptId = binding.etDeptId.text.toString()
        val deptPassword = binding.etDeptPassword.text.toString()

        databaseReference.child(deptId).addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                val deptModel : DeptModel = snapshot.getValue(DeptModel::class.java)!!

                if (deptPassword == deptModel.deptPassword) {
                    Toast.makeText(context,"Logged In", Toast.LENGTH_SHORT).show()
                }else {
                    Toast.makeText(context,"Wrong password or id", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

}