package com.zte.gameassist.ext.netspeedup;

import android.text.TextUtils;

/* loaded from: classes2.dex */
public final class NetSpeedupConfig {
    public static final String USER_MANUAL_OFF = "N";
    public static final String USER_MANUAL_ON = "Y";
    public static final String VENDOR_LEIGOD = "leigod";
    public static final String VENDOR_SUBAO = "subao";
    public final String mPackageName;
    public boolean mUserManualEnabled = false;
    public String mAcceleratorVendor = VENDOR_LEIGOD;

    public NetSpeedupConfig(String str) {
        this.mPackageName = str;
    }

    public static String normalizeVendor(String str) {
        return (!TextUtils.isEmpty(str) && VENDOR_SUBAO.equals(str.trim().toLowerCase())) ? VENDOR_SUBAO : VENDOR_LEIGOD;
    }

    public static NetSpeedupConfig withDefaults(String str) {
        return new NetSpeedupConfig(str);
    }

    public NetSpeedupConfig normalizedCopy() {
        NetSpeedupConfig netSpeedupConfig = new NetSpeedupConfig(this.mPackageName);
        netSpeedupConfig.mUserManualEnabled = this.mUserManualEnabled;
        netSpeedupConfig.mAcceleratorVendor = normalizeVendor(this.mAcceleratorVendor);
        return netSpeedupConfig;
    }
}
