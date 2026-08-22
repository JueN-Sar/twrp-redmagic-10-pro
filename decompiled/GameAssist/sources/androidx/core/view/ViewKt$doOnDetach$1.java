package androidx.core.view;

import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes.dex */
public final class ViewKt$doOnDetach$1 implements View.OnAttachStateChangeListener {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ View f3394c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ Function1 f3395h;

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        this.f3394c.removeOnAttachStateChangeListener(this);
        this.f3395h.c(view);
    }
}
