package cn.nubia.multisubscreen.view;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import cn.nubia.gameassist.R;
import cn.nubia.multisubscreen.data.SinkChartData;
import cn.nubia.multisubscreen.data.SinkDisplayData;
import cn.nubia.multisubscreen.secondary.DisplayOneHolder;
import cn.nubia.multisubscreen.secondary.DisplayThreeHolder;
import cn.nubia.multisubscreen.secondary.DisplayTwoHolder;
import cn.nubia.multisubscreen.secondary.NotificationMsgData;
import cn.nubia.multisubscreen.secondary.RemoveNotificationMsgData;
import cn.nubia.multisubscreen.utils.MultiSubScreenNotiMsgUtils;
import cn.nubia.multisubscreen.utils.MultiSubScreenUtils;
import com.zte.gameassist.common.InflaterHelper;
import com.zte.gameassist.utils.GaLog;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class SinkDisplayView extends FrameLayout implements ViewPager.OnPageChangeListener, MultiSubScreenUtils.GameStatusCallback {
    private static final int NOTI_MSG_INDICATOR_POSITION = 2;
    private static final int PAGER_NUMBER = 2;
    private static final String TAG = "MultiSubScreen_SinkDisplayView";
    private ViewPager mCurrentViewPager;
    private ImageView[] mIndicatorViews;
    private boolean mIsSupportNotiMsg;
    private boolean mIsZoomIn;
    private List<String> mKeys;
    private ViewGroup.LayoutParams mLayoutParams;
    private Handler mMainHandler;
    private ArrayList<String> mNotReadMsgList;
    private ImageView mNotiMsgIndicator;
    private OnZoomListener mOnZoomListener;
    private DisplayPagerAdapter mPagerAdapter;
    private SinkChartData mSinkChartData;
    private SinkDisplayData mSinkDisplayData;
    private final Runnable mUpdateNotiMsg;
    private ViewPager mViewPager;
    private ImageView mZoom;
    private DisplayPagerAdapter mZoomInPagerAdapter;
    private ViewPager mZoomInViewPager;

    private class DisplayPagerAdapter extends PagerAdapter {

        /* renamed from: c, reason: collision with root package name */
        private ArrayList f8195c = new ArrayList(2);

        /* renamed from: d, reason: collision with root package name */
        private DisplayOneHolder f8196d;

        /* renamed from: e, reason: collision with root package name */
        private DisplayTwoHolder f8197e;

        /* renamed from: f, reason: collision with root package name */
        private DisplayThreeHolder f8198f;

        /* renamed from: g, reason: collision with root package name */
        private boolean f8199g;

        /* renamed from: h, reason: collision with root package name */
        private boolean f8200h;

        public DisplayPagerAdapter(boolean z, boolean z2) {
            this.f8199g = false;
            GaLog.b(SinkDisplayView.TAG, "SinkDisplayView DisplayPagerAdapter zoomIn = " + z);
            GaLog.b(SinkDisplayView.TAG, "SinkDisplayView DisplayPagerAdapter this = " + this);
            this.f8200h = z;
            this.f8199g = z2;
            if (z) {
                this.f8195c.add((ViewGroup) InflaterHelper.e(R.layout.multi_sub_screen_sink_display_large_view));
                this.f8195c.add((ViewGroup) InflaterHelper.e(R.layout.multi_sub_screen_sink_chart_large_view));
                if (this.f8199g) {
                    this.f8195c.add((ViewGroup) InflaterHelper.e(R.layout.multi_sub_screen_sink_noti_msg_large_view));
                }
            } else {
                this.f8195c.add((ViewGroup) InflaterHelper.e(R.layout.multi_sub_screen_sink_display_view));
                this.f8195c.add((ViewGroup) InflaterHelper.e(R.layout.multi_sub_screen_sink_chart_view));
                if (this.f8199g) {
                    this.f8195c.add((ViewGroup) InflaterHelper.e(R.layout.multi_sub_screen_sink_noti_msg_view));
                }
            }
            this.f8196d = new DisplayOneHolder((ViewGroup) this.f8195c.get(0));
            this.f8197e = new DisplayTwoHolder((ViewGroup) this.f8195c.get(1));
            if (this.f8199g) {
                this.f8198f = new DisplayThreeHolder((ViewGroup) this.f8195c.get(2));
            }
            GaLog.b(SinkDisplayView.TAG, "SinkDisplayView DisplayPagerAdapter mDisplayOneHolder = " + this.f8196d);
        }

        public void A(ArrayList arrayList) {
            DisplayThreeHolder displayThreeHolder;
            if (!this.f8199g || (displayThreeHolder = this.f8198f) == null) {
                return;
            }
            displayThreeHolder.b(arrayList);
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public void b(ViewGroup viewGroup, int i2, Object obj) {
            viewGroup.removeView((View) this.f8195c.get(i2));
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int e() {
            GaLog.b(SinkDisplayView.TAG, "SinkDisplayView DisplayPagerAdapter getCount = " + this.f8195c.size());
            return this.f8195c.size();
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public Object j(ViewGroup viewGroup, int i2) {
            viewGroup.addView((View) this.f8195c.get(i2));
            return this.f8195c.get(i2);
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public boolean k(View view, Object obj) {
            return view == obj;
        }

        public DisplayOneHolder v() {
            return this.f8196d;
        }

        public DisplayThreeHolder w() {
            return this.f8198f;
        }

        public DisplayTwoHolder x() {
            return this.f8197e;
        }

        public void y(boolean z) {
            GaLog.b(SinkDisplayView.TAG, "SinkDisplayView DisplayPagerAdapter updateNotiMsg = " + z);
            if (this.f8199g == z) {
                return;
            }
            this.f8199g = z;
            int size = this.f8195c.size();
            if (z) {
                GaLog.b(SinkDisplayView.TAG, "SinkDisplayView DisplayPagerAdapter count = " + size);
                GaLog.b(SinkDisplayView.TAG, "SinkDisplayView DisplayPagerAdapter mZoomIn = " + this.f8200h);
                if (size == 2) {
                    if (this.f8200h) {
                        this.f8195c.add((ViewGroup) InflaterHelper.e(R.layout.multi_sub_screen_sink_noti_msg_large_view));
                    } else {
                        this.f8195c.add((ViewGroup) InflaterHelper.e(R.layout.multi_sub_screen_sink_noti_msg_view));
                    }
                    this.f8198f = new DisplayThreeHolder((ViewGroup) this.f8195c.get(2));
                }
            } else if (size == 3) {
                this.f8195c.remove(2);
            }
            l();
        }

        public void z() {
            DisplayThreeHolder displayThreeHolder;
            if (!this.f8199g || (displayThreeHolder = this.f8198f) == null || displayThreeHolder.c()) {
                return;
            }
            SinkDisplayView.this.mNotReadMsgList.clear();
            SinkDisplayView.this.mNotiMsgIndicator.setImageResource(R.drawable.multi_sub_screen_sink_bg_page_indicator);
        }
    }

    public interface OnZoomListener {
        void a();

        void b();
    }

    public SinkDisplayView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mNotReadMsgList = new ArrayList<>();
        this.mUpdateNotiMsg = new Runnable() { // from class: cn.nubia.multisubscreen.view.SinkDisplayView.1
            @Override // java.lang.Runnable
            public void run() {
                if (SinkDisplayView.this.getCurrentPagerAdapter().w().c()) {
                    SinkDisplayView.this.mMainHandler.postDelayed(SinkDisplayView.this.mUpdateNotiMsg, 60000L);
                } else {
                    SinkDisplayView.this.mNotReadMsgList.clear();
                    SinkDisplayView.this.mNotiMsgIndicator.setImageResource(R.drawable.multi_sub_screen_sink_bg_page_indicator);
                }
            }
        };
        k();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DisplayPagerAdapter getCurrentPagerAdapter() {
        return (DisplayPagerAdapter) getCurrentViewPager().getAdapter();
    }

    private ViewPager getCurrentViewPager() {
        return this.mCurrentViewPager;
    }

    private void k() {
        this.mSinkDisplayData = new SinkDisplayData();
        this.mSinkChartData = new SinkChartData();
        this.mLayoutParams = new ViewGroup.LayoutParams(-1, -1);
        this.mMainHandler = new Handler(Looper.getMainLooper());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l(View view) {
        if (this.mIsZoomIn) {
            y();
            this.mZoom.setImageResource(R.drawable.multi_sub_screen_sink_zoom_in);
        } else {
            x();
            this.mZoom.setImageResource(R.drawable.multi_sub_screen_sink_zoom_out);
        }
    }

    private void m() {
        boolean isShown = isShown();
        DisplayOneHolder v = getCurrentPagerAdapter().v();
        v.e(this.mSinkDisplayData.getCurFanSpeed(), this.mSinkDisplayData.getMaxFanSpeed(), isShown);
        v.c(this.mSinkDisplayData.getCurCpu(), this.mSinkDisplayData.getMaxCpu(), isShown);
        v.g(this.mSinkDisplayData.getCurGpu(), this.mSinkDisplayData.getMaxGpu(), isShown);
        v.a(this.mSinkDisplayData.getBatteryLevel());
        v.i(this.mSinkDisplayData.getPerformanceMode());
    }

    private void n() {
        DisplayOneHolder v = getCurrentPagerAdapter().v();
        v.d(this.mSinkDisplayData.getCurFanSpeed(), this.mSinkDisplayData.getMaxFanSpeed());
        v.b(this.mSinkDisplayData.getCurCpu(), this.mSinkDisplayData.getMaxCpu());
        v.f(this.mSinkDisplayData.getCurGpu(), this.mSinkDisplayData.getMaxGpu());
        v.a(this.mSinkDisplayData.getBatteryLevel());
        v.i(this.mSinkDisplayData.getPerformanceMode());
        DisplayTwoHolder x = getCurrentPagerAdapter().x();
        x.d(this.mSinkChartData.getFpsList());
        x.f(this.mSinkChartData.getNetList());
        x.c(this.mSinkChartData.getCpsList());
        x.e(this.mSinkChartData.getMpmList());
    }

    private void setIndicatorSelected(int i2) {
        int i3 = 0;
        while (true) {
            ImageView[] imageViewArr = this.mIndicatorViews;
            if (i3 >= imageViewArr.length) {
                return;
            }
            imageViewArr[i3].setSelected(i3 == i2);
            i3++;
        }
    }

    @VisibleForTesting
    private void test() {
        r(200, 2000);
        float f2 = 200000000;
        float f3 = 2000000000;
        q(f2, f3);
        t(f2, f3);
        setBatteryLevel(100);
        setPerformanceMode(1);
    }

    private void u(ViewPager viewPager, ViewPager viewPager2) {
        int currentItem = viewPager.getCurrentItem();
        setIndicatorSelected(currentItem);
        viewPager.J(this);
        removeView(viewPager);
        viewPager2.setCurrentItem(currentItem);
        addView(viewPager2, 0, this.mLayoutParams);
        viewPager2.c(this);
        this.mCurrentViewPager = viewPager2;
        getCurrentPagerAdapter().z();
    }

    private void x() {
        if (this.mZoomInViewPager == null) {
            this.mZoomInViewPager = new ViewPager(getContext());
            DisplayPagerAdapter displayPagerAdapter = new DisplayPagerAdapter(true, this.mIsSupportNotiMsg);
            this.mZoomInPagerAdapter = displayPagerAdapter;
            this.mZoomInViewPager.setAdapter(displayPagerAdapter);
            this.mZoomInPagerAdapter.v().h(this.mKeys);
        }
        this.mIsZoomIn = true;
        this.mOnZoomListener.a();
        u(this.mViewPager, this.mZoomInViewPager);
        n();
    }

    private void y() {
        this.mIsZoomIn = false;
        this.mOnZoomListener.b();
        u(this.mZoomInViewPager, this.mViewPager);
        n();
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void d(int i2, float f2, int i3) {
    }

    @Override // cn.nubia.multisubscreen.utils.MultiSubScreenUtils.GameStatusCallback
    public void e(boolean z) {
        m();
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void f(int i2) {
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void g(int i2) {
        if (i2 == 2) {
            this.mNotReadMsgList.clear();
            this.mNotiMsgIndicator.setImageResource(R.drawable.multi_sub_screen_sink_bg_page_indicator);
        }
        setIndicatorSelected(i2);
    }

    public void o(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        try {
            JSONObject jSONObject = new JSONObject(str);
            GaLog.e(TAG, "removeNotificationMsg jsonObject = " + jSONObject);
            RemoveNotificationMsgData removeNotificationMsgData = new RemoveNotificationMsgData(jSONObject.getString("multi_sub_screen_noti_msg_pkg_name"), jSONObject.getLong("multi_sub_screen_noti_msg_noti_id"));
            GaLog.e(TAG, "updateNotificationMsg data = " + removeNotificationMsgData);
            arrayList.add(removeNotificationMsgData);
            this.mNotReadMsgList.remove(removeNotificationMsgData.f8044a + "_" + removeNotificationMsgData.f8045b);
            if (this.mNotReadMsgList.isEmpty()) {
                this.mNotiMsgIndicator.setImageResource(R.drawable.multi_sub_screen_sink_bg_page_indicator);
            }
            getCurrentPagerAdapter().w().a(arrayList);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        Handler handler = this.mMainHandler;
        if (handler != null) {
            handler.removeCallbacks(this.mUpdateNotiMsg);
            this.mMainHandler.postDelayed(this.mUpdateNotiMsg, 60000L);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getCurrentViewPager().c(this);
        MultiSubScreenUtils.B(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getCurrentViewPager().J(this);
        MultiSubScreenUtils.M(this);
        Handler handler = this.mMainHandler;
        if (handler != null) {
            handler.removeCallbacks(this.mUpdateNotiMsg);
        }
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mViewPager = (ViewPager) findViewById(R.id.pager);
        ImageView imageView = (ImageView) findViewById(R.id.zoom);
        this.mZoom = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.multisubscreen.view.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SinkDisplayView.this.l(view);
            }
        });
        DisplayPagerAdapter displayPagerAdapter = new DisplayPagerAdapter(false, this.mIsSupportNotiMsg);
        this.mPagerAdapter = displayPagerAdapter;
        this.mViewPager.setAdapter(displayPagerAdapter);
        this.mCurrentViewPager = this.mViewPager;
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.indicator);
        this.mIndicatorViews = new ImageView[viewGroup.getChildCount()];
        for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
            this.mIndicatorViews[i2] = (ImageView) viewGroup.getChildAt(i2);
        }
        ImageView imageView2 = (ImageView) findViewById(R.id.indicator_noti_msg);
        this.mNotiMsgIndicator = imageView2;
        imageView2.setVisibility(8);
        this.mViewPager.setCurrentItem(0);
        setIndicatorSelected(0);
    }

    public void q(float f2, float f3) {
        this.mSinkDisplayData.setCpu(f2, f3);
        getCurrentPagerAdapter().v().c(f2, f3, true);
    }

    public void r(int i2, int i3) {
        SinkDisplayData sinkDisplayData = this.mSinkDisplayData;
        sinkDisplayData.setFanSpeed(sinkDisplayData.isFanOn() ? i2 : 0, i3);
        getCurrentPagerAdapter().v().e(i2, i3, true);
    }

    public void setBatteryLevel(int i2) {
        this.mSinkDisplayData.setBatteryLevel(i2);
        getCurrentPagerAdapter().v().a(i2);
    }

    public void setCps(String str) {
        this.mSinkChartData.addCps(Float.parseFloat(str));
        getCurrentPagerAdapter().x().c(this.mSinkChartData.getCpsList());
    }

    public void setFanOn(boolean z) {
        this.mSinkDisplayData.setFanOn(z);
        if (z) {
            return;
        }
        r(0, this.mSinkDisplayData.getMaxFanSpeed());
    }

    public void setFps(String str) {
        this.mSinkChartData.addFps(Float.parseFloat(str));
        getCurrentPagerAdapter().x().d(this.mSinkChartData.getFpsList());
    }

    public void setKeys(List<String> list) {
        this.mKeys = list;
        DisplayPagerAdapter displayPagerAdapter = this.mPagerAdapter;
        if (displayPagerAdapter != null) {
            displayPagerAdapter.v().h(list);
        }
        DisplayPagerAdapter displayPagerAdapter2 = this.mZoomInPagerAdapter;
        if (displayPagerAdapter2 != null) {
            displayPagerAdapter2.v().h(this.mKeys);
        }
    }

    public void setMpm(String str) {
        this.mSinkChartData.addMpm(Float.parseFloat(str));
        getCurrentPagerAdapter().x().e(this.mSinkChartData.getMpmList());
    }

    public void setNet(String str) {
        this.mSinkChartData.addNet(Float.parseFloat(str));
        getCurrentPagerAdapter().x().f(this.mSinkChartData.getNetList());
    }

    public void setOnZoomListener(OnZoomListener onZoomListener) {
        this.mOnZoomListener = onZoomListener;
    }

    public void setPerformanceMode(int i2) {
        this.mSinkDisplayData.setPerformanceMode(i2);
        getCurrentPagerAdapter().v().i(i2);
    }

    public void t(float f2, float f3) {
        this.mSinkDisplayData.setGpu(f2, f3);
        getCurrentPagerAdapter().v().g(f2, f3, true);
    }

    public void v(boolean z) {
        this.mIsSupportNotiMsg = z;
        DisplayPagerAdapter displayPagerAdapter = this.mPagerAdapter;
        if (displayPagerAdapter != null) {
            displayPagerAdapter.y(z);
        }
        DisplayPagerAdapter displayPagerAdapter2 = this.mZoomInPagerAdapter;
        if (displayPagerAdapter2 != null) {
            displayPagerAdapter2.y(z);
        }
        this.mNotiMsgIndicator.setVisibility(z ? 0 : 8);
    }

    public void w(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        try {
            JSONObject jSONObject = new JSONObject(str);
            GaLog.e(TAG, "updateNotificationMsg jsonObject = " + jSONObject);
            NotificationMsgData notificationMsgData = new NotificationMsgData(1);
            String string = jSONObject.getString("multi_sub_screen_noti_msg_pkg_name");
            notificationMsgData.f8038a = string;
            MultiSubScreenNotiMsgUtils.f8166c.put(string, jSONObject.getString("multi_sub_screen_noti_msg_app_label"));
            MultiSubScreenNotiMsgUtils.f8165b.put(string, MultiSubScreenNotiMsgUtils.a(jSONObject.getString("multi_sub_screen_noti_msg_app_icon")));
            notificationMsgData.f8039b = jSONObject.getString("multi_sub_screen_noti_msg_title");
            notificationMsgData.f8040c = jSONObject.getString("multi_sub_screen_noti_msg_content");
            notificationMsgData.f8041d = jSONObject.getLong("multi_sub_screen_noti_msg_time");
            notificationMsgData.f8043f = jSONObject.getLong("multi_sub_screen_noti_msg_noti_id");
            GaLog.e(TAG, "updateNotificationMsg data = " + notificationMsgData);
            arrayList.add(notificationMsgData);
            this.mNotReadMsgList.add(notificationMsgData.f8038a + "_" + notificationMsgData.f8043f);
            getCurrentPagerAdapter().A(arrayList);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        if (this.mCurrentViewPager.getCurrentItem() != 2) {
            this.mNotiMsgIndicator.setImageResource(R.drawable.multi_sub_screen_noti_msg_indicator);
        }
        Handler handler = this.mMainHandler;
        if (handler != null) {
            handler.removeCallbacks(this.mUpdateNotiMsg);
            this.mMainHandler.postDelayed(this.mUpdateNotiMsg, 60000L);
        }
    }

    public SinkDisplayView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mNotReadMsgList = new ArrayList<>();
        this.mUpdateNotiMsg = new Runnable() { // from class: cn.nubia.multisubscreen.view.SinkDisplayView.1
            @Override // java.lang.Runnable
            public void run() {
                if (SinkDisplayView.this.getCurrentPagerAdapter().w().c()) {
                    SinkDisplayView.this.mMainHandler.postDelayed(SinkDisplayView.this.mUpdateNotiMsg, 60000L);
                } else {
                    SinkDisplayView.this.mNotReadMsgList.clear();
                    SinkDisplayView.this.mNotiMsgIndicator.setImageResource(R.drawable.multi_sub_screen_sink_bg_page_indicator);
                }
            }
        };
        k();
    }

    public SinkDisplayView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.mNotReadMsgList = new ArrayList<>();
        this.mUpdateNotiMsg = new Runnable() { // from class: cn.nubia.multisubscreen.view.SinkDisplayView.1
            @Override // java.lang.Runnable
            public void run() {
                if (SinkDisplayView.this.getCurrentPagerAdapter().w().c()) {
                    SinkDisplayView.this.mMainHandler.postDelayed(SinkDisplayView.this.mUpdateNotiMsg, 60000L);
                } else {
                    SinkDisplayView.this.mNotReadMsgList.clear();
                    SinkDisplayView.this.mNotiMsgIndicator.setImageResource(R.drawable.multi_sub_screen_sink_bg_page_indicator);
                }
            }
        };
        k();
    }
}
