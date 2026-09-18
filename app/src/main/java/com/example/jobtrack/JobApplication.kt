package com.example.jobtrack

data class JobApplication(
    val id: Long,
    val applicantName: String,
    val companyName: String,
    val jobRole: String,
    val employmentType: String,
    var status: String
)