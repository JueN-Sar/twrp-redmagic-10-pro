package androidx.recyclerview.widget;

import androidx.annotation.VisibleForTesting;
import androidx.collection.LongSparseArray;
import androidx.collection.SimpleArrayMap;
import androidx.core.util.Pools;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes.dex */
class ViewInfoStore {

    @VisibleForTesting
    final SimpleArrayMap<RecyclerView.ViewHolder, InfoRecord> mLayoutHolderMap = new SimpleArrayMap<>();

    @VisibleForTesting
    final LongSparseArray<RecyclerView.ViewHolder> mOldChangedHolders = new LongSparseArray<>();

    static class InfoRecord {

        /* renamed from: d, reason: collision with root package name */
        static Pools.Pool f5329d = new Pools.SimplePool(20);

        /* renamed from: a, reason: collision with root package name */
        int f5330a;

        /* renamed from: b, reason: collision with root package name */
        RecyclerView.ItemAnimator.ItemHolderInfo f5331b;

        /* renamed from: c, reason: collision with root package name */
        RecyclerView.ItemAnimator.ItemHolderInfo f5332c;

        private InfoRecord() {
        }

        static void a() {
            while (f5329d.acquire() != null) {
            }
        }

        static InfoRecord b() {
            InfoRecord infoRecord = (InfoRecord) f5329d.acquire();
            return infoRecord == null ? new InfoRecord() : infoRecord;
        }

        static void c(InfoRecord infoRecord) {
            infoRecord.f5330a = 0;
            infoRecord.f5331b = null;
            infoRecord.f5332c = null;
            f5329d.release(infoRecord);
        }
    }

    interface ProcessCallback {
        void a(RecyclerView.ViewHolder viewHolder, RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo, RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo2);

        void b(RecyclerView.ViewHolder viewHolder);

        void c(RecyclerView.ViewHolder viewHolder, RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo, RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo2);

        void d(RecyclerView.ViewHolder viewHolder, RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo, RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo2);
    }

    ViewInfoStore() {
    }

    private RecyclerView.ItemAnimator.ItemHolderInfo l(RecyclerView.ViewHolder viewHolder, int i2) {
        InfoRecord infoRecord;
        RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo;
        int d2 = this.mLayoutHolderMap.d(viewHolder);
        if (d2 >= 0 && (infoRecord = (InfoRecord) this.mLayoutHolderMap.j(d2)) != null) {
            int i3 = infoRecord.f5330a;
            if ((i3 & i2) != 0) {
                int i4 = (~i2) & i3;
                infoRecord.f5330a = i4;
                if (i2 == 4) {
                    itemHolderInfo = infoRecord.f5331b;
                } else {
                    if (i2 != 8) {
                        throw new IllegalArgumentException("Must provide flag PRE or POST");
                    }
                    itemHolderInfo = infoRecord.f5332c;
                }
                if ((i4 & 12) == 0) {
                    this.mLayoutHolderMap.h(d2);
                    InfoRecord.c(infoRecord);
                }
                return itemHolderInfo;
            }
        }
        return null;
    }

    void a(RecyclerView.ViewHolder viewHolder, RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo) {
        InfoRecord infoRecord = (InfoRecord) this.mLayoutHolderMap.get(viewHolder);
        if (infoRecord == null) {
            infoRecord = InfoRecord.b();
            this.mLayoutHolderMap.put(viewHolder, infoRecord);
        }
        infoRecord.f5330a |= 2;
        infoRecord.f5331b = itemHolderInfo;
    }

    void b(RecyclerView.ViewHolder viewHolder) {
        InfoRecord infoRecord = (InfoRecord) this.mLayoutHolderMap.get(viewHolder);
        if (infoRecord == null) {
            infoRecord = InfoRecord.b();
            this.mLayoutHolderMap.put(viewHolder, infoRecord);
        }
        infoRecord.f5330a |= 1;
    }

    void c(long j2, RecyclerView.ViewHolder viewHolder) {
        this.mOldChangedHolders.k(j2, viewHolder);
    }

