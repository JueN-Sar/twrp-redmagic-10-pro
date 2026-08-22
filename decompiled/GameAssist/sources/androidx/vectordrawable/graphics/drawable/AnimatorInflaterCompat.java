package androidx.vectordrawable.graphics.drawable;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.TypeEvaluator;
import android.content.Context;
import androidx.annotation.RestrictTo;
import androidx.core.graphics.PathParser;

@RestrictTo
/* loaded from: classes.dex */
public class AnimatorInflaterCompat {

    private static class PathDataEvaluator implements TypeEvaluator<PathParser.PathDataNode[]> {

        /* renamed from: a, reason: collision with root package name */
        private PathParser.PathDataNode[] f5649a;

        @Override // android.animation.TypeEvaluator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public PathParser.PathDataNode[] evaluate(float f2, PathParser.PathDataNode[] pathDataNodeArr, PathParser.PathDataNode[] pathDataNodeArr2) {
            if (!PathParser.b(pathDataNodeArr, pathDataNodeArr2)) {
                throw new IllegalArgumentException("Can't interpolate between two incompatible pathData");
            }
            if (!PathParser.b(this.f5649a, pathDataNodeArr)) {
                this.f5649a = PathParser.f(pathDataNodeArr);
            }
            for (int i2 = 0; i2 < pathDataNodeArr.length; i2++) {
                this.f5649a[i2].h(pathDataNodeArr[i2], pathDataNodeArr2[i2], f2);
            }
            return this.f5649a;
        }
    }

    public static Animator a(Context context, int i2) {
        return AnimatorInflater.loadAnimator(context, i2);
    }
}
