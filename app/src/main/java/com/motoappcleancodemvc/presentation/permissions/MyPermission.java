package com.motoappcleancodemvc.presentation.permissions;

import android.Manifest;

public enum MyPermission {
    // declare runtime permissions specific to your app here (don't keep unused ones)
    COARSE_LOCATION(Manifest.permission.ACCESS_COARSE_LOCATION),
    FINE_LOCATION(Manifest.permission.ACCESS_FINE_LOCATION);

    public static MyPermission fromAndroidPermission(String androidPermission) {
        for (MyPermission permission : MyPermission.values()) {
            if (permission.getAndroidPermission().equals(androidPermission)) {
                return permission;
            }
        }
        throw new RuntimeException("Android permission not supported yet: " + androidPermission);
    }

    private final String mAndroidPermission;

    MyPermission(String androidPermission) {
        mAndroidPermission = androidPermission;
    }

    public String getAndroidPermission() {
        return mAndroidPermission;
    }
}
