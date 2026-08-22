package cn.nubia.gamecenter.settings.summary;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.utils.LogUtil;
import cn.nubia.gamecenter.settings.utils.Utils;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class ArkBaseLabel extends FrameLayout implements View.OnClickListener, View.OnFocusChangeListener {
    private static final int DEFAULT_LABEL_HEIGHT = 0;
    private static final int DEFAULT_LABEL_WIDTH = 0;
    private static final String TAG = "ArkBaseLabel";
    private static Drawable mLabelAllMeetBg = null;
    private static Drawable mLabelDefaultBg = null;
    private static final int mLabelDescriptionTvTextColor = -637534209;
    private static Drawable mLabelSelectedBg;
    private Context mContext;
    private LayoutInflater mInflater;
    private TextView mLabelDescriptionTv;
    private WindowManager.LayoutParams mLabelDescriptionTvParams;
    private int mLabelHeight;
    private ImageView mLabelIcon;
    private Drawable mLabelIconRes;
    private boolean mLabelMeetAllConditions;
    private View mLabelPanel;
    private TextView mLabelText;
    private CharSequence mLabelTextRes;
    private int mLabelWidth;
    private ArrayList<View> mViews;
    private WindowManager mWindowManager;

    public ArkBaseLabel(Context context) {
        this(context, null);
    }

    public ArkBaseLabel(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mViews = new ArrayList<>();
        this.mLabelWidth = 0;
        this.mLabelHeight = 0;
        this.mContext = context;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ArkbaseLabel_attrs);
        this.mLabelWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ArkbaseLabel_attrs_label_width, 0);
        this.mLabelHeight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ArkbaseLabel_attrs_label_height, 0);
        this.mLabelTextRes = obtainStyledAttributes.getText(R.styleable.ArkbaseLabel_attrs_label_text);
        this.mLabelIconRes = obtainStyledAttributes.getDrawable(R.styleable.ArkbaseLabel_attrs_label_icon);
        obtainStyledAttributes.recycle();
        this.mWindowManager = (WindowManager) this.mContext.getApplicationContext().getSystemService("window");
        this.mInflater = (LayoutInflater) this.mContext.getSystemService("layout_inflater");
        initView();
    }

    private void dismissDescriptionWindow() {
        if (isShowingDescriptionWindow()) {
            this.mWindowManager.removeViewImmediate(this.mLabelDescriptionTv);
            removeRecord(this.mLabelDescriptionTv);
            if (this.mLabelMeetAllConditions) {
                this.mLabelPanel.setBackground(mLabelAllMeetBg);
            } else {
                this.mLabelPanel.setBackground(mLabelDefaultBg);
            }
        }
    }

    private WindowManager.LayoutParams getWindowParams() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.type = 2038;
        layoutParams.format = 1;
        layoutParams.flags = 262184;
        layoutParams.width = -2;
        layoutParams.height = Utils.dip2px(this.mContext, 37.0f);
        layoutParams.gravity = 8388659;
        int[] iArr = new int[2];
        this.mLabelPanel.getLocationInWindow(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        layoutParams.x = i - ((layoutParams.width / 2) - (this.mLabelPanel.getWidth() / 2));
        layoutParams.y = i2 + this.mLabelPanel.getHeight() + Utils.dip2px(this.mContext, 7.0f);
        return layoutParams;
    }

    private void initView() {
        this.mInflater.inflate(R.layout.ark_base_label, this);
        View findViewById = findViewById(R.id.account_label_panel_id);
        this.mLabelPanel = findViewById;
        ViewGroup.LayoutParams layoutParams = findViewById.getLayoutParams();
        layoutParams.width = this.mLabelWidth;
        this.mLabelPanel.setLayoutParams(layoutParams);
        this.mLabelPanel.setOnClickListener(this);
        this.mLabelPanel.setOnFocusChangeListener(this);
        ImageView imageView = (ImageView) findViewById(R.id.account_label_img_id);
        this.mLabelIcon = imageView;
        imageView.setImageDrawable(this.mLabelIconRes);
        TextView textView = (TextView) findViewById(R.id.account_label_text_id);
        this.mLabelText = textView;
        textView.setText(this.mLabelTextRes);
        TextView textView2 = new TextView(this.mContext);
        this.mLabelDescriptionTv = textView2;
        textView2.setGravity(17);
        this.mLabelDescriptionTv.setTextColor(mLabelDescriptionTvTextColor);
        this.mLabelDescriptionTv.setSingleLine(true);
        this.mLabelDescriptionTv.setTextSize(11.0f);
        this.mLabelDescriptionTv.setText("test label description window");
        int dip2px = Utils.dip2px(this.mContext, 6.0f);
        this.mLabelDescriptionTv.setPadding(dip2px, 0, dip2px, 0);
        this.mLabelDescriptionTv.setBackground(this.mContext.getDrawable(R.drawable.arkbase_account_label_description_bg));
        mLabelDefaultBg = this.mContext.getDrawable(R.drawable.arkbase_account_label_bg);
        mLabelAllMeetBg = this.mContext.getDrawable(R.drawable.arkbase_account_all_meet_label_bg);
        mLabelSelectedBg = this.mContext.getDrawable(R.drawable.arkbase_account_label_selected_bg);
    }

    private boolean isShowingDescriptionWindow() {
        ArrayList<View> arrayList = this.mViews;
        return arrayList != null && arrayList.contains(this.mLabelDescriptionTv);
    }

    private void record(View view) {
        synchronized (this) {
            this.mViews.add(view);
        }
    }

    private void removeRecord(View view) {
        synchronized (this) {
            this.mViews.remove(view);
        }
    }

    private void showDescriptionWindow() {
        if (isShowingDescriptionWindow()) {
            return;
        }
        if (this.mLabelDescriptionTvParams == null) {
            this.mLabelDescriptionTvParams = getWindowParams();
        }
        this.mLabelPanel.setBackground(mLabelSelectedBg);
        this.mWindowManager.addView(this.mLabelDescriptionTv, this.mLabelDescriptionTvParams);
        record(this.mLabelDescriptionTv);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.mLabelPanel) {
            if (isShowingDescriptionWindow()) {
                dismissDescriptionWindow();
            } else {
                showDescriptionWindow();
            }
        }
    }

    @Override // android.view.View.OnFocusChangeListener
    public void onFocusChange(View view, boolean z) {
        LogUtil.e(TAG, "onFocusChange,  " + z);
        if (z) {
            showDescriptionWindow();
        } else {
            dismissDescriptionWindow();
        }
    }

    public void setLabelDescriptionText(String str) {
        TextView textView = this.mLabelDescriptionTv;
        if (textView != null) {
            textView.setText(str);
        }
    }

    public void setLabelIcon(int i) {
        ImageView imageView = this.mLabelIcon;
        if (imageView != null) {
            imageView.setImageDrawable(this.mContext.getDrawable(i));
        }
    }

    public void setLabelIcon(Drawable drawable) {
        ImageView imageView = this.mLabelIcon;
        if (imageView != null) {
            imageView.setImageDrawable(drawable);
        }
    }

    public void setLabelMeetAllConditions(boolean z) {
        this.mLabelMeetAllConditions = z;
        if (z) {
            this.mLabelPanel.setBackground(mLabelAllMeetBg);
        }
    }

    public void setLabelText(String str) {
        TextView textView = this.mLabelText;
        if (textView != null) {
            textView.setText(str);
        }
    }
}
