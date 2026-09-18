package com.example.jobtrack

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment

class DashboardFragment : Fragment() {

    companion object {
        private const val TAG = "JobTrackLifecycle"
    }

    private lateinit var applicationContainer: LinearLayout
    private lateinit var tvCount: TextView
    private lateinit var tvEmpty: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "DashboardFragment - onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        Log.d(TAG, "DashboardFragment - onCreateView")

        return inflater.inflate(
            R.layout.fragment_dashboard,
            container,
            false
        )
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {

        super.onViewCreated(view, savedInstanceState)

        Log.d(TAG, "DashboardFragment - onViewCreated")

        applicationContainer =
            view.findViewById(R.id.applicationContainer)

        tvCount =
            view.findViewById(R.id.tvApplicationCount)

        tvEmpty =
            view.findViewById(R.id.tvEmpty)

        val btnAdd =
            view.findViewById<Button>(R.id.btnAddApplication)

        btnAdd.setOnClickListener {

            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.fragmentContainer,
                    ApplicationFragment()
                )
                .addToBackStack("ApplicationFragment")
                .commit()
        }

        loadApplications()
    }

    override fun onResume() {
        super.onResume()

        Log.d(TAG, "DashboardFragment - onResume")

        if (::applicationContainer.isInitialized) {
            loadApplications()
        }
    }

    private fun loadApplications() {

        applicationContainer.removeAllViews()

        val applications =
            JobStorage.getApplications(requireContext())

        tvCount.text =
            "Applications: ${applications.size}"

        if (applications.isEmpty()) {

            tvEmpty.visibility = View.VISIBLE

            return

        } else {

            tvEmpty.visibility = View.GONE
        }

        applications.reversed().forEach { application ->

            val card = createApplicationCard(application)

            applicationContainer.addView(card)

        }
    }

    private fun createApplicationCard(
        application: JobApplication
    ): View {

        val card = LinearLayout(requireContext())

        card.orientation = LinearLayout.VERTICAL

        card.setPadding(
            20,
            20,
            20,
            20
        )

        card.background =
            resources.getDrawable(
                R.drawable.bg_card,
                null
            )

        val params =
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

        params.setMargins(0, 0, 0, 15)

        card.layoutParams = params

        val name = TextView(requireContext())

        name.text = application.applicantName

        name.textSize = 19f

        name.setTextColor(
            resources.getColor(
                R.color.black,
                null
            )
        )

        name.setTypeface(null, android.graphics.Typeface.BOLD)

        val company = TextView(requireContext())

        company.text =
            "${application.jobRole}  •  ${application.companyName}"

        company.textSize = 16f

        company.setTextColor(
            resources.getColor(
                R.color.gray,
                null
            )
        )

        val employment = TextView(requireContext())

        employment.text =
            "Employment: ${application.employmentType}"

        employment.textSize = 14f

        employment.setTextColor(
            resources.getColor(
                R.color.gray,
                null
            )
        )

        val status = TextView(requireContext())

        status.text =
            "Status: ${application.status}"

        status.textSize = 14f

        status.setTypeface(null, android.graphics.Typeface.BOLD)

        status.setTextColor(
            resources.getColor(
                R.color.blue_primary,
                null
            )
        )

        status.setPadding(0, 12, 0, 0)

        card.addView(name)
        card.addView(company)
        card.addView(employment)
        card.addView(status)

        card.setOnClickListener {

            val intent =
                Intent(
                    requireContext(),
                    JobDetailsActivity::class.java
                )

            intent.putExtra(
                "APPLICATION_ID",
                application.id
            )

            startActivity(intent)
        }

        return card
    }

    override fun onDestroyView() {
        super.onDestroyView()

        Log.d(TAG, "DashboardFragment - onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d(TAG, "DashboardFragment - onDestroy")
    }
}