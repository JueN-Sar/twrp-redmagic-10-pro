package androidx.recyclerview.widget;

import android.util.SparseArray;
import java.lang.reflect.Array;

/* loaded from: classes.dex */
class TileList<T> {

    /* renamed from: a, reason: collision with root package name */
    private final SparseArray f5316a;

    /* renamed from: b, reason: collision with root package name */
    Tile f5317b;

    public static class Tile<T> {

        /* renamed from: a, reason: collision with root package name */
        public final Object[] f5318a;

        /* renamed from: b, reason: collision with root package name */
        public int f5319b;

        /* renamed from: c, reason: collision with root package name */
        public int f5320c;

        /* renamed from: d, reason: collision with root package name */
        Tile f5321d;

        public Tile(Class cls, int i2) {
            this.f5318a = (Object[]) Array.newInstance((Class<?>) cls, i2);
        }
    }

    public Tile a(Tile tile) {
        int indexOfKey = this.f5316a.indexOfKey(tile.f5319b);
        if (indexOfKey < 0) {
            this.f5316a.put(tile.f5319b, tile);
            return null;
        }
        Tile tile2 = (Tile) this.f5316a.valueAt(indexOfKey);
        this.f5316a.setValueAt(indexOfKey, tile);
        if (this.f5317b == tile2) {
            this.f5317b = tile;
        }
        return tile2;
    }

    public void b() {
        this.f5316a.clear();
    }

    public Tile c(int i2) {
        return (Tile) this.f5316a.valueAt(i2);
    }

    public Tile d(int i2) {
        Tile tile = (Tile) this.f5316a.get(i2);
        if (this.f5317b == tile) {
            this.f5317b = null;
        }
        this.f5316a.delete(i2);
        return tile;
    }

    public int e() {
        return this.f5316a.size();
    }
}