    void d(RecyclerView.ViewHolder viewHolder, RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo) {
        InfoRecord infoRecord = (InfoRecord) this.mLayoutHolderMap.get(viewHolder);
        if (infoRecord == null) {
            infoRecord = InfoRecord.b();
            this.mLayoutHolderMap.put(viewHolder, infoRecord);
        }
        infoRecord.f5332c = itemHolderInfo;
        infoRecord.f5330a |= 8;
    }

    void e(RecyclerView.ViewHolder viewHolder, RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo) {
        InfoRecord infoRecord = (InfoRecord) this.mLayoutHolderMap.get(viewHolder);
        if (infoRecord == null) {
            infoRecord = InfoRecord.b();
            this.mLayoutHolderMap.put(viewHolder, infoRecord);
        }
        infoRecord.f5331b = itemHolderInfo;
        infoRecord.f5330a |= 4;
    }

    void f() {
        this.mLayoutHolderMap.clear();
        this.mOldChangedHolders.b();
    }

    RecyclerView.ViewHolder g(long j2) {
        return (RecyclerView.ViewHolder) this.mOldChangedHolders.f(j2);
    }

    boolean h(RecyclerView.ViewHolder viewHolder) {
        InfoRecord infoRecord = (InfoRecord) this.mLayoutHolderMap.get(viewHolder);
        return (infoRecord == null || (infoRecord.f5330a & 1) == 0) ? false : true;
    }

    boolean i(RecyclerView.ViewHolder viewHolder) {
        InfoRecord infoRecord = (InfoRecord) this.mLayoutHolderMap.get(viewHolder);
        return (infoRecord == null || (infoRecord.f5330a & 4) == 0) ? false : true;
    }

    void j() {
        InfoRecord.a();
    }

    public void k(RecyclerView.ViewHolder viewHolder) {
        p(viewHolder);
    }

    RecyclerView.ItemAnimator.ItemHolderInfo m(RecyclerView.ViewHolder viewHolder) {
        return l(viewHolder, 8);
    }

    RecyclerView.ItemAnimator.ItemHolderInfo n(RecyclerView.ViewHolder viewHolder) {
        return l(viewHolder, 4);
    }

    void o(ProcessCallback processCallback) {
        for (int size = this.mLayoutHolderMap.size() - 1; size >= 0; size--) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) this.mLayoutHolderMap.f(size);
            InfoRecord infoRecord = (InfoRecord) this.mLayoutHolderMap.h(size);
            int i2 = infoRecord.f5330a;
            if ((i2 & 3) == 3) {
                processCallback.b(viewHolder);
            } else if ((i2 & 1) != 0) {
                RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo = infoRecord.f5331b;
                if (itemHolderInfo == null) {
                    processCallback.b(viewHolder);
                } else {
                    processCallback.c(viewHolder, itemHolderInfo, infoRecord.f5332c);
                }
            } else if ((i2 & 14) == 14) {
                processCallback.a(viewHolder, infoRecord.f5331b, infoRecord.f5332c);
            } else if ((i2 & 12) == 12) {
                processCallback.d(viewHolder, infoRecord.f5331b, infoRecord.f5332c);
            } else if ((i2 & 4) != 0) {
                processCallback.c(viewHolder, infoRecord.f5331b, null);
            } else if ((i2 & 8) != 0) {
                processCallback.a(viewHolder, infoRecord.f5331b, infoRecord.f5332c);
            }
            InfoRecord.c(infoRecord);
        }
    }

    void p(RecyclerView.ViewHolder viewHolder) {
        InfoRecord infoRecord = (InfoRecord) this.mLayoutHolderMap.get(viewHolder);
        if (infoRecord == null) {
            return;
        }
        infoRecord.f5330a &= -2;
    }

    void q(RecyclerView.ViewHolder viewHolder) {
        int n2 = this.mOldChangedHolders.n() - 1;
        while (true) {
            if (n2 < 0) {
                break;
            }
            if (viewHolder == this.mOldChangedHolders.o(n2)) {
                this.mOldChangedHolders.m(n2);
                break;
            }
            n2--;
        }
        InfoRecord infoRecord = (InfoRecord) this.mLayoutHolderMap.remove(viewHolder);
        if (infoRecord != null) {
            InfoRecord.c(infoRecord);
        }
    }
}
