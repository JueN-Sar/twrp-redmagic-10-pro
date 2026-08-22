package com.zte.gameassist.lowsugar.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.icu.text.BreakIterator;
import android.icu.util.ULocale;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.DisplayCutout;
import android.view.WindowManager;
import com.zte.distbus.basetransfer.Constants;
import com.zte.gameassist.common.RotationMgr;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;
import com.zte.shared.wrapper.VirtualHandleWrapper;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class LowSugarUtils {
    public static final List A;
    public static final List B;
    public static final List C;
    public static final List D;
    public static final List E;
    private static final Uri F;
    public static boolean G;
    public static boolean H;
    public static int I;

    /* renamed from: a, reason: collision with root package name */
    private static final Pattern f17006a = Pattern.compile("[\\p{IsHan}\\p{IsHiragana}\\p{IsKatakana}\\p{IsHangul}]");

    /* renamed from: b, reason: collision with root package name */
    private static final Pattern f17007b = Pattern.compile("[\\p{IsThai}\\p{IsLao}\\p{IsKhmer}\\p{IsMyanmar}]");

    /* renamed from: c, reason: collision with root package name */
    private static final Pattern f17008c = Pattern.compile("[\\p{IsThai}]");

    /* renamed from: d, reason: collision with root package name */
    private static final Pattern f17009d = Pattern.compile("[\\p{IsLao}]");

    /* renamed from: e, reason: collision with root package name */
    private static final Pattern f17010e = Pattern.compile("[\\p{IsKhmer}]");

    /* renamed from: f, reason: collision with root package name */
    private static final Pattern f17011f = Pattern.compile("[\\p{IsMyanmar}]");

    /* renamed from: g, reason: collision with root package name */
    public static boolean f17012g = false;

    /* renamed from: h, reason: collision with root package name */
    public static String f17013h = "剩余时间：";

    /* renamed from: i, reason: collision with root package name */
    public static String f17014i = "剩余时间";

    /* renamed from: j, reason: collision with root package name */
    public static String f17015j = "天";

    /* renamed from: k, reason: collision with root package name */
    public static String f17016k = "小时";

    /* renamed from: l, reason: collision with root package name */
    public static String f17017l = "时";

    /* renamed from: m, reason: collision with root package name */
    public static String f17018m = "分钟";

    /* renamed from: n, reason: collision with root package name */
    public static String f17019n = "分";

    /* renamed from: o, reason: collision with root package name */
    public static String f17020o = "未明确活动";

    /* renamed from: p, reason: collision with root package name */
    public static final Uri f17021p = Uri.parse("content://com.zte.aispeaker.contentProvider");

    /* renamed from: q, reason: collision with root package name */
    public static final List f17022q;

    /* renamed from: r, reason: collision with root package name */
    public static final List f17023r;

    /* renamed from: s, reason: collision with root package name */
    public static final List f17024s;
    public static final List t;
    public static final List u;
    public static final List v;
    public static final List w;
    public static final List x;
    public static final List y;
    public static final List z;

    static {
        List singletonList = Collections.singletonList("com.tencent.tmgp.sgame");
        f17022q = singletonList;
        List asList = Arrays.asList("com.levelinfinite.sgameGlobal", "com.levelinfinite.sgameGlobal.midaspay");
        f17023r = asList;
        List asList2 = Arrays.asList("com.tencent.ig");
        f17024s = asList2;
        List asList3 = Arrays.asList("com.miHoYo.Yuanshen", "com.miHoYo.ys.bilibili", "com.miHoYo.cloudgames.sys");
        t = asList3;
        List asList4 = Arrays.asList("com.riotgames.league.wildrift", "com.riotgames.league.wildrifttw");
        u = asList4;
        ArrayList arrayList = new ArrayList(asList3);
        v = arrayList;
        arrayList.addAll(singletonList);
        arrayList.addAll(asList);
        arrayList.addAll(asList4);
        arrayList.addAll(asList2);
        w = new ArrayList(singletonList);
        arrayList.addAll(asList4);
        List singletonList2 = Collections.singletonList("zh");
        x = singletonList2;
        List singletonList3 = Collections.singletonList("ja");
        y = singletonList3;
        List singletonList4 = Collections.singletonList("ko");
        z = singletonList4;
        List asList5 = Arrays.asList("hi", "mr", "ne", "sa", "kok");
        A = asList5;
        List asList6 = Arrays.asList("af", "ca", "cs", "da", "de", "en", "es", "et", "fi", "fil", "fr", "hr", "hu", VirtualHandleWrapper.KEY_ID, "it", "is", "lt", "lv", "ms", "nl", "no", "pl", "pt", "ro", "sk", "sl", "sq", "sr", "sv", "tl", "tr", "vi");
        B = asList6;
        ArrayList arrayList2 = new ArrayList(asList6);
        C = arrayList2;
        arrayList2.addAll(singletonList2);
        arrayList2.addAll(singletonList3);
        arrayList2.addAll(singletonList4);
        arrayList2.addAll(asList5);
        List asList7 = Arrays.asList("zh", "th", "ru");
        D = asList7;
        ArrayList arrayList3 = new ArrayList(arrayList2);
        E = arrayList3;
        arrayList3.addAll(asList7);
        F = Uri.parse("content://com.zte.aiengine.ocr.provider");
        G = false;
        H = false;
    }

    public static Double a(CharSequence charSequence, CharSequence charSequence2) {
        Double valueOf = Double.valueOf(0.0d);
        if (charSequence != null && charSequence2 != null) {
            Set y2 = y(charSequence);
            Set y3 = y(charSequence2);
            if (!y2.isEmpty() && !y3.isEmpty()) {
                new HashSet(y2).addAll(y3);
                new HashSet(y2).retainAll(y3);
                return Double.valueOf((r1.size() * 1.0d) / r0.size());
            }
        }
        return valueOf;
    }

    public static boolean b(Context context) {
        GaLog.b("LowSugarUtils", "checkAiUserLogin");
        return true;
    }

    public static void c(Context context) {
        DisplayCutout displayCutout;
        try {
            displayCutout = ((WindowManager) context.getSystemService("window")).getCurrentWindowMetrics().getWindowInsets().getDisplayCutout();
        } catch (Exception e2) {
            GaLog.e("LowSugarUtils", "acquire display cutout size error for " + e2.getMessage());
            displayCutout = null;
        }
        if (displayCutout != null) {
            I = 0;
            H = true;
            List<Rect> boundingRects = displayCutout.getBoundingRects();
            GaLog.e("LowSugarUtils", "checkDisplayCutout cutoutRect = " + boundingRects + ", isHorizontal = " + RotationMgr.j());
            for (Rect rect : boundingRects) {
                if (RotationMgr.j()) {
                    int i2 = rect.right;
                    if (i2 > I) {
                        I = i2;
                    }
                } else {
                    int i3 = rect.bottom;
                    if (i3 > I) {
                        I = i3;
                    }
                }
            }
            GaLog.e("LowSugarUtils", "checkDisplayCutout sCutoutEndX = " + I);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0149, code lost:
    
        com.zte.gameassist.utils.GaLog.b("LowSugarUtils", "compareTask mean the same task");
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x014e, code lost:
    
        if (r6 == false) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0150, code lost:
    
        com.zte.gameassist.utils.GaLog.b("LowSugarUtils", "the task has done and delete it form db");
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0159, code lost:
    
        if (r12 <= 0) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x015b, code lost:
    
        r20.getContentResolver().delete(com.zte.gameassist.lowsugar.provider.LowSugarColumn.f16922a, "_id=?", new java.lang.String[]{java.lang.Long.toString(r12)});
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x016e, code lost:
    
        r10.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0171, code lost:
    
        return true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean d(com.zte.gameassist.lowsugar.ai.LowSugarPurposeData r19, android.content.Context r20, java.lang.String r21) {
        /*
            Method dump skipped, instructions count: 422
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.zte.gameassist.lowsugar.utils.LowSugarUtils.d(com.zte.gameassist.lowsugar.ai.LowSugarPurposeData, android.content.Context, java.lang.String):boolean");
    }

    private static boolean e(String str) {
        int i2 = 0;
        while (i2 < str.length()) {
            int codePointAt = str.codePointAt(i2);
            int type = Character.getType(codePointAt);
            if (Character.isLetterOrDigit(codePointAt) || type == 6 || type == 8 || type == 7) {
                return true;
            }
            i2 += Character.charCount(codePointAt);
        }
        return false;
    }

    private static boolean f(CharSequence charSequence) {
        return charSequence != null && f17006a.matcher(charSequence).find();
    }

    private static boolean g(CharSequence charSequence) {
        return charSequence != null && f17007b.matcher(charSequence).find();
    }

    private static long h(String str, String str2) {
        try {
            return new SimpleDateFormat(str2).parse(str).getTime();
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1L;
        }
    }

    public static String i(long j2) {
        return new SimpleDateFormat("yyyy/MM/dd").format(new Date(j2));
    }

    public static long j(String str) {
        long j2;
        GaLog.e("LowSugarUtils", "getDeadLineTimeMillis aiString = " + str);
        Matcher matcher = Pattern.compile("((\\d+)(.)(\\d+))").matcher(str);
        ArrayList arrayList = new ArrayList();
        while (matcher.find()) {
            String trim = matcher.group().trim();
            if (trim.contains(".")) {
                GaLog.e("LowSugarUtils", "getDeadLineTimeMillis timeStr = " + trim);
                arrayList.add(trim);
            }
        }
        if (arrayList.isEmpty()) {
            j2 = 0;
        } else {
            String str2 = (String) Collections.max(arrayList);
            GaLog.e("LowSugarUtils", "getDeadLineTimeMillis maxTime = " + str2);
            String[] split = str2.split("\\.");
            if (split.length != 2) {
                GaLog.e("LowSugarUtils", "getDeadLineTimeMillis time is invalid!");
                return -1L;
            }
            int parseInt = Integer.parseInt(split[0]);
            int parseInt2 = Integer.parseInt(split[1]);
            GaLog.e("LowSugarUtils", "getDeadLineTimeMillis month = " + parseInt + ", day = " + parseInt2);
            if (parseInt > 12 || parseInt < 1 || parseInt2 < 1 || parseInt2 > 31) {
                GaLog.e("LowSugarUtils", "getDeadLineTimeMillis time is invalid!");
                return -1L;
            }
            String str3 = Calendar.getInstance().get(1) + "." + str2 + " 23:59";
            GaLog.e("LowSugarUtils", "getDeadLineTimeMillis deadTime = " + str3);
            j2 = h(str3, "yyyy.MM.dd HH:mm");
        }
        GaLog.e("LowSugarUtils", "getDeadLineTimeMillis deadTimeMillis = " + j2);
        return j2;
    }

    public static String k(long j2) {
        Date date = new Date(j2);
        return new SimpleDateFormat("HH").format(date) + "h";
    }

    public static long l(String str) {
        int i2;
        int parseInt;
        GaLog.e("LowSugarUtils", "getRemainingTimeMillis time = " + str);
        if (TextUtils.isEmpty(str) || !str.contains(f17014i)) {
            return 0L;
        }
        Matcher matcher = Pattern.compile("((\\d+)天(\\d+)(时|小时)(\\d+)(分|分钟))|((\\d+)天(\\d+)(时|小时))|((\\d+)天(\\d+)(分|分钟))|((\\d+)(时|小时)(\\d+)(分|分钟))|((\\d+)(时|小时))|((\\d+)(分|分钟))|((\\d+)(天))").matcher(str);
        String trim = matcher.find() ? matcher.group().trim() : null;
        GaLog.e("LowSugarUtils", "getRemainingTimeMillis remaining = " + trim);
        if (TextUtils.isEmpty(trim)) {
            return 0L;
        }
        int indexOf = trim.indexOf(f17015j);
        if (indexOf != -1) {
            i2 = Integer.parseInt(trim.substring(0, indexOf));
            trim = trim.substring(indexOf + 1);
        } else {
            i2 = 0;
        }
        int indexOf2 = trim.indexOf(f17016k);
        if (indexOf2 == -1) {
            int indexOf3 = trim.indexOf(f17017l);
            if (indexOf3 != -1) {
                parseInt = Integer.parseInt(trim.substring(0, indexOf3));
                trim = trim.substring(indexOf3 + 1);
            } else {
                parseInt = 0;
            }
        } else {
            parseInt = Integer.parseInt(trim.substring(0, indexOf2));
            trim = trim.substring(indexOf2 + 2);
        }
        int indexOf4 = trim.indexOf(f17019n);
        int parseInt2 = indexOf4 != -1 ? Integer.parseInt(trim.substring(0, indexOf4)) : 59 - Calendar.getInstance().get(12);
        GaLog.e("LowSugarUtils", "getRemainingTimeMillis day = " + i2 + ", hour = " + parseInt + ", minute = " + parseInt2);
        long j2 = (((long) ((i2 * 24) + parseInt)) * 3600000) + (((long) parseInt2) * 60000);
        StringBuilder sb = new StringBuilder();
        sb.append("getRemainingTimeMillis remainingMillis = ");
        sb.append(j2);
        GaLog.e("LowSugarUtils", sb.toString());
        return j2;
    }

    public static String m(long j2) {
        return new SimpleDateFormat("HH:mm").format(new Date(j2));
    }

    private static boolean n(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        Locale locale = Locale.ROOT;
        String lowerCase = str.toLowerCase(locale);
        String lowerCase2 = str2.toLowerCase(locale);
        if (!f(lowerCase) && !g(lowerCase)) {
            Set y2 = y(lowerCase);
            Set y3 = y(lowerCase2);
            if (y2.isEmpty() || y3.isEmpty()) {
                return false;
            }
            return y3.containsAll(y2);
        }
        String replaceAll = lowerCase.replaceAll("\\s+", "");
        if (replaceAll.isEmpty()) {
            return false;
        }
        for (int i2 = 0; i2 < replaceAll.length(); i2++) {
            if (!lowerCase2.contains(String.valueOf(replaceAll.charAt(i2)))) {
                return false;
            }
        }
        return true;
    }

    public static boolean o(String str) {
        GaLog.e("LowSugarUtils", "isLowSugarEnable pkgName = " + str);
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String language = Locale.getDefault().getLanguage();
        GaLog.e("LowSugarUtils", "isAppEnable language = " + language);
        if (E.contains(language)) {
            return v.contains(str);
        }
        return false;
    }

    public static boolean p(Context context) {
        return true;
    }

    public static boolean q(Context context) {
        if (G) {
            return true;
        }
        String authority = F.getAuthority();
        if (authority == null) {
            G = false;
            return false;
        }
        G = context.getPackageManager().resolveContentProvider(authority, 0) != null;
        GaLog.a("LowSugarUtils", "isZteOcrProviderAvailable sIsZteOcrAvailable = " + G);
        return G;
    }

    public static boolean r(long j2) {
        long currentTimeMillis = System.currentTimeMillis();
        return j2 < currentTimeMillis || j2 - currentTimeMillis < 86400000;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x002f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.os.Bundle s(java.lang.String r2, android.os.Bundle r3, android.content.Context r4) {
        /*
            r0 = 0
            android.content.ContentResolver r4 = r4.getContentResolver()     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L22
            android.net.Uri r1 = com.zte.gameassist.lowsugar.utils.LowSugarUtils.f17021p     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L22
            android.content.ContentProviderClient r4 = r4.acquireUnstableContentProviderClient(r1)     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L22
            if (r4 != 0) goto L13
            if (r4 == 0) goto L12
            r4.close()
        L12:
            return r0
        L13:
            android.os.Bundle r2 = r4.call(r2, r0, r3)     // Catch: java.lang.Throwable -> L1b java.lang.Exception -> L1e
            r4.close()
            return r2
        L1b:
            r2 = move-exception
            r0 = r4
            goto L2d
        L1e:
            r2 = move-exception
            goto L24
        L20:
            r2 = move-exception
            goto L2d
        L22:
            r2 = move-exception
            r4 = r0
        L24:
            r2.printStackTrace()     // Catch: java.lang.Throwable -> L1b
            if (r4 == 0) goto L2c
            r4.close()
        L2c:
            return r0
        L2d:
            if (r0 == 0) goto L32
            r0.close()
        L32:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.zte.gameassist.lowsugar.utils.LowSugarUtils.s(java.lang.String, android.os.Bundle, android.content.Context):android.os.Bundle");
    }

    private static ULocale t(CharSequence charSequence) {
        if (charSequence != null) {
            if (f17008c.matcher(charSequence).find()) {
                return ULocale.forLanguageTag("th");
            }
            if (f17009d.matcher(charSequence).find()) {
                return ULocale.forLanguageTag("lo");
            }
            if (f17010e.matcher(charSequence).find()) {
                return ULocale.forLanguageTag("km");
            }
            if (f17011f.matcher(charSequence).find()) {
                return ULocale.forLanguageTag("my");
            }
        }
        return ULocale.ROOT;
    }

    private static Set u(String str, ULocale uLocale) {
        if (TextUtils.isEmpty(str)) {
            return Collections.emptySet();
        }
        BreakIterator wordInstance = BreakIterator.getWordInstance(uLocale);
        wordInstance.setText(str);
        HashSet hashSet = new HashSet();
        int first = wordInstance.first();
        while (true) {
            int i2 = first;
            first = wordInstance.next();
            if (first == -1) {
                return hashSet;
            }
            if (first > i2) {
                String trim = str.substring(i2, first).trim();
                if (!trim.isEmpty() && e(trim)) {
                    hashSet.add(trim);
                }
            }
        }
    }

    public static void v(String str, Context context) {
        GaLog.b("LowSugarUtils", "sendTextMessageToRedmagickyi text = " + str);
        Bundle bundle = new Bundle();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("ID", "");
            jSONObject.put("output_type", 0);
            jSONObject.put("output", str);
            jSONObject.put("scene_id", "");
            jSONObject.put("show_notification", 1);
            GaLog.b("LowSugarUtils", "sendTextMessageToRedmagickyi jsonObject = " + jSONObject.toString());
            bundle.putString("message", jSONObject.toString());
            GaLog.e("LowSugarUtils", "call redmagickyi sendMessage and result = " + context.getContentResolver().call(Uri.parse("content://cn.nubia.redmagickyi.AigcProvider"), "cn.nubia.gameassist", "sendMessage", bundle).getBoolean(Constants.EXTRA_RESULT));
        } catch (JSONException e2) {
            e2.printStackTrace();
            GaLog.b("LowSugarUtils", "sendTextMessageToRedmagickyi has exception and e = " + e2.toString());
        }
    }

    public static void w(String str, Context context, String str2) {
        CharSequence charSequence = "";
        GaLog.b("LowSugarUtils", "sendTextMessageToRedmagickyi text = " + str + ", pkgName = " + str2);
        Bundle bundle = new Bundle();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("ID", "");
            jSONObject.put("output_type", 0);
            jSONObject.put("output", str);
            jSONObject.put("scene_id", "");
            jSONObject.put("show_notification", 1);
            PackageManager packageManager = context.getPackageManager();
            if (!TextUtils.isEmpty(str2)) {
                try {
                    charSequence = packageManager.getApplicationLabel(packageManager.getApplicationInfo(str2, 0));
                } catch (PackageManager.NameNotFoundException e2) {
                    e2.printStackTrace();
                }
            }
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("action_id", str2);
            jSONObject2.put("title", charSequence);
            jSONArray.put(jSONObject2);
            jSONObject.put("button_action", jSONArray);
            jSONObject.put("name", "RedMagicSetting|game_start_recognition_game_task");
            GaLog.b("LowSugarUtils", "sendTextMessageToRedmagickyi jsonObject = " + jSONObject.toString());
            bundle.putString("message", jSONObject.toString());
            GaLog.e("LowSugarUtils", "call redmagickyi sendMessage and result = " + context.getContentResolver().call(Uri.parse("content://cn.nubia.redmagickyi.AigcProvider"), "cn.nubia.gameassist", "sendMessage", bundle).getBoolean(Constants.EXTRA_RESULT));
        } catch (JSONException e3) {
            e3.printStackTrace();
            GaLog.b("LowSugarUtils", "sendTextMessageToRedmagickyi has exception and e = " + e3.toString());
        }
    }

    private static Set x(String str, int i2) {
        if (TextUtils.isEmpty(str)) {
            return Collections.emptySet();
        }
        int[] iArr = new int[str.length()];
        int i3 = 0;
        int i4 = 0;
        while (i3 < str.length()) {
            int codePointAt = str.codePointAt(i3);
            int type = Character.getType(codePointAt);
            if (Character.isLetterOrDigit(codePointAt) || type == 6 || type == 8 || type == 7) {
                iArr[i4] = codePointAt;
                i4++;
            }
            i3 += Character.charCount(codePointAt);
        }
        if (i4 == 0) {
            return Collections.emptySet();
        }
        HashSet hashSet = new HashSet();
        if (i4 <= i2) {
            hashSet.add(new String(iArr, 0, i4));
            return hashSet;
        }
        for (int i5 = 0; i5 <= i4 - i2; i5++) {
            hashSet.add(new String(iArr, i5, i2));
        }
        return hashSet;
    }

    public static Set y(CharSequence charSequence) {
        if (charSequence == null) {
            return Collections.emptySet();
        }
        String lowerCase = charSequence.toString().toLowerCase(Locale.ROOT);
        if (f(lowerCase)) {
            return x(lowerCase, 1);
        }
        if (g(lowerCase)) {
            Set u2 = u(lowerCase, t(lowerCase));
            return !u2.isEmpty() ? u2 : x(lowerCase, 2);
        }
        String trim = lowerCase.replaceAll("[^\\p{L}\\p{N}\\p{M}\\s]", " ").trim();
        if (trim.isEmpty()) {
            return Collections.emptySet();
        }
        String[] split = trim.split("\\s+");
        HashSet hashSet = new HashSet();
        for (String str : split) {
            if (str != null && !str.isEmpty()) {
                hashSet.add(str);
            }
        }
        return hashSet;
    }

    public static void z(Context context, String str) {
        CharSequence charSequence;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        String t2 = SystemMgr.t();
        PackageManager packageManager = context.getPackageManager();
        try {
            charSequence = packageManager.getApplicationInfo(SystemMgr.A(t2), 0).loadLabel(packageManager);
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            GaLog.a("LowSugarUtils", "trackLowSugarEffect getAppLabel has error and e = " + e2.toString());
            charSequence = "";
        }
        GaLog.a("LowSugarUtils", "trackLowSugarEffect mAppLabel = " + ((Object) charSequence) + ", pkgName = " + t2 + ", mode = " + str);
        Bundle bundle = new Bundle();
        bundle.putCharSequence("app_name", charSequence);
        bundle.putString("package_name", t2);
        bundle.putString("effect_mpde", str);
        NubiaTrackManager.p().x("cn.nubia.gamelauncher", "ai_game_agent_effect", bundle);
    }
}
