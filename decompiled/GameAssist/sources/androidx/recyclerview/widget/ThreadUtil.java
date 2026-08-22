package androidx.recyclerview.widget;

import androidx.recyclerview.widget.TileList;

/* loaded from: classes.dex */
interface ThreadUtil<T> {

    public interface BackgroundCallback<T> {
        void a(int i2, int i3, int i4, int i5, int i6);

        void b(int i2, int i3);

        void c(int i2);

        void d(TileList.Tile tile);
    }

    public interface MainThreadCallback<T> {
        void a(int i2, int i3);

        void b(int i2, TileList.Tile tile);

        void c(int i2, int i3);
    }
}
