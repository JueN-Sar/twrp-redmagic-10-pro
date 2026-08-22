package cn.nubia.gamelauncher.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.atmosphere.Atmosphere;
import cn.nubia.gamelauncher.bean.AppListItemBean;
import cn.nubia.gamelauncher.helper.CardHelper;
import cn.nubia.gamelauncher.recycler.BannerCardTransformation;
import cn.nubia.gamelauncher.view.AlphaGroup;
import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class HostGameAdapter extends RecyclerView.Adapter {
    private static final String TAG = "HostMode";
    CardHelper mCardHelper;
    private Context mContext;
    ArrayList<AppListItemBean> mList;
    AppClickListener mListener;

    public interface AppClickListener {
        void onAppBeanClick(AppListItemBean appListItemBean, String str);
    }

    class HostGameHolder extends RecyclerView.ViewHolder {
        public ImageView mBanner;
        public ImageView mBg;
        public View mCard;
        public ImageView mCenterIcon;
        public TextView mGameName;
        public AlphaGroup mGroup;
        public ImageView mLeftIcon;
        public TextView mManagerGame;
        public TextView mPlayTime;

        public HostGameHolder(View view) {
            super(view);
            this.mCard = view;
            this.mBg = (ImageView) view.findViewById(R.id.host_game_item_bg);
            this.mBanner = (ImageView) view.findViewById(R.id.host_game_banner);
            this.mGameName = (TextView) view.findViewById(R.id.host_game_name);
            this.mPlayTime = (TextView) view.findViewById(R.id.host_game_total_time);
            this.mCenterIcon = (ImageView) view.findViewById(R.id.host_game_center_icon);
            this.mLeftIcon = (ImageView) view.findViewById(R.id.host_game_left_icon);
            this.mGroup = (AlphaGroup) view.findViewById(R.id.host_game_bottom_group);
            this.mManagerGame = (TextView) view.findViewById(R.id.host_game_manager_game);
        }
    }

    public HostGameAdapter(Context context, ArrayList<AppListItemBean> arrayList, AppClickListener appClickListener, CardHelper cardHelper) {
        this.mContext = context;
        this.mListener = appClickListener;
        this.mList = arrayList;
        this.mCardHelper = cardHelper;
        Log.d(TAG, "---->HostGameAdapter() mList.size() : " + this.mList.size());
    }

    private void addItemClickListener(final int i, View view) {
        view.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.adapter.HostGameAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                HostGameAdapter.this.mListener.onAppBeanClick(HostGameAdapter.this.mList.get(i), "game_list");
            }
        });
    }

    private void bindAddGame(HostGameHolder hostGameHolder) {
        Log.d(TAG, "---->bindAddGame() item");
        hostGameHolder.mGroup.setAlpha(0.0f);
        hostGameHolder.mManagerGame.setVisibility(0);
        hostGameHolder.mBanner.setImageResource(R.mipmap.host_add_game);
    }

    private void bindGame(HostGameHolder hostGameHolder, int i) {
        AppListItemBean appListItemBean = this.mList.get(i);
        hostGameHolder.mGroup.setAlpha(1.0f);
        hostGameHolder.mManagerGame.setVisibility(8);
        hostGameHolder.mGameName.setText(appListItemBean.getName());
        Bitmap icon = appListItemBean.getIcon();
        hostGameHolder.mCenterIcon.setVisibility(0);
        hostGameHolder.mCenterIcon.setImageBitmap(icon);
        hostGameHolder.mLeftIcon.setImageBitmap(icon);
        hostGameHolder.mPlayTime.setText(getTotalString(appListItemBean));
        String atmosphereUrl = appListItemBean.getAtmosphereUrl();
        hostGameHolder.mCenterIcon.setTag(atmosphereUrl);
        Log.d(TAG, "---->bindGame(" + i + ") item : " + appListItemBean.getName() + ", id : " + hostGameHolder.mBanner.toString() + ", url : " + atmosphereUrl);
        fillCardView(this.mContext, hostGameHolder.mBanner, hostGameHolder.mCenterIcon, atmosphereUrl, 5);
    }

    private void fillCardView(Context context, ImageView imageView, final View view, final String str, int i) {
        if (str == null || context == null || Atmosphere.isDefaultUrl(str)) {
            return;
        }
        Glide.with(this.mContext).load(str).placeholder(R.mipmap.host_game_item_default).transform(new BannerCardTransformation(i)).diskCacheStrategy(DiskCacheStrategy.SOURCE).into((DrawableRequestBuilder<String>) new ViewTarget<ImageView, GlideDrawable>(imageView) { // from class: cn.nubia.gamelauncher.adapter.HostGameAdapter.1
            public void onResourceReady(GlideDrawable glideDrawable, GlideAnimation<? super GlideDrawable> glideAnimation) {
                String str2;
                if (view.getTag() == null || (str2 = (String) view.getTag()) == null || !str2.equals(str)) {
                    return;
                }
                view.setVisibility(8);
                ((ImageView) this.view).setImageDrawable(glideDrawable);
            }

            @Override // com.bumptech.glide.request.target.Target
            public /* bridge */ /* synthetic */ void onResourceReady(Object obj, GlideAnimation glideAnimation) {
                onResourceReady((GlideDrawable) obj, (GlideAnimation<? super GlideDrawable>) glideAnimation);
            }
        });
    }

    private String getTotalString(AppListItemBean appListItemBean) {
        return appListItemBean.getTotalTimeHour() == 0 ? this.mContext.getResources().getString(R.string.string_less_than_an_hour) : this.mContext.getResources().getString(R.string.string_playing_for, Long.valueOf(appListItemBean.getTotalTimeHour()));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        HostGameHolder hostGameHolder = (HostGameHolder) viewHolder;
        if (this.mList.get(i).isAddItem()) {
            bindAddGame(hostGameHolder);
        } else {
            bindGame(hostGameHolder, i);
        }
        addItemClickListener(i, hostGameHolder.mCard);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new HostGameHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.host_game_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewRecycled(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder != null && (viewHolder instanceof HostGameHolder)) {
            HostGameHolder hostGameHolder = (HostGameHolder) viewHolder;
            hostGameHolder.mGameName.setText("");
            hostGameHolder.mBanner.setImageResource(R.mipmap.host_game_item_default);
            hostGameHolder.mCenterIcon.setVisibility(8);
            hostGameHolder.mManagerGame.setVisibility(8);
        }
        super.onViewRecycled(viewHolder);
    }
}
