package androidx.core.view;

import android.view.Menu;
import android.view.MenuItem;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.sequences.Sequence;

@Metadata
/* loaded from: classes.dex */
public final class MenuKt$children$1 implements Sequence<MenuItem> {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ Menu f3346a;

    @Override // kotlin.sequences.Sequence
    public Iterator iterator() {
        return MenuKt.a(this.f3346a);
    }
}
