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
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.atmosphere.Atmosphere;
import cn.nubia.gamelauncher.bean.AppListItemBean;
import cn.nubia.gamelauncher.helper.CardHelper;
import cn.nubia.gamelauncher.layoutmanager.HandheldLayoutManager;
import cn.nubia.gamelauncher.util.Util;
import cn.nubia.gamelauncher.view.HandheldItemLayout;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
public class HandheldAdapter extends RecyclerView.Adapter {
    public static final int POSITION_ALL = 9;
    private static final String TAG = "Handheld";
    public static final int TYPE_MORE = 2;
    public static final int TYPE_RECTANGLE = 1;
    public static final int TYPE_SQUARE = 0;
    CardHelper mCardHelper;
    private final Context mContext;
    HandheldLayoutManager mHandheldManager;
    CopyOnWriteArrayList<AppListItemBean> mList;
    AppClickListener mListener;

    public interface AppClickListener {
        void onAppBeanClick(AppListItemBean appListItemBean, String str, boolean z);
    }

    class HandheldHolder extends RecyclerView.ViewHolder {
        public ImageView card_mask;
        public ImageView iv_allgame_1;
        public ImageView iv_allgame_2;
        public ImageView iv_allgame_3;
        public ImageView iv_allgame_4;
        public ImageView iv_game_cover;
        public ImageView iv_game_icon;
        public HandheldItemLayout mBanner;
        public View mContentView;
        public TextView mGameName;
        public ImageView mLogo;
        public int position;

        public HandheldHolder(View view, View view2, int i) {
            super(view);
            this.mBanner = (HandheldItemLayout) view.findViewById(R.id.handheld_banner);
            this.mGameName = (TextView) view.findViewById(R.id.handheld_game_name);
            this.mBanner.setFocusable(true);
            this.mBanner.setFocusableInTouchMode(true);
            setOnFocusChangeListener();
            this.mContentView = view2;
            this.mBanner.setContentView(view2);
            if (i == 2) {
                this.iv_allgame_1 = (ImageView) view2.findViewById(R.id.iv_allgame_1);
                this.iv_allgame_2 = (ImageView) view2.findViewById(R.id.iv_allgame_2);
                this.iv_allgame_3 = (ImageView) view2.findViewById(R.id.iv_allgame_3);
                this.iv_allgame_4 = (ImageView) view2.findViewById(R.id.iv_allgame_4);
                return;
            }
            this.iv_game_cover = (ImageView) view2.findViewById(R.id.iv_game_cover);
            this.iv_game_icon = (ImageView) view2.findViewById(R.id.iv_game_icon);
            this.card_mask = (ImageView) view2.findViewById(R.id.card_mask);
            this.mLogo = (ImageView) view2.findViewById(R.id.iv_game_logo);
        }

        public ImageView getImageViewForAllGame(int i) {
            return i != 0 ? i != 1 ? i != 2 ? i != 3 ? this.iv_allgame_4 : this.iv_allgame_4 : this.iv_allgame_3 : this.iv_allgame_2 : this.iv_allgame_1;
        }

