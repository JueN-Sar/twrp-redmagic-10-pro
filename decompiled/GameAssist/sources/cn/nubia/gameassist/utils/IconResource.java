package cn.nubia.gameassist.utils;

import android.content.ComponentName;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import com.zte.distbus.basetransfer.Constants;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;

/* loaded from: classes.dex */
public class IconResource {

    /* renamed from: h, reason: collision with root package name */
    private static String f7663h;

    /* renamed from: i, reason: collision with root package name */
    private static volatile IconResource f7664i;

    /* renamed from: c, reason: collision with root package name */
    private final Context f7667c;

    /* renamed from: d, reason: collision with root package name */
    private Bitmap f7668d;

    /* renamed from: e, reason: collision with root package name */
    private Bitmap f7669e;

    /* renamed from: g, reason: collision with root package name */
    private volatile Bitmap f7671g;

    /* renamed from: a, reason: collision with root package name */
    private volatile String f7665a = null;

    /* renamed from: b, reason: collision with root package name */
    private int f7666b = Typeface.DEFAULT.hashCode();

    /* renamed from: f, reason: collision with root package name */
    private final Object f7670f = new Object();

    private static class IconFileInfo {

        /* renamed from: a, reason: collision with root package name */
        private String f7672a;

        /* renamed from: b, reason: collision with root package name */
        private ComponentName f7673b;

        IconFileInfo(ComponentName componentName, String str) {
            this.f7672a = str;
            this.f7673b = componentName;
        }

        public String a() {
            return this.f7672a;
        }
    }

    public IconResource(Context context) {
        this.f7667c = context.getApplicationContext();
    }

