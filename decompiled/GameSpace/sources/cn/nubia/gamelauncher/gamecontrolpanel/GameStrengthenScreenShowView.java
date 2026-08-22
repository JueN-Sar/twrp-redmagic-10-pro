package cn.nubia.gamelauncher.gamecontrolpanel;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.gamecontrolpanel.GameControlDialog;
import cn.nubia.gamelauncher.gamecontrolpanel.superresolution.SuperResolutionHelper;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.AnimationUtil;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.LogUtil;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.Utils;
import cn.nubia.gamelauncher.util.NubiaTrackManager;
import cn.nubia.plug.Constant;

/* loaded from: classes.dex */
public class GameStrengthenScreenShowView extends GameStrengthenBaseSelectedView implements GameControlDialog.ISetViewAnimation {
    private static final String TAG = "GameStrengthenScreenShowView";
    private String GAME_STRENGTHEN_MODE_VALUE;
    private boolean mIsOpenColorInvert;
    private boolean mIsSprdPlatform;
    private boolean mIsSupportGcp;
    private int[] mPreviewImages;
    private int[] mSprdPreviewImages;
    private View mViewPageLayout;
    private RadioButton vGcpBtnCar;
    private RadioButton vGcpBtnMenu;
    private RadioButton vGcpBtnMoba;
    private RadioButton vGcpBtnShoot;
    private ViewPager vPreviewPager;
    private LinearLayout vPreviewPagerIndicator;

    public GameStrengthenScreenShowView(Context context) {
        this(context, null);
    }

