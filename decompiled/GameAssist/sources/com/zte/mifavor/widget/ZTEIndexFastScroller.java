package com.zte.mifavor.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.SectionIndexer;
import com.zte.extres.R;
import java.util.Locale;

/* loaded from: classes2.dex */
public class ZTEIndexFastScroller {
    private BitmapDrawable B;
    private Bitmap C;
    private Paint D;
    private Paint E;
    private Paint F;
    private Paint G;
    private Locale H;
    private int I;
    private int J;

    /* renamed from: e, reason: collision with root package name */
    private float f17868e;

    /* renamed from: f, reason: collision with root package name */
    private float f17869f;

    /* renamed from: g, reason: collision with root package name */
    private float f17870g;

    /* renamed from: h, reason: collision with root package name */
    private float f17871h;

    /* renamed from: i, reason: collision with root package name */
    private float f17872i;

    /* renamed from: j, reason: collision with root package name */
    private int f17873j;

    /* renamed from: k, reason: collision with root package name */
    private int f17874k;

    /* renamed from: p, reason: collision with root package name */
    private android.widget.ListView f17879p;

    /* renamed from: s, reason: collision with root package name */
    private RectF f17882s;
    private Adapter x;
    private int y;
    private Context z;

    /* renamed from: a, reason: collision with root package name */
    public int f17864a = 0;

    /* renamed from: b, reason: collision with root package name */
    public boolean f17865b = true;

    /* renamed from: c, reason: collision with root package name */
    public boolean f17866c = true;

    /* renamed from: d, reason: collision with root package name */
    public float f17867d = 0.0f;

    /* renamed from: l, reason: collision with root package name */
    private int f17875l = -1;

    /* renamed from: m, reason: collision with root package name */
    private boolean f17876m = false;

    /* renamed from: n, reason: collision with root package name */
    private boolean f17877n = false;

    /* renamed from: o, reason: collision with root package name */
    private boolean f17878o = false;

    /* renamed from: q, reason: collision with root package name */
    private SectionIndexer f17880q = null;

