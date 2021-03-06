package com.markesilva.spotifystreamer;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import com.markesilva.spotifystreamer.utils.LogUtils;
import com.markesilva.spotifystreamer.utils.NotificationHelper;

/**
 * Created by marke on 8/5/2015.
 *
 * Fragment for preferences
 */
public class SettingsFragment extends PreferenceFragment {
    private final static String LOG_TAG = LogUtils.makeLogTag(SettingsFragment.class);
    /**
     * A preference value change listener that updates the preference's summary
     * to reflect its new value.
     */
    private Preference.OnPreferenceChangeListener sBindPreferenceSummaryToValueListener = new Preference.OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object value) {
            String stringValue = value.toString();

            if (preference instanceof CheckBoxPreference) {
                // If it is the notification setting, we update the notification appropriately
                if (preference.getKey().equals(getString(R.string.pref_notification_key))) {
                    if (stringValue.equals("false")) {
                        NotificationHelper.hideNotification();
                    } else {
                        NotificationHelper.showNotification();
                    }
                }
            } else {
                // Country needs to be upper case. We change the display to show uppercase here.
                if (preference.getKey().equals(getString(R.string.pref_spotify_locale_key))) {
                    stringValue = stringValue.toUpperCase();
                }
                // For all other preferences, set the summary to the value's
                // simple string representation.
                preference.setSummary(stringValue);
            }
            return true;
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.pref_main);
        bindPreferenceSummaryToValue(findPreference(getString(R.string.pref_notification_key)));
        bindPreferenceSummaryToValue(findPreference(getString(R.string.pref_spotify_locale_key)));
    }

    /**
     * Binds a preference's summary to its value. More specifically, when the
     * preference's value is changed, its summary (line of text below the
     * preference title) is updated to reflect the value. The summary is also
     * immediately updated upon calling this method. The exact display format is
     * dependent on the type of preference.
     *
     * @see #sBindPreferenceSummaryToValueListener
     */
    private void bindPreferenceSummaryToValue(Preference preference) {
        // Set the listener to watch for value changes.
        preference.setOnPreferenceChangeListener(sBindPreferenceSummaryToValueListener);

        // Trigger the listener immediately with the preference's
        // current value.
        if (preference instanceof CheckBoxPreference) {
            sBindPreferenceSummaryToValueListener.onPreferenceChange(preference,
                    PreferenceManager
                            .getDefaultSharedPreferences(preference.getContext())
                            .getBoolean(preference.getKey(), true));
        } else {
            sBindPreferenceSummaryToValueListener.onPreferenceChange(preference,
                    PreferenceManager
                            .getDefaultSharedPreferences(preference.getContext())
                            .getString(preference.getKey(), ""));
        }
    }
}
