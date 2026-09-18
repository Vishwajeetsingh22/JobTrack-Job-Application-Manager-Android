package com.example.jobtrack

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "JobTrackLifecycle"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "MainActivity - onCreate")

        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {

            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.fragmentContainer,
                    DashboardFragment()
                )
                .commit()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "MainActivity - onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "MainActivity - onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "MainActivity - onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "MainActivity - onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "MainActivity - onDestroy")
    }
}