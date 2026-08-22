package com.zte.mifavor.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ListAdapter;

/* loaded from: classes2.dex */
public class ZTEIndexListView extends ListView {
    public static final String TAG = "ZTEIndexListView";
    private GestureDetector mGestureDetector;
    private boolean mIsFastScrollEnabled;
    private ZTEIndexFastScroller mScroller;

    public ZTEIndexListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mIsFastScrollEnabled = false;
        this.mScroller = null;
        this.mGestureDetector = null;
    }

    @Override // android.widget.AbsListView, android.view.View
    public void draw(Canvas canvas) {
        try {
            try {
                super.draw(canvas);
            } catch (Exception unused) {
                for (int i2 = 0; i2 < getChildCount(); i2++) {
                    Log.d(TAG, "child " + i2 + ":" + getChildAt(i2));
                }
                Log.d(TAG, "header,footer:" + getHeaderViewsCount() + "," + getFooterViewsCount());
                if (getAdapter() != null) {
                    for (int i3 = 0; i3 < getAdapter().getCount(); i3++) {
                        Log.d(TAG, "adapter " + i3 + ":" + getAdapter().getItemViewType(i3));
                    }
                }
            }
            ZTEIndexFastScroller zTEIndexFastScroller = this.mScroller;
            if (zTEIndexFastScroller != null) {
                zTEIndexFastScroller.m(canvas);
            }
        } catch (Exception e2) {
            Log.e(TAG, "draw error, e = ", e2);
        }
    }

    @Override // android.widget.AbsListView
    public boolean isFastScrollEnabled() {
        return this.mIsFastScrollEnabled;
    }

    @Override // android.widget.AbsListView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        ZTEIndexFastScroller zTEIndexFastScroller = this.mScroller;
        if (zTEIndexFastScroller != null) {
            zTEIndexFastScroller.t(i2, i3, i4, i5);
        }
    }

    @Override // com.zte.mifavor.widget.ListView, android.widget.AbsListView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        ZTEIndexFastScroller zTEIndexFastScroller = this.mScroller;
        if (zTEIndexFastScroller != null) {
            if (zTEIndexFastScroller.u(motionEvent)) {
                return true;
            }
            if (this.mGestureDetector == null) {
                this.mGestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() { // from class: com.zte.mifavor.widget.ZTEIndexListView.1
                    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
                    public boolean onFling(MotionEvent motionEvent2, MotionEvent motionEvent3, float f2, float f3) {
                        ZTEIndexListView.this.mScroller.x();
                        return super.onFling(motionEvent2, motionEvent3, f2, f3);
                    }
                });
            }
            this.mGestureDetector.onTouchEvent(motionEvent);
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.widget.AbsListView
    public void setFastScrollEnabled(boolean z) {
        this.mIsFastScrollEnabled = z;
        if (z) {
            if (this.mScroller == null) {
                this.mScroller = new ZTEIndexFastScroller(getContext(), this);
            }
        } else {
            ZTEIndexFastScroller zTEIndexFastScroller = this.mScroller;
            if (zTEIndexFastScroller != null) {
                zTEIndexFastScroller.s();
                this.mScroller = null;
            }
        }
    }

    public void setIndexBarTopMargin(float f2) {
        ZTEIndexFastScroller zTEIndexFastScroller = this.mScroller;
        if (zTEIndexFastScroller != null) {
            zTEIndexFastScroller.f17867d = f2;
        }
    }

    @Override // com.zte.mifavor.widget.ListView, android.widget.AdapterView
    public void setAdapter(ListAdapter listAdapter) {
        super.setAdapter(listAdapter);
        ZTEIndexFastScroller zTEIndexFastScroller = this.mScroller;
        if (zTEIndexFastScroller != null) {
            zTEIndexFastScroller.v(listAdapter);
        }
    }

    public ZTEIndexListView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mIsFastScrollEnabled = false;
        this.mScroller = null;
        this.mGestureDetector = null;
    }
}
