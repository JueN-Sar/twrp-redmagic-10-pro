package com.zte.gameassist.lowsugar.ai;

import android.graphics.Point;
import cn.nubia.gameassist.view.NubiaTextClock;

/* loaded from: classes2.dex */
public class LowSugarOcrData {

    /* renamed from: a, reason: collision with root package name */
    public String f16746a;

    /* renamed from: b, reason: collision with root package name */
    public BoxPoint f16747b = new BoxPoint();

    public static class BoxPoint {

        /* renamed from: a, reason: collision with root package name */
        public Point f16748a;

        /* renamed from: b, reason: collision with root package name */
        public Point f16749b;

        /* renamed from: c, reason: collision with root package name */
        public Point f16750c;

        /* renamed from: d, reason: collision with root package name */
        public Point f16751d;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            BoxPoint boxPoint = (BoxPoint) obj;
            Point point = this.f16748a;
            if (point != null ? point.equals(boxPoint.f16748a) : boxPoint.f16748a == null) {
                Point point2 = this.f16749b;
                if (point2 != null ? point2.equals(boxPoint.f16749b) : boxPoint.f16749b == null) {
                    Point point3 = this.f16750c;
                    if (point3 != null ? point3.equals(boxPoint.f16750c) : boxPoint.f16750c == null) {
                        Point point4 = this.f16751d;
                        if (point4 == null) {
                            if (boxPoint.f16751d == null) {
                                return true;
                            }
                        } else if (point4.equals(boxPoint.f16751d)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LowSugarOcrData lowSugarOcrData = (LowSugarOcrData) obj;
        String str = this.f16746a;
        if (str != null ? str.equals(lowSugarOcrData.f16746a) : lowSugarOcrData.f16746a == null) {
            BoxPoint boxPoint = this.f16747b;
            if (boxPoint == null) {
                if (lowSugarOcrData.f16747b == null) {
                    return true;
                }
            } else if (boxPoint.equals(lowSugarOcrData.f16747b)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        String str;
        String str2;
        String str3;
        StringBuilder sb = new StringBuilder();
        sb.append("LowSugarOcrData{text='");
        sb.append(this.f16746a);
        sb.append(NubiaTextClock.QUOTE);
        sb.append(", boxPoint={startTop=");
        String str4 = "null";
        if (this.f16747b.f16748a != null) {
            str = "(" + this.f16747b.f16748a.x + "," + this.f16747b.f16748a.y + ")";
        } else {
            str = "null";
        }
        sb.append(str);
        sb.append(", endTop=");
        if (this.f16747b.f16749b != null) {
            str2 = "(" + this.f16747b.f16749b.x + "," + this.f16747b.f16749b.y + ")";
        } else {
            str2 = "null";
        }
        sb.append(str2);
        sb.append(", startBottom=");
        if (this.f16747b.f16750c != null) {
            str3 = "(" + this.f16747b.f16750c.x + "," + this.f16747b.f16750c.y + ")";
        } else {
            str3 = "null";
        }
        sb.append(str3);
        sb.append(", endBottom=");
        if (this.f16747b.f16751d != null) {
            str4 = "(" + this.f16747b.f16751d.x + "," + this.f16747b.f16751d.y + ")";
        }
        sb.append(str4);
        sb.append("}");
        sb.append('}');
        return sb.toString();
    }
}
