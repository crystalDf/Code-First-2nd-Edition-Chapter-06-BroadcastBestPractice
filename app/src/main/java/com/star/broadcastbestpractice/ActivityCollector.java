package com.star.broadcastbestpractice;


import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityCollector {

    public static List<Activity> sActivityList = new ArrayList<>();

    public static void addActivity(Activity activity) {
        sActivityList.add(activity);
    }

    public static void removeActivity(Activity activity) {
        sActivityList.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : sActivityList) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}
