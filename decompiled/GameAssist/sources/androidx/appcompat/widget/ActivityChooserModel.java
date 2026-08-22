package androidx.appcompat.widget;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.database.DataSetObservable;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes.dex */
class ActivityChooserModel extends DataSetObservable {

    /* renamed from: n, reason: collision with root package name */
    static final String f664n = "ActivityChooserModel";

    /* renamed from: o, reason: collision with root package name */
    private static final Object f665o = new Object();

    /* renamed from: p, reason: collision with root package name */
    private static final Map f666p = new HashMap();

    /* renamed from: d, reason: collision with root package name */
    final Context f670d;

    /* renamed from: e, reason: collision with root package name */
    final String f671e;

    /* renamed from: f, reason: collision with root package name */
    private Intent f672f;

    /* renamed from: m, reason: collision with root package name */
    private OnChooseActivityListener f679m;

    /* renamed from: a, reason: collision with root package name */
    private final Object f667a = new Object();

    /* renamed from: b, reason: collision with root package name */
    private final List f668b = new ArrayList();

    /* renamed from: c, reason: collision with root package name */
    private final List f669c = new ArrayList();

    /* renamed from: g, reason: collision with root package name */
    private ActivitySorter f673g = new DefaultSorter();

    /* renamed from: h, reason: collision with root package name */
    private int f674h = 50;

    /* renamed from: i, reason: collision with root package name */
    boolean f675i = true;

    /* renamed from: j, reason: collision with root package name */
    private boolean f676j = false;

    /* renamed from: k, reason: collision with root package name */
    private boolean f677k = true;

    /* renamed from: l, reason: collision with root package name */
    private boolean f678l = false;

    public interface ActivityChooserModelClient {
    }

    public static final class ActivityResolveInfo implements Comparable<ActivityResolveInfo> {

        /* renamed from: c, reason: collision with root package name */
        public final ResolveInfo f680c;

        /* renamed from: h, reason: collision with root package name */
        public float f681h;

        public ActivityResolveInfo(ResolveInfo resolveInfo) {
            this.f680c = resolveInfo;
        }

        @Override // java.lang.Comparable
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public int compareTo(ActivityResolveInfo activityResolveInfo) {
            return Float.floatToIntBits(activityResolveInfo.f681h) - Float.floatToIntBits(this.f681h);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && ActivityResolveInfo.class == obj.getClass() && Float.floatToIntBits(this.f681h) == Float.floatToIntBits(((ActivityResolveInfo) obj).f681h);
        }

        public int hashCode() {
            return Float.floatToIntBits(this.f681h) + 31;
        }

        public String toString() {
            return "[resolveInfo:" + this.f680c.toString() + "; weight:" + new BigDecimal(this.f681h) + "]";
        }
    }

    public interface ActivitySorter {
        void a(Intent intent, List list, List list2);
    }

    private static final class DefaultSorter implements ActivitySorter {

        /* renamed from: a, reason: collision with root package name */
        private final Map f682a = new HashMap();

        DefaultSorter() {
        }

