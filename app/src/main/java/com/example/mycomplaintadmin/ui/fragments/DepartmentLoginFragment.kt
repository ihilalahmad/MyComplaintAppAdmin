package com.example.mycomplaintadmin.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.mycomplaintadmin.R
import com.example.mycomplaintadmin.databinding.FragmentDepartmentLoginBinding
import com.example.mycomplaintadmin.model.DeptModel
import com.example.mycomplaintadmin.utils.AppPreferences
import com.google.firebase.database.*


class DepartmentLoginFragment : Fragment() {

    private lateinit var binding: FragmentDepartmentLoginBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val isUserLoggedIn = AppPreferences.getLoginStatus(requireContext())
        if (isUserLoggedIn) {
            AppPreferences.setLoginState(requireContext(), false)
            AppPreferences.setDeptName(requireContext(), "")
        }
    }

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

        val deptName = binding.etDeptName.text.toString()
        val deptId = binding.etDeptId.text.toString()
        val deptPassword = binding.etDeptPassword.text.toString()

        databaseReference.child(deptName).addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                val deptModel : DeptModel = snapshot.getValue(DeptModel::class.java)!!

                if (deptId == deptModel.deptId && deptPassword == deptModel.deptPassword) {
                    Toast.makeText(context,"Logged In", Toast.LENGTH_SHORT).show()

                    //passing department in arguments.
                    val bundle = Bundle().apply {
                        putSerializable("deptName", deptName)
                    }
                    findNavController().navigate(R.id.navigate_login_to_home,bundle)
                    AppPreferences.setLoginState(requireContext(), true)
                    AppPreferences.setDeptName(requireContext(), deptName)

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