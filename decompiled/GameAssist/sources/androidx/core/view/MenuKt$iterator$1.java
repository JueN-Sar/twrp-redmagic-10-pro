package androidx.core.view;

import android.view.Menu;
import android.view.MenuItem;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMutableIterator;

@Metadata
@SourceDebugExtension
/* loaded from: classes.dex */
public final class MenuKt$iterator$1 implements Iterator<MenuItem>, KMutableIterator {

    /* renamed from: c, reason: collision with root package name */
    private int f3347c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ Menu f3348h;

    MenuKt$iterator$1(Menu menu) {
        this.f3348h = menu;
    }

    @Override // java.util.Iterator
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public MenuItem next() {
        Menu menu = this.f3348h;
        int i2 = this.f3347c;
        this.f3347c = i2 + 1;
        MenuItem item = menu.getItem(i2);
        if (item != null) {
            return item;
        }
        throw new IndexOutOfBoundsException();
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f3347c < this.f3348h.size();
    }

    @Override // java.util.Iterator
    public void remove() {
        Unit unit;
        Menu menu = this.f3348h;
        int i2 = this.f3347c - 1;
        this.f3347c = i2;
        MenuItem item = menu.getItem(i2);
        if (item != null) {
            menu.removeItem(item.getItemId());
            unit = Unit.f18288a;
        } else {
            unit = null;
        }
        if (unit == null) {
            throw new IndexOutOfBoundsException();
        }
    }
}
