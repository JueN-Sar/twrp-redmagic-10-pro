package androidx.vectordrawable.graphics.drawable;

import android.animation.TypeEvaluator;
import androidx.annotation.RestrictTo;

@RestrictTo
/* loaded from: classes.dex */
public class ArgbEvaluator implements TypeEvaluator {

    /* renamed from: a, reason: collision with root package name */
    private static final ArgbEvaluator f5650a = new ArgbEvaluator();

    @Override // android.animation.TypeEvaluator
    public Object evaluate(float f2, Object obj, Object obj2) {
        int intValue = ((Integer) obj).intValue();
        float f3 = ((intValue >> 24) & 255) / 255.0f;
        int intValue2 = ((Integer) obj2).intValue();
        float pow = (float) Math.pow(((intValue >> 16) & 255) / 255.0f, 2.2d);
        float pow2 = (float) Math.pow(((intValue >> 8) & 255) / 255.0f, 2.2d);
        float pow3 = (float) Math.pow((intValue & 255) / 255.0f, 2.2d);
        float pow4 = (float) Math.pow(((intValue2 >> 16) & 255) / 255.0f, 2.2d);
        float f4 = f3 + (((((intValue2 >> 24) & 255) / 255.0f) - f3) * f2);
        float pow5 = pow2 + ((((float) Math.pow(((intValue2 >> 8) & 255) / 255.0f, 2.2d)) - pow2) * f2);
        float pow6 = pow3 + (f2 * (((float) Math.pow((intValue2 & 255) / 255.0f, 2.2d)) - pow3));
        float pow7 = ((float) Math.pow(pow + ((pow4 - pow) * f2), 0.45454545454545453d)) * 255.0f;
        float pow8 = ((float) Math.pow(pow5, 0.45454545454545453d)) * 255.0f;
        return Integer.valueOf(Math.round(((float) Math.pow(pow6, 0.45454545454545453d)) * 255.0f) | (Math.round(pow7) << 16) | (Math.round(f4 * 255.0f) << 24) | (Math.round(pow8) << 8));
    }
}
