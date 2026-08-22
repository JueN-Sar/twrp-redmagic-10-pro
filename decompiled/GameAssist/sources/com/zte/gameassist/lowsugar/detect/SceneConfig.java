package com.zte.gameassist.lowsugar.detect;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Size;
import android.util.Xml;
import cn.nubia.gameassist.view.NubiaTextClock;
import cn.nubia.yolox.YOLOXncnn;
import com.zte.gameassist.common.ThreadManager;
import com.zte.gameassist.lowsugar.R;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes2.dex */
public class SceneConfig {

    /* renamed from: b, reason: collision with root package name */
    private static volatile SceneConfig f16814b;

    /* renamed from: a, reason: collision with root package name */
    private final Map f16815a = new ConcurrentHashMap();

    public static class GameFrameScene {

        /* renamed from: a, reason: collision with root package name */
        public final int f16816a;

        /* renamed from: b, reason: collision with root package name */
        public final String f16817b;

        /* renamed from: c, reason: collision with root package name */
        private final Map f16818c = new ArrayMap();

        public static class Label {

            /* renamed from: a, reason: collision with root package name */
            public final String f16819a;

            /* renamed from: b, reason: collision with root package name */
            public final float f16820b;

            /* renamed from: c, reason: collision with root package name */
            public final float f16821c;

            /* renamed from: d, reason: collision with root package name */
            public final float f16822d;

            /* renamed from: e, reason: collision with root package name */
            public final float f16823e;

            /* renamed from: f, reason: collision with root package name */
            public final float f16824f;

            /* renamed from: g, reason: collision with root package name */
            public final float f16825g;

            /* renamed from: h, reason: collision with root package name */
            public final float f16826h;

            /* renamed from: i, reason: collision with root package name */
            public final float f16827i;

            public Label(String str, String str2, String str3, String str4, String str5) {
                this.f16819a = str;
                float[] d2 = d(str2);
                this.f16820b = d2[0];
                this.f16821c = d2[1];
                float[] d3 = d(str3);
                this.f16822d = d3[0];
                this.f16823e = d3[1];
                float[] d4 = d(str4);
                this.f16824f = d4[0];
                this.f16825g = d4[1];
                float[] d5 = d(str5);
                this.f16826h = d5[0];
                this.f16827i = d5[1];
            }

            private float c(String str, float f2) {
                if (str == null) {
                    return f2;
                }
                try {
                    return Float.valueOf(str).floatValue();
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return f2;
                }
            }

            private float[] d(String str) {
                if (!str.contains(":")) {
                    return new float[]{0.0f, c(str.trim(), 1.0f)};
                }
                String[] split = str.trim().split(":");
                return new float[]{c(TextUtils.isEmpty(split[0]) ? "0" : split[0].trim(), 0.0f), c((split.length == 1 || TextUtils.isEmpty(split[1])) ? "1" : split[1].trim(), 1.0f)};
            }

            public boolean a(float f2, float f3, float f4, float f5) {
                return this.f16821c >= f2 && f2 >= this.f16820b && this.f16823e >= f3 && f3 >= this.f16822d && this.f16825g >= f4 && f4 >= this.f16824f && this.f16827i >= f5 && f5 >= this.f16826h;
            }

            public boolean b(YOLOLabel yOLOLabel) {
                return TextUtils.equals(yOLOLabel.f16828a, this.f16819a) && a(yOLOLabel.f16829b, yOLOLabel.f16830c, yOLOLabel.f16831d, yOLOLabel.f16832e);
            }

            public String toString() {
                return "Label{name='" + this.f16819a + NubiaTextClock.QUOTE + ", minW=" + this.f16820b + ", maxW=" + this.f16821c + ", minH=" + this.f16822d + ", maxH=" + this.f16823e + ", minX=" + this.f16824f + ", maxX=" + this.f16825g + ", minY=" + this.f16826h + ", maxY=" + this.f16827i + '}';
            }
        }

        public static class YOLOLabel {

            /* renamed from: a, reason: collision with root package name */
            public final String f16828a;

            /* renamed from: b, reason: collision with root package name */
            public final float f16829b;

            /* renamed from: c, reason: collision with root package name */
            public final float f16830c;

            /* renamed from: d, reason: collision with root package name */
            public final float f16831d;

