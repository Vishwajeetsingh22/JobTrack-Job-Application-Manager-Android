package com.example.jobtrack

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject

object JobStorage {

    private const val PREF_NAME = "JobTrackPrefs"
    private const val KEY_APPLICATIONS = "applications"

    private fun getPrefs(context: Context) =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun getApplications(context: Context): MutableList<JobApplication> {

        val list = mutableListOf<JobApplication>()

        val jsonString = getPrefs(context)
            .getString(KEY_APPLICATIONS, "[]") ?: "[]"

        val jsonArray = JSONArray(jsonString)

        for (i in 0 until jsonArray.length()) {

            val obj = jsonArray.getJSONObject(i)

            list.add(
                JobApplication(
                    id = obj.getLong("id"),
                    applicantName = obj.getString("applicantName"),
                    companyName = obj.getString("companyName"),
                    jobRole = obj.getString("jobRole"),
                    employmentType = obj.getString("employmentType"),
                    status = obj.getString("status")
                )
            )
        }

        return list
    }

    fun saveApplications(
        context: Context,
        applications: List<JobApplication>
    ) {

        val jsonArray = JSONArray()

        applications.forEach {

            val obj = JSONObject()

            obj.put("id", it.id)
            obj.put("applicantName", it.applicantName)
            obj.put("companyName", it.companyName)
            obj.put("jobRole", it.jobRole)
            obj.put("employmentType", it.employmentType)
            obj.put("status", it.status)

            jsonArray.put(obj)
        }

        getPrefs(context)
            .edit()
            .putString(KEY_APPLICATIONS, jsonArray.toString())
            .apply()
    }

    fun addApplication(
        context: Context,
        application: JobApplication
    ) {

        val applications = getApplications(context)

        applications.add(application)

        saveApplications(context, applications)
    }

    fun updateStatus(
        context: Context,
        id: Long,
        newStatus: String
    ) {

        val applications = getApplications(context)

        val application = applications.find {
            it.id == id
        }

        application?.status = newStatus

        saveApplications(context, applications)
    }

    fun getApplication(
        context: Context,
        id: Long
    ): JobApplication? {

        return getApplications(context)
            .find { it.id == id }
    }
}