package androidx.core.view;

import android.view.View;
import android.view.ViewGroup;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.sequences.Sequence;

@Metadata
/* loaded from: classes.dex */
public final class ViewGroupKt {
    public static final Sequence a(final ViewGroup viewGroup) {
        return new Sequence<View>() { // from class: androidx.core.view.ViewGroupKt$children$1
            @Override // kotlin.sequences.Sequence
            public Iterator iterator() {
                return ViewGroupKt.c(viewGroup);
            }
        };
    }

    public static final Sequence b(final ViewGroup viewGroup) {
        return new Sequence<View>() { // from class: androidx.core.view.ViewGroupKt$special$$inlined$Sequence$1
            @Override // kotlin.sequences.Sequence
            public Iterator iterator() {
                return new TreeIterator(ViewGroupKt.a(viewGroup).iterator(), new Function1<View, Iterator<? extends View>>() { // from class: androidx.core.view.ViewGroupKt$descendants$1$1
                    @Override // kotlin.jvm.functions.Function1
                    /* renamed from: d, reason: merged with bridge method [inline-methods] */
                    public final Iterator c(View view) {
                        Sequence a2;
                        ViewGroup viewGroup2 = view instanceof ViewGroup ? (ViewGroup) view : null;
                        if (viewGroup2 == null || (a2 = ViewGroupKt.a(viewGroup2)) == null) {
                            return null;
                        }
                        return a2.iterator();
                    }
                });
            }
        };
    }

    public static final Iterator c(ViewGroup viewGroup) {
        return new ViewGroupKt$iterator$1(viewGroup);
    }
}
