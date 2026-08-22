package androidx.recyclerview.widget;

import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import java.util.List;

/* loaded from: classes.dex */
public abstract class ListAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    /* renamed from: c, reason: collision with root package name */
    final AsyncListDiffer f5118c;

    /* renamed from: androidx.recyclerview.widget.ListAdapter$1, reason: invalid class name */
    class AnonymousClass1 implements AsyncListDiffer.ListListener<T> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ ListAdapter f5119a;

        @Override // androidx.recyclerview.widget.AsyncListDiffer.ListListener
        public void a(List list, List list2) {
            this.f5119a.L(list, list2);
        }
    }

    public void L(List list, List list2) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int m() {
        return this.f5118c.a().size();
    }
}
