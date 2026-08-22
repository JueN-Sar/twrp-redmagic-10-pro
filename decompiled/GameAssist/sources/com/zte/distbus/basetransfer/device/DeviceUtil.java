package com.zte.distbus.basetransfer.device;

import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.zte.distbus.basetransfer.device.shake.Shake128;
import com.zte.distbus.basetransfer.servicemanager.DistService;

/* loaded from: classes.dex */
public class DeviceUtil {
    private static final int BYTE_LENGTH = 6;
    private static final String TAG = "DeviceUtil";
    private static boolean abroadVersion = false;
    private static int devType = 0;
    private static String deviceId = null;
    private static byte[] deviceIdBytes = null;
    private static boolean featureMusicNfc = false;
    private static boolean gameAssistVersion = false;
    private static String sipId = null;
    private static boolean useSipId = false;
    private static boolean wifiDualBandDualConcurrent = true;

    public static int getDevType() {
        return devType;
    }

    public static String getDeviceId(Context context) {
        if (TextUtils.isEmpty(deviceId)) {
            initDeviceId(context);
        }
        return deviceId;
    }

    public static byte[] getDeviceIdBytes(Context context) {
        if (deviceIdBytes == null) {
            initDeviceId(context);
        }
        return deviceIdBytes;
    }

    public static String getDeviceIdFromAndroidId(String str) {
        Log.d(TAG, "getDeviceIdFromAndroidId androidId: " + str);
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String deviceIdFromBytes = getDeviceIdFromBytes(Shake128.getHash(6, str.getBytes()));
        Log.d(TAG, "getDeviceIdFromAndroidId deviceId: " + deviceIdFromBytes);
        return deviceIdFromBytes;
    }

    public static String getDeviceIdFromBytes(byte[] bArr) {
        if (bArr == null || bArr.length != 6) {
            StringBuilder sb = new StringBuilder();
            sb.append("getDeviceIdFromBytes bytes length error: ");
            sb.append(bArr != null ? bArr.length : 0);
            Log.d(TAG, sb.toString());
            return null;
        }
        StringBuilder sb2 = new StringBuilder();
        while (r0 < bArr.length) {
            sb2.append(Integer.toHexString((bArr[r0] >> 4) & 15));
            sb2.append(Integer.toHexString(bArr[r0] & 15));
            r0++;
        }
        return sb2.toString();
    }

    public static String getSipId() {
        return sipId;
    }

    public static boolean getUseSipId() {
        return useSipId;
    }

    public static boolean getWifiDualBandDualConcurrent() {
        return wifiDualBandDualConcurrent;
    }

    private static void initDeviceId(Context context) {
        if (!(useSipId && TextUtils.isEmpty(sipId)) && TextUtils.isEmpty(deviceId)) {
            String string = (!useSipId || TextUtils.isEmpty(sipId)) ? Settings.Secure.getString(context.getContentResolver(), "android_id") : sipId;
            if (DistService.getInstance().isSdk()) {
                string = string + context.getPackageName();
            }
            Log.d(TAG, "getDeviceId androidId: " + string);
            if (TextUtils.isEmpty(string)) {
                return;
            }
            long currentTimeMillis = System.currentTimeMillis();
            deviceIdBytes = Shake128.getHash(6, string.getBytes());
            Log.d(TAG, "getDeviceId shake128 length: " + deviceIdBytes.length + ", cost MS: " + (System.currentTimeMillis() - currentTimeMillis));
            deviceId = getDeviceIdFromBytes(deviceIdBytes);
            StringBuilder sb = new StringBuilder();
            sb.append("getDeviceId deviceId: ");
            sb.append(deviceId);
            Log.d(TAG, sb.toString());
        }
    }

    public static boolean isAbroadVersion() {
        return abroadVersion;
    }

    public static boolean isOnlyFeatureMusicNfc() {
        return abroadVersion && featureMusicNfc && !gameAssistVersion;
    }

    public static void setAbroadVersion(boolean z) {
        abroadVersion = z;
    }

    public static void setDevType(int i2) {
        devType = i2;
    }

    public static void setFeatureMusicNfc(boolean z) {
        featureMusicNfc = z;
    }

    public static void setGameAssistVersion(boolean z) {
        gameAssistVersion = z;
    }

    public static void setSipId(String str) {
        sipId = str;
    }

    public static void setUseSipId(boolean z) {
        useSipId = z;
    }

    public static void setWifiDualBandDualConcurrent(boolean z) {
        wifiDualBandDualConcurrent = z;
    }
}
