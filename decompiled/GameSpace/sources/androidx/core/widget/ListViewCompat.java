package androidx.core.widget;

import android.widget.ListView;

/* loaded from: classes.dex */
public final class ListViewCompat {

    static class Api19Impl {
        private Api19Impl() {
        }

        static boolean canScrollList(ListView listView, int i) {
            return listView.canScrollList(i);
        }

        static void scrollListBy(ListView listView, int i) {
            listView.scrollListBy(i);
        }
    }

    private ListViewCompat() {
    }

    public static boolean canScrollList(ListView listView, int i) {
        return Api19Impl.canScrollList(listView, i);
    }

    public static void scrollListBy(ListView listView, int i) {
        Api19Impl.scrollListBy(listView, i);
    }
}