            /* renamed from: e, reason: collision with root package name */
            public final float f16832e;

            /* renamed from: f, reason: collision with root package name */
            public final YOLOXncnn.Obj f16833f;

            /* renamed from: g, reason: collision with root package name */
            public final Size f16834g;

            public YOLOLabel(YOLOXncnn.Obj obj, Size size) {
                float height = obj.w / size.getHeight();
                float height2 = obj.f9233h / size.getHeight();
                float width = obj.x / size.getWidth();
                float height3 = obj.y / size.getHeight();
                this.f16828a = obj.label;
                this.f16833f = obj;
                this.f16834g = size;
                this.f16829b = height;
                this.f16830c = height2;
                this.f16831d = width;
                this.f16832e = height3;
            }

            public String toString() {
                return "YOLOLabel{name='" + this.f16828a + NubiaTextClock.QUOTE + ", w=" + this.f16829b + ", h=" + this.f16830c + ", x=" + this.f16831d + ", y=" + this.f16832e + ", obj=" + this.f16833f + '}';
            }
        }

        public GameFrameScene(int i2, String str) {
            this.f16816a = i2;
            this.f16817b = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b(Label label) {
            synchronized (this.f16818c) {
                this.f16818c.put(label.f16819a, label);
            }
        }

        public boolean c(YOLOLabel yOLOLabel) {
            Label d2 = d(yOLOLabel.f16828a);
            if (d2 == null) {
                return false;
            }
            return d2.b(yOLOLabel);
        }

        public Label d(String str) {
            Label label;
            synchronized (this.f16818c) {
                label = (Label) this.f16818c.get(str);
            }
            return label;
        }

        public String toString() {
            return "GameFrameScene{mSceneIndex=" + this.f16816a + ", mSceneName='" + this.f16817b + NubiaTextClock.QUOTE + ", mLabels=" + this.f16818c + '}';
        }
    }

