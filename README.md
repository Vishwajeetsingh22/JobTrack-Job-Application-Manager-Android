# 💼 JobTrack – Job Application Manager

JobTrack is a Kotlin/XML based Android application developed for the **Mobile Application Development (MAD) Lab**. It helps users add, view, manage, and track their job applications through a simple and professional blue-themed interface.

## 📱 Application Overview

JobTrack provides a Job Dashboard where saved job applications are displayed. Users can add new applications, view complete application details, update the application status, and receive a notification whenever the status changes.

## ✨ Features

- 📊 Job Dashboard
- ➕ Add Application using Fragment
- 👤 Applicant Name
- 🏢 Company Name
- 💼 Job Role
- 🕒 Employment Type: Full Time, Part Time, Internship
- 📌 Application Status: Applied, Interview, Selected, Rejected
- 📄 Job Details Activity
- 🔗 Intent-based navigation
- 🔄 Update Application Status
- 🔔 Status update notification
- 💾 Local data storage using SharedPreferences and JSON
- 🔄 Activity lifecycle logging
- 🔄 Fragment lifecycle logging
- 🔵 Professional blue UI
- 💼 Custom JobTrack application icon

## 🛠️ Technologies Used

- **Kotlin** – Application logic
- **XML** – User interface design
- **Android Studio** – Development environment
- **Android SDK** – Android application development
- **AndroidX** – Android support libraries
- **Material Design** – UI components and styling
- **Fragments** – Add Application screen
- **Explicit Intent** – Activity navigation
- **SharedPreferences** – Local data storage
- **JSON** – Application data representation
- **NotificationCompat** – Android notifications
- **Logcat** – Lifecycle monitoring and debugging

## 📂 Project Structure

```text
JobTrack/
│
├── app/
│   └── src/
│       └── main/
│           ├── java/com/example/jobtrack/
│           │   ├── MainActivity.kt
│           │   ├── DashboardFragment.kt
│           │   ├── ApplicationFragment.kt
│           │   ├── JobDetailsActivity.kt
│           │   ├── JobApplication.kt
│           │   ├── JobStorage.kt
│           │   └── NotificationHelper.kt
│           │
│           ├── res/
│           │   ├── drawable/
│           │   ├── layout/
│           │   └── values/
│           │
│           └── AndroidManifest.xml
│
├── screenshots/
│   ├── dashboard.png
│   ├── add_application.png
│   ├── saved_application.png
│   ├── job_details.png
│   ├── update_status.png
│   ├── notification.png
│   └── logcat.png
│
├── build.gradle.kts
├── settings.gradle.kts
├── gradle.properties
├── .gitignore
└── README.md
```

## 🔄 Application Workflow

```text
Launch JobTrack
       │
       ▼
Job Dashboard
       │
       ▼
+ Add Application
       │
       ▼
ApplicationFragment
       │
       ▼
Enter Application Details
       │
       ▼
Save Application
       │
       ▼
Job Dashboard
       │
       ▼
Select Job Application
       │
       ▼
JobDetailsActivity
       │
       ▼
Update Status
       │
       ├──────────────► Save Updated Status
       │
       ▼
Android Notification
```

## 📸 Screenshots

Add the actual screenshots of the working application inside the `screenshots` folder.

### 1. Job Dashboard

Displays all previously added job applications.

<img width="720" height="1600" alt="WhatsApp Image 2026-09-18 at 8 15 40 PM" src="https://github.com/user-attachments/assets/e88cfa8a-080d-4af1-986e-a378f1a431d6" />
![Job Dashboard](screenshots/dashboard.png)

### 2. Add Application

Allows the user to enter applicant, company, job role, employment type, and application status.

<img width="720" height="1600" alt="WhatsApp Image 2026-09-18 at 8 15 39 PM (2)" src="https://github.com/user-attachments/assets/bdc7f932-a8be-44ef-b2c9-5c4a2d5ce327" />
![Add Application](screenshots/add_application.png)

### 3. Dashboard with Saved Application

Displays the newly saved job application on the dashboard.

<img width="720" height="1600" alt="WhatsApp Image 2026-09-18 at 8 15 40 PM" src="https://github.com/user-attachments/assets/88b4286a-93f4-4ebd-a7ed-b17569a09413" />
![Saved Application](screenshots/saved_application.png)

