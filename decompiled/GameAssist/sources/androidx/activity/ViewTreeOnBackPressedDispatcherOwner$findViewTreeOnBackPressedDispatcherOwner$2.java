package androidx.activity;

import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata
/* loaded from: classes.dex */
final class ViewTreeOnBackPressedDispatcherOwner$findViewTreeOnBackPressedDispatcherOwner$2 extends Lambda implements Function1<View, OnBackPressedDispatcherOwner> {
    public static final ViewTreeOnBackPressedDispatcherOwner$findViewTreeOnBackPressedDispatcherOwner$2 INSTANCE = new ViewTreeOnBackPressedDispatcherOwner$findViewTreeOnBackPressedDispatcherOwner$2();

    ViewTreeOnBackPressedDispatcherOwner$findViewTreeOnBackPressedDispatcherOwner$2() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public final OnBackPressedDispatcherOwner c(View it) {
        Intrinsics.e(it, "it");
        Object tag = it.getTag(R.id.view_tree_on_back_pressed_dispatcher_owner);
        if (tag instanceof OnBackPressedDispatcherOwner) {
            return (OnBackPressedDispatcherOwner) tag;
        }
        return null;
    }
}
