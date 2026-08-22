package cn.nubia.gamecenter.settings.widget;

import android.content.Context;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import cn.nubia.gamecenter.settings.widget.ViewPager;

/* loaded from: classes.dex */
public class VerticalViewPager extends ViewPager {
    private static final String TAG = "VerticalViewPager";
    private ViewGroup m_listView;
    private int m_listViewId;
    private float m_yDistance;
    private float m_yLast;

    private class VerticalPageTransformer implements ViewPager.PageTransformer {
        private VerticalPageTransformer() {
        }

        @Override // cn.nubia.gamecenter.settings.widget.ViewPager.PageTransformer
        public void transformPage(View view, float f) {
            if (f < -1.0f) {
                view.setAlpha(0.0f);
            } else {
                if (f > 1.0f) {
                    view.setAlpha(0.0f);
                    return;
                }
                view.setAlpha(1.0f);
                view.setTranslationX(view.getWidth() * (-f));
                view.setTranslationY(f * view.getHeight());
            }
        }
    }

    public VerticalViewPager(Context context) {
        super(context);
        this.m_listViewId = -1;
        init();
    }

    public VerticalViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.m_listViewId = -1;
        init();
    }

    public VerticalViewPager(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        this.m_listViewId = -1;
        init();
    }

    private void clearListView() {
        this.m_listView = null;
    }

    private ViewGroup getListView() {
        if (this.m_listView == null) {
            this.m_listView = (ViewGroup) findViewById(this.m_listViewId);
        }
        return this.m_listView;
    }

    private RectF getListViewLocation() {
        if (getListView() == null) {
            return null;
        }
        int[] iArr = new int[2];
        getListView().getLocationOnScreen(iArr);
        return new RectF(iArr[0], iArr[1], r2 + r7.getWidth(), iArr[1] + r7.getHeight());
    }

    private void init() {
        setCustomVertical(true);
        setPageTransformer(true, new VerticalPageTransformer());
    }

    private boolean isInternalListCanScroll(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            clearListView();
        }
        RectF listViewLocation = getListViewLocation();
        if (listViewLocation == null || !listViewLocation.contains(motionEvent.getRawX(), motionEvent.getRawY())) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            this.m_yDistance = 0.0f;
            this.m_yLast = motionEvent.getY();
        } else if (action == 2) {
            this.m_yDistance = motionEvent.getY() - this.m_yLast;
        }
        float f = this.m_yDistance;
        if (f > 1.0f) {
            return isListCanMoveUp();
        }
        if (f < -1.0f) {
            return isListCanMoveDown();
        }
        return false;
    }

    private boolean isListCanMoveDown() {
        ViewGroup listView = getListView();
        if (listView == null || listView.getChildCount() == 0) {
            return false;
        }
        View childAt = listView.getChildAt(listView.getChildCount() - 1);
        return childAt == null || childAt.getBottom() > listView.getHeight();
    }

    private boolean isListCanMoveUp() {
        ViewGroup listView = getListView();
        if (listView == null || listView.getChildCount() == 0) {
            return false;
        }
        View childAt = listView.getChildAt(0);
        return childAt == null || childAt.getTop() < 0;
    }

    private MotionEvent swapXY(MotionEvent motionEvent) {
        float width = getWidth();
        float height = getHeight();
        motionEvent.setLocation((motionEvent.getY() / height) * width, (motionEvent.getX() / width) * height);
        return motionEvent;
    }

    @Override // cn.nubia.gamecenter.settings.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (isInternalListCanScroll(motionEvent)) {
            return false;
        }
        boolean onInterceptTouchEvent = super.onInterceptTouchEvent(swapXY(motionEvent));
        swapXY(motionEvent);
        return onInterceptTouchEvent;
    }

    @Override // cn.nubia.gamecenter.settings.widget.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(swapXY(motionEvent));
    }

    public void setListView(int i) {
        this.m_listViewId = i;
    }
}