        @Override // androidx.appcompat.widget.ActivityChooserModel.ActivitySorter
        public void a(Intent intent, List list, List list2) {
            Map map = this.f682a;
            map.clear();
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                ActivityResolveInfo activityResolveInfo = (ActivityResolveInfo) list.get(i2);
                activityResolveInfo.f681h = 0.0f;
                ActivityInfo activityInfo = activityResolveInfo.f680c.activityInfo;
                map.put(new ComponentName(activityInfo.packageName, activityInfo.name), activityResolveInfo);
            }
            float f2 = 1.0f;
            for (int size2 = list2.size() - 1; size2 >= 0; size2--) {
                HistoricalRecord historicalRecord = (HistoricalRecord) list2.get(size2);
                ActivityResolveInfo activityResolveInfo2 = (ActivityResolveInfo) map.get(historicalRecord.f683a);
                if (activityResolveInfo2 != null) {
                    activityResolveInfo2.f681h += historicalRecord.f685c * f2;
                    f2 *= 0.95f;
                }
            }
            Collections.sort(list);
        }
    }

    public static final class HistoricalRecord {

        /* renamed from: a, reason: collision with root package name */
        public final ComponentName f683a;

        /* renamed from: b, reason: collision with root package name */
        public final long f684b;

        /* renamed from: c, reason: collision with root package name */
        public final float f685c;

        public HistoricalRecord(String str, long j2, float f2) {
            this(ComponentName.unflattenFromString(str), j2, f2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || HistoricalRecord.class != obj.getClass()) {
                return false;
            }
            HistoricalRecord historicalRecord = (HistoricalRecord) obj;
            ComponentName componentName = this.f683a;
            if (componentName == null) {
                if (historicalRecord.f683a != null) {
                    return false;
                }
            } else if (!componentName.equals(historicalRecord.f683a)) {
                return false;
            }
            return this.f684b == historicalRecord.f684b && Float.floatToIntBits(this.f685c) == Float.floatToIntBits(historicalRecord.f685c);
        }

        public int hashCode() {
            ComponentName componentName = this.f683a;
            int hashCode = componentName == null ? 0 : componentName.hashCode();
            long j2 = this.f684b;
            return ((((hashCode + 31) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + Float.floatToIntBits(this.f685c);
        }

        public String toString() {
            return "[; activity:" + this.f683a + "; time:" + this.f684b + "; weight:" + new BigDecimal(this.f685c) + "]";
        }

        public HistoricalRecord(ComponentName componentName, long j2, float f2) {
            this.f683a = componentName;
            this.f684b = j2;
            this.f685c = f2;
        }
    }

    public interface OnChooseActivityListener {
        boolean a(ActivityChooserModel activityChooserModel, Intent intent);
    }

    private final class PersistHistoryAsyncTask extends AsyncTask<Object, Void, Void> {
        PersistHistoryAsyncTask() {
        }

        /* JADX WARN: Code restructure failed: missing block: B:12:0x0074, code lost:
        
            if (r15 != null) goto L44;
         */
        /* JADX WARN: Code restructure failed: missing block: B:16:0x0076, code lost:
        
            r15.close();
         */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x0096, code lost:
        
            if (r15 == null) goto L33;
         */
        /* JADX WARN: Code restructure failed: missing block: B:38:0x00b5, code lost:
        
            if (r15 == null) goto L33;
         */
        /* JADX WARN: Code restructure failed: missing block: B:43:0x00d4, code lost:
        
            if (r15 == null) goto L33;
         */
        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public java.lang.Void doInBackground(java.lang.Object... r15) {
            /*
                Method dump skipped, instructions count: 248
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ActivityChooserModel.PersistHistoryAsyncTask.doInBackground(java.lang.Object[]):java.lang.Void");
        }
    }

    private ActivityChooserModel(Context context, String str) {
        this.f670d = context.getApplicationContext();
        if (TextUtils.isEmpty(str) || str.endsWith(".xml")) {
            this.f671e = str;
            return;
        }
        this.f671e = str + ".xml";
    }

    private boolean a(HistoricalRecord historicalRecord) {
        boolean add = this.f669c.add(historicalRecord);
        if (add) {
            this.f677k = true;
            l();
            k();
            p();
            notifyChanged();
        }
        return add;
    }

    private void c() {
        boolean j2 = j() | m();
        l();
        if (j2) {
            p();
            notifyChanged();
        }
    }

    public static ActivityChooserModel d(Context context, String str) {
        ActivityChooserModel activityChooserModel;
        synchronized (f665o) {
            try {
                Map map = f666p;
                activityChooserModel = (ActivityChooserModel) map.get(str);
                if (activityChooserModel == null) {
                    activityChooserModel = new ActivityChooserModel(context, str);
                    map.put(str, activityChooserModel);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return activityChooserModel;
    }

    private boolean j() {
        if (!this.f678l || this.f672f == null) {
            return false;
        }
        this.f678l = false;
        this.f668b.clear();
        List<ResolveInfo> queryIntentActivities = this.f670d.getPackageManager().queryIntentActivities(this.f672f, 0);
        int size = queryIntentActivities.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.f668b.add(new ActivityResolveInfo(queryIntentActivities.get(i2)));
        }
        return true;
    }

    private void k() {
        if (!this.f676j) {
            throw new IllegalStateException("No preceding call to #readHistoricalData");
        }
        if (this.f677k) {
            this.f677k = false;
            if (TextUtils.isEmpty(this.f671e)) {
                return;
            }
            new PersistHistoryAsyncTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new ArrayList(this.f669c), this.f671e);
        }
    }

    private void l() {
        int size = this.f669c.size() - this.f674h;
        if (size <= 0) {
            return;
        }
        this.f677k = true;
        for (int i2 = 0; i2 < size; i2++) {
        }
    }

    private boolean m() {
        if (!this.f675i || !this.f677k || TextUtils.isEmpty(this.f671e)) {
            return false;
        }
        this.f675i = false;
        this.f676j = true;
        n();
        return true;
    }

    private void n() {
        FileInputStream openFileInput;
        XmlPullParser newPullParser;
        try {
            try {
                openFileInput = this.f670d.openFileInput(this.f671e);
                try {
                    newPullParser = Xml.newPullParser();
                    newPullParser.setInput(openFileInput, "UTF-8");
                    for (int i2 = 0; i2 != 1 && i2 != 2; i2 = newPullParser.next()) {
                    }
                } catch (IOException e2) {
                    Log.e(f664n, "Error reading historical recrod file: " + this.f671e, e2);
                    if (openFileInput == null) {
                        return;
                    }
                } catch (XmlPullParserException e3) {
                    Log.e(f664n, "Error reading historical recrod file: " + this.f671e, e3);
                    if (openFileInput == null) {
                        return;
                    }
                }
                if (!"historical-records".equals(newPullParser.getName())) {
                    throw new XmlPullParserException("Share records file does not start with historical-records tag.");
                }
                List list = this.f669c;
                list.clear();
                while (true) {
                    int next = newPullParser.next();
                    if (next == 1) {
                        if (openFileInput == null) {
                            return;
                        }
                    } else if (next != 3 && next != 4) {
                        if (!"historical-record".equals(newPullParser.getName())) {
                            throw new XmlPullParserException("Share records file not well-formed.");
                        }
                        list.add(new HistoricalRecord(newPullParser.getAttributeValue(null, "activity"), Long.parseLong(newPullParser.getAttributeValue(null, "time")), Float.parseFloat(newPullParser.getAttributeValue(null, "weight"))));
                    }
                }
                try {
                    openFileInput.close();
                } catch (IOException unused) {
                }
            } catch (FileNotFoundException unused2) {
            }
        } catch (Throwable th) {
            if (openFileInput != null) {
                try {
                    openFileInput.close();
                } catch (IOException unused3) {
                }
            }
            throw th;
        }
    }

    private boolean p() {
        if (this.f673g == null || this.f672f == null || this.f668b.isEmpty() || this.f669c.isEmpty()) {
            return false;
        }
        this.f673g.a(this.f672f, this.f668b, Collections.unmodifiableList(this.f669c));
        return true;
    }

    public Intent b(int i2) {
        synchronized (this.f667a) {
            try {
                if (this.f672f == null) {
                    return null;
                }
                c();
                ActivityInfo activityInfo = ((ActivityResolveInfo) this.f668b.get(i2)).f680c.activityInfo;
                ComponentName componentName = new ComponentName(activityInfo.packageName, activityInfo.name);
                Intent intent = new Intent(this.f672f);
                intent.setComponent(componentName);
                if (this.f679m != null) {
                    if (this.f679m.a(this, new Intent(intent))) {
                        return null;
                    }
                }
                a(new HistoricalRecord(componentName, System.currentTimeMillis(), 1.0f));
                return intent;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public ResolveInfo e(int i2) {
        ResolveInfo resolveInfo;
        synchronized (this.f667a) {
            c();
            resolveInfo = ((ActivityResolveInfo) this.f668b.get(i2)).f680c;
        }
        return resolveInfo;
    }

    public int f() {
        int size;
        synchronized (this.f667a) {
            c();
            size = this.f668b.size();
        }
        return size;
    }

    public int g(ResolveInfo resolveInfo) {
        synchronized (this.f667a) {
            try {
                c();
                List list = this.f668b;
                int size = list.size();
                for (int i2 = 0; i2 < size; i2++) {
                    if (((ActivityResolveInfo) list.get(i2)).f680c == resolveInfo) {
                        return i2;
                    }
                }
                return -1;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public ResolveInfo h() {
        synchronized (this.f667a) {
            try {
                c();
                if (this.f668b.isEmpty()) {
                    return null;
                }
                return ((ActivityResolveInfo) this.f668b.get(0)).f680c;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public int i() {
        int size;
        synchronized (this.f667a) {
            c();
            size = this.f669c.size();
        }
        return size;
    }

    public void o(int i2) {
        synchronized (this.f667a) {
            try {
                c();
                ActivityResolveInfo activityResolveInfo = (ActivityResolveInfo) this.f668b.get(i2);
                ActivityResolveInfo activityResolveInfo2 = (ActivityResolveInfo) this.f668b.get(0);
                float f2 = activityResolveInfo2 != null ? (activityResolveInfo2.f681h - activityResolveInfo.f681h) + 5.0f : 1.0f;
                ActivityInfo activityInfo = activityResolveInfo.f680c.activityInfo;
                a(new HistoricalRecord(new ComponentName(activityInfo.packageName, activityInfo.name), System.currentTimeMillis(), f2));
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
