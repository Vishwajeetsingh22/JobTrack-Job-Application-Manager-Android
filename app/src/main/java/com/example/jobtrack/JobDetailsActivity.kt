package com.example.jobtrack

import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

class JobDetailsActivity : AppCompatActivity() {

    companion object {

        private const val TAG = "JobTrackLifecycle"

        private const val NOTIFICATION_PERMISSION_REQUEST = 100
    }

    private var applicationId: Long = -1

    private lateinit var tvName: TextView
    private lateinit var tvCompany: TextView
    private lateinit var tvRole: TextView
    private lateinit var tvEmployment: TextView
    private lateinit var tvStatus: TextView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        Log.d(TAG, "JobDetailsActivity - onCreate")

        setContentView(R.layout.activity_job_details)

        applicationId =
            intent.getLongExtra(
                "APPLICATION_ID",
                -1
            )

        tvName =
            findViewById(R.id.tvName)

        tvCompany =
            findViewById(R.id.tvCompany)

        tvRole =
            findViewById(R.id.tvRole)

        tvEmployment =
            findViewById(R.id.tvEmployment)

        tvStatus =
            findViewById(R.id.tvStatus)

        findViewById<Button>(
            R.id.btnUpdateStatus
        ).setOnClickListener {

            showStatusDialog()
        }

        createNotificationChannel()

        requestNotificationPermission()

        displayApplication()
    }

    private fun displayApplication() {

        val application =
            JobStorage.getApplication(
                this,
                applicationId
            )

        if (application == null) {

            Toast.makeText(
                this,
                "Application not found",
                Toast.LENGTH_SHORT
            ).show()

            finish()

            return
        }

        tvName.text =
            "Applicant: ${application.applicantName}"

        tvCompany.text =
            "Company: ${application.companyName}"

        tvRole.text =
            "Job Role: ${application.jobRole}"

        tvEmployment.text =
            "Employment Type: ${application.employmentType}"

        tvStatus.text =
            "Status: ${application.status}"
    }

    private fun showStatusDialog() {

        val statuses =
            arrayOf(
                "Applied",
                "Interview",
                "Selected",
                "Rejected"
            )

        val application =
            JobStorage.getApplication(
                this,
                applicationId
            )

        var selectedPosition =
            statuses.indexOf(application?.status)

        if (selectedPosition < 0) {
            selectedPosition = 0
        }

        AlertDialog.Builder(this)
            .setTitle("Update Application Status")
            .setSingleChoiceItems(
                statuses,
                selectedPosition
            ) { dialog, which ->

                val newStatus =
                    statuses[which]

                JobStorage.updateStatus(
                    this,
                    applicationId,
                    newStatus
                )

                NotificationHelper.showStatusNotification(
                    this,
                    application?.companyName ?: "Job Application",
                    newStatus
                )

                displayApplication()

                Toast.makeText(
                    this,
                    "Status updated to $newStatus",
                    Toast.LENGTH_SHORT
                ).show()

                dialog.dismiss()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun requestNotificationPermission() {

        if (
            Build.VERSION.SDK_INT >=
            Build.VERSION_CODES.TIRAMISU
        ) {

            if (
                ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {

                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        Manifest.permission.POST_NOTIFICATIONS
                    ),
                    NOTIFICATION_PERMISSION_REQUEST
                )
            }
        }
    }

    private fun createNotificationChannel() {

        NotificationHelper.createChannel(this)
    }

    override fun onStart() {

        super.onStart()

        Log.d(TAG, "JobDetailsActivity - onStart")
    }

    override fun onResume() {

        super.onResume()

        Log.d(TAG, "JobDetailsActivity - onResume")
    }

    override fun onPause() {

        super.onPause()

        Log.d(TAG, "JobDetailsActivity - onPause")
    }

    override fun onStop() {

        super.onStop()

        Log.d(TAG, "JobDetailsActivity - onStop")
    }

    override fun onDestroy() {

        super.onDestroy()

        Log.d(TAG, "JobDetailsActivity - onDestroy")
    }
}