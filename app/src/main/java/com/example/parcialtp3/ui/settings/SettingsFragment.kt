package com.example.parcialtp3.ui.settings

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import com.example.parcialtp3.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SettingsFragment : PreferenceFragmentCompat() {
    val TAG2 = "SettingsFragment"
    init {
        Log.d(TAG2, "Init settings fragment")
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        val sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val isDarkMode = sharedPreferences.getBoolean("darkMode", false)

        val darkModeSwitchPref = findPreference<SwitchPreferenceCompat>("darkModeSwitch")
        darkModeSwitchPref?.isChecked = isDarkMode

        darkModeSwitchPref?.setOnPreferenceChangeListener { preference: Preference, newValue: Any ->
            val isDarkModeOn = newValue as Boolean
            if (isDarkModeOn) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }

            val editor = sharedPreferences.edit()
            editor.putBoolean("darkMode", isDarkModeOn)
            editor.apply()

            true
        }
    }
}