    private void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable th) {
                Log.w("IconResource", "closeSilently e=" + th);
            }
        }
    }

    private int b(Context context, float f2) {
        return (int) ((f2 * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    private String c() {
        if (!TextUtils.isEmpty(f7663h)) {
            return f7663h;
        }
        Log.d("IconResource", "generateSysIconPath sysPath=/data/resource-cache/cache/icon-cache/icon/icon");
        f7663h = "/data/resource-cache/cache/icon-cache/icon/icon";
        return "/data/resource-cache/cache/icon-cache/icon/icon";
    }

    private byte[] d(ComponentName componentName) {
        InputStream inputStream;
        Throwable th;
        Exception e2;
        String n2 = n(componentName);
        Log.d("IconResource", "getIconBitmapFromFile path=" + n2);
        File file = new File(n2);
        Closeable closeable = null;
        try {
            if (file.exists()) {
                inputStream = new FileInputStream(file);
                try {
                    try {
                        IconFileInfo m2 = m(file.getName(), inputStream);
                        if (m2 != null) {
                            byte[] bytes = m2.a().getBytes(Charset.forName("UTF-8"));
                            a(inputStream);
                            return bytes;
                        }
                        closeable = inputStream;
                    } catch (Exception e3) {
                        e2 = e3;
                        Log.d("IconResource", "getIconBitmapFromFile e=" + e2);
                        a(inputStream);
                        return new byte[0];
                    }
                } catch (Throwable th2) {
                    th = th2;
                    a(inputStream);
                    throw th;
                }
            }
            a(closeable);
        } catch (Exception e4) {
            inputStream = null;
            e2 = e4;
        } catch (Throwable th3) {
            inputStream = null;
            th = th3;
            a(inputStream);
            throw th;
        }
        return new byte[0];
    }

    private Bitmap e(Bitmap bitmap, int i2, int i3, int i4) {
        if (bitmap == null) {
            Log.d("IconResource", "getCalendarIconWithWeek icon is null");
            return null;
        }
        BitmapDrawable bitmapDrawable = new BitmapDrawable(this.f7667c.getResources(), bitmap);
        Context context = this.f7667c;
        int b2 = b(context, i4);
        int b3 = b(context, i2);
        Bitmap createBitmap = Bitmap.createBitmap(bitmapDrawable.getIntrinsicWidth(), bitmapDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        bitmapDrawable.setBounds(0, 0, bitmapDrawable.getIntrinsicWidth(), bitmapDrawable.getIntrinsicHeight());
        bitmapDrawable.draw(canvas);
        TextPaint textPaint = new TextPaint(1);
        textPaint.setAntiAlias(true);
        textPaint.setColor(i3);
        textPaint.setTextSize(b2);
        textPaint.setTypeface(Typeface.DEFAULT);
        Date date = new Date();
        String format = new SimpleDateFormat("EEEE").format(date);
        int measureText = (int) textPaint.measureText(format);
        if (format.length() > 8) {
            format = new SimpleDateFormat("EEE").format(date);
            measureText = (int) textPaint.measureText(format);
        }
        canvas.drawText(format, (bitmapDrawable.getIntrinsicWidth() - measureText) / 2, Math.abs(textPaint.getFontMetricsInt().ascent) + b3, textPaint);
        return createBitmap;
    }

    private Bitmap f(ComponentName componentName) {
        return g(componentName, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x0047 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0048  */
    /* JADX WARN: Type inference failed for: r0v1, types: [boolean] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private android.graphics.Bitmap g(android.content.ComponentName r7, boolean r8) {
        /*
            r6 = this;
            java.lang.String r0 = r6.k(r7)
            java.io.File r1 = new java.io.File
            r1.<init>(r0)
            boolean r0 = r1.exists()
            java.lang.String r2 = "getIconBitmapFromFile e="
            java.lang.String r3 = "IconResource"
            r4 = 0
            if (r0 == 0) goto L44
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L26 java.lang.Exception -> L28
            r0.<init>(r1)     // Catch: java.lang.Throwable -> L26 java.lang.Exception -> L28
            android.graphics.Bitmap r7 = r6.j(r0)     // Catch: java.lang.Throwable -> L21 java.lang.Exception -> L24
            r6.a(r0)
            return r7
        L21:
            r7 = move-exception
            r4 = r0
            goto L40
        L24:
            r1 = move-exception
            goto L2a
        L26:
            r7 = move-exception
            goto L40
        L28:
            r1 = move-exception
            r0 = r4
        L2a:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L21
            r5.<init>()     // Catch: java.lang.Throwable -> L21
            r5.append(r2)     // Catch: java.lang.Throwable -> L21
            r5.append(r1)     // Catch: java.lang.Throwable -> L21
            java.lang.String r1 = r5.toString()     // Catch: java.lang.Throwable -> L21
            android.util.Log.d(r3, r1)     // Catch: java.lang.Throwable -> L21
            r6.a(r0)
            goto L45
        L40:
            r6.a(r4)
            throw r7
        L44:
            r0 = r4
        L45:
            if (r8 != 0) goto L48
            return r4
        L48:
            java.lang.String r7 = r6.l(r7)
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r1 = "getIconBitmapFromFile path="
            r8.append(r1)
            r8.append(r7)
            java.lang.String r8 = r8.toString()
            android.util.Log.d(r3, r8)
            java.io.File r8 = new java.io.File
            r8.<init>(r7)
            boolean r7 = r8.exists()
            if (r7 == 0) goto L9b
            java.io.FileInputStream r7 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L7e java.lang.Exception -> L80
            r7.<init>(r8)     // Catch: java.lang.Throwable -> L7e java.lang.Exception -> L80
            android.graphics.Bitmap r8 = r6.j(r7)     // Catch: java.lang.Throwable -> L78 java.lang.Exception -> L7b
            r6.a(r7)
            return r8
        L78:
            r8 = move-exception
            r0 = r7
            goto L97
        L7b:
            r8 = move-exception
            r0 = r7
            goto L81
        L7e:
            r8 = move-exception
            goto L97
        L80:
            r8 = move-exception
        L81:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L7e
            r7.<init>()     // Catch: java.lang.Throwable -> L7e
            r7.append(r2)     // Catch: java.lang.Throwable -> L7e
            r7.append(r8)     // Catch: java.lang.Throwable -> L7e
            java.lang.String r7 = r7.toString()     // Catch: java.lang.Throwable -> L7e
            android.util.Log.d(r3, r7)     // Catch: java.lang.Throwable -> L7e
            r6.a(r0)
            goto L9b
        L97:
            r6.a(r0)
            throw r8
        L9b:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gameassist.utils.IconResource.g(android.content.ComponentName, boolean):android.graphics.Bitmap");
    }

    public static IconResource h(Context context) {
        if (f7664i == null) {
            synchronized (IconResource.class) {
                try {
                    if (f7664i == null) {
                        f7664i = new IconResource(context);
                    }
                } finally {
                }
            }
        }
        return f7664i;
    }

    private void i() {
        Canvas canvas;
        Bitmap bitmap;
        try {
            this.f7665a = this.f7667c.getResources().getConfiguration().locale.toString();
            r();
            s();
            Bitmap bitmap2 = this.f7668d;
            if (bitmap2 == null || bitmap2.isRecycled() || (bitmap = this.f7669e) == null || bitmap.isRecycled()) {
                canvas = null;
            } else {
                p(this.f7671g);
                synchronized (this.f7670f) {
                    Bitmap bitmap3 = this.f7668d;
                    this.f7671g = bitmap3.copy(bitmap3.getConfig(), true);
                }
                canvas = new Canvas(this.f7671g);
                try {
                    canvas.drawBitmap(this.f7669e, 0.0f, 0.0f, (Paint) null);
                    canvas.save();
                } catch (Throwable th) {
                    th = th;
                    if (canvas != null) {
                        canvas.setBitmap(null);
                    }
                    throw th;
                }
            }
            if (canvas != null) {
                canvas.setBitmap(null);
            }
        } catch (Throwable th2) {
            th = th2;
            canvas = null;
        }
    }

    private Bitmap j(InputStream inputStream) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inMutable = true;
        return BitmapFactory.decodeStream(inputStream, null, options);
    }

    private String k(ComponentName componentName) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(c());
        stringBuffer.append("/");
        stringBuffer.append(componentName.getPackageName().replace(".", "_"));
        if (!"null".equals(componentName.getClassName())) {
            stringBuffer.append("-");
            stringBuffer.append(componentName.getClassName().replace(".", "_"));
        }
        stringBuffer.append(".png");
        return stringBuffer.toString();
    }

    private String l(ComponentName componentName) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(c());
        stringBuffer.append("/");
        stringBuffer.append(componentName.getPackageName().replace(".", "_"));
        stringBuffer.append(".png");
        return stringBuffer.toString();
    }

    private IconFileInfo m(String str, InputStream inputStream) {
        int indexOf;
        String substring = str.substring(5);
        if (substring.length() == 0 || (indexOf = substring.indexOf(".xml")) == -1) {
            return null;
        }
        ComponentName componentName = new ComponentName(substring.replaceAll("_", ".").substring(0, indexOf), "null");
        StringBuffer stringBuffer = new StringBuffer();
        try {
            XmlPullParser newPullParser = Xml.newPullParser();
            newPullParser.setInput(inputStream, "UTF-8");
            for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.next()) {
                if (eventType == 2 && newPullParser.getName().equals(Constants.EXTRA_ITEM)) {
                    String attributeValue = newPullParser.getAttributeValue(null, "key");
                    String attributeValue2 = newPullParser.getAttributeValue(null, "value");
                    stringBuffer.append(attributeValue);
                    stringBuffer.append("-");
                    stringBuffer.append(attributeValue2);
                    stringBuffer.append("-");
                }
            }
            Log.e("IconResource", "parseXmlInfo values=" + ((Object) stringBuffer));
            return new IconFileInfo(componentName, stringBuffer.toString());
        } catch (Exception e2) {
            Log.e("IconResource", "parseXmlInfo e=" + e2);
            return null;
        }
    }

    private String n(ComponentName componentName) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(c());
        stringBuffer.append("/");
        stringBuffer.append(componentName.getPackageName().replace(".", "_"));
        stringBuffer.append(".xml");
        return stringBuffer.toString();
    }

    private void o() {
        if (this.f7671g != null && !this.f7671g.isRecycled()) {
            this.f7671g.recycle();
        }
        this.f7671g = null;
        Bitmap bitmap = this.f7668d;
        if (bitmap != null && !bitmap.isRecycled()) {
            this.f7668d.recycle();
        }
        this.f7668d = null;
        Bitmap bitmap2 = this.f7669e;
        if (bitmap2 != null && !bitmap2.isRecycled()) {
            this.f7669e.recycle();
        }
        this.f7669e = null;
    }

    private void p(Bitmap bitmap) {
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        bitmap.recycle();
    }

    private void q() {
        byte[] d2 = d(new ComponentName("theme.info.dynamic.calendar", "null"));
        if (d2.length == 0) {
            Log.d("IconResource", "refreshCalanderIconWithWeek theme info not found!");
            o();
            return;
        }
        try {
            String[] split = new String(d2, Charset.forName("utf-8")).split("-");
            HashMap hashMap = new HashMap();
            for (int i2 = 0; i2 < split.length - 1; i2 += 2) {
                hashMap.put(split[i2], split[i2 + 1]);
            }
            if (Integer.parseInt((String) hashMap.get("showWeekInfo")) != 1) {
                return;
            }
            int parseFloat = (int) Float.parseFloat((String) hashMap.get("paddingTop"));
            int parseColor = Color.parseColor((String) hashMap.get("textColor"));
            int parseInt = Integer.parseInt((String) hashMap.get("textSize"));
            Log.d("IconResource", "refreshCalanderIconWithWeek paddingTop=" + parseFloat + " textColor=" + parseColor + " textSize=" + parseInt);
            synchronized (this.f7670f) {
                Bitmap e2 = e(this.f7671g, parseFloat, parseColor, parseInt);
                p(this.f7671g);
                this.f7671g = e2;
            }
        } catch (Exception e3) {
            Log.d("IconResource", "refreshCalanderIconWithWeek e=" + e3);
        }
    }

    private void r() {
        p(this.f7668d);
        Bitmap f2 = f(new ComponentName("theme.dynamic.calendar", "null"));
        if (f2 != null) {
            int height = f2.getHeight();
            Log.d("IconResource", "refreshCalendarBg height=" + height + " width=" + f2.getWidth());
            int i2 = height * 31;
            if (f2.getWidth() - (i2 + height) < 0) {
                Log.d("IconResource", "refreshCalendarBg mCalendarBg resource is error");
                p(f2);
            } else {
                this.f7668d = Bitmap.createBitmap(f2, i2, 0, height, height, (Matrix) null, true);
                p(f2);
            }
        }
    }

    private void s() {
        int i2 = Calendar.getInstance().get(5);
        p(this.f7669e);
        Bitmap f2 = f(new ComponentName("theme.dynamic.calendar", "null"));
        if (f2 != null) {
            int height = f2.getHeight();
            int i3 = (i2 - 1) * height;
            if (i3 + height > f2.getWidth()) {
                this.f7669e = f2;
                Log.d("IconResource", "refreshCalendarDay mCalendarBg resource is error");
            } else {
                this.f7669e = Bitmap.createBitmap(f2, i3, 0, height, height, (Matrix) null, true);
                p(f2);
            }
        }
    }

    public Bitmap t(boolean z) {
        boolean z2;
        String locale = this.f7667c.getResources().getConfiguration().locale.toString();
        int i2 = this.f7666b;
        Typeface typeface = Typeface.DEFAULT;
        if (i2 != typeface.hashCode()) {
            this.f7666b = typeface.hashCode();
            z2 = true;
        } else {
            z2 = false;
        }
        boolean z3 = (TextUtils.isEmpty(this.f7665a) || locale.equals(this.f7665a)) ? false : true;
        Log.d("IconResource", "updateCalendarIcon languageChanged=" + z3 + " fontChanged=" + z2 + " forceUpdate=" + z + this.f7671g);
        if (this.f7671g != null && !this.f7671g.isRecycled() && !z && !z3 && !z2) {
            return this.f7671g;
        }
        i();
        q();
        return this.f7671g;
    }
}
