package cn.nubia.gamelauncher.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.common.view.ProgressView;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.bean.GameItemBean;
import cn.nubia.gamelauncher.commoninterface.NeoGameDBColumns;
import cn.nubia.gamelauncher.helper.CardHelper;
import cn.nubia.gamelauncher.util.Util;
import cn.nubia.gamelauncher.view.HandheldItemLayout;
import com.bumptech.glide.BitmapRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class RecommendAdapter extends RecyclerView.Adapter {
    public static final String PAYLOAD_GAME_COVER = "gamecover";
    public static final String PAYLOAD_PROGRESS = "progress";
    private static final String TAG = "Handheld";
    public static final int TYPE_MORE = 2;
    public static final int TYPE_RECTANGLE = 1;
    public static final int TYPE_SQUARE = 0;
    CardHelper mCardHelper;
    private final Context mContext;
    ArrayList<GameItemBean> mList;
    RecommendClickListener mListener;

    public interface RecommendClickListener {
        void onRecommendItemClick(GameItemBean gameItemBean, String str);

        void onRecommendMoreClick();
    }

    class RecommendHolder extends RecyclerView.ViewHolder {
        public ImageView card_mask;
        public ImageView iv_game_cover;
        public ImageView iv_game_icon;
        public HandheldItemLayout mBanner;
        public View mContentView;
        public TextView mGameName;
        public ImageView mLogoView;
        public ProgressView mProgressView;
        public int position;

        public RecommendHolder(View view, View view2) {
            super(view);
            this.mBanner = (HandheldItemLayout) view.findViewById(R.id.handheld_banner);
            this.mGameName = (TextView) view.findViewById(R.id.handheld_game_name);
            this.mBanner.setFocusable(true);
            this.mBanner.setFocusableInTouchMode(true);
            setOnFocusChangeListener();
            this.mContentView = view2;
            this.iv_game_cover = (ImageView) view2.findViewById(R.id.iv_game_cover);
            this.iv_game_icon = (ImageView) view2.findViewById(R.id.iv_game_icon);
            this.mLogoView = (ImageView) view2.findViewById(R.id.iv_game_logo);
            this.card_mask = (ImageView) view2.findViewById(R.id.card_mask);
            this.mProgressView = (ProgressView) view2.findViewById(R.id.icon_progress);
            this.mBanner.setContentView(this.mContentView);
        }

        public void setOnFocusChangeListener() {
            this.mBanner.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: cn.nubia.gamelauncher.adapter.RecommendAdapter.RecommendHolder.1
                @Override // android.view.View.OnFocusChangeListener
                public void onFocusChange(View view, boolean z) {
                    RecommendHolder.this.mBanner.setSelect(z, null, false);
                }
            });
            this.mBanner.setOnTouchListener(new View.OnTouchListener() { // from class: cn.nubia.gamelauncher.adapter.RecommendAdapter.RecommendHolder.2
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() != 1) {
                        return false;
                    }
                    RecommendHolder.this.mBanner.postDelayed(new Runnable() { // from class: cn.nubia.gamelauncher.adapter.RecommendAdapter.RecommendHolder.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (!RecommendHolder.this.mBanner.hasFocus() || RecommendAdapter.this.mListener == null) {
                                return;
                            }
                            if (RecommendHolder.this.position == RecommendAdapter.this.getItemCount() - 1) {
                                RecommendAdapter.this.mListener.onRecommendMoreClick();
                            } else {
                                RecommendAdapter.this.mListener.onRecommendItemClick(RecommendAdapter.this.mList.get(RecommendHolder.this.position), "game_list");
                            }
                        }
                    }, 300L);
                    return false;
                }
            });
        }
    }

    public RecommendAdapter(Context context, ArrayList<GameItemBean> arrayList, RecommendClickListener recommendClickListener, CardHelper cardHelper) {
        Log.d("Handheld", "mList size" + arrayList.size());
        this.mContext = context;
        this.mListener = recommendClickListener;
        this.mList = arrayList;
        this.mCardHelper = cardHelper;
    }

    private void addItemClickListener(final int i, View view) {
        view.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.adapter.RecommendAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                RecommendAdapter.this.m228x624d3c63(i, view2);
            }
        });
    }

    private void fillCoverView(Context context, ImageView imageView, String str, int i) {
        if (str == null || context == null) {
            return;
        }
        Glide.with(this.mContext).load(str).override(818, 440).placeholder(R.mipmap.host_game_item_default).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
    }

    private void fillIconView(Context context, final ImageView imageView, final CardView cardView, String str) {
        if (str == null || context == null) {
            return;
        }
        Glide.with(this.mContext).load(str).asBitmap().diskCacheStrategy(DiskCacheStrategy.SOURCE).into((BitmapRequestBuilder<String, Bitmap>) new SimpleTarget<Bitmap>() { // from class: cn.nubia.gamelauncher.adapter.RecommendAdapter.1
            public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
                imageView.setImageBitmap(bitmap);
                Util.setBackgroundColorWithIcon(RecommendAdapter.this.mContext, bitmap, cardView);
            }

            @Override // com.bumptech.glide.request.target.Target
            public /* bridge */ /* synthetic */ void onResourceReady(Object obj, GlideAnimation glideAnimation) {
                onResourceReady((Bitmap) obj, (GlideAnimation<? super Bitmap>) glideAnimation);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        ArrayList<GameItemBean> arrayList = this.mList;
        if (arrayList == null || arrayList.size() < 1) {
            return 0;
        }
        return this.mList.size() + 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return i == getItemCount() - 1 ? 2 : 1;
    }

    /* renamed from: lambda$addItemClickListener$0$cn-nubia-gamelauncher-adapter-RecommendAdapter, reason: not valid java name */
    /* synthetic */ void m228x624d3c63(int i, View view) {
        if (this.mListener == null) {
            return;
        }
        if (i == getItemCount() - 1) {
            this.mListener.onRecommendMoreClick();
        } else {
            this.mListener.onRecommendItemClick(this.mList.get(i), "game_list");
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        RecommendHolder recommendHolder = (RecommendHolder) viewHolder;
        recommendHolder.position = i;
        if (getItemViewType(i) == 2) {
            recommendHolder.mBanner.setSquare(true);
            recommendHolder.mGameName.setText(R.string.handheld_recommend_more);
            recommendHolder.iv_game_cover.setVisibility(0);
            recommendHolder.iv_game_icon.setVisibility(8);
            recommendHolder.iv_game_cover.setImageResource(R.mipmap.handheld_to_gameshop);
        } else {
            recommendHolder.mBanner.setSquare(false);
            GameItemBean gameItemBean = this.mList.get(i);
            recommendHolder.mGameName.setText(gameItemBean.getSoftName());
            recommendHolder.card_mask.setImageResource(R.mipmap.banner_foreground_medium);
            String url = gameItemBean.getUrl();
            if (TextUtils.isEmpty(url)) {
                recommendHolder.iv_game_cover.setVisibility(8);
                fillIconView(this.mContext, recommendHolder.iv_game_icon, (CardView) recommendHolder.mContentView, gameItemBean.getIconUrl());
                recommendHolder.iv_game_icon.setVisibility(0);
            } else {
                fillCoverView(this.mContext, recommendHolder.iv_game_cover, url, 5);
                recommendHolder.iv_game_cover.setVisibility(0);
                recommendHolder.iv_game_icon.setVisibility(8);
            }
            recommendHolder.mLogoView.setVisibility(0);
            if (gameItemBean.getProgress() > 0) {
                recommendHolder.mProgressView.setVisibility(0);
                recommendHolder.mProgressView.setProgress(gameItemBean.getProgress(), NeoGameDBColumns.STATUS_DOWNLOADING.equals(gameItemBean.getStatus()));
            } else {
                recommendHolder.mProgressView.setVisibility(8);
            }
        }
        addItemClickListener(i, recommendHolder.mBanner);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i, List list) {
        if (list.isEmpty()) {
            onBindViewHolder(viewHolder, i);
            return;
        }
        String str = (String) list.get(0);
        RecommendHolder recommendHolder = (RecommendHolder) viewHolder;
        recommendHolder.position = i;
        GameItemBean gameItemBean = this.mList.get(i);
        if ("progress".equals(str)) {
            if (gameItemBean.getProgress() <= 0) {
                recommendHolder.mProgressView.setVisibility(8);
                return;
            } else {
                recommendHolder.mProgressView.setVisibility(0);
                recommendHolder.mProgressView.setProgress(gameItemBean.getProgress(), NeoGameDBColumns.STATUS_DOWNLOADING.equals(gameItemBean.getStatus()));
                return;
            }
        }
        if (PAYLOAD_GAME_COVER.equals(str)) {
            String url = gameItemBean.getUrl();
            if (TextUtils.isEmpty(url)) {
                recommendHolder.iv_game_cover.setVisibility(8);
                fillIconView(this.mContext, recommendHolder.iv_game_icon, (CardView) recommendHolder.mContentView, gameItemBean.getIconUrl());
                recommendHolder.iv_game_icon.setVisibility(0);
            } else {
                fillCoverView(this.mContext, recommendHolder.iv_game_cover, url, 5);
                recommendHolder.iv_game_cover.setVisibility(0);
                recommendHolder.iv_game_icon.setVisibility(8);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new RecommendHolder(LayoutInflater.from(viewGroup.getContext()).inflate(i == 1 ? R.layout.handheld_item_rectangle : R.layout.handheld_item_square, viewGroup, false), LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.handheld_item_image, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewRecycled(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof RecommendHolder) {
            ((RecommendHolder) viewHolder).mGameName.setText("");
        }
        super.onViewRecycled(viewHolder);
    }
}
