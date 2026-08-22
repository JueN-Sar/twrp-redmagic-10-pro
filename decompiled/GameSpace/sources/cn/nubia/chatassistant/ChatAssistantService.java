package cn.nubia.chatassistant;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;
import cn.nubia.chatassistant.ChatAssistantAudioTrack;
import cn.nubia.chatassistant.ChatAssistantView;
import cn.nubia.chatassistant.bean.ChatAssistantVoicePackBean;
import cn.nubia.chatassistant.db.ChatAssistantBean;
import cn.nubia.chatassistant.db.DBManager;
import cn.nubia.chatassistant.db.DBOpenHelper;
import cn.nubia.chatassistant.floatingball.BroadcastFloatingBall;
import cn.nubia.chatassistant.util.AssetsUtils;
import cn.nubia.chatassistant.util.LogUtils;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.util.GameCountTrack;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class ChatAssistantService extends Service {
    public static final int ACTION_DISMISS = 0;
    public static final int ACTION_PLAY = 2;
    public static final int ACTION_SHOW = 1;
    public static final String ACTION_TYPE = "type";
    public static final String ASSETS_DATA_FILE_ROOTDIR = "chat_assistant";
    public static int ASSETS_FILE_COUNT = 3;
    public static final int FILE_TYPE_ASSETS = 0;
    public static final int FILE_TYPE_DOWNLOAD = 1;
    public static final String GAME_PKG = "currentPkg";
    private static final String KEY_VIEW_SHOW = "chat_assistant_show";
    private static final String MAGIC = "魔姬";
    private static final int MSG_IMPORT_RES = 2;
    private static final int MSG_PLAY_AUDIO = 1;
    private static final int MSG_SHOW_VIEW = 0;
    private static final int MSG_STOP_SERVICE = 1000;
    private static final String PLAY_VOICE_ID = "id";
    public static final String POINT_X = "pointX";
    public static final String POINT_Y = "pointY";
    public static final String TAG = "ChatAssistantService";
    private static boolean isAlreadyApplauseAudio = false;
    private ChatAssistantAudioTrack chatAssistantAudioTrack;
    private BroadcastFloatingBall mBroadcastBall;
    private Handler mHandler;
    private WindowManager mWindowManager;
    private List<String> systemVoicePackList = Arrays.asList("和平风", "王者风", "红魔姬");
    private int mX = 0;
    private int mY = 0;
    private String gamePkg = "";
    private int mCount = 3;
    private int mFileType = 0;
    private String mPatch = null;
    private String mFileName = null;
    private List<String> mFirstTitleList = null;
    private List<List> mContentItemTitle = new ArrayList();
    private ChatAssistantView mChatAssistantView = null;
    private boolean mIsPlaying = false;
    private Toast mToast = null;
    private final Handler mCloseMusicHandler = new Handler();
    private Runnable mCloseMusicTimer = new Runnable() { // from class: cn.nubia.chatassistant.ChatAssistantService.1
        @Override // java.lang.Runnable
        public void run() {
            int i = Settings.Global.getInt(ChatAssistantService.this.getApplicationContext().getContentResolver(), "nubia_game_scene", 0);
            LogUtils.d(ChatAssistantService.TAG, "gameScene=" + i);
            if (i == 0) {
                ChatAssistantService.this.closeFloatViewAndMusic();
            }
        }
    };
    private ChatAssistantView.OnViewCloseListener mViewCloseListener = new ChatAssistantView.OnViewCloseListener() { // from class: cn.nubia.chatassistant.ChatAssistantService.4
        @Override // cn.nubia.chatassistant.ChatAssistantView.OnViewCloseListener
        public void onClickItemToViewClose(String str, int i, String str2) {
            LogUtils.i(ChatAssistantService.TAG, "onClickItemToViewClose");
            if (!ChatAssistantService.this.mIsPlaying) {
                Message obtainMessage = ChatAssistantService.this.mHandler.obtainMessage(1);
                ChatAssistantService.this.mPatch = str;
                ChatAssistantService.this.mFileName = str2;
                ChatAssistantService.this.mFileType = i;
                obtainMessage.obj = str;
                obtainMessage.arg1 = i;
                ChatAssistantService.this.mHandler.sendMessageDelayed(obtainMessage, 100L);
            }
            ChatAssistantService.this.dismissView();
        }

        @Override // cn.nubia.chatassistant.ChatAssistantView.OnViewCloseListener
        public void onOutsideToViewClose() {
            LogUtils.i(ChatAssistantService.TAG, "onOutsideToViewClose");
            ChatAssistantService.this.dismissView();
        }
    };
    private ChatAssistantAudioTrack.AudioTrackCallback mAudioTrackCallback = new ChatAssistantAudioTrack.AudioTrackCallback() { // from class: cn.nubia.chatassistant.ChatAssistantService.5
        @Override // cn.nubia.chatassistant.ChatAssistantAudioTrack.AudioTrackCallback
        public void onNextPlayAudioStart() {
            LogUtils.i(ChatAssistantService.TAG, "onNextPlayAudioStart");
            if (ChatAssistantService.this.mCount > 1) {
                ChatAssistantService.access$2410(ChatAssistantService.this);
                Message obtainMessage = ChatAssistantService.this.mHandler.obtainMessage(1);
                obtainMessage.obj = ChatAssistantService.this.mPatch;
                obtainMessage.arg1 = ChatAssistantService.this.mFileType;
                ChatAssistantService.this.mHandler.sendMessageDelayed(obtainMessage, 100L);
                return;
            }
            ChatAssistantService.this.mCount = 3;
            ChatAssistantService.this.mFileType = 0;
            ChatAssistantService.this.mPatch = null;
            ChatAssistantService.this.mFileName = null;
            ChatAssistantService.this.mIsPlaying = false;
            ChatAssistantService.this.stopService(100);
        }

        @Override // cn.nubia.chatassistant.ChatAssistantAudioTrack.AudioTrackCallback
        public void onPlayApplauseAudio() {
            LogUtils.i(ChatAssistantService.TAG, "onPlayApplauseAudio isAlreadyApplauseAudio");
            if (ChatAssistantService.isAlreadyApplauseAudio) {
                boolean unused = ChatAssistantService.isAlreadyApplauseAudio = false;
                ChatAssistantService.this.mIsPlaying = false;
                ChatAssistantService.this.stopService(100);
            } else {
                boolean unused2 = ChatAssistantService.isAlreadyApplauseAudio = true;
                Message obtainMessage = ChatAssistantService.this.mHandler.obtainMessage(1);
                obtainMessage.obj = "chat_assistant_assets/applause.wav";
                ChatAssistantService.this.mFileName = "鼓掌";
                obtainMessage.arg1 = 0;
                ChatAssistantService.this.mHandler.sendMessageDelayed(obtainMessage, 100L);
            }
        }

        @Override // cn.nubia.chatassistant.ChatAssistantAudioTrack.AudioTrackCallback
        public void onPlayAudioStop() {
            LogUtils.i(ChatAssistantService.TAG, "onPlayAudioStop");
            ChatAssistantService.this.mIsPlaying = false;
            ChatAssistantService.this.stopService(100);
        }
    };

    static /* synthetic */ int access$2410(ChatAssistantService chatAssistantService) {
        int i = chatAssistantService.mCount;
        chatAssistantService.mCount = i - 1;
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissView() {
        LogUtils.i(TAG, "dismissView");
        ChatAssistantView chatAssistantView = this.mChatAssistantView;
        if (chatAssistantView != null) {
            this.mWindowManager.removeView(chatAssistantView);
            this.mChatAssistantView = null;
        }
        Settings.Global.putInt(getContentResolver(), KEY_VIEW_SHOW, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<List> getContentItemTitle() {
        return AssetsUtils.getContentItemTitle(getApplicationContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<String> getFistTitle() {
        return AssetsUtils.getFistTitle(getApplicationContext());
    }

    private List<Map<String, Object>> getItemTitle(String str, boolean z) {
        ArrayList arrayList = new ArrayList();
        if (z) {
            try {
                str = "chat_assistant/" + str;
                for (String str2 : getApplication().getAssets().list(str)) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("title", str2.substring(0, str2.length() - 4));
                    hashMap.put("path", str + "/" + str2);
                    arrayList.add(hashMap);
                }
            } catch (IOException unused) {
                LogUtils.e(TAG, "open " + str + " failed!");
            }
        } else {
            for (File file : new File(getApplicationContext().getFilesDir().getPath() + "/chat_assistant/" + str).listFiles()) {
                String name = file.getName();
                String path = file.getPath();
                HashMap hashMap2 = new HashMap();
                hashMap2.put("title", name.substring(0, name.length() - 4));
                hashMap2.put("path", path);
                arrayList.add(hashMap2);
            }
        }
        if (arrayList.size() == 0) {
            return null;
        }
        return arrayList;
    }

    private String getTitle(String str) {
        if (str == null) {
            LogUtils.d(TAG, "getTitle path is null");
            return "";
        }
        String[] split = str.split("/");
        String str2 = split.length > 0 ? split[split.length - 1] : "";
        return str2.length() > 0 ? str2.substring(0, str2.length() - 4) : "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public WindowManager.LayoutParams getViewPopParam() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.setTitle("ChatAssistantWindow");
        layoutParams.type = 2038;
        layoutParams.flags = 263976;
        layoutParams.screenOrientation = 3;
        layoutParams.layoutInDisplayCutoutMode = 1;
        layoutParams.gravity = 51;
        int[] xy = getXY();
        layoutParams.x = xy[0];
        layoutParams.y = xy[1];
        layoutParams.width = getApplication().getResources().getDimensionPixelSize(R.dimen.chat_assistant_new_width);
        layoutParams.height = getApplication().getResources().getDimensionPixelSize(R.dimen.chat_assistant_height);
        layoutParams.format = 1;
        return layoutParams;
    }

    private int[] getXY() {
        int[] iArr = {326, 0};
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.mWindowManager.getDefaultDisplay().getMetrics(displayMetrics);
        if (this.mX > 0) {
            int i = displayMetrics.widthPixels;
            int i2 = this.mX;
            if (i - i2 < 682) {
                iArr[0] = i2 - 682;
            } else {
                iArr[0] = i2 + 196;
            }
        }
        if (this.mY > 0) {
            int i3 = displayMetrics.heightPixels;
            int i4 = this.mY;
            if (i3 - i4 < 588) {
                iArr[1] = displayMetrics.heightPixels - 588;
            } else {
                iArr[1] = i4;
            }
        }
        if (iArr[0] < 0) {
            int i5 = displayMetrics.heightPixels;
            int i6 = this.mY;
            if (i5 - i6 < 724) {
                iArr[1] = i6 - 598;
            } else {
                iArr[1] = i6 + 136;
            }
            iArr[0] = (displayMetrics.widthPixels - 600) / 2;
        }
        return iArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideFloatView() {
        BroadcastFloatingBall broadcastFloatingBall = this.mBroadcastBall;
        if (broadcastFloatingBall == null || !broadcastFloatingBall.isFloatViewVisible()) {
            LogUtils.d(TAG, "hideFloatView error");
        } else {
            this.mBroadcastBall.hideFloatView();
        }
    }

    private boolean isUpdateVoicePackData() {
        return false;
    }

    private void playChatAssistantVoice(String str) {
        Cursor cursor = null;
        try {
            cursor = DBManager.getInstance(getApplicationContext()).queryVoiceForID(str);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    this.mFileName = cursor.getString(cursor.getColumnIndex(DBOpenHelper.VOICE_FILE_NAME));
                    this.mFileType = !this.systemVoicePackList.contains(cursor.getString(cursor.getColumnIndex(DBOpenHelper.VOICE_PACK_NAME))) ? 1 : 0;
                    this.mPatch = cursor.getString(cursor.getColumnIndex(DBOpenHelper.VOICE_FILE_PATH));
                }
            }
            Message obtainMessage = this.mHandler.obtainMessage(1);
            obtainMessage.obj = this.mPatch;
            obtainMessage.arg1 = this.mFileType;
            this.mHandler.sendMessageDelayed(obtainMessage, 100L);
        } finally {
            if (cursor != null) {
                try {
                    cursor.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<ChatAssistantVoicePackBean> queryAllVoicePackDataToRefresh() {
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            cursor = DBManager.getInstance(getApplicationContext()).queryAllVoicePackData();
            LogUtils.i(TAG, "chatAssistantVoicePackBeanList cursor: " + cursor);
            if (cursor == null) {
                return arrayList;
            }
            Settings.Global.putInt(getContentResolver(), "voice_pack_quantity", cursor.getCount());
            while (cursor.moveToNext()) {
                ChatAssistantVoicePackBean chatAssistantVoicePackBean = new ChatAssistantVoicePackBean(cursor.getString(cursor.getColumnIndex(DBOpenHelper.VOICE_PACK_NAME)));
                boolean z = true;
                chatAssistantVoicePackBean.setShow(Integer.valueOf(cursor.getString(cursor.getColumnIndex(DBOpenHelper.VOICE_PACK_SHOW))).intValue() == 0);
                if (Integer.valueOf(cursor.getString(cursor.getColumnIndex(DBOpenHelper.VOICE_PACK_SYSTEM))).intValue() != 0) {
                    z = false;
                }
                chatAssistantVoicePackBean.setSystemDefault(z);
                chatAssistantVoicePackBean.setPosition(Integer.valueOf(cursor.getString(cursor.getColumnIndex(DBOpenHelper.VOICE_PACK_POSITION))).intValue());
                arrayList.add(chatAssistantVoicePackBean);
            }
            if (cursor != null) {
                try {
                    cursor.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            Comparator<ChatAssistantVoicePackBean> comparator = new Comparator<ChatAssistantVoicePackBean>() { // from class: cn.nubia.chatassistant.ChatAssistantService.3
                @Override // java.util.Comparator
                public int compare(ChatAssistantVoicePackBean chatAssistantVoicePackBean2, ChatAssistantVoicePackBean chatAssistantVoicePackBean3) {
                    if (chatAssistantVoicePackBean2.getPosition() > chatAssistantVoicePackBean3.getPosition()) {
                        return 1;
                    }
                    return chatAssistantVoicePackBean2.getPosition() < chatAssistantVoicePackBean3.getPosition() ? -1 : 0;
                }
            };
            LogUtils.i(TAG, "chatAssistantVoicePackBeanList: " + arrayList.size());
            Collections.sort(arrayList, comparator);
            return arrayList;
        } finally {
            if (cursor != null) {
                try {
                    cursor.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void queryAllVoicePackDataToUpdate() {
        if (isUpdateVoicePackData()) {
            AssetsUtils.updateChatAssistantVoicePack(getApplicationContext());
        }
    }

    private void sendMessageDelayed(int i, int i2) {
        this.mHandler.removeMessages(1000);
        this.mHandler.removeMessages(i);
        this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(i), i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showFloatingView(String str) {
        BroadcastFloatingBall broadcastFloatingBall = this.mBroadcastBall;
        if (broadcastFloatingBall == null || broadcastFloatingBall.isFloatViewExist()) {
            this.mBroadcastBall = BroadcastFloatingBall.getInstance(getApplicationContext());
        }
        this.mBroadcastBall.createFloatView();
        this.mBroadcastBall.onFloatViewClick(new View.OnClickListener() { // from class: cn.nubia.chatassistant.ChatAssistantService.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ChatAssistantService.this.mBroadcastBall != null) {
                    ChatAssistantService.this.mBroadcastBall.hideFloatView();
                }
            }
        });
        this.mBroadcastBall.updateViewLayout();
        this.mBroadcastBall.setVoiceText(this.mFileName);
        this.mBroadcastBall.resetVoiceTextPosition();
        this.mBroadcastBall.showFloatView();
        LogUtils.d(TAG, "showFloatView!");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopService(int i) {
        sendMessageDelayed(1000, i);
    }

    public void closeFloatViewAndMusic() {
        this.mIsPlaying = false;
        stopService(0);
        ChatAssistantAudioTrack chatAssistantAudioTrack = this.chatAssistantAudioTrack;
        if (chatAssistantAudioTrack != null) {
            chatAssistantAudioTrack.stop();
        }
    }

    public void getChatAssistantVoiceData() {
        this.mContentItemTitle.clear();
        this.mFirstTitleList.clear();
        List<ChatAssistantVoicePackBean> queryAllVoicePackDataToRefresh = queryAllVoicePackDataToRefresh();
        for (int i = 0; i < queryAllVoicePackDataToRefresh.size(); i++) {
            String voicePackName = queryAllVoicePackDataToRefresh.get(i).getVoicePackName();
            Cursor cursor = null;
            try {
                cursor = DBManager.getInstance(getApplicationContext()).queryDataByVoicePackName(voicePackName);
                if (cursor != null) {
                    ArrayList arrayList = new ArrayList();
                    int i2 = 1;
                    while (cursor.moveToNext()) {
                        String string = cursor.getString(cursor.getColumnIndex(DBOpenHelper.VOICE_FILE_NAME));
                        String string2 = cursor.getString(cursor.getColumnIndex(DBOpenHelper.VOICE_FILE_PATH));
                        int intValue = Integer.valueOf(cursor.getString(cursor.getColumnIndex(DBOpenHelper.VOICE_PACK_SHOW))).intValue();
                        HashMap hashMap = new HashMap();
                        hashMap.put("title", string);
                        hashMap.put("path", string2);
                        if (!TextUtils.isEmpty(string)) {
                            arrayList.add(hashMap);
                        }
                        i2 = intValue;
                    }
                    if (i2 == 0) {
                        this.mContentItemTitle.add(arrayList);
                        this.mFirstTitleList.add(voicePackName);
                    }
                }
                if (cursor != null) {
                    try {
                        cursor.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Throwable th) {
                if (cursor != null) {
                    try {
                        cursor.close();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                throw th;
            }
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        this.mWindowManager = (WindowManager) getSystemService("window");
        GameCountTrack.getInstance().initGameCountTrack();
        this.mHandler = new Handler(getMainLooper()) { // from class: cn.nubia.chatassistant.ChatAssistantService.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                LogUtils.e(ChatAssistantService.TAG, "MSG msg.what = " + message.what);
                int i = message.what;
                if (i == 0) {
                    ChatAssistantService chatAssistantService = ChatAssistantService.this;
                    chatAssistantService.mFirstTitleList = chatAssistantService.getFistTitle();
                    ChatAssistantService chatAssistantService2 = ChatAssistantService.this;
                    chatAssistantService2.mContentItemTitle = chatAssistantService2.getContentItemTitle();
                    if (ChatAssistantService.this.queryAllVoicePackDataToRefresh() == null || ChatAssistantService.this.queryAllVoicePackDataToRefresh().size() == 0) {
                        Settings.Global.putInt(ChatAssistantService.this.getContentResolver(), "played_voice_pack", 0);
                        Settings.Global.putInt(ChatAssistantService.this.getContentResolver(), "played_voice", 0);
                        Settings.Global.putInt(ChatAssistantService.this.getContentResolver(), "played_voice_pack_temp", 0);
                        Settings.Global.putInt(ChatAssistantService.this.getContentResolver(), "played_voice_temp", 0);
                        ChatAssistantService chatAssistantService3 = ChatAssistantService.this;
                        chatAssistantService3.setChatAssistantVoiceData(chatAssistantService3.mFirstTitleList, ChatAssistantService.this.getContentItemTitle());
                    }
                    ChatAssistantService.this.queryAllVoicePackDataToUpdate();
                    ChatAssistantService.this.getChatAssistantVoiceData();
                    ChatAssistantService chatAssistantService4 = ChatAssistantService.this;
                    chatAssistantService4.mBroadcastBall = BroadcastFloatingBall.getInstance(chatAssistantService4.getApplicationContext());
                    if (ChatAssistantService.this.mFirstTitleList != null && ChatAssistantService.this.mFirstTitleList.size() > 0) {
                        ChatAssistantService.this.mChatAssistantView = new ChatAssistantView(ChatAssistantService.this.getApplicationContext());
                        ChatAssistantService.this.mChatAssistantView.setData(ChatAssistantService.this.mFirstTitleList, ChatAssistantService.this.mContentItemTitle, ChatAssistantService.ASSETS_FILE_COUNT);
                        Settings.Global.putInt(ChatAssistantService.this.getContentResolver(), ChatAssistantService.KEY_VIEW_SHOW, 1);
                        ChatAssistantService.this.mWindowManager.addView(ChatAssistantService.this.mChatAssistantView, ChatAssistantService.this.getViewPopParam());
                        ChatAssistantService.this.mChatAssistantView.setCloseListener(ChatAssistantService.this.mViewCloseListener);
                    }
                } else if (i == 1) {
                    String str = (String) message.obj;
                    int i2 = message.arg1;
                    ChatAssistantService.this.showFloatingView(str);
                    ChatAssistantService.this.chatAssistantAudioTrack = new ChatAssistantAudioTrack(ChatAssistantService.this.getApplicationContext(), str, i2, ChatAssistantService.this.mAudioTrackCallback);
                    ChatAssistantService.this.chatAssistantAudioTrack.startPlay();
                    ChatAssistantService.this.mIsPlaying = true;
                    ChatAssistantService.this.mCloseMusicHandler.postDelayed(ChatAssistantService.this.mCloseMusicTimer, 200L);
                    Settings.Global.putInt(ChatAssistantService.this.getContentResolver(), "played_voice_pack", Settings.Global.getInt(ChatAssistantService.this.getContentResolver(), "played_voice_pack_temp", 0));
                    Settings.Global.putInt(ChatAssistantService.this.getContentResolver(), "played_voice", Settings.Global.getInt(ChatAssistantService.this.getContentResolver(), "played_voice_temp", 0));
                } else if (i == 2) {
                    ChatAssistantImport.parserChatAssistantDataFile(ChatAssistantService.this.getApplicationContext(), ChatAssistantService.this.getApplicationContext().getFilesDir().getPath() + "/chat_assistant/ziyoufeng.zip");
                    ChatAssistantService.this.stopService(50);
                } else if (i == 1000) {
                    LogUtils.e(ChatAssistantService.TAG, "isPlaying = " + ChatAssistantService.this.mIsPlaying);
                    ChatAssistantService.this.dismissView();
                    Settings.Global.putInt(ChatAssistantService.this.getContentResolver(), "played_voice_pack_temp", Settings.Global.getInt(ChatAssistantService.this.getContentResolver(), "played_voice_pack", 0));
                    Settings.Global.putInt(ChatAssistantService.this.getContentResolver(), "played_voice_temp", Settings.Global.getInt(ChatAssistantService.this.getContentResolver(), "played_voice", 0));
                    if (!ChatAssistantService.this.mIsPlaying) {
                        ChatAssistantService.this.stopSelf();
                        ChatAssistantService.this.hideFloatView();
                    }
                }
                super.handleMessage(message);
            }
        };
        super.onCreate();
    }

    @Override // android.app.Service
    public void onDestroy() {
        Runnable runnable;
        LogUtils.e(TAG, "onDestroy");
        Handler handler = this.mCloseMusicHandler;
        if (handler != null && (runnable = this.mCloseMusicTimer) != null) {
            handler.removeCallbacks(runnable);
        }
        Handler handler2 = this.mHandler;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages(null);
        }
        Toast toast = this.mToast;
        if (toast != null) {
            toast.cancel();
            this.mToast = null;
        }
        GameCountTrack.getInstance().unbindArkService();
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent == null) {
            LogUtils.e(TAG, "intent is null!");
            return super.onStartCommand(intent, i, i2);
        }
        int intExtra = intent.getIntExtra("type", -1);
        LogUtils.i(TAG, "Start service actionType=" + intExtra);
        if (intExtra == Settings.Global.getInt(getContentResolver(), KEY_VIEW_SHOW, 0)) {
            return super.onStartCommand(intent, i, i2);
        }
        if (1 == intExtra) {
            if (Settings.canDrawOverlays(getApplicationContext())) {
                this.mX = intent.getIntExtra(POINT_X, -1);
                this.mY = intent.getIntExtra(POINT_Y, -1);
                this.gamePkg = intent.getStringExtra(GAME_PKG);
                Settings.Global.putString(getContentResolver(), "game_pack_name", this.gamePkg);
                sendMessageDelayed(0, 20);
            } else {
                Toast toast = this.mToast;
                if (toast != null) {
                    toast.cancel();
                }
                Toast makeText = Toast.makeText(getApplicationContext(), getApplicationContext().getResources().getString(R.string.floating_permission_dialog_message), 0);
                this.mToast = makeText;
                makeText.show();
                stopService(2000);
            }
        } else if (intExtra == 0) {
            this.mCloseMusicHandler.post(this.mCloseMusicTimer);
        } else if (2 == intExtra) {
            String stringExtra = intent.getStringExtra("id");
            LogUtils.i(TAG, "Start service id=" + stringExtra);
            if (stringExtra == null) {
                return super.onStartCommand(intent, i, i2);
            }
            playChatAssistantVoice(stringExtra);
        } else {
            stopService(0);
        }
        return super.onStartCommand(intent, i, i2);
    }

    public void setChatAssistantVoiceData(List<String> list, List<List> list2) {
        if (list == null || list2 == null) {
            LogUtils.e(TAG, "data is null!");
            return;
        }
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < list.size()) {
            ChatAssistantBean chatAssistantBean = new ChatAssistantBean();
            chatAssistantBean.voicePackPosition = i;
            String[] split = list.get(i).split("_", 2);
            if (split[1].equals("redmagic")) {
                split[1] = "红魔姬";
            } else if (split[1].equals("king")) {
                split[1] = "王者风";
            } else if (split[1].equals("peace")) {
                split[1] = "和平风";
            }
            arrayList.add(split[1]);
            List<Map<String, Object>> itemTitle = AssetsUtils.getItemTitle(i, this.mFirstTitleList.get(i), i < ASSETS_FILE_COUNT, getApplicationContext());
            for (int i2 = 0; i2 < itemTitle.size(); i2++) {
                chatAssistantBean.voicePackName = (String) arrayList.get(i);
                chatAssistantBean.voiceFileName = (String) itemTitle.get(i2).get("title");
                chatAssistantBean.voiceFilePath = (String) itemTitle.get(i2).get("path");
                DBManager.getInstance(getApplicationContext()).insertEventToDb(chatAssistantBean);
            }
            i++;
        }
    }
}
