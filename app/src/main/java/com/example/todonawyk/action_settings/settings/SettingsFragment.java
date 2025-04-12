package com.example.todonawyk.action_settings.settings;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import com.example.todonawyk.R;

public class SettingsFragment extends Fragment {
    private static final String PREFS_NAME = "settings_prefs";
    private static final String KEY_THEME_MODE = "theme_mode";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        @SuppressLint("UseSwitchCompatOrMaterialCode")
        Switch themeSwitch = view.findViewById(R.id.themeSwitch);
        getContext();
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        // Set the switch state based on saved preferences
        boolean isDarkMode = sharedPreferences.getBoolean(KEY_THEME_MODE, true);
        themeSwitch.setChecked(isDarkMode);

        // Handle switch state changes
        themeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            AppCompatDelegate.setDefaultNightMode(
                    isChecked ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO
            );
            sharedPreferences.edit().putBoolean(KEY_THEME_MODE, isChecked).apply();
        });

        return view;
    }
}