        public void setOnFocusChangeListener() {
            this.mBanner.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: cn.nubia.gamelauncher.adapter.HandheldAdapter.HandheldHolder.1
                @Override // android.view.View.OnFocusChangeListener
                public void onFocusChange(View view, boolean z) {
                    HandheldHolder.this.mBanner.setSelect(z, HandheldHolder.this.mGameName, HandheldAdapter.this.isExpand());
                }
            });
            this.mBanner.setOnTouchListener(new View.OnTouchListener() { // from class: cn.nubia.gamelauncher.adapter.HandheldAdapter.HandheldHolder.2
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() != 1) {
                        return false;
                    }
                    HandheldHolder.this.mBanner.postDelayed(new Runnable() { // from class: cn.nubia.gamelauncher.adapter.HandheldAdapter.HandheldHolder.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (HandheldHolder.this.mBanner.hasFocus()) {
                                HandheldAdapter.this.mListener.onAppBeanClick(HandheldAdapter.this.mList.get(HandheldHolder.this.position), "game_list", 2 == HandheldAdapter.this.getItemViewType(HandheldHolder.this.position));
                            }
                        }
                    }, 467L);
                    return false;
                }
            });
        }
    }

    public HandheldAdapter(Context context, HandheldLayoutManager handheldLayoutManager, CopyOnWriteArrayList<AppListItemBean> copyOnWriteArrayList, AppClickListener appClickListener, CardHelper cardHelper) {
        this.mContext = context;
        this.mHandheldManager = handheldLayoutManager;
        this.mListener = appClickListener;
        this.mList = copyOnWriteArrayList;
        this.mCardHelper = cardHelper;
        Log.d("Handheld", "---->HostGameAdapter() mList.size() : " + this.mList.size());
    }

    private void addItemClickListener(final int i, View view) {
        view.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.adapter.HandheldAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                HandheldAdapter.this.mListener.onAppBeanClick(HandheldAdapter.this.mList.get(i), "game_list", 2 == HandheldAdapter.this.getItemViewType(i));
            }
        });
    }

    private void fillCoverView(Context context, ImageView imageView, String str, int i) {
        if (str == null || context == null) {
            return;
        }
        Glide.with(this.mContext).load(str).override(i == 1 ? 818 : 440, 440).placeholder(R.mipmap.host_game_item_default).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
    }

    private int getDefaultCard(int i) {
        return (i == 0 || i != 1) ? R.mipmap.banner_foreground_small : R.mipmap.banner_foreground_medium;
    }

    private String getTotalString(AppListItemBean appListItemBean) {
        return appListItemBean.getTotalTimeHour() == 0 ? this.mContext.getResources().getString(R.string.string_less_than_an_hour) : this.mContext.getResources().getString(R.string.string_playing_for, Long.valueOf(appListItemBean.getTotalTimeHour()));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return !this.mHandheldManager.isExpand() ? Math.min(this.mList.size(), 10) : this.mList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        if (isExpand()) {
            return 0;
        }
        if (i == 0) {
            return 1;
        }
        return i >= 9 ? 2 : 0;
    }

    public boolean isExpand() {
        return this.mHandheldManager.isExpand();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        HandheldHolder handheldHolder = (HandheldHolder) viewHolder;
        handheldHolder.position = i;
        if (getItemViewType(i) == 2) {
            handheldHolder.mBanner.setSquare(true);
            handheldHolder.mGameName.setText(R.string.all_games);
            handheldHolder.mGameName.setAlpha(0.0f);
            int i2 = 9;
            for (int i3 = 0; i3 < 4; i3++) {
                handheldHolder.getImageViewForAllGame(i3).setImageBitmap(null);
                if (i2 <= this.mList.size() - 1) {
                    AppListItemBean appListItemBean = this.mList.get(i2);
                    String atmosphereUrl = appListItemBean.getAtmosphereUrl();
                    if (atmosphereUrl.isEmpty()) {
                        handheldHolder.getImageViewForAllGame(i3).setImageBitmap(appListItemBean.getIcon());
                    } else {
                        Glide.with(this.mContext).load(atmosphereUrl).override(140, 140).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(handheldHolder.getImageViewForAllGame(i3));
                    }
                    i2++;
                }
            }
        } else {
            AppListItemBean appListItemBean2 = this.mList.get(i);
            handheldHolder.mBanner.setSquare(getItemViewType(i) != 1);
            Bitmap icon = appListItemBean2.getIcon();
            handheldHolder.iv_game_icon.setImageBitmap(icon);
            handheldHolder.card_mask.setImageResource(getDefaultCard(getItemViewType(i)));
            handheldHolder.mLogo.setVisibility(appListItemBean2.isHandheldGame() ? 0 : 8);
            handheldHolder.mGameName.setText(appListItemBean2.getName());
            if (isExpand()) {
                handheldHolder.mGameName.setAlpha(1.0f);
            } else {
                handheldHolder.mGameName.setAlpha(0.0f);
            }
            String atmosphereUrl2 = appListItemBean2.getAtmosphereUrl();
            Log.d("Handheld", "---->bindGame(" + i + ") item : " + appListItemBean2.getName() + ", id : " + handheldHolder.mBanner.toString() + ", url : " + atmosphereUrl2);
            if (TextUtils.isEmpty(atmosphereUrl2) || Atmosphere.isDefaultUrl(atmosphereUrl2)) {
                handheldHolder.iv_game_cover.setVisibility(8);
                handheldHolder.iv_game_icon.setVisibility(0);
                Util.setBackgroundColorWithIcon(this.mContext, icon, (CardView) handheldHolder.mContentView);
            } else {
                fillCoverView(this.mContext, handheldHolder.iv_game_cover, atmosphereUrl2, getItemViewType(i));
                handheldHolder.iv_game_cover.setVisibility(0);
                handheldHolder.iv_game_icon.setVisibility(8);
            }
        }
        addItemClickListener(i, handheldHolder.mBanner);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new HandheldHolder(LayoutInflater.from(viewGroup.getContext()).inflate(i == 1 ? R.layout.handheld_item_rectangle : R.layout.handheld_item_square, viewGroup, false), LayoutInflater.from(viewGroup.getContext()).inflate(i == 2 ? R.layout.handheld_item_allgame : R.layout.handheld_item_image, viewGroup, false), i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewRecycled(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof HandheldHolder) {
            ((HandheldHolder) viewHolder).mGameName.setText("");
        }
        super.onViewRecycled(viewHolder);
    }

    public void setExpand(Boolean bool) {
        this.mHandheldManager.setExpand(bool.booleanValue());
        notifyDataSetChanged();
    }
}