    public static SceneConfig c() {
        if (f16814b == null) {
            synchronized (SceneConfig.class) {
                try {
                    if (f16814b == null) {
                        f16814b = new SceneConfig();
                    }
                } finally {
                }
            }
        }
        return f16814b;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void e(Context context) {
        try {
            g(context.getResources().openRawResource(R.raw.low_sugar_scene_config));
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0090 A[Catch: IOException -> 0x0033, TryCatch #0 {IOException -> 0x0033, blocks: (B:9:0x001b, B:14:0x002b, B:15:0x00a4, B:24:0x0036, B:34:0x006a, B:35:0x0090, B:36:0x004e, B:39:0x0056), top: B:8:0x001b }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void f(org.xmlpull.v1.XmlPullParser r18) {
        /*
            r17 = this;
            r1 = r18
            int r0 = r18.getEventType()
            java.util.concurrent.ConcurrentHashMap r2 = new java.util.concurrent.ConcurrentHashMap
            r2.<init>()
            r3 = 0
            r4 = r0
            r5 = r3
        Le:
            r0 = 1
            if (r4 == r0) goto Laf
            r6 = 2
            java.lang.String r7 = "scene_config"
            if (r4 == r6) goto L36
            r0 = 3
            if (r4 == r0) goto L1b
            goto La4
        L1b:
            java.lang.String r0 = r18.getName()     // Catch: java.io.IOException -> L33
            java.lang.String r0 = r0.trim()     // Catch: java.io.IOException -> L33
            boolean r0 = r7.equals(r0)     // Catch: java.io.IOException -> L33
            if (r0 == 0) goto La4
            if (r5 == 0) goto La4
            java.lang.String r0 = r5.f16817b     // Catch: java.io.IOException -> L33
            r2.put(r0, r5)     // Catch: java.io.IOException -> L33
            r5 = r3
            goto La4
        L33:
            r0 = move-exception
            goto Laa
        L36:
            java.lang.String r6 = r18.getName()     // Catch: java.io.IOException -> L33
            java.lang.String r6 = r6.trim()     // Catch: java.io.IOException -> L33
            int r8 = r6.hashCode()     // Catch: java.io.IOException -> L33
            r9 = 102727412(0x61f7ef4, float:2.9997847E-35)
            r10 = 0
            if (r8 == r9) goto L56
            r9 = 1814426197(0x6c25f255, float:8.024686E26)
            if (r8 == r9) goto L4e
            goto L60
        L4e:
            boolean r6 = r6.equals(r7)     // Catch: java.io.IOException -> L33
            if (r6 == 0) goto L60
            r6 = r10
            goto L61
        L56:
            java.lang.String r7 = "label"
            boolean r6 = r6.equals(r7)     // Catch: java.io.IOException -> L33
            if (r6 == 0) goto L60
            r6 = r0
            goto L61
        L60:
            r6 = -1
        L61:
            java.lang.String r7 = "name"
            if (r6 == 0) goto L90
            if (r6 == r0) goto L68
            goto La4
        L68:
            if (r5 == 0) goto La4
            java.lang.String r12 = r1.getAttributeValue(r3, r7)     // Catch: java.io.IOException -> L33
            java.lang.String r0 = "w"
            java.lang.String r13 = r1.getAttributeValue(r3, r0)     // Catch: java.io.IOException -> L33
            java.lang.String r0 = "h"
            java.lang.String r14 = r1.getAttributeValue(r3, r0)     // Catch: java.io.IOException -> L33
            java.lang.String r0 = "x"
            java.lang.String r15 = r1.getAttributeValue(r3, r0)     // Catch: java.io.IOException -> L33
            java.lang.String r0 = "y"
            java.lang.String r16 = r1.getAttributeValue(r3, r0)     // Catch: java.io.IOException -> L33
            com.zte.gameassist.lowsugar.detect.SceneConfig$GameFrameScene$Label r0 = new com.zte.gameassist.lowsugar.detect.SceneConfig$GameFrameScene$Label     // Catch: java.io.IOException -> L33
            r11 = r0
            r11.<init>(r12, r13, r14, r15, r16)     // Catch: java.io.IOException -> L33
            com.zte.gameassist.lowsugar.detect.SceneConfig.GameFrameScene.a(r5, r0)     // Catch: java.io.IOException -> L33
            goto La4
        L90:
            java.lang.String r0 = r1.getAttributeValue(r3, r7)     // Catch: java.io.IOException -> L33
            java.lang.String r6 = "scene_index"
            java.lang.String r6 = r1.getAttributeValue(r3, r6)     // Catch: java.io.IOException -> L33
            com.zte.gameassist.lowsugar.detect.SceneConfig$GameFrameScene r7 = new com.zte.gameassist.lowsugar.detect.SceneConfig$GameFrameScene     // Catch: java.io.IOException -> L33
            int r6 = i(r6, r10)     // Catch: java.io.IOException -> L33
            r7.<init>(r6, r0)     // Catch: java.io.IOException -> L33
            r5 = r7
        La4:
            int r4 = r18.next()     // Catch: java.io.IOException -> L33
            goto Le
        Laa:
            r0.printStackTrace()
            goto Le
        Laf:
            r4 = r17
            java.util.Map r0 = r4.f16815a
            r0.putAll(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.zte.gameassist.lowsugar.detect.SceneConfig.f(org.xmlpull.v1.XmlPullParser):void");
    }

    private void g(InputStream inputStream) {
        XmlPullParser newPullParser = Xml.newPullParser();
        try {
            newPullParser.setInput(inputStream, "UTF-8");
            f(newPullParser);
            inputStream.close();
        } catch (XmlPullParserException e2) {
            e2.printStackTrace();
        }
    }

    public static List h(YOLOXncnn.Obj[] objArr, Size size) {
        ArrayList arrayList = new ArrayList();
        for (YOLOXncnn.Obj obj : objArr) {
            arrayList.add(new GameFrameScene.YOLOLabel(obj, size));
        }
        return arrayList;
    }

    private static int i(String str, int i2) {
        try {
            return Integer.valueOf(str).intValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return i2;
        }
    }

    public GameFrameScene b(String str) {
        return (GameFrameScene) this.f16815a.get(str);
    }

    public void d(final Context context) {
        new Handler(ThreadManager.c().a()).post(new Runnable() { // from class: com.zte.gameassist.lowsugar.detect.e
            @Override // java.lang.Runnable
            public final void run() {
                SceneConfig.this.e(context);
            }
        });
    }

    public String toString() {
        return "SceneConfig{mGameFrameSceneMap=" + this.f16815a + '}';
    }
}
