package com.google.android.material.color.utilities;

import androidx.annotation.RestrictTo;
import java.util.Comparator;

@RestrictTo
/* loaded from: classes.dex */
public final class Score {

    private static class ScoredComparator implements Comparator<ScoredHCT> {
        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(ScoredHCT scoredHCT, ScoredHCT scoredHCT2) {
            return Double.compare(scoredHCT2.f14375a, scoredHCT.f14375a);
        }
    }

    private static class ScoredHCT {

        /* renamed from: a, reason: collision with root package name */
        public final double f14375a;
    }
}
