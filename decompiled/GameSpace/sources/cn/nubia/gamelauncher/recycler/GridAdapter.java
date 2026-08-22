package cn.nubia.gamelauncher.recycler;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.common.helper.AppUsageStatsHelper;
import cn.nubia.common.util.BitmapUtils;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.atmosphere.Atmosphere;
import cn.nubia.gamelauncher.bean.AppListItemBean;
import cn.nubia.gamelauncher.bean.NeoIconDownloadInfo;
import cn.nubia.gamelauncher.model.NeoDownloadHelper;
import cn.nubia.gamelauncher.util.CommonUtil;
import cn.nubia.gamelauncher.util.LogUtil;
import cn.nubia.gamelauncher.util.Util;
import cn.nubia.gamelauncher.view.DrawableLeftTextView;
import com.bumptech.glide.BitmapRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
public class GridAdapter extends RecyclerView.Adapter {
    public static int MEDIUM_COUNT_IN_MEDIUM_MODE = 3;
    public static final String TAG = "Grid";
    private Context mContext;
    CopyOnWriteArrayList<AppListItemBean> mList;
    OnAppBeanClickListener mListener;
    private HashMap<Integer, GridHolder> mNeoDownloadIconMap = new HashMap<>();

    public class GridHolder extends RecyclerView.ViewHolder {
        public ImageView mBanner;
        public CardView mCard;
        public ImageView mIcon;
        public ImageView mMask;
        public DrawableLeftTextView mNameView;
        public TextView mState;

        public GridHolder(View view) {
            super(view);
            this.mCard = (CardView) view;
            this.mIcon = (ImageView) view.findViewById(R.id.icon);
            this.mBanner = (ImageView) view.findViewById(R.id.game_banner);
            this.mNameView = (DrawableLeftTextView) view.findViewById(R.id.game_name);
            this.mState = (TextView) view.findViewById(R.id.download_state_text);
            this.mMask = (ImageView) view.findViewById(R.id.card_mask);
        }
    }

    public interface OnAppBeanClickListener {
        void onAppBeanClick(AppListItemBean appListItemBean);
    }

    public GridAdapter(Context context, CopyOnWriteArrayList<AppListItemBean> copyOnWriteArrayList, OnAppBeanClickListener onAppBeanClickListener) {
        this.mContext = context;
        this.mList = copyOnWriteArrayList;
        this.mListener = onAppBeanClickListener;
        LogUtil.d(TAG, "---->GridAdapter() mList.size() : " + this.mList.size());
    }

