package androidx.core.widget;

import android.text.Editable;
import android.text.TextWatcher;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes.dex */
public final class TextViewKt$addTextChangedListener$textWatcher$1 implements TextWatcher {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ Function1 f3561c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ Function4 f3562h;

    /* renamed from: i, reason: collision with root package name */
    final /* synthetic */ Function4 f3563i;

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        this.f3561c.c(editable);
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        this.f3562h.j(charSequence, Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4));
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        this.f3563i.j(charSequence, Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4));
    }
}
