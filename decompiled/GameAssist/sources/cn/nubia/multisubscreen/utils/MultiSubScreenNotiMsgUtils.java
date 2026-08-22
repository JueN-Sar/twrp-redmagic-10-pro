package cn.nubia.multisubscreen.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Base64;
import cn.nubia.multisubscreen.secondary.NotificationMsgData;
import cn.nubia.multisubscreen.secondary.RemoveNotificationMsgData;
import com.zte.gameassist.BaseApplication;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.WechatHelper;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes.dex */
public class MultiSubScreenNotiMsgUtils {

    /* renamed from: a, reason: collision with root package name */
    public static HashMap f8164a = new HashMap();

    /* renamed from: b, reason: collision with root package name */
    public static HashMap f8165b = new HashMap();

    /* renamed from: c, reason: collision with root package name */
    public static HashMap f8166c = new HashMap();

    /* renamed from: d, reason: collision with root package name */
    public static ArrayList f8167d = new ArrayList();

    /* renamed from: e, reason: collision with root package name */
    public static ArrayList f8168e = new ArrayList();

    public static Bitmap a(String str) {
        try {
            byte[] decode = Base64.decode(str, 0);
            return BitmapFactory.decodeByteArray(decode, 0, decode.length);
        } catch (Exception unused) {
            return null;
        }
    }

    public static String b(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
    }

    public static String c(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return b(((BitmapDrawable) drawable).getBitmap());
        }
        Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return b(createBitmap);
    }

    public static Drawable d(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return WechatHelper.i(str) ? WechatHelper.a().c(str, true) : BaseApplication.a().getPackageManager().getApplicationIcon(str);
        } catch (PackageManager.NameNotFoundException unused) {
            GaLog.a("MultiSubScreen_MultiSubScreenNotiMsgUtils", "setIconView getIcon exception and packageName = " + str);
            return null;
        }
    }

    public static String e(String str) {
        PackageManager packageManager = BaseApplication.a().getPackageManager();
        try {
            return packageManager.getApplicationLabel(packageManager.getApplicationInfo(str, 128)).toString();
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static boolean f(Context context, String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            int i2 = context.getPackageManager().getApplicationInfo(str, 0).flags;
            return ((i2 & 1) == 0 && (i2 & 128) == 0) ? false : true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static void g() {
        HashMap hashMap = f8164a;
        if (hashMap != null) {
            hashMap.clear();
        }
        HashMap hashMap2 = f8165b;
        if (hashMap2 != null) {
            Iterator it = hashMap2.values().iterator();
            while (it.hasNext()) {
                ((Bitmap) it.next()).recycle();
            }
            f8165b.clear();
        }
        HashMap hashMap3 = f8166c;
        if (hashMap3 != null) {
            hashMap3.clear();
        }
        ArrayList arrayList = f8167d;
        if (arrayList != null) {
            arrayList.clear();
        }
        ArrayList arrayList2 = f8168e;
        if (arrayList2 != null) {
            arrayList2.clear();
        }
    }

    public static ArrayList h(ArrayList arrayList) {
        GaLog.e("MultiSubScreen_MultiSubScreenNotiMsgUtils", "removeNotiMsgListData updateData list = " + arrayList);
        if (arrayList == null || arrayList.isEmpty()) {
            return f8168e;
        }
        f8168e.clear();
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            RemoveNotificationMsgData removeNotificationMsgData = (RemoveNotificationMsgData) it.next();
            GaLog.e("MultiSubScreen_MultiSubScreenNotiMsgUtils", "NotificationMsgAdapter updateData data = " + removeNotificationMsgData);
            ArrayList arrayList3 = (ArrayList) f8164a.get(removeNotificationMsgData.f8044a);
            GaLog.e("MultiSubScreen_MultiSubScreenNotiMsgUtils", "NotificationMsgAdapter updateData dataList = " + arrayList3);
            if (arrayList3 != null && !arrayList3.isEmpty()) {
                Iterator it2 = arrayList3.iterator();
                while (it2.hasNext()) {
                    NotificationMsgData notificationMsgData = (NotificationMsgData) it2.next();
                    if (notificationMsgData.f8043f == removeNotificationMsgData.f8045b) {
                        arrayList2.add(notificationMsgData);
                    }
                }
                arrayList3.removeAll(arrayList2);
                if (arrayList3.isEmpty()) {
                    f8166c.remove(arrayList3);
                    f8167d.remove(removeNotificationMsgData.f8044a);
                }
            }
        }
        Iterator it3 = f8167d.iterator();
        while (it3.hasNext()) {
            String str = (String) it3.next();
            GaLog.e("MultiSubScreen_MultiSubScreenNotiMsgUtils", "NotificationMsgAdapter updateData pkgName = " + str);
            ArrayList arrayList4 = (ArrayList) f8164a.get(str);
            GaLog.e("MultiSubScreen_MultiSubScreenNotiMsgUtils", "NotificationMsgAdapter updateData dataList = " + arrayList4);
            if (arrayList4 != null && !arrayList4.isEmpty()) {
                f8168e.add(new NotificationMsgData(0, str));
                Iterator it4 = arrayList4.iterator();
                while (it4.hasNext()) {
                    f8168e.add((NotificationMsgData) it4.next());
                }
            }
        }
        return f8168e;
    }

    public static ArrayList i(ArrayList arrayList) {
        GaLog.e("MultiSubScreen_MultiSubScreenNotiMsgUtils", "NotificationMsgAdapter updateData list = " + arrayList);
        if (arrayList == null || arrayList.isEmpty()) {
            return f8168e;
        }
        f8168e.clear();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            NotificationMsgData notificationMsgData = (NotificationMsgData) it.next();
            GaLog.e("MultiSubScreen_MultiSubScreenNotiMsgUtils", "NotificationMsgAdapter updateData data = " + notificationMsgData);
            f8167d.remove(notificationMsgData.f8038a);
            f8167d.add(0, notificationMsgData.f8038a);
            ArrayList arrayList2 = (ArrayList) f8164a.get(notificationMsgData.f8038a);
            GaLog.e("MultiSubScreen_MultiSubScreenNotiMsgUtils", "NotificationMsgAdapter updateData dataList = " + arrayList2);
            if (arrayList2 == null) {
                ArrayList arrayList3 = new ArrayList();
                arrayList3.add(notificationMsgData);
                f8164a.put(notificationMsgData.f8038a, arrayList3);
            } else {
                arrayList2.add(0, notificationMsgData);
            }
        }
        Iterator it2 = f8167d.iterator();
        while (it2.hasNext()) {
            String str = (String) it2.next();
            GaLog.e("MultiSubScreen_MultiSubScreenNotiMsgUtils", "NotificationMsgAdapter updateData pkgName = " + str);
            ArrayList arrayList4 = (ArrayList) f8164a.get(str);
            GaLog.e("MultiSubScreen_MultiSubScreenNotiMsgUtils", "NotificationMsgAdapter updateData dataList = " + arrayList4);
            if (arrayList4 != null && !arrayList4.isEmpty()) {
                f8168e.add(new NotificationMsgData(0, str));
                Iterator it3 = arrayList4.iterator();
                while (it3.hasNext()) {
                    f8168e.add((NotificationMsgData) it3.next());
                }
            }
        }
        return f8168e;
    }
}
