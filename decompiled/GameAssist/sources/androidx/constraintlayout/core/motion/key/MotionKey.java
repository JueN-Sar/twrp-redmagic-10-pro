package androidx.constraintlayout.core.motion.key;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.HashMap;

/* loaded from: classes.dex */
public abstract class MotionKey implements TypedValues {

    /* renamed from: f, reason: collision with root package name */
    public static int f1693f = -1;

    /* renamed from: a, reason: collision with root package name */
    public int f1694a;

    /* renamed from: b, reason: collision with root package name */
    int f1695b;

    /* renamed from: c, reason: collision with root package name */
    String f1696c;

    /* renamed from: d, reason: collision with root package name */
    public int f1697d;

    /* renamed from: e, reason: collision with root package name */
    public HashMap f1698e;

    public MotionKey() {
        int i2 = f1693f;
        this.f1694a = i2;
        this.f1695b = i2;
        this.f1696c = null;
    }

    @Override // 
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public abstract MotionKey clone();

    public MotionKey b(MotionKey motionKey) {
        this.f1694a = motionKey.f1694a;
        this.f1695b = motionKey.f1695b;
        this.f1696c = motionKey.f1696c;
        this.f1697d = motionKey.f1697d;
        return this;
    }
}