    public GameStrengthenScreenShowView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GameStrengthenScreenShowView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.GAME_STRENGTHEN_MODE_VALUE = "game_strengthen_mode_value";
        this.mPreviewImages = new int[]{R.drawable.nubia_game_strengthen_screen_default, R.drawable.nubia_game_strengthen_screen_car, R.drawable.nubia_game_strengthen_screen_shoot, R.drawable.nubia_game_strengthen_screen_moba};
        this.mSprdPreviewImages = new int[]{R.drawable.nubia_game_strengthen_screen_car};
        this.mIsSprdPlatform = Utils.isSprdPlatform();
        this.vGcpBtnMenu = (RadioButton) findViewById(R.id.nubia_game_strength_screen_show_auto);
        this.vGcpBtnCar = (RadioButton) findViewById(R.id.nubia_game_strength_screen_show_car);
        this.vGcpBtnShoot = (RadioButton) findViewById(R.id.nubia_game_strength_screen_show_shoot);
        this.vGcpBtnMoba = (RadioButton) findViewById(R.id.nubia_game_strength_screen_show_moba);
        if (this.mIsSprdPlatform) {
            this.vGcpBtnMenu.setVisibility(8);
            this.vGcpBtnCar.setText(R.string.nubia_game_screen_strengthen_sprd_softening);
            this.vGcpBtnShoot.setText(R.string.nubia_game_screen_strengthen_sprd_clear);
            this.vGcpBtnMoba.setText(R.string.nubia_game_screen_strengthen_sprd_details);
        }
        initScreenShowPreviewPager();
    }

    private void initScreenShowPreviewPager() {
        this.mViewPageLayout = findViewById(R.id.my_viewpage_layout);
        this.vPreviewPager = (ViewPager) findViewById(R.id.nubia_game_screen_show_preview_pager);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.nubia_game_screen_show_preview_pager_indicator);
        this.vPreviewPagerIndicator = linearLayout;
        if (this.mIsSprdPlatform) {
            linearLayout.setVisibility(8);
        }
        setPreviewPageIndicator(0);
        this.vPreviewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameStrengthenScreenShowView.1
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                GameStrengthenScreenShowView.this.setPreviewPageIndicator(i);
            }
        });
        this.vPreviewPager.setAdapter(new PagerAdapter() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameStrengthenScreenShowView.2
            @Override // androidx.viewpager.widget.PagerAdapter
            public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
                viewGroup.removeView((View) obj);
            }

            @Override // androidx.viewpager.widget.PagerAdapter
            public int getCount() {
                return GameStrengthenScreenShowView.this.mIsSprdPlatform ? GameStrengthenScreenShowView.this.mSprdPreviewImages.length : GameStrengthenScreenShowView.this.mPreviewImages.length;
            }

            @Override // androidx.viewpager.widget.PagerAdapter
            public Object instantiateItem(ViewGroup viewGroup, int i) {
                ImageView imageView = new ImageView(GameStrengthenScreenShowView.this.getContext());
                ViewPager.LayoutParams layoutParams = new ViewPager.LayoutParams();
                layoutParams.height = -1;
                layoutParams.width = -1;
                imageView.setLayoutParams(layoutParams);
                if (GameStrengthenScreenShowView.this.mIsSprdPlatform) {
                    imageView.setImageResource(GameStrengthenScreenShowView.this.mSprdPreviewImages[0]);
                } else {
                    imageView.setImageResource(GameStrengthenScreenShowView.this.mPreviewImages[i]);
                }
                viewGroup.addView(imageView);
                return imageView;
            }

            @Override // androidx.viewpager.widget.PagerAdapter
            public boolean isViewFromObject(View view, Object obj) {
                return view == obj;
            }
        });
        showFlicker(this.vGameStrengthenGroup);
    }

    private void reportScreenShowUsed(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("level", str);
        bundle.putCharSequence("app_name ", Utils.getCurrentAppName());
        LogUtil.d(TAG, "  reportScreenShowUsed level = " + str + "  ;; event = game_enhance_display_switch_used");
        NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "game_enhance_display_switch_used", bundle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPreviewPageIndicator(int i) {
        int childCount = this.vPreviewPagerIndicator.getChildCount();
        int i2 = 0;
        while (i2 < childCount) {
            this.vPreviewPagerIndicator.getChildAt(i2).setBackgroundResource(i2 == i ? R.drawable.nubia_game_strengthen_screen_show_indicator_selected : R.drawable.nubia_game_strengthen_screen_show_indicator_unselected);
            i2++;
        }
    }

    private void updateGameScreenShowForZTE(int i) {
        Utils.isNubiaOS();
    }

    private void updateGameStrengthScreenShowDesc(int i) {
        if (this.mIsOpenColorInvert) {
            LogUtil.d(TAG, "updateGameStrengthScreenShowDesc:  mIsOpenColorInvert is true");
            this.vGameStrengthenDesc.setText(getContext().getString(R.string.nubia_game_color_invert_desc));
            return;
        }
        String str = SuperResolutionHelper.DEFAULT_SUPPORT;
        if (i == R.id.nubia_game_strength_screen_show_default) {
            this.vGameStrengthenDesc.setText(getContext().getString(R.string.nubia_game_screen_strengthen_default_desc));
            updateGameScreenShowForZTE(0);
            this.vPreviewPager.setCurrentItem(0);
        } else if (i == R.id.nubia_game_strength_screen_show_car) {
            if (this.mIsSprdPlatform) {
                this.vGameStrengthenDesc.setText(getContext().getString(R.string.nubia_game_screen_strengthen_sprd_softening_desc));
            } else {
                this.vGameStrengthenDesc.setText(getContext().getString(R.string.nubia_game_screen_strengthen_car_desc));
            }
            updateGameScreenShowForZTE(1);
            this.vPreviewPager.setCurrentItem(1);
            str = "racing";
        } else if (i == R.id.nubia_game_strength_screen_show_shoot) {
            if (this.mIsSprdPlatform) {
                this.vGameStrengthenDesc.setText(getContext().getString(R.string.nubia_game_screen_strengthen_sprd_clear_desc));
            } else {
                this.vGameStrengthenDesc.setText(getContext().getString(R.string.nubia_game_screen_strengthen_shoot_desc));
            }
            updateGameScreenShowForZTE(2);
            this.vPreviewPager.setCurrentItem(2);
            str = "shooting";
        } else if (i == R.id.nubia_game_strength_screen_show_moba) {
            if (this.mIsSprdPlatform) {
                this.vGameStrengthenDesc.setText(getContext().getString(R.string.nubia_game_screen_strengthen_sprd_details_desc));
            } else {
                this.vGameStrengthenDesc.setText(getContext().getString(R.string.nubia_game_screen_strengthen_moba_desc));
            }
            updateGameScreenShowForZTE(3);
            this.vPreviewPager.setCurrentItem(3);
            str = Constant.GAME_TYPE_MOBA;
        } else if (i == R.id.nubia_game_strength_screen_show_auto) {
            this.vGameStrengthenDesc.setText(getContext().getString(R.string.nubia_game_screen_strengthen_auto_desc));
            updateGameScreenShowForZTE(4);
            this.vPreviewPager.setCurrentItem(0);
            str = "smart";
        }
        reportScreenShowUsed(str);
    }

    private void updateShowStrengthenEnable() {
        int childCount = this.vGameStrengthenGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            RadioButton radioButton = (RadioButton) this.vGameStrengthenGroup.getChildAt(i);
            radioButton.setAlpha(this.mIsOpenColorInvert ? 0.5f : 1.0f);
            if (radioButton.isSelected()) {
                updateGameStrengthScreenShowDesc(radioButton.getId());
            }
        }
    }

    @Override // cn.nubia.gamelauncher.gamecontrolpanel.GameControlDialog.ISetViewAnimation
    public void animationSelf(final boolean z) {
        new Handler().post(new Runnable() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameStrengthenScreenShowView.3
            @Override // java.lang.Runnable
            public void run() {
                if (!z) {
                    GameStrengthenScreenShowView.this.mViewPageLayout.setAlpha(0.0f);
                    GameStrengthenScreenShowView.this.mViewPageLayout.setAlpha(0.0f);
                    GameStrengthenScreenShowView.this.vGameStrengthenDesc.setAlpha(0.0f);
                    GameStrengthenScreenShowView.this.vGameStrengthenGroup.setAlpha(0.0f);
                    return;
                }
                AnimationUtil.setGpuTranslationY(GameStrengthenScreenShowView.this.mViewPageLayout);
                AnimationUtil.setGcsRedItemAlpha(GameStrengthenScreenShowView.this.mViewPageLayout);
                AnimationUtil.setGpuTranslationY(GameStrengthenScreenShowView.this.vGameStrengthenDesc);
                AnimationUtil.setGcsRedItemAlpha(GameStrengthenScreenShowView.this.vGameStrengthenDesc);
                AnimationUtil.setDoublePxTranslationY(GameStrengthenScreenShowView.this.vGameStrengthenGroup);
                AnimationUtil.setGcsRedItemAlpha(GameStrengthenScreenShowView.this.vGameStrengthenGroup);
            }
        });
    }

    @Override // cn.nubia.gamelauncher.gamecontrolpanel.GameStrengthenBaseSelectedView
    protected int getGameStrengthenDescId() {
        return R.id.nubia_game_screen_strengthen_desc;
    }

    @Override // cn.nubia.gamelauncher.gamecontrolpanel.GameStrengthenBaseSelectedView
    protected int getGameStrengthenGroupId() {
        return R.id.nubia_game_screen_show_group;
    }

    @Override // cn.nubia.gamelauncher.gamecontrolpanel.GameStrengthenBaseSelectedView
    protected int getGameStrengthenLayout() {
        return GameControlOrientationManager.getInstance().isPortrait() ? R.layout.nubia_game_strengthen_view_screen_show_gcp_port : R.layout.nubia_game_strengthen_view_screen_show_gcp;
    }

    @Override // cn.nubia.gamelauncher.gamecontrolpanel.GameStrengthenBaseSelectedView
    protected int getGameStrengthenType() {
        return 1;
    }

    @Override // cn.nubia.gamelauncher.gamecontrolpanel.GameStrengthenBaseSelectedView, android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.mIsSupportGcp || view.getId() != R.id.nubia_game_strength_screen_show_auto) {
            if (this.mIsOpenColorInvert) {
                return;
            }
            super.onClick(view);
        } else {
            Toast toast = new Toast(getContext());
            View inflate = LayoutInflater.from(getContext()).inflate(R.layout.nubia_transient_notification, (ViewGroup) null);
            ((TextView) inflate.findViewById(R.id.message)).setText(R.string.nubia_game_screen_strengthen_auto_unsupport);
            toast.setView(inflate);
            toast.setDuration(0);
            toast.show();
        }
    }

    @Override // cn.nubia.gamelauncher.gamecontrolpanel.GameStrengthenBaseSelectedView
    protected void saveCurrentMode(int i) {
    }

    public void setGameStrengthenScreenShowMode(int i, boolean z, boolean z2) {
        LogUtil.i(TAG, " setGameStrengthenScreenShowMode --  isSupportGcp = " + z + " ;; isOpenColorInvert = " + z2);
        this.mIsSupportGcp = z;
        this.mIsOpenColorInvert = z2;
        updateGameStrength(this.vGameStrengthenGroup.getChildAt(i).getId());
        updateShowStrengthenEnable();
        this.vGcpBtnMenu.setAlpha((!z || this.mIsOpenColorInvert) ? 0.5f : 1.0f);
    }

    public void setScreenShowStrengthenEnable(boolean z) {
        this.mIsOpenColorInvert = z;
        updateShowStrengthenEnable();
        this.vGcpBtnMenu.setAlpha((!this.mIsSupportGcp || this.mIsOpenColorInvert) ? 0.5f : 1.0f);
    }

    @Override // cn.nubia.gamelauncher.gamecontrolpanel.GameStrengthenBaseSelectedView
    protected void updateGameStrength(int i) {
        super.updateGameStrength(i);
        updateGameStrengthScreenShowDesc(i);
    }
}
