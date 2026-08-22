package cn.nubia.tgk;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import cn.nubia.gamelauncher.R;
import cn.nubia.tgk.util.TgkFeatureUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class TgkGuideView extends FrameLayout {
    private LinearLayout indications;
    private Context mContext;
    private List<View> mGameSpaceSlideViewList;
    private ITgkGuideViewClickListener mIClickListener;
    private List<ImageView> mIndicationViewList;
    private boolean mIsLandscape;
    private ViewPager mViewPaper;

    public interface ITgkGuideViewClickListener {
        void doClose();
    }

    public TgkGuideView(Context context) {
        this(context, null);
    }

    public TgkGuideView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mGameSpaceSlideViewList = new ArrayList();
        this.mIndicationViewList = new ArrayList();
        this.mContext = context;
        this.mIsLandscape = context.getResources().getConfiguration().orientation == 2;
        initImageViewList();
        initViewPaper();
        initIndication();
    }

    private void initImageViewList() {
        LayoutInflater from = LayoutInflater.from(this.mContext);
        View inflate = from.inflate(TgkFeatureUtil.isTgkSupportPortrait().booleanValue() ? R.layout.tgk_guide_dialog_slide_1 : R.layout.tgk_guide_dialog_slide_1_old, (ViewGroup) null);
        View inflate2 = from.inflate(TgkFeatureUtil.isTgkSupportPortrait().booleanValue() ? R.layout.tgk_guide_dialog_slide_3 : R.layout.tgk_guide_dialog_slide_3_old, (ViewGroup) null);
        this.mGameSpaceSlideViewList.add(inflate);
        this.mGameSpaceSlideViewList.add(inflate2);
        if (TgkHelper.isSPRDDevice()) {
            ((ImageView) inflate2.findViewById(R.id.tgk_slide3)).setBackgroundResource(TgkFeatureUtil.isTgkSupportPortrait().booleanValue() ? this.mIsLandscape ? R.drawable.tgk_guide_3_no_redmagic : R.drawable.tgk_guide_3_no_redmagic_portrait : R.drawable.tgk_guide_3_no_redmagic_old);
        }
        final View findViewById = inflate2.findViewById(R.id.start_setting);
        findViewById.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.tgk.TgkGuideView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                findViewById.setBackgroundColor(R.color.tgk_text_color_guide_click);
                if (TgkGuideView.this.mIClickListener != null) {
                    TgkGuideView.this.mIClickListener.doClose();
                }
            }
        });
    }

    private void initIndication() {
        ImageView imageView = (ImageView) findViewById(R.id.page_indicator0);
        ImageView imageView2 = (ImageView) findViewById(R.id.page_indicator1);
        this.indications = (LinearLayout) findViewById(R.id.page_indicator);
        imageView.setBackgroundResource(R.drawable.tgk_navigation_light);
        imageView2.setBackgroundResource(R.drawable.tgk_navigation_default);
        this.mIndicationViewList.add(imageView);
        this.mIndicationViewList.add(imageView2);
        this.mViewPaper.setOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: cn.nubia.tgk.TgkGuideView.3
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
                if (TgkGuideView.this.mIsLandscape) {
                    if (f > 0.2d) {
                        TgkGuideView.this.indications.setVisibility(8);
                        return;
                    } else {
                        TgkGuideView.this.indications.setVisibility(0);
                        return;
                    }
                }
                if (f > 0.2d) {
                    TgkGuideView.this.indications.setVisibility(8);
                } else {
                    TgkGuideView.this.indications.setVisibility(0);
                }
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                TgkGuideView.this.onViewPageChanged(i);
            }
        });
    }

    private void initViewPaper() {
        this.mViewPaper = (ViewPager) LayoutInflater.from(this.mContext).inflate(TgkFeatureUtil.isTgkSupportPortrait().booleanValue() ? R.layout.tgk_guide_dialog : R.layout.tgk_guide_dialog_old, this).findViewById(R.id.pager);
        this.mViewPaper.setAdapter(new PagerAdapter() { // from class: cn.nubia.tgk.TgkGuideView.1
            @Override // androidx.viewpager.widget.PagerAdapter
            public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
                viewGroup.removeView((View) TgkGuideView.this.mGameSpaceSlideViewList.get(i));
            }

            @Override // androidx.viewpager.widget.PagerAdapter
            public int getCount() {
                return TgkGuideView.this.mGameSpaceSlideViewList.size();
            }

            @Override // androidx.viewpager.widget.PagerAdapter
            public Object instantiateItem(ViewGroup viewGroup, int i) {
                viewGroup.addView((View) TgkGuideView.this.mGameSpaceSlideViewList.get(i));
                return TgkGuideView.this.mGameSpaceSlideViewList.get(i);
            }

            @Override // androidx.viewpager.widget.PagerAdapter
            public boolean isViewFromObject(View view, Object obj) {
                return view == obj;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onViewPageChanged(int i) {
        for (int i2 = 0; i2 < this.mIndicationViewList.size(); i2++) {
            ImageView imageView = this.mIndicationViewList.get(i2);
            if (i2 == i) {
                imageView.setBackgroundResource(R.drawable.tgk_navigation_light);
            } else {
                imageView.setBackgroundResource(R.drawable.tgk_navigation_default);
            }
        }
    }

    public void setCloseListener(ITgkGuideViewClickListener iTgkGuideViewClickListener) {
        this.mIClickListener = iTgkGuideViewClickListener;
    }
}
