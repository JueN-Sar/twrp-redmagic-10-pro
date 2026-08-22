package cn.nubia.gamecenter.settings.records;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.media3.common.MimeTypes;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.records.utils.HighLightsUtils;
import cn.nubia.gamecenter.settings.utils.LogUtil;
import cn.nubia.settings.trackclient.NubiaTrackManager;
import java.util.List;

/* loaded from: classes.dex */
public class ImagelistAdapter extends RecyclerView.Adapter<RecyclerListImageViewHolder> {
    private static final String APPLICATION_ID = "com.android.settings.files";
    private static final String TAG = "ImagelistAdapter";
    private Context mContext;
    private String mImagePath;
    private List<VideoFile> mVideoList;

    ImagelistAdapter(Context context, List<VideoFile> list, String str) {
        this.mContext = context;
        this.mVideoList = list;
        this.mImagePath = str;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mVideoList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerListImageViewHolder recyclerListImageViewHolder, final int i) {
        try {
            List<VideoFile> list = this.mVideoList;
            if (list != null) {
                VideoListUtil.setThumbImage(this.mContext, list.get(i), recyclerListImageViewHolder.mImageView);
            }
            recyclerListImageViewHolder.itemView.setTag(Integer.valueOf(i));
            recyclerListImageViewHolder.mItemLayout.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamecenter.settings.records.ImagelistAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (ImagelistAdapter.this.mVideoList != null) {
                        if (ImagelistAdapter.this.mImagePath != null) {
                            NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", HighLightsUtils.TRACK_SCREENSHOT_EVENT, HighLightsUtils.TRACK_GAME_NAME_KEY, ImagelistAdapter.this.mImagePath.substring(ImagelistAdapter.this.mImagePath.lastIndexOf("/") + 1));
                        }
                        ImagelistAdapter imagelistAdapter = ImagelistAdapter.this;
                        imagelistAdapter.openImage(imagelistAdapter.mContext, ((VideoFile) ImagelistAdapter.this.mVideoList.get(i)).getUri());
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerListImageViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new RecyclerListImageViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout.gcs_gamecenter_fragment_image_list_item, viewGroup, false));
    }

    public void openImage(Context context, Uri uri) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(uri, MimeTypes.IMAGE_JPEG);
            intent.putExtra("is_game_highlights", "cn.nubia.gamehighlights");
            context.startActivity(intent);
        } catch (Exception e) {
            LogUtil.d(TAG, "Exception" + e);
        }
    }
}
