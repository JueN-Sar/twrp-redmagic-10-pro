package androidx.core.view;

import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes.dex */
public final class ViewKt$doOnLayout$$inlined$doOnNextLayout$1 implements View.OnLayoutChangeListener {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ Function1 f3391c;

    @Override // android.view.View.OnLayoutChangeListener
    public void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        view.removeOnLayoutChangeListener(this);
        this.f3391c.c(view);
    }
}