    private void addItemClickListener(final int i, View view) {
        view.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.recycler.GridAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                GridAdapter.this.mListener.onAppBeanClick(GridAdapter.this.mList.get(i));
            }
        });
    }

    private void bindDownloadGame(GridHolder gridHolder, int i, AppListItemBean appListItemBean) {
        if (gridHolder == null || appListItemBean == null) {
            return;
        }
        NeoIconDownloadInfo downloadInfo = appListItemBean.getDownloadInfo();
        Bitmap icon = appListItemBean.getIcon();
        this.mNeoDownloadIconMap.put(Integer.valueOf(downloadInfo.appId), gridHolder);
        LogUtil.d(TAG, "bindDownloadGame() state : " + downloadInfo.status);
        LogUtil.d(NeoDownloadHelper.TAG, "bindDownloadGame(" + downloadInfo.appId + ") status : " + downloadInfo.status);
        Bitmap bitmap = downloadInfo.progressIcon;
        if (bitmap != null) {
            gridHolder.mIcon.setBackground(BitmapUtils.convertBitmapToDrawable(bitmap));
        } else {
            gridHolder.mIcon.setBackground(BitmapUtils.convertBitmapToDrawable(icon));
        }
        gridHolder.mState.setVisibility(0);
        gridHolder.mState.setText(CommonUtil.convertToShowStateText(downloadInfo.status));
        updateGameNameView(gridHolder, downloadInfo.mIcon, appListItemBean.getName());
    }

    private void bindGame(GridHolder gridHolder, int i) {
        View view;
        AppListItemBean appListItemBean = this.mList.get(i);
        Bitmap icon = appListItemBean.getIcon();
        int typeForMedium = getTypeForMedium(i);
        gridHolder.mIcon.setImageBitmap(icon);
        String name = appListItemBean.getName();
        gridHolder.mNameView.setText(name);
        if (CommonUtil.isInternalVersion()) {
            gridHolder.mNameView.setMaxLines(2);
        }
        if (appListItemBean.isDownloadItem()) {
            bindDownloadGame(gridHolder, i, appListItemBean);
        } else {
            gridHolder.mNameView.setVisibility(8);
            gridHolder.mState.setVisibility(8);
            gridHolder.mState.setTag(null);
            updateGameNameView(gridHolder, icon, name);
        }
        String urlByItem = getUrlByItem(appListItemBean, typeForMedium);
        if (typeForMedium == 2) {
            view = gridHolder.mNameView;
            gridHolder.mIcon.setVisibility(8);
            view.setVisibility(0);
        } else if (typeForMedium == 3) {
            view = gridHolder.mIcon;
            gridHolder.mNameView.setVisibility(8);
            view.setVisibility(0);
        } else {
            view = null;
        }
        gridHolder.mBanner.setImageResource(getDefaultCard(typeForMedium));
        gridHolder.mMask.setImageResource(getDefaultCard(typeForMedium));
        view.setTag(urlByItem);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(this.mContext.getResources(), getDefaultCard(typeForMedium), options);
        int i2 = options.outWidth;
        int i3 = options.outHeight;
        LogUtil.d(TAG, "bindGame(" + appListItemBean.getName() + ") position : " + i + ", type : " + typeForMedium + ", w : " + i2 + ", url : " + urlByItem);
        if (appListItemBean.isAddItem()) {
            gridHolder.mBanner.setTag(R.id.game_banner, urlByItem);
            gridHolder.mIcon.setVisibility(8);
            LogUtil.d(TAG, "bindAddView(" + appListItemBean.getName() + ")");
            urlByItem = "android.resource://cn.nubia.gamelauncher/mipmap/pic_card_add";
        }
        if (TextUtils.isEmpty(urlByItem) || Atmosphere.isDefaultUrl(urlByItem)) {
            LogUtil.d(TAG, "bindCardView(" + appListItemBean.getName() + ") -> setBackgroundColorWithIcon() and view : " + gridHolder.mBanner);
            gridHolder.mBanner.setTag(R.id.game_banner, null);
            Util.setBackgroundColorWithIcon(this.mContext, icon, gridHolder.mCard);
        } else if (appListItemBean.isAddItem()) {
            gridHolder.mBanner.setTag(R.id.game_banner, urlByItem);
            gridHolder.mBanner.setBackgroundResource(R.mipmap.pic_card_add);
            view.setVisibility(8);
        } else {
            LogUtil.d(TAG, "------------>fillCard name : " + name + ", type : " + typeForMedium + ", position : " + i + "\nview : " + gridHolder.mBanner + "\nurl : " + urlByItem);
            gridHolder.mBanner.setTag(R.id.game_banner, urlByItem);
            fillCardView(gridHolder.mBanner, view, urlByItem, i2, i3, typeForMedium, i);
        }
        addItemClickListener(i, gridHolder.mCard);
    }

    private void fillCardView(final ImageView imageView, final View view, final String str, final int i, final int i2, final int i3, final int i4) {
        if (str == null) {
            return;
        }
        LogUtil.d(TAG, "fillCardView  ImageURL = " + str);
        Glide.with(this.mContext).load(str).asBitmap().placeholder(getDefaultCard(i3)).diskCacheStrategy(DiskCacheStrategy.SOURCE).into((BitmapRequestBuilder<String, Bitmap>) new SimpleTarget<Bitmap>() { // from class: cn.nubia.gamelauncher.recycler.GridAdapter.2
            public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
                imageView.getDrawingRect(new Rect());
                LogUtil.d(GridAdapter.TAG, "======================>onResourceReady() type : " + i3 + ", position : " + i4 + "\nview : " + imageView + "\ntag : " + imageView.getTag(R.id.game_banner));
                if (bitmap == null) {
                    LogUtil.w(GridAdapter.TAG, "resource is null, url : " + str + ", return!!!!");
                    return;
                }
                if (!str.equals(imageView.getTag(R.id.game_banner))) {
                    LogUtil.w(GridAdapter.TAG, "url and view do not match, return!!");
                    return;
                }
                if (bitmap.getWidth() <= 0 || bitmap.getHeight() <= 0) {
                    LogUtil.w(GridAdapter.TAG, "resource.size is 0, resource : " + bitmap + ", return!!!!");
                    return;
                }
                if (i <= 0 || i2 <= 0) {
                    LogUtil.w(GridAdapter.TAG, "size is 0, w : " + i + ", h : " + i2 + ", return!!!!");
                    return;
                }
                LogUtil.d(GridAdapter.TAG, "resource : " + bitmap + ", r.w : " + bitmap.getWidth() + ", r.h : " + bitmap.getHeight());
                LogUtil.d(GridAdapter.TAG, "load() w : " + i + ", h : " + i2);
                Bitmap cropBitmapTop = BitmapUtils.getCropBitmapTop(bitmap, i, i2, 20, 0);
                LogUtil.d(GridAdapter.TAG, "load() r.w : " + cropBitmapTop.getWidth() + ", r.h : " + cropBitmapTop.getHeight());
                imageView.setImageBitmap(cropBitmapTop);
                view.setVisibility(8);
            }

            @Override // com.bumptech.glide.request.target.Target
            public /* bridge */ /* synthetic */ void onResourceReady(Object obj, GlideAnimation glideAnimation) {
                onResourceReady((Bitmap) obj, (GlideAnimation<? super Bitmap>) glideAnimation);
            }
        });
    }

    private int getDefaultCard(int i) {
        return i != 2 ? (i == 3 && Build.DEVICE.equals("P780S03")) ? R.mipmap.banner_foreground_small_h_not_900 : R.mipmap.banner_foreground_small : Build.DEVICE.equals("P780S03") ? R.mipmap.banner_foreground_medium_h_not_900 : R.mipmap.banner_foreground_medium;
    }

    private int getTypeForMedium(int i) {
        if (i >= this.mList.size() - 1) {
            return 3;
        }
        AppListItemBean appListItemBean = this.mList.get(i);
        long cutoffTotalTime = AppUsageStatsHelper.getInstance().getCutoffTotalTime();
        long totalTimeMillisecond = appListItemBean.getTotalTimeMillisecond();
        LogUtil.v(TAG, "getTypeForMedium() gameName = " + appListItemBean.getName() + ", total : " + totalTimeMillisecond + ", cutoff : " + cutoffTotalTime);
        return (totalTimeMillisecond > 0 && totalTimeMillisecond >= cutoffTotalTime) ? 2 : 3;
    }

    private String getUrlByItem(AppListItemBean appListItemBean, int i) {
        return i == 3 ? appListItemBean.getMediumUrl() : appListItemBean.getAtmosphereUrl();
    }

    private boolean isAddPrefix(String str) {
        if (str == null || str.equals("")) {
            return true;
        }
        return (str.contains("http") || str.contains("storage")) ? false : true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        LogUtil.d(TAG, "----->onBindViewHolder(" + this.mList.size() + ") position : " + i);
        bindGame((GridHolder) viewHolder, i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LogUtil.d(TAG, "onCreateViewHolder(" + this.mList.size() + ") position : " + i);
        return new GridHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.game_item_medium, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewRecycled(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder != null && (viewHolder instanceof GridHolder)) {
            GridHolder gridHolder = (GridHolder) viewHolder;
            gridHolder.mNameView.setCompoundDrawables(null, null, null, null);
            gridHolder.mNameView.setVisibility(8);
            Glide.clear(gridHolder.mBanner);
            gridHolder.mBanner.setTag(R.id.game_banner, null);
            gridHolder.mBanner.setImageResource(getDefaultCard(viewHolder.getAdapterPosition()));
            gridHolder.mIcon.setVisibility(8);
        }
        super.onViewRecycled(viewHolder);
    }

    public void resetNeoDownloadMap() {
        this.mNeoDownloadIconMap.clear();
    }

    void updateGameNameView(GridHolder gridHolder, Bitmap bitmap, String str) {
        gridHolder.mNameView.setText(str);
        Drawable convertBitmapToDrawable = BitmapUtils.convertBitmapToDrawable(bitmap);
        int dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(R.dimen.medium_icon_size);
        convertBitmapToDrawable.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
        gridHolder.mNameView.setCompoundDrawablePadding(this.mContext.getResources().getDimensionPixelSize(R.dimen.text_compound_drawable_padding));
        gridHolder.mNameView.setCompoundDrawables(convertBitmapToDrawable, null, null, null);
    }

    public void updateNeoDownloadIcon(AppListItemBean appListItemBean, GridHolder gridHolder) {
        NeoIconDownloadInfo downloadInfo = appListItemBean.getDownloadInfo();
        LogUtil.d(NeoDownloadHelper.TAG, "updateNeoDownloadIcon(" + downloadInfo.appId + ") status : " + downloadInfo.status + ", progress : " + downloadInfo.progress);
        if (gridHolder == null) {
            gridHolder = this.mNeoDownloadIconMap.get(Integer.valueOf(downloadInfo.appId));
        }
        if (gridHolder == null || downloadInfo == null) {
            return;
        }
        ImageView imageView = gridHolder.mIcon;
        appListItemBean.setIcon(downloadInfo.mIcon);
        imageView.setBackground(BitmapUtils.convertBitmapToDrawable(downloadInfo.progressIcon));
        updateGameNameView(gridHolder, downloadInfo.mIcon, downloadInfo.title);
        gridHolder.mState.setVisibility(0);
        gridHolder.mState.setText(CommonUtil.convertToShowStateText(downloadInfo.status));
    }
}
