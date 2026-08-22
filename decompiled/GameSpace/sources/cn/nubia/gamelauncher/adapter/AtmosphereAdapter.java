package cn.nubia.gamelauncher.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.common.helper.ImageCache;
import cn.nubia.common.view.SimpleEditImageView;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.atmosphere.Atmosphere;
import cn.nubia.gamelauncher.atmosphere.CustomBean;
import com.bumptech.glide.BitmapTypeRequest;
import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.ViewTarget;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class AtmosphereAdapter extends RecyclerView.Adapter {
    public static final String TAG = "Atmosphere";
    Atmosphere mAtmosphere;
    private Context mContext;
    private SimpleEditImageView mEditImageView;
    Runnable mGalleryClick;
    int mItemDecorationSpace;
    private ArrayList<CustomBean> mList;
    TextView mPrompt;
    private int mSelectedPosition = 0;
    String mUrl;

    class CustomHolder extends RecyclerView.ViewHolder {
        private View mBottom;
        private View mDivider;
        private ImageView mFlagImage;
        private ImageView mIcon;
        private TextView mLabel;
        private View mTop;

        public CustomHolder(View view) {
            super(view);
            this.mIcon = (ImageView) view.findViewById(R.id.thumbnail_icon);
            this.mLabel = (TextView) view.findViewById(R.id.atmosphere_label);
            this.mFlagImage = (ImageView) view.findViewById(R.id.type_flag);
            this.mTop = view.findViewById(R.id.top_place);
            this.mBottom = view.findViewById(R.id.bottom_place);
            this.mDivider = view.findViewById(R.id.item_divider);
        }
    }

    public AtmosphereAdapter(Context context, SimpleEditImageView simpleEditImageView, TextView textView, ArrayList<CustomBean> arrayList, Atmosphere atmosphere, Runnable runnable) {
        this.mContext = context;
        this.mEditImageView = simpleEditImageView;
        this.mPrompt = textView;
        this.mList = arrayList;
        this.mAtmosphere = atmosphere;
        this.mGalleryClick = runnable;
        this.mItemDecorationSpace = context.getResources().getDimensionPixelOffset(R.dimen.atmosphere_item_min_space);
        loadAtmosphere(0);
    }

    private void loadAtmosphere(int i) {
        String url;
        String type;
        CustomBean customBean = this.mList.get(i);
        url = customBean.getUrl();
        type = customBean.getType();
        Log.d("Atmosphere", "loadAtmosphere(" + i + ") type : " + type + ", url : " + url);
        type.hashCode();
        switch (type) {
            case "highlight":
                loadHighLight(url);
                this.mEditImageView.setEnable(false);
                break;
            case "gallery":
                startGalley();
                return;
            case "net":
            case "crop":
            case "local":
                this.mEditImageView.setEnable(true);
                loadImage(url, false, Atmosphere.TYPE_CROP.equals(type));
                break;
            default:
                Log.d("Atmosphere", "loadAtmosphere() type : " + type);
                break;
        }
        updateSelected(i);
    }

    private void loadHighLight(String str) {
        Log.d("Atmosphere", "loadHighLight() url : " + str);
        if (TextUtils.isEmpty(str)) {
            loadImage(this.mAtmosphere.getDefaultUrl(), true, false);
        } else {
            this.mEditImageView.setVisibility(8);
        }
    }

    private void loadImage(final String str, boolean z, boolean z2) {
        Log.d("Atmosphere", "loadImage() url : " + str);
        this.mEditImageView.setVisibility(0);
        this.mPrompt.setVisibility(z ? 0 : 8);
        Bitmap bitmap = ImageCache.getInstance().get(str);
        if (!z2 || bitmap == null || bitmap.isRecycled()) {
            Glide.with(this.mContext).load(str).asBitmap().into((BitmapTypeRequest<String>) new SimpleTarget<Bitmap>() { // from class: cn.nubia.gamelauncher.adapter.AtmosphereAdapter.1
                public void onResourceReady(Bitmap bitmap2, GlideAnimation<? super Bitmap> glideAnimation) {
                    AtmosphereAdapter.this.mEditImageView.setBitmap(bitmap2);
                    AtmosphereAdapter.this.mUrl = str;
                }

                @Override // com.bumptech.glide.request.target.Target
                public /* bridge */ /* synthetic */ void onResourceReady(Object obj, GlideAnimation glideAnimation) {
                    onResourceReady((Bitmap) obj, (GlideAnimation<? super Bitmap>) glideAnimation);
                }
            });
            Log.d("Atmosphere", "loadImage() load by url : " + str);
        } else {
            this.mEditImageView.setBitmap(ImageCache.getInstance().get(str));
            this.mUrl = str;
            Log.d("Atmosphere", "loadImage() show from cache : " + str);
        }
    }

    private void showSelectedRect(CustomHolder customHolder, int i) {
        if (i == this.mSelectedPosition) {
            customHolder.mIcon.setForeground(this.mContext.getDrawable(R.drawable.selected));
        } else {
            customHolder.mIcon.setForeground(null);
        }
    }

    private void updateIcon(CustomHolder customHolder, final String str, final String str2) {
        Log.d("Atmosphere", "updateIcon(" + str2 + ") url : " + str);
        customHolder.mFlagImage.setVisibility(Atmosphere.TYPE_HIGHLIGHT.equals(str2) ? 0 : 8);
        Glide.with(this.mContext).load(str).override(108, 108).into((DrawableRequestBuilder<String>) new ViewTarget<ImageView, GlideDrawable>(customHolder.mIcon) { // from class: cn.nubia.gamelauncher.adapter.AtmosphereAdapter.2
            public void onResourceReady(GlideDrawable glideDrawable, GlideAnimation<? super GlideDrawable> glideAnimation) {
                ((ImageView) this.view).setImageDrawable(glideDrawable);
                Log.d("Atmosphere", "updateIcon() - onResourceReady(" + str2 + ") url : " + str);
            }

            @Override // com.bumptech.glide.request.target.Target
            public /* bridge */ /* synthetic */ void onResourceReady(Object obj, GlideAnimation glideAnimation) {
                onResourceReady((GlideDrawable) obj, (GlideAnimation<? super GlideDrawable>) glideAnimation);
            }
        });
    }

    private void updateLabel(CustomHolder customHolder, int i) {
        customHolder.mLabel.setVisibility(i == getItemCount() + (-1) ? 0 : 8);
    }

    private void updateSelected(int i) {
        Log.d("Atmosphere", "updateSelected(" + i + ")");
        this.mSelectedPosition = i;
        notifyDataSetChanged();
    }

    public void doSelectedGallery(boolean z) {
        Log.d("Atmosphere", "selectedGallery(" + z + ")");
        if (z) {
            this.mPrompt.setVisibility(8);
            this.mEditImageView.setVisibility(0);
            this.mEditImageView.setEnable(true);
            updateSelected(getItemCount() - 1);
        }
    }

    public String getApplyType() {
        return this.mList.get(this.mSelectedPosition).getType();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return i;
    }

    public String getUrl() {
        return this.mUrl;
    }

    /* renamed from: lambda$onBindViewHolder$0$cn-nubia-gamelauncher-adapter-AtmosphereAdapter, reason: not valid java name */
    /* synthetic */ void m227x66238cc2(int i, View view) {
        loadAtmosphere(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        CustomHolder customHolder = (CustomHolder) viewHolder;
        CustomBean customBean = this.mList.get(i);
        String url = customBean.getUrl();
        String type = customBean.getType();
        Log.d("Atmosphere", "-->onBindViewHolder(" + i + ") type : " + customBean.getType() + ", url : " + url);
        updateItemDecoration(customHolder, i);
        updateLabel(customHolder, i);
        if (TextUtils.isEmpty(url)) {
            url = this.mAtmosphere.getDefaultUrl();
        }
        updateIcon(customHolder, url, type);
        customHolder.mIcon.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.adapter.AtmosphereAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AtmosphereAdapter.this.m227x66238cc2(i, view);
            }
        });
        showSelectedRect(customHolder, i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public CustomHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new CustomHolder(LayoutInflater.from(this.mContext).inflate(R.layout.atmosphere_item, (ViewGroup) null));
    }

    public void startGalley() {
        Log.d("Atmosphere", "startGalley()");
        Runnable runnable = this.mGalleryClick;
        if (runnable == null) {
            return;
        }
        runnable.run();
    }

    public void updateItemDecoration(CustomHolder customHolder, int i) {
        if (i == 0) {
            customHolder.mTop.setMinimumHeight(this.mItemDecorationSpace * 2);
            customHolder.mBottom.setMinimumHeight(this.mItemDecorationSpace);
            customHolder.mBottom.setVisibility(0);
            customHolder.mDivider.setVisibility(0);
            return;
        }
        if (i != getItemCount() - 1) {
            customHolder.mTop.setMinimumHeight(this.mItemDecorationSpace);
            customHolder.mBottom.setVisibility(8);
            customHolder.mDivider.setVisibility(8);
        } else {
            customHolder.mTop.setMinimumHeight(this.mItemDecorationSpace);
            customHolder.mBottom.setMinimumHeight((this.mItemDecorationSpace * 5) / 2);
            customHolder.mBottom.setVisibility(0);
            customHolder.mDivider.setVisibility(8);
        }
    }
}
