package com.example.finalproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.view.ViewConfiguration;

public class UtilsDraw {

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    private static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public static int getFullScreenHeight(Context context) {
        return (getScreenHeight() + getNavigationBarHeight(context) + getStatusBarHeight());
    }

    @SuppressLint({"DiscouragedApi", "InternalInsetResource"})
    public static int getStatusBarHeight() {
        Resources resources = Resources.getSystem();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return resources.getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    @SuppressLint({"DiscouragedApi", "InternalInsetResource"})
    public static int getNavigationBarHeight(Context context) {
        Resources resources = context.getResources();
        boolean hasMenuKey = ViewConfiguration.get(context).hasPermanentMenuKey();
        // "navigation_bar_height_landscape"
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0 && !hasMenuKey) {
            return resources.getDimensionPixelSize(resourceId);
        }
        return 0;
    }
}
