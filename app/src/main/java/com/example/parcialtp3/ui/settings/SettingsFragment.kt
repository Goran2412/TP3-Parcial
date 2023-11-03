package com.example.parcialtp3.ui.settings

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.preference.CheckBoxPreference
import androidx.preference.PreferenceFragmentCompat
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


    }
}