    /* renamed from: r, reason: collision with root package name */
    private String[] f17881r = null;
    private boolean t = false;
    private boolean u = false;
    private boolean v = false;
    private boolean w = true;
    private GestureDetector A = null;
    private Handler K = new Handler() { // from class: com.zte.mifavor.widget.ZTEIndexFastScroller.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            ZTEIndexFastScroller zTEIndexFastScroller = ZTEIndexFastScroller.this;
            int i2 = zTEIndexFastScroller.f17864a;
            if (i2 == 1) {
                zTEIndexFastScroller.w(2);
                if (!ZTEIndexFastScroller.this.t) {
                    int firstVisiblePosition = ZTEIndexFastScroller.this.f17879p.getFirstVisiblePosition() - ZTEIndexFastScroller.this.f17879p.getHeaderViewsCount();
                    int i3 = firstVisiblePosition >= 0 ? firstVisiblePosition : 0;
                    ZTEIndexFastScroller zTEIndexFastScroller2 = ZTEIndexFastScroller.this;
                    zTEIndexFastScroller2.f17875l = zTEIndexFastScroller2.q(i3);
                }
                ZTEIndexFastScroller.this.f17879p.invalidate();
                return;
            }
            if (i2 == 2) {
                zTEIndexFastScroller.w(3);
                return;
            }
            if (i2 != 3) {
                return;
            }
            if (zTEIndexFastScroller.u) {
                ZTEIndexFastScroller.this.f17878o = false;
            }
            ZTEIndexFastScroller.this.w(0);
            ZTEIndexFastScroller.this.f17879p.invalidate();
            ZTEIndexFastScroller.this.n(10L);
        }
    };
    AbsListView.OnScrollListener L = new AbsListView.OnScrollListener() { // from class: com.zte.mifavor.widget.ZTEIndexFastScroller.2
        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
            if (!ZTEIndexFastScroller.this.f17878o || ZTEIndexFastScroller.this.u) {
                return;
            }
            Log.i("ZTEIndexFastScroller", "onScroll STATE_SHOWING");
            ZTEIndexFastScroller.this.w(1);
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i2) {
            if (i2 == 1 || i2 == 2) {
                ZTEIndexFastScroller.this.f17878o = true;
                ZTEIndexFastScroller.this.u = false;
                Log.i("ZTEIndexFastScroller", "onScrollStateChanged STATE_SHOWING");
                ZTEIndexFastScroller.this.w(1);
                return;
            }
            ZTEIndexFastScroller.this.u = true;
            int firstVisiblePosition = ZTEIndexFastScroller.this.f17879p.getFirstVisiblePosition() - ZTEIndexFastScroller.this.f17879p.getHeaderViewsCount();
            int i3 = firstVisiblePosition >= 0 ? firstVisiblePosition : 0;
            ZTEIndexFastScroller zTEIndexFastScroller = ZTEIndexFastScroller.this;
            zTEIndexFastScroller.f17875l = zTEIndexFastScroller.q(i3);
            Log.i("ZTEIndexFastScroller", "onScrollStateChanged STATE_HIDING");
            ZTEIndexFastScroller.this.w(3);
        }
    };

    public ZTEIndexFastScroller(Context context, android.widget.ListView listView) {
        this.f17879p = null;
        this.y = -6842473;
        this.z = null;
        this.B = null;
        this.C = null;
        this.D = null;
        this.E = null;
        this.F = null;
        this.G = null;
        this.z = context;
        this.f17871h = context.getResources().getDisplayMetrics().density;
        this.f17872i = context.getResources().getDisplayMetrics().scaledDensity;
        this.f17879p = listView;
        v(listView.getAdapter());
        float f2 = this.f17871h;
        this.f17868e = 16.0f * f2;
        this.f17869f = f2 * 0.0f;
        this.f17870g = f2 * 12.0f;
        this.f17867d *= f2;
        listView.setOnScrollListener(this.L);
        this.f17882s = new RectF(0.0f, 0.0f, 1.0f, 1.0f);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) this.z.getResources().getDrawable(R.drawable.fastscroll_label_zte_light);
        this.B = bitmapDrawable;
        this.C = bitmapDrawable.getBitmap();
        Paint paint = new Paint();
        this.D = paint;
        paint.setColor(10066329);
        this.D.setAlpha(0);
        this.D.setAntiAlias(true);
        Paint paint2 = new Paint();
        this.E = paint2;
        paint2.setAntiAlias(true);
        this.E.setTextSize(this.f17872i * 12.0f);
        Paint paint3 = new Paint();
        this.F = paint3;
        paint3.setColor(-16777216);
        Paint paint4 = new Paint();
        this.G = paint4;
        paint4.setColor(-1);
        this.G.setAntiAlias(true);
        this.G.setTextSize(this.f17871h * 45.0f);
        this.y = this.z.getResources().getColor(R.color.mfv_common_index_letter);
        this.J = Utils.c(this.z, 16);
    }

    private boolean l(float f2, float f3) {
        RectF rectF = this.f17882s;
        if (rectF != null) {
            float f4 = rectF.top;
            if (f3 >= f4 || f3 <= f4 + rectF.height()) {
                if (this.I == 1) {
                    if (!this.f17865b || f2 > this.f17882s.right) {
                        this.t = false;
                    } else {
                        this.t = true;
                    }
                } else if (!this.f17865b || f2 < this.f17882s.left) {
                    Log.d("wyt", "contains=false");
                    this.t = false;
                } else {
                    this.t = true;
                    Log.d("wyt", "contains=true");
                }
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n(long j2) {
        this.K.removeMessages(0);
        this.K.sendEmptyMessageAtTime(0, SystemClock.uptimeMillis() + j2);
    }

    private String o(int i2) {
        Object item;
        Adapter adapter = this.x;
        if (adapter == null || (item = adapter.getItem(i2)) == null) {
            return null;
        }
        return item.toString().substring(0, 1);
    }

    private int p() {
        int firstVisiblePosition = this.f17879p.getFirstVisiblePosition() - this.f17879p.getHeaderViewsCount();
        if (firstVisiblePosition < 0) {
            return 0;
        }
        return firstVisiblePosition;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int q(int i2) {
        SectionIndexer sectionIndexer = this.f17880q;
        if (sectionIndexer != null) {
            return sectionIndexer.getSectionForPosition(i2);
        }
        return -1;
    }

    private int r(float f2) {
        String[] strArr = this.f17881r;
        if (strArr == null || strArr.length == 0) {
            return 0;
        }
        RectF rectF = this.f17882s;
        float f3 = rectF.top;
        if (f2 < this.f17869f + f3) {
            return 0;
        }
        float height = f3 + rectF.height();
        float f4 = this.f17869f;
        if (f2 >= height - f4) {
            return this.f17881r.length - 1;
        }
        RectF rectF2 = this.f17882s;
        return (int) (((f2 - rectF2.top) - f4) / ((rectF2.height() - (this.f17869f * 2.0f)) / this.f17881r.length));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w(int i2) {
        if (i2 < 0 || i2 > 3) {
            return;
        }
        this.f17864a = i2;
        Log.i("ZTEIndexFastScroller", "setState mState = " + this.f17864a);
        int i3 = this.f17864a;
        if (i3 == 0) {
            this.K.removeMessages(0);
            return;
        }
        if (i3 == 1) {
            n(0L);
        } else if (i3 == 2) {
            this.K.removeMessages(0);
        } else {
            if (i3 != 3) {
                return;
            }
            n(500L);
        }
    }

    public void m(Canvas canvas) {
        Locale locale = Locale.getDefault();
        this.H = locale;
        this.I = TextUtils.getLayoutDirectionFromLocale(locale);
        String[] strArr = this.f17881r;
        if (strArr == null || strArr.length <= 0 || !this.f17865b) {
            return;
        }
        RectF rectF = this.f17882s;
        float f2 = this.f17871h;
        canvas.drawRoundRect(rectF, f2 * 5.0f, f2 * 5.0f, this.D);
        float height = (this.f17882s.height() - (this.f17869f * 2.0f)) / this.f17881r.length;
        float descent = (height - (this.E.descent() - this.E.ascent())) / 2.0f;
        for (int i2 = 0; i2 < this.f17881r.length; i2++) {
            if (i2 == this.f17875l) {
                this.E.setColor(this.z.getResources().getColor(R.color.mfv_common_index_letter_fc));
            } else {
                this.E.setColor(this.y);
            }
            float measureText = (this.f17868e - this.E.measureText(this.f17881r[i2])) / 2.0f;
            if (this.I != 1) {
                String str = this.f17881r[i2];
                RectF rectF2 = this.f17882s;
                canvas.drawText(str, rectF2.left + measureText + this.J, (((rectF2.top + this.f17869f) + (i2 * height)) + descent) - this.E.ascent(), this.E);
            } else {
                canvas.drawText(this.f17881r[i2], measureText, (((this.f17882s.top + this.f17869f) + (i2 * height)) + descent) - this.E.ascent(), this.E);
            }
        }
        float descent2 = ((this.f17870g * 2.0f) + this.G.descent()) - this.G.ascent();
        int i3 = this.f17873j;
        int i4 = this.f17874k;
        RectF rectF3 = new RectF((i3 - descent2) / 2.0f, (i4 - descent2) / 2.0f, ((i3 - descent2) / 2.0f) + descent2, ((i4 - descent2) / 2.0f) + descent2);
        String o2 = o(p());
        if (this.w) {
            if (this.t) {
                int i5 = this.f17875l;
                if (i5 < 0 || !this.v) {
                    return;
                }
                float measureText2 = this.G.measureText(this.f17881r[i5]);
                canvas.drawBitmap(this.C, (Rect) null, rectF3, this.F);
                canvas.drawText(this.f17881r[this.f17875l], (rectF3.left + ((descent2 - measureText2) / 2.0f)) - 1.0f, ((rectF3.top + this.f17870g) - this.G.ascent()) + 1.0f, this.G);
                return;
            }
            if ((this.f17876m || this.f17878o) && o2 != null && this.f17866c) {
                float measureText3 = this.G.measureText(o2);
                canvas.drawBitmap(this.C, (Rect) null, rectF3, this.F);
                canvas.drawText(o2, (rectF3.left + ((descent2 - measureText3) / 2.0f)) - 1.0f, ((rectF3.top + this.f17870g) - this.G.ascent()) + 1.0f, this.G);
            }
        }
    }

    public void s() {
    }

    public void t(int i2, int i3, int i4, int i5) {
        this.f17873j = i2;
        this.f17874k = i3;
        Locale locale = Locale.getDefault();
        this.H = locale;
        int layoutDirectionFromLocale = TextUtils.getLayoutDirectionFromLocale(locale);
        this.I = layoutDirectionFromLocale;
        if (layoutDirectionFromLocale == 1) {
            float f2 = this.f17869f;
            this.f17882s = new RectF(f2, this.f17867d + f2, this.f17868e + f2 + this.J, i3 - f2);
        } else {
            float f3 = i2;
            float f4 = this.f17869f;
            this.f17882s = new RectF(((f3 - f4) - this.f17868e) - this.J, this.f17867d + f4, f3 - f4, i3 - f4);
        }
    }

    public boolean u(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            if (l(motionEvent.getX(), motionEvent.getY())) {
                this.f17876m = true;
                if (this.t) {
                    this.f17877n = true;
                    this.v = true;
                    int r2 = r(motionEvent.getY());
                    this.f17875l = r2;
                    SectionIndexer sectionIndexer = this.f17880q;
                    if (sectionIndexer != null) {
                        this.f17879p.setSelection(sectionIndexer.getPositionForSection(r2));
                    }
                    Log.i("ZTEIndexFastScroller", "gengbin,onTouchEvent():MotionEvent.ACTION_DOWN mTouchScroller=true");
                    return true;
                }
                this.f17876m = false;
                this.f17877n = false;
                this.f17875l = q(this.f17879p.getFirstVisiblePosition() + 4);
                Log.i("ZTEIndexFastScroller", "gengbin,onTouchEvent():MotionEvent.ACTION_DOWN mTouchScroller=false1");
            }
            Log.i("ZTEIndexFastScroller", "gengbin,onTouchEvent():MotionEvent.ACTION_DOWN mTouchScroller=false2");
        } else if (action == 1) {
            this.f17876m = false;
            this.v = false;
            this.f17877n = false;
            w(3);
            if (this.t) {
                return true;
            }
            Log.i("ZTEIndexFastScroller", "gengbin,onTouchEvent():MotionEvent.ACTION_UP");
        } else {
            if (action == 2) {
                if (l(motionEvent.getX(), motionEvent.getY())) {
                    if (this.f17877n) {
                        this.v = true;
                        int r3 = r(motionEvent.getY());
                        this.f17875l = r3;
                        SectionIndexer sectionIndexer2 = this.f17880q;
                        if (sectionIndexer2 != null) {
                            this.f17879p.setSelection(sectionIndexer2.getPositionForSection(r3));
                        }
                    } else {
                        GestureDetector gestureDetector = new GestureDetector(this.z, new GestureDetector.SimpleOnGestureListener() { // from class: com.zte.mifavor.widget.ZTEIndexFastScroller.3
                            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
                            public boolean onFling(MotionEvent motionEvent2, MotionEvent motionEvent3, float f2, float f3) {
                                if (motionEvent3.getY() - motionEvent2.getY() > 100.0f) {
                                    ZTEIndexFastScroller zTEIndexFastScroller = ZTEIndexFastScroller.this;
                                    zTEIndexFastScroller.f17875l = zTEIndexFastScroller.q(zTEIndexFastScroller.f17879p.getFirstVisiblePosition() + 4);
                                    ZTEIndexFastScroller.this.f17876m = true;
                                } else {
                                    ZTEIndexFastScroller.this.f17876m = false;
                                }
                                return super.onFling(motionEvent2, motionEvent3, f2, f3);
                            }
                        });
                        this.A = gestureDetector;
                        gestureDetector.onTouchEvent(motionEvent);
                    }
                }
                Log.i("ZTEIndexFastScroller", "gengbin,onTouchEvent():MotionEvent.ACTION_MOVE");
                return false;
            }
            if (action == 3) {
                Log.i("ZTEIndexFastScroller", "gengbin,onTouchEvent():MotionEvent.ACTION_CANCEL");
                this.f17876m = false;
                this.v = false;
                this.f17877n = false;
                w(3);
            }
        }
        Log.i("ZTEIndexFastScroller", "gengbin,onTouchEvent():nocase");
        return false;
    }

    public void v(Adapter adapter) {
        if (adapter instanceof SectionIndexer) {
            SectionIndexer sectionIndexer = (SectionIndexer) adapter;
            this.f17880q = sectionIndexer;
            this.f17881r = (String[]) sectionIndexer.getSections();
            this.x = adapter;
        }
    }

    public void x() {
        if (this.f17864a == 0) {
            w(1);
        }
    }
}
