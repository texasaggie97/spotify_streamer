package com.markesilva.spotifystreamer.utils;

/**
 * Created by marke on 8/11/2015.
 */

import android.util.Log;

/**
 * Helper methods that make logging more consistent throughout the app.
 */
public class LogUtils {
    // Not using a prefix for this app
    private static final String LOG_PREFIX = "";
    private static final int LOG_PREFIX_LENGTH = LOG_PREFIX.length();
    private static final int MAX_LOG_TAG_LENGTH = 23;

    // Explicitly allow turning off logging at certain levels
    private static final int LOG_LEVEL = Log.INFO;

    private LogUtils() {
    }

    public static String makeLogTag(String str) {
        if (str.length() > MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH) {
            return LOG_PREFIX + str.substring(0, MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH - 1);
        }

        return LOG_PREFIX + str;
    }

    /**
     * WARNING: Don't use this when obfuscating class names with Proguard!
     */
    public static String makeLogTag(Class cls) {
        return makeLogTag(cls.getSimpleName());
    }

    public static void LOGD(final String tag, String message) {
        if (LOG_LEVEL <= Log.DEBUG) {
            Log.d(tag, message);
        }
    }

    public static void LOGD(final String tag, String message, Throwable cause) {
        if (LOG_LEVEL <= Log.DEBUG) {
            Log.d(tag, message, cause);
        }
    }

    public static void LOGV(final String tag, String message) {
        if (LOG_LEVEL <= Log.VERBOSE) {
            Log.v(tag, message);
        }
    }

    public static void LOGV(final String tag, String message, Throwable cause) {
        if (LOG_LEVEL <= Log.VERBOSE) {
            Log.v(tag, message, cause);
        }
    }

    public static void LOGI(final String tag, String message) {
        if (LOG_LEVEL <= Log.INFO) {
            Log.i(tag, message);
        }
    }

    public static void LOGI(final String tag, String message, Throwable cause) {
        if (LOG_LEVEL <= Log.INFO) {
            Log.i(tag, message, cause);
        }
    }

    public static void LOGW(final String tag, String message) {
        if (LOG_LEVEL <= Log.WARN) {
            Log.w(tag, message);
        }
    }

    public static void LOGW(final String tag, String message, Throwable cause) {
        if (LOG_LEVEL <= Log.WARN) {
            Log.w(tag, message, cause);
        }
    }

    // We always log errors
    public static void LOGE(final String tag, String message) {
        Log.e(tag, message);
    }

    public static void LOGE(final String tag, String message, Throwable cause) {
        Log.e(tag, message, cause);
    }
}