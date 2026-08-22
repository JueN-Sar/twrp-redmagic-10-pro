package cn.nubia.gamecenter.settings.records;

import android.content.ContentValues;
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
import cn.nubia.gamecenter.settings.recordsdb.HighLightsDb;
import cn.nubia.gamecenter.settings.recordsdb.RTimeDataBaseHelper;
import cn.nubia.gamecenter.settings.utils.LogUtil;
import cn.nubia.settings.trackclient.NubiaTrackManager;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes.dex */
public class VideolistAdapter extends RecyclerView.Adapter<RecyclerListViewHolder> {
    private static final String APPLICATION_ID = "com.android.settings.files";
    private static final String TAG = "RecordsFragment";
    private Context mContext;
    private HashMap<String, Integer> mVideoHashMap;
    private List<VideoFile> mVideoList;
    private String mVideoPath;

    VideolistAdapter(Context context, HashMap<String, Integer> hashMap, List<VideoFile> list, String str) {
        this.mContext = context;
        this.mVideoHashMap = hashMap;
        this.mVideoList = list;
        this.mVideoPath = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ContentValues covertToVideoContentValues(HighLightsDb highLightsDb) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("path", highLightsDb.getPath());
        contentValues.put("isPreview", (Integer) 1);
        return contentValues;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mVideoList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerListViewHolder recyclerListViewHolder, final int i) {
        List<VideoFile> list = this.mVideoList;
        if (list != null) {
            VideoListUtil.setThumbImage(this.mContext, list.get(i), recyclerListViewHolder.mImageView);
            recyclerListViewHolder.mVideoLengthTime.setText(VideoListUtil.getFormatDuration(this.mVideoList.get(i).getTime()));
            String title = this.mVideoList.get(i).getTitle();
            String videoPath = this.mVideoList.get(i).getVideoPath();
            HashMap<String, Integer> hashMap = this.mVideoHashMap;
            if (hashMap == null || hashMap.size() <= 0) {
                recyclerListViewHolder.mNewIcon.setVisibility(0);
                recyclerListViewHolder.mVideoLabel.setVisibility(8);
            } else if ((this.mVideoHashMap.get(videoPath) == null || 1 != this.mVideoHashMap.get(videoPath).intValue()) && this.mVideoHashMap.get(videoPath) != null) {
                recyclerListViewHolder.mNewIcon.setVisibility(0);
                recyclerListViewHolder.mVideoLabel.setVisibility(8);
            } else {
                recyclerListViewHolder.mNewIcon.setVisibility(8);
                recyclerListViewHolder.mVideoLabel.setVisibility(0);
                if (title != null) {
                    if (title.contains("full")) {
                        recyclerListViewHolder.mVideoLabel.setText(R.string.gcs_game_video_label_full);
                    } else if (title.contains("death")) {
                        recyclerListViewHolder.mVideoLabel.setText(R.string.gcs_game_video_label_death);
                    } else if (title.contains("collection")) {
                        recyclerListViewHolder.mVideoLabel.setText(R.string.gcs_game_video_label_collection);
                    } else if (title.contains("moment")) {
                        recyclerListViewHolder.mVideoLabel.setText(R.string.gcs_game_video_label_moment);
                    } else if (title.contains("manual") || videoPath.contains("manual")) {
                        recyclerListViewHolder.mVideoLabel.setText(R.string.gcs_game_video_label_manual);
                    } else {
                        recyclerListViewHolder.mVideoLabel.setVisibility(8);
                    }
                }
            }
        }
        recyclerListViewHolder.itemView.setTag(Integer.valueOf(i));
        recyclerListViewHolder.mItemLayout.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamecenter.settings.records.VideolistAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (VideolistAdapter.this.mVideoList != null) {
                    if (VideolistAdapter.this.mVideoPath != null) {
                        NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", HighLightsUtils.TRACK_VIDEO_EVENT, HighLightsUtils.TRACK_GAME_NAME_KEY, VideolistAdapter.this.mVideoPath.substring(VideolistAdapter.this.mVideoPath.lastIndexOf("/") + 1));
                    }
                    VideolistAdapter.this.mContext.getContentResolver().update(RTimeDataBaseHelper.REDMAGICTIME_NOTIFY_URI, VideolistAdapter.this.covertToVideoContentValues(new HighLightsDb(((VideoFile) VideolistAdapter.this.mVideoList.get(i)).getVideoPath(), 1)), "path=?", new String[]{((VideoFile) VideolistAdapter.this.mVideoList.get(i)).getVideoPath()});
                    if (VideoListUtil.isNubiaOS() || VideoListUtil.isInternal()) {
                        VideolistAdapter videolistAdapter = VideolistAdapter.this;
                        videolistAdapter.playVideo(videolistAdapter.mContext, ((VideoFile) VideolistAdapter.this.mVideoList.get(i)).getUri());
                    } else {
                        VideolistAdapter videolistAdapter2 = VideolistAdapter.this;
                        videolistAdapter2.playZteVideo(videolistAdapter2.mContext, ((VideoFile) VideolistAdapter.this.mVideoList.get(i)).getUri());
                    }
                }
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new RecyclerListViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout.gcs_gamecenter_fragment_record_list_item, viewGroup, false));
    }

    public void playVideo(Context context, Uri uri) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.putExtra("is_game_highlights", "cn.nubia.gamehighlights");
            intent.setDataAndType(uri, MimeTypes.VIDEO_MP4);
            context.startActivity(intent);
        } catch (Exception e) {
            LogUtil.d(TAG, "Exception" + e);
        }
    }

    public void playZteVideo(Context context, Uri uri) {
        try {
            Intent intent = new Intent("android.intent.action.EDIT");
            intent.setClassName("com.zte.videoplayer", "com.zte.videoplayer.VideoPlayerActivity");
            intent.setAction("com.android.camera.action.REVIEW");
            intent.putExtra("gallery_rotation", true);
            intent.setDataAndType(uri, MimeTypes.VIDEO_MP4);
            context.startActivity(intent);
        } catch (Exception e) {
            LogUtil.d(TAG, "Exception" + e);
        }
    }
}
