package com.example.jobtrack

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment

class ApplicationFragment : Fragment() {

    companion object {
        private const val TAG = "JobTrackLifecycle"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "ApplicationFragment - onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        Log.d(TAG, "ApplicationFragment - onCreateView")

        return inflater.inflate(
            R.layout.fragment_application,
            container,
            false
        )
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {

        super.onViewCreated(view, savedInstanceState)

        Log.d(TAG, "ApplicationFragment - onViewCreated")

        val etApplicantName =
            view.findViewById<EditText>(
                R.id.etApplicantName
            )

        val etCompanyName =
            view.findViewById<EditText>(
                R.id.etCompanyName
            )

        val etJobRole =
            view.findViewById<EditText>(
                R.id.etJobRole
            )

        val rgEmployment =
            view.findViewById<RadioGroup>(
                R.id.rgEmployment
            )

        val rgStatus =
            view.findViewById<RadioGroup>(
                R.id.rgStatus
            )

        val btnSave =
            view.findViewById<Button>(
                R.id.btnSaveApplication
            )

        btnSave.setOnClickListener {

            val applicantName =
                etApplicantName.text.toString().trim()

            val companyName =
                etCompanyName.text.toString().trim()

            val jobRole =
                etJobRole.text.toString().trim()

            if (
                applicantName.isEmpty() ||
                companyName.isEmpty() ||
                jobRole.isEmpty()
            ) {

                Toast.makeText(
                    requireContext(),
                    "Please fill all required fields",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            val employmentType =
                when (rgEmployment.checkedRadioButtonId) {

                    R.id.rbFullTime ->
                        "Full Time"

                    R.id.rbPartTime ->
                        "Part Time"

                    R.id.rbInternship ->
                        "Internship"

                    else -> {

                        Toast.makeText(
                            requireContext(),
                            "Select employment type",
                            Toast.LENGTH_SHORT
                        ).show()

                        return@setOnClickListener
                    }
                }

            val status =
                when (rgStatus.checkedRadioButtonId) {

                    R.id.rbApplied ->
                        "Applied"

                    R.id.rbInterview ->
                        "Interview"

                    R.id.rbSelected ->
                        "Selected"

                    R.id.rbRejected ->
                        "Rejected"

                    else ->
                        "Applied"
                }

            val application =
                JobApplication(
                    id = System.currentTimeMillis(),
                    applicantName = applicantName,
                    companyName = companyName,
                    jobRole = jobRole,
                    employmentType = employmentType,
                    status = status
                )

            JobStorage.addApplication(
                requireContext(),
                application
            )

            Toast.makeText(
                requireContext(),
                "Application Saved Successfully",
                Toast.LENGTH_SHORT
            ).show()

            parentFragmentManager.popBackStack()
        }
    }

    override fun onResume() {
        super.onResume()

        Log.d(TAG, "ApplicationFragment - onResume")
    }

    override fun onPause() {
        super.onPause()

        Log.d(TAG, "ApplicationFragment - onPause")
    }

    override fun onDestroyView() {
        super.onDestroyView()

        Log.d(TAG, "ApplicationFragment - onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d(TAG, "ApplicationFragment - onDestroy")
    }
}