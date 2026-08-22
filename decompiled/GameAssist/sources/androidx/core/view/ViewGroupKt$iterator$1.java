package androidx.core.view;

import android.view.View;
import android.view.ViewGroup;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMutableIterator;

@Metadata
/* loaded from: classes.dex */
public final class ViewGroupKt$iterator$1 implements Iterator<View>, KMutableIterator {

    /* renamed from: c, reason: collision with root package name */
    private int f3389c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ ViewGroup f3390h;

    ViewGroupKt$iterator$1(ViewGroup viewGroup) {
        this.f3390h = viewGroup;
    }

    @Override // java.util.Iterator
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public View next() {
        ViewGroup viewGroup = this.f3390h;
        int i2 = this.f3389c;
        this.f3389c = i2 + 1;
        View childAt = viewGroup.getChildAt(i2);
        if (childAt != null) {
            return childAt;
        }
        throw new IndexOutOfBoundsException();
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f3389c < this.f3390h.getChildCount();
    }

    @Override // java.util.Iterator
    public void remove() {
        ViewGroup viewGroup = this.f3390h;
        int i2 = this.f3389c - 1;
        this.f3389c = i2;
        viewGroup.removeViewAt(i2);
    }
}
