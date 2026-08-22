package androidx.core.widget;

import android.text.Editable;
import android.text.TextWatcher;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes.dex */
public final class TextViewKt$doAfterTextChanged$$inlined$addTextChangedListener$default$1 implements TextWatcher {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ Function1 f3558c;

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        this.f3558c.c(editable);
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
    }
}
