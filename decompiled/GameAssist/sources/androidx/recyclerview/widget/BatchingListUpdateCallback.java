package androidx.recyclerview.widget;

/* loaded from: classes.dex */
public class BatchingListUpdateCallback implements ListUpdateCallback {

    /* renamed from: c, reason: collision with root package name */
    final ListUpdateCallback f4915c;

    /* renamed from: h, reason: collision with root package name */
    int f4916h = 0;

    /* renamed from: i, reason: collision with root package name */
    int f4917i = -1;

    /* renamed from: j, reason: collision with root package name */
    int f4918j = -1;

    /* renamed from: k, reason: collision with root package name */
    Object f4919k = null;

    public BatchingListUpdateCallback(ListUpdateCallback listUpdateCallback) {
        this.f4915c = listUpdateCallback;
    }

    @Override // androidx.recyclerview.widget.ListUpdateCallback
    public void a(int i2, int i3) {
        int i4;
        if (this.f4916h == 1 && i2 >= (i4 = this.f4917i)) {
            int i5 = this.f4918j;
            if (i2 <= i4 + i5) {
                this.f4918j = i5 + i3;
                this.f4917i = Math.min(i2, i4);
                return;
            }
        }
        e();
        this.f4917i = i2;
        this.f4918j = i3;
        this.f4916h = 1;
    }

    @Override // androidx.recyclerview.widget.ListUpdateCallback
    public void b(int i2, int i3) {
        int i4;
        if (this.f4916h == 2 && (i4 = this.f4917i) >= i2 && i4 <= i2 + i3) {
            this.f4918j += i3;
            this.f4917i = i2;
        } else {
            e();
            this.f4917i = i2;
            this.f4918j = i3;
            this.f4916h = 2;
        }
    }

    @Override // androidx.recyclerview.widget.ListUpdateCallback
    public void c(int i2, int i3, Object obj) {
        int i4;
        if (this.f4916h == 3) {
            int i5 = this.f4917i;
            int i6 = this.f4918j;
            if (i2 <= i5 + i6 && (i4 = i2 + i3) >= i5 && this.f4919k == obj) {
                this.f4917i = Math.min(i2, i5);
                this.f4918j = Math.max(i6 + i5, i4) - this.f4917i;
                return;
            }
        }
        e();
        this.f4917i = i2;
        this.f4918j = i3;
        this.f4919k = obj;
        this.f4916h = 3;
    }

    @Override // androidx.recyclerview.widget.ListUpdateCallback
    public void d(int i2, int i3) {
        e();
        this.f4915c.d(i2, i3);
    }

    public void e() {
        int i2 = this.f4916h;
        if (i2 == 0) {
            return;
        }
        if (i2 == 1) {
            this.f4915c.a(this.f4917i, this.f4918j);
        } else if (i2 == 2) {
            this.f4915c.b(this.f4917i, this.f4918j);
        } else if (i2 == 3) {
            this.f4915c.c(this.f4917i, this.f4918j, this.f4919k);
        }
        this.f4919k = null;
        this.f4916h = 0;
    }
}
