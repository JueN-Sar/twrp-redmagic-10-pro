package cn.nubia.chatassistant.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.chatassistant.bean.ChatAssistantVoiceBean;
import cn.nubia.chatassistant.customchat.ChatAssistantSettingsActivity;
import cn.nubia.chatassistant.customchat.DeleteDialog;
import cn.nubia.chatassistant.ui.MusicLineView;
import cn.nubia.chatassistant.util.LogUtils;
import cn.nubia.chatassistant.util.MusicManagerUtils;
import cn.nubia.gamelauncher.R;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes.dex */
public class ChatAssistantSettingContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "ChatAssistantSettingContentAdapter";
    private WeakReference<Activity> activityWeakReference;
    private Context mContext;
    private List<ChatAssistantVoiceBean> mList;
    private int mSelectPosition = -1;
    private Timer mTimer;

    public class VoiceViewHolder extends RecyclerView.ViewHolder {
        private ImageView mDeleteVoice;
        private boolean mIsPlaying;
        private ImageView mPlayVoice;
        private TextView mVoiceName;
        private View mVoiceSelectItemLayout;
        private MusicLineView musicProcessLine;

        public VoiceViewHolder(View view) {
            super(view);
            this.mIsPlaying = false;
            initView(view);
        }

        private void initView(View view) {
            this.mVoiceName = (TextView) view.findViewById(R.id.tv_voice_name);
            this.mPlayVoice = (ImageView) view.findViewById(R.id.iv_play_voice);
            this.mVoiceSelectItemLayout = view.findViewById(R.id.rl_voice_select_item_layout);
            this.mDeleteVoice = (ImageView) view.findViewById(R.id.iv_delete_voice);
            this.musicProcessLine = (MusicLineView) view.findViewById(R.id.music_process_line);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void refreshUI(ChatAssistantVoiceBean chatAssistantVoiceBean, int i, boolean z) {
            setVoiceItemState(chatAssistantVoiceBean.isSystemDefault(), true);
            if (ChatAssistantSettingContentAdapter.this.mSelectPosition != i) {
                if (ChatAssistantSettingContentAdapter.this.mSelectPosition != -1) {
                    ChatAssistantSettingContentAdapter chatAssistantSettingContentAdapter = ChatAssistantSettingContentAdapter.this;
                    chatAssistantSettingContentAdapter.notifyItemChanged(chatAssistantSettingContentAdapter.mSelectPosition);
                }
                ChatAssistantSettingContentAdapter.this.mSelectPosition = i;
                if (z) {
                    MusicManagerUtils.getInstance().stopPlay();
                }
            }
        }

        private void setVoiceItemState(boolean z, boolean z2) {
            if (z2 && !z) {
                this.mDeleteVoice.setAlpha(1.0f);
                this.mVoiceName.setAlpha(1.0f);
                this.mPlayVoice.setAlpha(1.0f);
                this.mDeleteVoice.setVisibility(0);
                return;
            }
            if (z2 && z) {
                this.mDeleteVoice.setAlpha(1.0f);
                this.mVoiceName.setAlpha(1.0f);
                this.mPlayVoice.setAlpha(1.0f);
                this.mDeleteVoice.setVisibility(8);
                return;
            }
            this.mDeleteVoice.setAlpha(0.6f);
            this.mVoiceName.setAlpha(0.6f);
            this.mPlayVoice.setAlpha(0.6f);
            this.mDeleteVoice.setVisibility(8);
        }

        public void setData(final ChatAssistantVoiceBean chatAssistantVoiceBean, final int i) {
            if (TextUtils.isEmpty(chatAssistantVoiceBean.getVoiceFileName())) {
                this.mVoiceSelectItemLayout.setVisibility(8);
            } else {
                this.mVoiceSelectItemLayout.setVisibility(0);
            }
            if (i == ChatAssistantSettingContentAdapter.this.mSelectPosition) {
                setVoiceItemState(chatAssistantVoiceBean.isSystemDefault(), true);
                if (MusicManagerUtils.getInstance().isPlay()) {
                    this.musicProcessLine.setVisibility(0);
                    this.musicProcessLine.reStart(MusicManagerUtils.getInstance().getCurrentPosition(), MusicManagerUtils.getInstance().getDuration());
                    if (ChatAssistantSettingContentAdapter.this.mTimer != null) {
                        ChatAssistantSettingContentAdapter.this.mTimer.cancel();
                    }
                    ChatAssistantSettingContentAdapter.this.mTimer = new Timer();
                    ChatAssistantSettingContentAdapter.this.mTimer.schedule(new TimerTask() { // from class: cn.nubia.chatassistant.adapter.ChatAssistantSettingContentAdapter.VoiceViewHolder.1
                        @Override // java.util.TimerTask, java.lang.Runnable
                        public void run() {
                            if (MusicManagerUtils.getInstance().isPlay()) {
                                VoiceViewHolder.this.musicProcessLine.startupdateProcess();
                            } else {
                                ChatAssistantSettingContentAdapter.this.mTimer.cancel();
                            }
                        }
                    }, 0L, 50L);
                    this.mPlayVoice.setBackgroundResource(R.drawable.chat_assistant_stop);
                } else {
                    this.mPlayVoice.setBackgroundResource(R.drawable.chat_assistant_play);
                    this.musicProcessLine.setVisibility(8);
                }
            } else {
                this.musicProcessLine.setVisibility(4);
                this.musicProcessLine.reset();
                this.mPlayVoice.setBackgroundResource(R.drawable.chat_assistant_play);
                setVoiceItemState(chatAssistantVoiceBean.isSystemDefault(), false);
                this.mVoiceSelectItemLayout.setBackgroundResource(R.drawable.shape_chat_assistant_rectangle);
            }
            this.mVoiceName.setText(chatAssistantVoiceBean.getVoiceFileName());
            this.mPlayVoice.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.chatassistant.adapter.ChatAssistantSettingContentAdapter.VoiceViewHolder.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (ChatAssistantSettingContentAdapter.this.mSelectPosition == i && MusicManagerUtils.getInstance().isPlay()) {
                        VoiceViewHolder.this.mPlayVoice.setBackgroundResource(R.drawable.chat_assistant_play);
                        MusicManagerUtils.getInstance().stopPlay();
                    } else {
                        if (chatAssistantVoiceBean.isSystemDefault()) {
                            MusicManagerUtils.getInstance().startPlay(ChatAssistantSettingContentAdapter.this.mContext, chatAssistantVoiceBean.getVoiceFilePath(), new MediaPlayer.OnCompletionListener() { // from class: cn.nubia.chatassistant.adapter.ChatAssistantSettingContentAdapter.VoiceViewHolder.2.1
                                @Override // android.media.MediaPlayer.OnCompletionListener
                                public void onCompletion(MediaPlayer mediaPlayer) {
                                    if (ChatAssistantSettingContentAdapter.this.mSelectPosition != i) {
                                        ChatAssistantSettingContentAdapter.this.notifyItemChanged(ChatAssistantSettingContentAdapter.this.mSelectPosition);
                                    }
                                    VoiceViewHolder.this.mPlayVoice.setBackgroundResource(R.drawable.chat_assistant_play);
                                    VoiceViewHolder.this.musicProcessLine.setVisibility(8);
                                }
                            });
                        } else {
                            MusicManagerUtils.getInstance().startPlay(chatAssistantVoiceBean.getVoiceFilePath(), new MediaPlayer.OnCompletionListener() { // from class: cn.nubia.chatassistant.adapter.ChatAssistantSettingContentAdapter.VoiceViewHolder.2.2
                                @Override // android.media.MediaPlayer.OnCompletionListener
                                public void onCompletion(MediaPlayer mediaPlayer) {
                                    if (ChatAssistantSettingContentAdapter.this.mSelectPosition != i) {
                                        ChatAssistantSettingContentAdapter.this.notifyItemChanged(ChatAssistantSettingContentAdapter.this.mSelectPosition);
                                    }
                                    VoiceViewHolder.this.mPlayVoice.setBackgroundResource(R.drawable.chat_assistant_play);
                                    VoiceViewHolder.this.musicProcessLine.setVisibility(8);
                                }
                            });
                        }
                        VoiceViewHolder.this.mPlayVoice.setBackgroundResource(R.drawable.chat_assistant_stop);
                        ((ChatAssistantVoiceBean) ChatAssistantSettingContentAdapter.this.mList.get(ChatAssistantSettingContentAdapter.this.mSelectPosition)).setProcess(-1);
                        VoiceViewHolder.this.musicProcessLine.setVisibility(0);
                        VoiceViewHolder.this.musicProcessLine.setDuration(MusicManagerUtils.getInstance().getDuration());
                        if (ChatAssistantSettingContentAdapter.this.mTimer != null) {
                            ChatAssistantSettingContentAdapter.this.mTimer.cancel();
                        }
                        ChatAssistantSettingContentAdapter.this.mTimer = new Timer();
                        ChatAssistantSettingContentAdapter.this.mTimer.schedule(new TimerTask() { // from class: cn.nubia.chatassistant.adapter.ChatAssistantSettingContentAdapter.VoiceViewHolder.2.3
                            @Override // java.util.TimerTask, java.lang.Runnable
                            public void run() {
                                if (MusicManagerUtils.getInstance().isPlay()) {
                                    VoiceViewHolder.this.musicProcessLine.startupdateProcess();
                                } else {
                                    ChatAssistantSettingContentAdapter.this.mTimer.cancel();
                                }
                            }
                        }, 0L, 50L);
                    }
                    VoiceViewHolder.this.refreshUI(chatAssistantVoiceBean, i, false);
                }
            });
            this.mVoiceSelectItemLayout.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.chatassistant.adapter.ChatAssistantSettingContentAdapter.VoiceViewHolder.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    VoiceViewHolder.this.refreshUI(chatAssistantVoiceBean, i, true);
                }
            });
            this.mDeleteVoice.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.chatassistant.adapter.ChatAssistantSettingContentAdapter.VoiceViewHolder.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtils.i(ChatAssistantSettingContentAdapter.TAG, "delete voiceFileName : " + chatAssistantVoiceBean.getVoiceFileName());
                    Settings.Global.putInt(ChatAssistantSettingContentAdapter.this.mContext.getContentResolver(), "played_voice_pack", 0);
                    Settings.Global.putInt(ChatAssistantSettingContentAdapter.this.mContext.getContentResolver(), "played_voice", 0);
                    Settings.Global.putInt(ChatAssistantSettingContentAdapter.this.mContext.getContentResolver(), "played_voice_pack_temp", 0);
                    Settings.Global.putInt(ChatAssistantSettingContentAdapter.this.mContext.getContentResolver(), "played_voice_temp", 0);
                    Intent intent = new Intent(ChatAssistantSettingContentAdapter.this.mContext, (Class<?>) DeleteDialog.class);
                    intent.putExtra("text", ChatAssistantSettingContentAdapter.this.mContext.getResources().getString(R.string.select_delete_voice_text));
                    intent.putExtra("voiceFileName", chatAssistantVoiceBean.getVoiceFileName());
                    ((Activity) ChatAssistantSettingContentAdapter.this.activityWeakReference.get()).startActivityForResult(intent, ChatAssistantSettingsActivity.DELETE_VOICE_REQUEST_CODE);
                }
            });
        }
    }

    public ChatAssistantSettingContentAdapter(Context context, List<ChatAssistantVoiceBean> list, Activity activity) {
        LogUtils.d(TAG, "list size : " + list.size());
        this.mContext = context;
        this.mList = list;
        this.activityWeakReference = new WeakReference<>(activity);
    }

    public void addDate(ChatAssistantVoiceBean chatAssistantVoiceBean) {
        LogUtils.d(TAG, "addDate: ");
        this.mList.add(chatAssistantVoiceBean);
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof VoiceViewHolder) {
            ((VoiceViewHolder) viewHolder).setData(this.mList.get(i), i);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new VoiceViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout.chat_assistant_voice_recy_list, viewGroup, false));
    }

    public void refreshDate(List<ChatAssistantVoiceBean> list) {
        LogUtils.d(TAG, "refreshDate list size : " + list.size());
        if (list.size() > 0) {
            this.mSelectPosition = 0;
            this.mList = list;
            MusicManagerUtils.getInstance().stopPlay();
            notifyDataSetChanged();
        }
    }
}
