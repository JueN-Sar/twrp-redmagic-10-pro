package cn.nubia.gamecenter.settings.wallpaper;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.common.wallpaper.WallpaperItemBean;
import cn.nubia.gamecenter.settings.R;
import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import java.util.List;

/* loaded from: classes.dex */
public class WallpaperListAdapter extends RecyclerView.Adapter<WallpaperViewHolder> {
    private static final String TAG = "wallpaper";
    private Context mContext;
    private List<WallpaperItemBean> mList;
    OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(View view, WallpaperItemBean wallpaperItemBean);
    }

    class WallpaperViewHolder extends RecyclerView.ViewHolder {
        private ImageView bgView;
        TextView mLabelText;
        private ImageView mLabelView;
        private ImageView mMarkView;
        private ImageView mPreviewView;
        TextView mTitle;

        public WallpaperViewHolder(View view) {
            super(view);
            this.mPreviewView = (ImageView) view.findViewById(R.id.wallpaper_preview);
            this.mMarkView = (ImageView) view.findViewById(R.id.preview_selected_mark);
            this.mLabelView = (ImageView) view.findViewById(R.id.live_label);
            this.bgView = (ImageView) view.findViewById(R.id.preview_selected_bg);
            this.mTitle = (TextView) view.findViewById(R.id.title);
            this.mLabelText = (TextView) view.findViewById(R.id.live_label_text);
            view.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamecenter.settings.wallpaper.WallpaperListAdapter.WallpaperViewHolder.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    Log.d("wallpaper", "onClick() position : " + WallpaperViewHolder.this.getLayoutPosition());
                    if (WallpaperListAdapter.this.mListener == null) {
                        return;
                    }
                    WallpaperListAdapter.this.mListener.onItemClick(view2, (WallpaperItemBean) WallpaperListAdapter.this.mList.get(WallpaperViewHolder.this.getLayoutPosition()));
                }
            });
        }
    }

    public WallpaperListAdapter(Context context, List<WallpaperItemBean> list) {
        this.mList = list;
        this.mContext = context;
        Log.d("wallpaper", "adapter - WallpaperListAdapter list.size() : " + list.size());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final WallpaperViewHolder wallpaperViewHolder, final int i) {
        Log.d("wallpaper", "adapter - onBindViewHolder() item : " + this.mList.get(i).toString());
        if (i == 0) {
            wallpaperViewHolder.mTitle.setVisibility(0);
        } else {
            wallpaperViewHolder.mTitle.setVisibility(8);
        }
        if (this.mList.get(i).isLiveWallpaper()) {
            wallpaperViewHolder.mLabelText.setVisibility(0);
            wallpaperViewHolder.mLabelView.setVisibility(0);
        } else {
            wallpaperViewHolder.mLabelText.setVisibility(8);
            wallpaperViewHolder.mLabelView.setVisibility(8);
        }
        Glide.with(this.mContext).load(this.mList.get(i).getPreviewUrl()).transform(new WallpaperTransformation(this.mContext.getApplicationContext())).into((DrawableRequestBuilder<String>) new SimpleTarget<GlideDrawable>() { // from class: cn.nubia.gamecenter.settings.wallpaper.WallpaperListAdapter.1
            public void onResourceReady(GlideDrawable glideDrawable, GlideAnimation<? super GlideDrawable> glideAnimation) {
                wallpaperViewHolder.mPreviewView.setImageDrawable(glideDrawable);
                if (((WallpaperItemBean) WallpaperListAdapter.this.mList.get(i)).isSelected()) {
                    wallpaperViewHolder.mMarkView.setImageResource(R.drawable.wallpaper_selected_mark);
                    wallpaperViewHolder.bgView.setVisibility(0);
                } else {
                    wallpaperViewHolder.mMarkView.setImageResource(R.drawable.wallpaper_selected_unmark);
                    wallpaperViewHolder.bgView.setVisibility(8);
                }
            }

            @Override // com.bumptech.glide.request.target.Target
            public /* bridge */ /* synthetic */ void onResourceReady(Object obj, GlideAnimation glideAnimation) {
                onResourceReady((GlideDrawable) obj, (GlideAnimation<? super GlideDrawable>) glideAnimation);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public WallpaperViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new WallpaperViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.gcs_wallpaper_item, viewGroup, false));
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mListener = onItemClickListener;
    }

    public void updateMask(WallpaperViewHolder wallpaperViewHolder, int i) {
        Log.d("wallpaper", "updateMask() position : " + i + ", holder : " + wallpaperViewHolder);
        if (wallpaperViewHolder == null) {
            return;
        }
        if (this.mList.get(i).isSelected()) {
            wallpaperViewHolder.mMarkView.setImageResource(R.drawable.wallpaper_selected_mark);
            wallpaperViewHolder.bgView.setVisibility(0);
        } else {
            wallpaperViewHolder.mMarkView.setImageResource(R.drawable.wallpaper_selected_unmark);
            wallpaperViewHolder.bgView.setVisibility(8);
        }
    }
}
