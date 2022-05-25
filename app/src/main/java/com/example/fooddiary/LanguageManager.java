package com.example.fooddiary;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

import com.example.fooddiary.fragment.LanguageFragment;

import java.util.Locale;

public class LanguageManager {

    private Context ct;
    public LanguageManager(LanguageFragment ctx) {
        ct = ctx;
    }

    public void updateResources (String code) {
        Locale locale = new Locale(code);
        Locale.setDefault(locale);
        Resources resources = ct.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }
}
