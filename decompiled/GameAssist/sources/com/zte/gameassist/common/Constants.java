package com.zte.gameassist.common;

import android.net.Uri;
import android.os.Build;
import android.os.SystemProperties;
import java.io.File;

/* loaded from: classes2.dex */
public class Constants {

    /* renamed from: a, reason: collision with root package name */
    public static final Uri f16461a = Uri.parse("content://cn.nubia.gamelauncher.db.AppAddProvider/appadd?notify=false");

    /* renamed from: b, reason: collision with root package name */
    public static final Uri f16462b = Uri.parse("content://cn.nubia.gamelauncher.db.AppAddProvider/shortcut_adds?notify=false");

    /* renamed from: c, reason: collision with root package name */
    public static final Uri f16463c = Uri.parse("content://cn.nubia.gamelauncher.db.AppAddProvider");

    /* renamed from: d, reason: collision with root package name */
    public static final Uri f16464d = Uri.parse("content://cn.zte.chargeseparation.chargeseparationcontentprovider");

    /* renamed from: e, reason: collision with root package name */
    public static final boolean f16465e = SystemProperties.getBoolean("ro.vendor.feature.zte_feature_side_shortcut_key", false);

    /* renamed from: f, reason: collision with root package name */
    public static final String[] f16466f;

    /* renamed from: g, reason: collision with root package name */
    public static final boolean f16467g;

    /* renamed from: h, reason: collision with root package name */
    public static final boolean f16468h;

    /* renamed from: i, reason: collision with root package name */
    public static final boolean f16469i;

    /* renamed from: j, reason: collision with root package name */
    public static final String f16470j;

    /* renamed from: k, reason: collision with root package name */
    public static final String f16471k;

    static {
        int i2 = 0;
        String[] strArr = {"23140000", "23100000"};
        f16466f = strArr;
        String str = Build.SOC_MANUFACTURER;
        f16467g = "QTI".equals(str);
        boolean equals = "Spreadtrum".equals(str);
        f16468h = equals;
        f16469i = "Mediatek".equals(str);
        if (!equals) {
            f16470j = "/sys/class/kgsl/kgsl-3d0/gpuclk";
            f16471k = "/sys/class/kgsl/kgsl-3d0/max_gpuclk";
            return;
        }
        String str2 = strArr[strArr.length - 1];
        while (true) {
            String[] strArr2 = f16466f;
            if (i2 >= strArr2.length) {
                f16470j = String.format("/sys/devices/platform/soc/soc:mm/%s.gpu/devfreq/%s.gpu/cur_freq", str2, str2);
                f16471k = String.format("/sys/devices/platform/soc/soc:mm/%s.gpu/devfreq/%s.gpu/max_freq", str2, str2);
                return;
            } else {
                String str3 = strArr2[i2];
                if (new File(String.format("/sys/devices/platform/soc/soc:mm/%s.gpu/devfreq/%s.gpu/cur_freq", str3, str3)).exists()) {
                    str2 = strArr2[i2];
                }
                i2++;
            }
        }
    }
}
