package androidx.core.widget;

import android.text.Editable;
import android.text.TextWatcher;
import kotlin.Metadata;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes.dex */
public final class TextViewKt$doOnTextChanged$$inlined$addTextChangedListener$default$1 implements TextWatcher {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ Function4 f3560c;

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        this.f3560c.j(charSequence, Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4));
    }
}