### 4. Job Details

Displays complete information about the selected job application.

<img width="720" height="1600" alt="WhatsApp Image 2026-09-18 at 8 15 39 PM" src="https://github.com/user-attachments/assets/3a0fca3c-db4b-4a2e-9e65-9b58900464d0" />
![Job Details](screenshots/job_details.png)

### 5. Update Status

Allows the user to update the current application status.

![Update Status](screenshots/update_status.png)

### 6. Status Update Notification

Notification generated after the application status is updated.

<img width="720" height="1600" alt="WhatsApp Image 2026-09-18 at 8 21 19 PM" src="https://github.com/user-attachments/assets/1f862dc2-69cd-428b-9cca-dc8add510e6c" />
<img width="720" height="1600" alt="WhatsApp Image 2026-09-18 at 8 21 14 PM" src="https://github.com/user-attachments/assets/385603bf-9af8-426d-976f-25825961dc32" />
![Notification](screenshots/notification.png)

## 🧪 Testing Workflow

1. Open JobTrack.
2. View Job Dashboard.
3. Click **+ Add Application**.
4. Enter applicant details.
5. Select Employment Type.
6. Select Application Status.
7. Click **Save Application**.
8. Verify application on Dashboard.
9. Select the application.
10. Open Job Details Activity.
11. Click **Update Status**.
12. Select a new status.
13. Verify the updated status.
14. Verify the notification.
15. Check Activity and Fragment lifecycle logs in Logcat.

## 💾 Data Storage

JobTrack stores application data locally using:

```text
SharedPreferences + JSON
```

This allows saved applications to remain available after closing and reopening the application.

## 🔔 Notification

A notification is generated whenever the application status is updated.

Example:

```text
Job Application Updated

Microsoft application is now Interview
```

For Android 13 and above, the application requests the notification permission before displaying notifications.

## 🔄 Lifecycle Methods

The application demonstrates suitable Activity and Fragment lifecycle methods, including:

```text
MainActivity - onCreate
MainActivity - onStart
MainActivity - onResume
MainActivity - onPause
MainActivity - onStop
MainActivity - onDestroy

DashboardFragment - onCreate
DashboardFragment - onCreateView
DashboardFragment - onViewCreated
DashboardFragment - onResume
DashboardFragment - onDestroyView
DashboardFragment - onDestroy

ApplicationFragment - onCreate
ApplicationFragment - onCreateView
ApplicationFragment - onViewCreated
ApplicationFragment - onResume
ApplicationFragment - onPause
ApplicationFragment - onDestroyView
ApplicationFragment - onDestroy

JobDetailsActivity - onCreate
JobDetailsActivity - onStart
JobDetailsActivity - onResume
JobDetailsActivity - onPause
JobDetailsActivity - onStop
JobDetailsActivity - onDestroy
```

Logcat tag used in the project:

```text
JobTrackLifecycle
```

## 🎯 Learning Outcomes

This project demonstrates:

- Android Activities
- Android Fragments
- Activity lifecycle
- Fragment lifecycle
- Explicit Intents
- Passing data using Intent extras
- EditText
- RadioButton and RadioGroup
- Button click handling
- SharedPreferences
- JSON data handling
- Android Notifications
- Notification Channels
- Runtime notification permissions
- Logcat debugging
- XML UI design
- Kotlin programming

## 🚀 How to Run the Project

1. Clone or download this repository.
2. Open **Android Studio**.
3. Select **Open Existing Project**.
4. Select the `JobTrack` project folder.
5. Wait for Gradle synchronization to complete.
6. Connect an Android device or start an Android Emulator.
7. Click **Run ▶**.
8. The JobTrack application will launch.

## 📚 Lab Test Project

This project was developed as part of the **Mobile Application Development (MAD) Lab** to demonstrate Android Activities, Fragments, Intents, local data storage, notifications, UI components, and lifecycle methods.

## 👨‍💻 Author

**Vishwajeet Singh**  
**USN:** 25MCAR0219  
**University:** Jain (Deemed-to-be University)  
**Course:** Master of Computer Applications (MCA)

## 📌 Repository

**Repository Name:** `JobTrack-Job-Application-Manager-Android`

## 📄 License

This project is developed for educational and academic purposes.

---

⭐ **JobTrack – Track Applications. Manage Opportunities.**
