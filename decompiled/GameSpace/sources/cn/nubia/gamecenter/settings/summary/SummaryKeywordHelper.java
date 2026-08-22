package cn.nubia.gamecenter.settings.summary;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.ItemTouchHelper;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.helper.AnimatorHelper;
import cn.nubia.gamecenter.settings.records.utils.HighLightsUtils;
import cn.nubia.gamecenter.settings.summary.SummaryDataHelper;
import cn.nubia.gamecenter.settings.summary.SummaryPageHelper;
import cn.nubia.gamecenter.settings.widget.MultiStateInterpolator;
import cn.nubia.gamelauncher.recycler.Anim3DHelper;

/* loaded from: classes.dex */
public class SummaryKeywordHelper extends SummaryPageHelper {
    private static final float[] COMMON_CURVE;
    private static final float[] INDICATOR_CURVE;
    private static final int NUMBER_CENTER = 1;
    private static final int NUMBER_LEFT = 0;
    private static int[] NUMBER_LIGHT = null;
    private static final int[][] NUMBER_LIGHT_STATEs;
    private static int[] NUMBER_NORMAL = {R.drawable.gcs_gamecenter_summarycenter_number1, R.drawable.gcs_gamecenter_summarycenter_number2, R.drawable.gcs_gamecenter_summarycenter_number3};
    private static final int NUMBER_RIGHT = 2;
    private static final int[][] NUMBER_STATEs;
    private static final int ONE_CIRCLE = 900;
    private static final int STEP_ONE_NUMBER = 70;
    private static final String TAG = "SummaryKeywordHelper";
    private final AnimatorHelper.Item[][] ITEMS;
    private final AnimatorHelper.Item[] ITEMS_NUMBER;
    private final AnimatorHelper.Item[][] ITEMS_ROTATE;
    private final AnimatorHelper.Item[] ITEMs_center;
    private final AnimatorHelper.Item[] ITEMs_indicator;
    private final AnimatorHelper.Item[] ITEMs_left;
    private final AnimatorHelper.Item[] ITEMs_right;
    private final AnimatorHelper.Item[] ITEMs_right_minite;
    private AnimatorHelper[] m_helpers;
    private AnimatorHelper m_indicator;
    private AnimatorHelper.Item m_item_light_m;
    private AnimatorHelper.Item m_item_light_x;
    private AnimatorHelper.Item m_item_most_hour;
    private AnimatorHelper.Item m_item_most_minite;
    private AnimatorHelper.Item m_item_number1;
    private AnimatorHelper.Item m_item_number2;
    private AnimatorHelper.Item m_item_number3;
    private AnimatorHelper.Item m_item_right_light_m;
    private AnimatorHelper.Item m_item_right_light_x;
    private AnimatorHelper.Item m_item_right_zhizhen;
    private AnimatorHelper.Item m_item_total_time;
    private AnimatorHelper.Item m_item_zhizhen;
    private ImageView[] m_keywordImage;
    private ImageView m_keywordNumberCenter;
    private ImageView m_keywordNumberCenterLight;
    private ImageView m_keywordNumberLeft;
    private ImageView m_keywordNumberLeftLight;
    private ImageView m_keywordNumberRight;
    private ImageView m_keywordNumberRightLight;
    private TextView m_keywordTitle;
    private TextView m_keywordView;
    private ImageView m_mostImage;
    private TextView m_mostTimeHour;
    private TextView m_mostTimeMinite;
    private TextView m_mostTitle;
    private TextView m_mostUnitHour;
    private TextView m_mostUnitMinite;
    private Interpolator m_scrollTipInterpolator;
    private AnimatorHelper.Item m_scroll_tip_alpha;
    private AnimatorHelper.Item m_scroll_tip_move;
    private TextView m_totalHour;
    private TextView m_totalMin;
    private TextView m_totalTime;

    static {
        int[] iArr = {R.drawable.gcs_gamecenter_summarycenter_number1_light, R.drawable.gcs_gamecenter_summarycenter_number2_light, R.drawable.gcs_gamecenter_summarycenter_number3_light};
        NUMBER_LIGHT = iArr;
        NUMBER_LIGHT_STATEs = new int[][]{new int[]{0, iArr[0], 0}, new int[]{0, iArr[1], 0}, new int[]{0, iArr[2], 0}};
        int[] iArr2 = NUMBER_NORMAL;
        int i = iArr2[2];
        int i2 = iArr2[0];
        int i3 = iArr2[1];
        NUMBER_STATEs = new int[][]{new int[]{i, i2, i3}, new int[]{i2, i3, i}, new int[]{i3, i, i2}};
        COMMON_CURVE = new float[]{0.2f, 0.22f, 0.17f, 1.0f};
        INDICATOR_CURVE = new float[]{0.2f, 0.08f, 0.49f, 1.0f};
    }

    SummaryKeywordHelper(View view, SummaryPageHelper.Callback callback, int i) {
        super(view, callback, i);
        float[] fArr = COMMON_CURVE;
        this.m_item_zhizhen = new AnimatorHelper.Item(R.id.summary_keyword_zhizhen, AnimatorHelper.Item.ROTATE, new float[]{0.0f, 0.0f}, fArr, 0, 1950);
        this.m_item_light_m = new AnimatorHelper.Item(R.id.summary_keyword_light_m, AnimatorHelper.Item.ROTATE, new float[]{0.0f, 0.0f}, fArr, 0, 1950);
        this.m_item_light_x = new AnimatorHelper.Item(R.id.summary_keyword_light_x, AnimatorHelper.Item.ROTATE, new float[]{0.0f, 0.0f}, fArr, 0, 1950);
        this.m_item_right_zhizhen = new AnimatorHelper.Item(R.id.summary_keyword_zhizhen, AnimatorHelper.Item.ROTATE, new float[]{0.0f, 0.0f}, fArr, 0, 1950);
        this.m_item_right_light_m = new AnimatorHelper.Item(R.id.summary_keyword_light_m, AnimatorHelper.Item.ROTATE, new float[]{0.0f, 0.0f}, fArr, 0, 1950);
        this.m_item_right_light_x = new AnimatorHelper.Item(R.id.summary_keyword_light_x, AnimatorHelper.Item.ROTATE, new float[]{0.0f, 0.0f}, fArr, 0, 1950);
        this.m_item_total_time = new AnimatorHelper.Item(R.id.summary_keywords_total_time, AnimatorHelper.Item.CUST_NUMBER, new float[]{0.0f, 0.0f}, null, 0, 1950);
        this.m_item_most_hour = new AnimatorHelper.Item(R.id.summary_keywords_most_hour, AnimatorHelper.Item.CUST_NUMBER, new float[]{0.0f, 0.0f}, null, 0, 1950);
        this.m_item_most_minite = new AnimatorHelper.Item(R.id.summary_keywords_most_minite, AnimatorHelper.Item.CUST_NUMBER, new float[]{0.0f, 0.0f}, null, 0, 1950);
        AnimatorHelper.Item[] itemArr = {new AnimatorHelper.Item(R.id.summary_keyword_title, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f}, null, 350, 0), new AnimatorHelper.Item(R.id.summary_keyword_bg_1, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f}, null, 300, 1050), new AnimatorHelper.Item(R.id.summary_keyword_point, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f}, null, 150, 350), new AnimatorHelper.Item(R.id.summary_keyword_point, AnimatorHelper.Item.SCALEX, new float[]{1.1f, 1.0f}, null, 150, 350), new AnimatorHelper.Item(R.id.summary_keyword_point, AnimatorHelper.Item.SCALEY, new float[]{1.1f, 1.0f}, null, 150, 350), new AnimatorHelper.Item(R.id.summary_keyword_1_1, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f}, null, 150, 350), new AnimatorHelper.Item(R.id.summary_keyword_1_1, AnimatorHelper.Item.SCALEX, new float[]{1.1f, 1.0f}, null, 150, 350), new AnimatorHelper.Item(R.id.summary_keyword_1_1, AnimatorHelper.Item.SCALEY, new float[]{1.1f, 1.0f}, null, 150, 350), new AnimatorHelper.Item(R.id.summary_keyword_zhizhen, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f}, null, 100, 400), new AnimatorHelper.Item(R.id.summary_keyword_zhizhen, AnimatorHelper.Item.SCALEX, new float[]{0.9f, 1.0f}, fArr, 350, 350), new AnimatorHelper.Item(R.id.summary_keyword_zhizhen, AnimatorHelper.Item.SCALEY, new float[]{0.9f, 1.0f}, fArr, 350, 350), new AnimatorHelper.Item(R.id.summary_keyword_zhizhen, AnimatorHelper.Item.ROTATE, new float[]{0.0f, 180.0f}, fArr, 600, 750), new AnimatorHelper.Item(R.id.summary_keyword_zhizhen, AnimatorHelper.Item.ROTATE, new float[]{180.0f, 0.0f}, fArr, 600, Anim3DHelper.START_ANIM_DURATION), new AnimatorHelper.Item(R.id.summary_keyword_light_m, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f}, null, 83, 800), new AnimatorHelper.Item(R.id.summary_keyword_light_m, AnimatorHelper.Item.ROTATE, new float[]{0.0f, 180.0f}, fArr, 600, 750), new AnimatorHelper.Item(R.id.summary_keyword_light_m, AnimatorHelper.Item.ROTATE, new float[]{180.0f, 0.0f}, fArr, 600, Anim3DHelper.START_ANIM_DURATION), new AnimatorHelper.Item(R.id.summary_keyword_light_x, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f}, null, 83, 800), new AnimatorHelper.Item(R.id.summary_keyword_light_x, AnimatorHelper.Item.ROTATE, new float[]{0.0f, 180.0f}, fArr, 600, 750), new AnimatorHelper.Item(R.id.summary_keyword_light_x, AnimatorHelper.Item.ROTATE, new float[]{180.0f, 0.0f}, fArr, 600, Anim3DHelper.START_ANIM_DURATION), new AnimatorHelper.Item(R.id.summary_keyword_ball_1, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f}, null, 350, 150), new AnimatorHelper.Item(R.id.summary_keyword_ball_1, AnimatorHelper.Item.SCALEX, new float[]{0.0f, 1.0f}, null, 350, 150), new AnimatorHelper.Item(R.id.summary_keyword_ball_1, AnimatorHelper.Item.SCALEY, new float[]{0.0f, 1.0f}, null, 350, 150), new AnimatorHelper.Item(R.id.summary_keyword_ball_1, AnimatorHelper.Item.ROTATE, new float[]{-360.0f, 0.0f}, null, 350, 150), new AnimatorHelper.Item(R.id.summary_keyword_2_light, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f}, null, 150, 1500), new AnimatorHelper.Item(R.id.summary_keyword_3_1, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f}, null, 150, 1950), new AnimatorHelper.Item(R.id.summary_keyword_3_1, AnimatorHelper.Item.SCALEX, new float[]{0.9f, 1.0f}, null, 150, 1950), new AnimatorHelper.Item(R.id.summary_keyword_3_1, AnimatorHelper.Item.SCALEY, new float[]{0.9f, 1.0f}, null, 150, 1950), new AnimatorHelper.Item(R.id.summary_keywords_total_hour, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f}, null, 450, 1500), new AnimatorHelper.Item(R.id.summary_keywords_total_hour, AnimatorHelper.Item.SCALEX, new float[]{0.9f, 1.0f}, null, 450, 1500), new AnimatorHelper.Item(R.id.summary_keywords_total_hour, AnimatorHelper.Item.SCALEY, new float[]{0.9f, 1.0f}, null, 450, 1500), new AnimatorHelper.Item(R.id.summary_keywords_total_time, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f}, null, 450, 1500), new AnimatorHelper.Item(R.id.summary_keywords_total_time, AnimatorHelper.Item.SCALEX, new float[]{0.9f, 1.0f}, null, 450, 1500), new AnimatorHelper.Item(R.id.summary_keywords_total_time, AnimatorHelper.Item.SCALEY, new float[]{0.9f, 1.0f}, null, 450, 1500), new AnimatorHelper.Item(R.id.summary_keywords_total_minite, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f}, null, 450, 1500), new AnimatorHelper.Item(R.id.summary_keywords_total_minite, AnimatorHelper.Item.SCALEX, new float[]{0.9f, 1.0f}, null, 450, 1500), new AnimatorHelper.Item(R.id.summary_keywords_total_minite, AnimatorHelper.Item.SCALEY, new float[]{0.9f, 1.0f}, null, 450, 1500), this.m_item_total_time, this.m_item_zhizhen, this.m_item_light_m, this.m_item_light_x};
        this.ITEMs_left = itemArr;
        AnimatorHelper.Item[] itemArr2 = {new AnimatorHelper.Item(R.id.summary_keyword_title, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f}, null, 350, 0), new AnimatorHelper.Item(R.id.summary_keyword_bg_1, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f}, null, 300, 1050), new AnimatorHelper.Item(R.id.summary_keyword_point, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f}, null, 150, 350), new AnimatorHelper.Item(R.id.summary_keyword_point, AnimatorHelper.Item.SCALEX, new float[]{1.1f, 1.0f}, null, 150, 350), new AnimatorHelper.Item(R.id.summary_keyword_point, AnimatorHelper.Item.SCALEY, new float[]{1.1f, 1.0f}, null, 150, 350), new AnimatorHelper.Item(R.id.summary_keyword_1_1, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f}, null, 150, 350), new AnimatorHelper.Item(R.id.summary_keyword_1_1, AnimatorHelper.Item.SCALEX, new float[]{1.1f, 1.0f}, null, 150, 350), new AnimatorHelper.Item(R.id.summary_keyword_1_1, AnimatorHelper.Item.SCALEY, new float[]{1.1f, 1.0f}, null, 150, 350), new AnimatorHelper.Item(R.id.summary_keyword_zhizhen, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f}, null, 100, 400), new AnimatorHelper.Item(R.id.summary_keyword_zhizhen, AnimatorHelper.Item.SCALEX, new float[]{0.9f, 1.0f}, fArr, 350, 350), new AnimatorHelper.Item(R.id.summary_keyword_zhizhen, AnimatorHelper.Item.SCALEY, new float[]{0.9f, 1.0f}, fArr, 350, 350), new AnimatorHelper.Item(R.id.summary_keyword_zhizhen, AnimatorHelper.Item.ROTATE, new float[]{0.0f, 180.0f}, fArr, 600, 750), new AnimatorHelper.Item(R.id.summary_keyword_zhizhen, AnimatorHelper.Item.ROTATE, new float[]{180.0f, 0.0f}, fArr, 600, Anim3DHelper.START_ANIM_DURATION), new AnimatorHelper.Item(R.id.summary_keyword_light_m, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f}, null, 83, 800), new AnimatorHelper.Item(R.id.summary_keyword_light_m, AnimatorHelper.Item.ROTATE, new float[]{0.0f, 180.0f}, fArr, 600, 750), new AnimatorHelper.Item(R.id.summary_keyword_light_m, AnimatorHelper.Item.ROTATE, new float[]{180.0f, 0.0f}, fArr, 600, Anim3DHelper.START_ANIM_DURATION), new AnimatorHelper.Item(R.id.summary_keyword_light_x, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f}, null, 83, 800), new AnimatorHelper.Item(R.id.summary_keyword_light_x, AnimatorHelper.Item.ROTATE, new float[]{0.0f, 180.0f}, fArr, 600, 750), new AnimatorHelper.Item(R.id.summary_keyword_light_x, AnimatorHelper.Item.ROTATE, new float[]{180.0f, 0.0f}, fArr, 600, Anim3DHelper.START_ANIM_DURATION), new AnimatorHelper.Item(R.id.summary_keyword_ball_1, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f}, null, 350, 150), new AnimatorHelper.Item(R.id.summary_keyword_ball_1, AnimatorHelper.Item.SCALEX, new float[]{0.0f, 1.0f}, null, 350, 150), new AnimatorHelper.Item(R.id.summary_keyword_ball_1, AnimatorHelper.Item.SCALEY, new float[]{0.0f, 1.0f}, null, 350, 150), new AnimatorHelper.Item(R.id.summary_keyword_ball_1, AnimatorHelper.Item.ROTATE, new float[]{-360.0f, 0.0f}, null, 350, 150), new AnimatorHelper.Item(R.id.summary_keyword_2_light, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f}, null, 150, 1500), new AnimatorHelper.Item(R.id.summary_keyword_3_1, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f}, null, 150, 1950), new AnimatorHelper.Item(R.id.summary_keyword_3_1, AnimatorHelper.Item.SCALEX, new float[]{0.9f, 1.0f}, null, 150, 1950), new AnimatorHelper.Item(R.id.summary_keyword_3_1, AnimatorHelper.Item.SCALEY, new float[]{0.9f, 1.0f}, null, 150, 1950), new AnimatorHelper.Item(R.id.summary_keywords_most_icon, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f}, null, 450, 1500), new AnimatorHelper.Item(R.id.summary_keywords_most_icon, AnimatorHelper.Item.SCALEX, new float[]{0.9f, 1.0f}, null, 450, 1500), new AnimatorHelper.Item(R.id.summary_keywords_most_icon, AnimatorHelper.Item.SCALEY, new float[]{0.9f, 1.0f}, null, 450, 1500), new AnimatorHelper.Item(R.id.summary_keywords_most_title, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f}, null, 450, 1500), new AnimatorHelper.Item(R.id.summary_keywords_most_title, AnimatorHelper.Item.SCALEX, new float[]{0.9f, 1.0f}, null, 450, 1500), new AnimatorHelper.Item(R.id.summary_keywords_most_title, AnimatorHelper.Item.SCALEY, new float[]{0.9f, 1.0f}, null, 450, 1500), new AnimatorHelper.Item(R.id.summary_keywords_most_hour, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f}, null, 450, 1500), new AnimatorHelper.Item(R.id.summary_keywords_most_hour, AnimatorHelper.Item.SCALEX, new float[]{0.9f, 1.0f}, null, 450, 1500), new AnimatorHelper.Item(R.id.summary_keywords_most_hour, AnimatorHelper.Item.SCALEY, new float[]{0.9f, 1.0f}, null, 450, 1500), this.m_item_most_hour, new AnimatorHelper.Item(R.id.summary_keywords_most_unit_hour, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f}, null, 450, 1500), new AnimatorHelper.Item(R.id.summary_keywords_most_unit_hour, AnimatorHelper.Item.SCALEX, new float[]{0.9f, 1.0f}, null, 450, 1500), new AnimatorHelper.Item(R.id.summary_keywords_most_unit_hour, AnimatorHelper.Item.SCALEY, new float[]{0.9f, 1.0f}, null, 450, 1500), this.m_item_right_zhizhen, this.m_item_right_light_m, this.m_item_right_light_x};
        this.ITEMs_right = itemArr2;
        AnimatorHelper.Item[] itemArr3 = {new AnimatorHelper.Item(R.id.summary_keywords_most_minite, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f}, null, 450, 1500), new AnimatorHelper.Item(R.id.summary_keywords_most_minite, AnimatorHelper.Item.SCALEX, new float[]{0.9f, 1.0f}, null, 450, 1500), new AnimatorHelper.Item(R.id.summary_keywords_most_minite, AnimatorHelper.Item.SCALEY, new float[]{0.9f, 1.0f}, null, 450, 1500), this.m_item_most_minite, new AnimatorHelper.Item(R.id.summary_keywords_most_unit_minite, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f}, null, 450, 1500), new AnimatorHelper.Item(R.id.summary_keywords_most_unit_minite, AnimatorHelper.Item.SCALEX, new float[]{0.9f, 1.0f}, null, 450, 1500), new AnimatorHelper.Item(R.id.summary_keywords_most_unit_minite, AnimatorHelper.Item.SCALEY, new float[]{0.9f, 1.0f}, null, 450, 1500)};
        this.ITEMs_right_minite = itemArr3;
        this.m_item_number1 = new AnimatorHelper.Item(R.id.summary_keywords_key_number_center_light, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f}, null, 100, 1900);
        this.m_item_number2 = new AnimatorHelper.Item(R.id.summary_keywords_key_number_left_light, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f}, null, 100, 1900);
        this.m_item_number3 = new AnimatorHelper.Item(R.id.summary_keywords_key_number_right_light, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f}, null, 100, 1900);
        AnimatorHelper.Item[] itemArr4 = {new AnimatorHelper.Item(R.id.summary_keywords_key_title, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f}, null, 350, 0), new AnimatorHelper.Item(R.id.summary_keyword_bg_line, AnimatorHelper.Item.SCALEX, new float[]{0.95f, 1.0f}, null, 350, 2000), new AnimatorHelper.Item(R.id.summary_keyword_bg_line, AnimatorHelper.Item.SCALEY, new float[]{0.95f, 1.0f}, null, 350, 2000), new AnimatorHelper.Item(R.id.summary_keyword_data_bg, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f}, null, 450, 550), new AnimatorHelper.Item(R.id.summary_keyword_data_bg, AnimatorHelper.Item.SCALEX, new float[]{1.1f, 1.0f}, null, 750, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION), new AnimatorHelper.Item(R.id.summary_keyword_data_bg, AnimatorHelper.Item.SCALEY, new float[]{1.1f, 1.0f}, null, 750, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION), new AnimatorHelper.Item(R.id.summary_keyword_3, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f}, null, 450, 550), new AnimatorHelper.Item(R.id.summary_keyword_3, AnimatorHelper.Item.SCALEX, new float[]{1.1f, 1.0f}, null, 750, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION), new AnimatorHelper.Item(R.id.summary_keyword_3, AnimatorHelper.Item.SCALEY, new float[]{1.1f, 1.0f}, null, 750, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION), new AnimatorHelper.Item(R.id.summary_keyword_2, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f}, null, 450, 550), new AnimatorHelper.Item(R.id.summary_keyword_2, AnimatorHelper.Item.SCALEX, new float[]{1.1f, 1.0f}, null, 750, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION), new AnimatorHelper.Item(R.id.summary_keyword_2, AnimatorHelper.Item.SCALEY, new float[]{1.1f, 1.0f}, null, 750, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION), new AnimatorHelper.Item(R.id.summary_keyword_1, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f}, null, 450, 550), new AnimatorHelper.Item(R.id.summary_keyword_1, AnimatorHelper.Item.SCALEX, new float[]{1.1f, 1.0f}, null, 750, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION), new AnimatorHelper.Item(R.id.summary_keyword_1, AnimatorHelper.Item.SCALEY, new float[]{1.1f, 1.0f}, null, 750, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION), new AnimatorHelper.Item(R.id.summary_keyword_bg_2, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f}, null, 600, 1100), new AnimatorHelper.Item(R.id.summary_keyword_bg_2, AnimatorHelper.Item.SCALEX, new float[]{0.8f, 1.0f}, null, 600, 1100), new AnimatorHelper.Item(R.id.summary_keyword_bg_2, AnimatorHelper.Item.SCALEY, new float[]{0.8f, 1.0f}, null, 600, 1100), new AnimatorHelper.Item(R.id.summary_keyword_circle_shadow, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f}, null, 350, 2000), new AnimatorHelper.Item(R.id.summary_keyword_circle_shadow, AnimatorHelper.Item.SCALEX, new float[]{0.0f, 1.0f}, null, 350, 2000), new AnimatorHelper.Item(R.id.summary_keyword_circle_shadow, AnimatorHelper.Item.SCALEY, new float[]{0.0f, 1.0f}, null, 350, 2000), new AnimatorHelper.Item(R.id.summary_keyword_ball, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f}, null, 350, 350), new AnimatorHelper.Item(R.id.summary_keyword_ball, AnimatorHelper.Item.ROTATE, new float[]{-180.0f, 0.0f}, null, HighLightsUtils.RESET_DELAY_TIME, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION), new AnimatorHelper.Item(R.id.summary_keyword_ball, AnimatorHelper.Item.SCALEX, new float[]{0.0f, 1.0f}, null, 350, 350), new AnimatorHelper.Item(R.id.summary_keyword_ball, AnimatorHelper.Item.SCALEY, new float[]{0.0f, 1.0f}, null, 350, 350), new AnimatorHelper.Item(R.id.summary_keyword_tuoyuanguang, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f}, null, 350, 2000), new AnimatorHelper.Item(R.id.summary_keywords_key_number_center, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f}, null, 450, 550), new AnimatorHelper.Item(R.id.summary_keywords_key_number_left, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f}, null, 450, 550), new AnimatorHelper.Item(R.id.summary_keywords_key_number_right, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f}, null, 450, 550), new AnimatorHelper.Item(R.id.summary_keywords_key_title_level, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f}, null, 450, 1900), new AnimatorHelper.Item(R.id.summary_keywords_key_title_level, AnimatorHelper.Item.SCALEX, new float[]{0.9f, 1.0f}, null, 400, 1850), new AnimatorHelper.Item(R.id.summary_keywords_key_title_level, AnimatorHelper.Item.SCALEY, new float[]{0.9f, 1.0f}, null, 400, 1850), new AnimatorHelper.Item(R.id.summary_keywords_key_number_center_light, AnimatorHelper.Item.SCALEX, new float[]{0.0f, 1.0f}, null, 400, 1700), new AnimatorHelper.Item(R.id.summary_keywords_key_number_center_light, AnimatorHelper.Item.SCALEY, new float[]{0.0f, 1.0f}, null, 400, 1700), new AnimatorHelper.Item(R.id.summary_keywords_key_number_left_light, AnimatorHelper.Item.SCALEX, new float[]{0.0f, 1.0f}, null, 400, 1700), new AnimatorHelper.Item(R.id.summary_keywords_key_number_left_light, AnimatorHelper.Item.SCALEY, new float[]{0.0f, 1.0f}, null, 400, 1700), new AnimatorHelper.Item(R.id.summary_keywords_key_number_right_light, AnimatorHelper.Item.SCALEX, new float[]{0.0f, 1.0f}, null, 400, 1700), new AnimatorHelper.Item(R.id.summary_keywords_key_number_right_light, AnimatorHelper.Item.SCALEY, new float[]{0.0f, 1.0f}, null, 400, 1700), this.m_item_number1, this.m_item_number2, this.m_item_number3};
        this.ITEMs_center = itemArr4;
        this.ITEMS = new AnimatorHelper.Item[][]{itemArr, itemArr4, itemArr2, itemArr3};
        this.ITEMS_ROTATE = new AnimatorHelper.Item[][]{new AnimatorHelper.Item[]{this.m_item_zhizhen, this.m_item_light_m, this.m_item_light_x}, null, new AnimatorHelper.Item[]{this.m_item_right_zhizhen, this.m_item_right_light_m, this.m_item_right_light_x}, null};
        this.ITEMS_NUMBER = new AnimatorHelper.Item[]{this.m_item_total_time, null, this.m_item_most_hour, this.m_item_most_minite};
        this.m_scroll_tip_move = new AnimatorHelper.Item(R.id.summary_scroll_tip, AnimatorHelper.Item.TRANSLATIONY, new float[]{0.0f, 20.0f}, null, HighLightsUtils.NORMAL_WIDTH, 2000);
        AnimatorHelper.Item item = new AnimatorHelper.Item(R.id.summary_scroll_tip, AnimatorHelper.Item.ALPHA, new float[]{1.0f, 0.6f}, null, HighLightsUtils.NORMAL_WIDTH, 2000);
        this.m_scroll_tip_alpha = item;
        this.ITEMs_indicator = new AnimatorHelper.Item[]{this.m_scroll_tip_move, item};
        this.m_helpers = new AnimatorHelper[4];
    }

    private ImageView getKeywordImage(int i) {
        if (this.m_keywordImage == null) {
            ImageView[] imageViewArr = new ImageView[3];
            this.m_keywordImage = imageViewArr;
            imageViewArr[0] = (ImageView) this.m_root.findViewById(R.id.summary_keyword_1);
            this.m_keywordImage[1] = (ImageView) this.m_root.findViewById(R.id.summary_keyword_2);
            this.m_keywordImage[2] = (ImageView) this.m_root.findViewById(R.id.summary_keyword_3);
        }
        if (i < 0) {
            return null;
        }
        ImageView[] imageViewArr2 = this.m_keywordImage;
        if (i >= imageViewArr2.length) {
            return null;
        }
        return imageViewArr2[i];
    }

    private ImageView getKeywordNumberCenter() {
        if (this.m_keywordNumberCenter == null) {
            this.m_keywordNumberCenter = (ImageView) this.m_root.findViewById(R.id.summary_keywords_key_number_center);
        }
        return this.m_keywordNumberCenter;
    }

    private ImageView getKeywordNumberCenterLight() {
        if (this.m_keywordNumberCenterLight == null) {
            this.m_keywordNumberCenterLight = (ImageView) this.m_root.findViewById(R.id.summary_keywords_key_number_center_light);
        }
        return this.m_keywordNumberCenterLight;
    }

    private ImageView getKeywordNumberLeft() {
        if (this.m_keywordNumberLeft == null) {
            this.m_keywordNumberLeft = (ImageView) this.m_root.findViewById(R.id.summary_keywords_key_number_left);
        }
        return this.m_keywordNumberLeft;
    }

    private ImageView getKeywordNumberLeftLight() {
        if (this.m_keywordNumberLeftLight == null) {
            this.m_keywordNumberLeftLight = (ImageView) this.m_root.findViewById(R.id.summary_keywords_key_number_left_light);
        }
        return this.m_keywordNumberLeftLight;
    }

    private ImageView getKeywordNumberRight() {
        if (this.m_keywordNumberRight == null) {
            this.m_keywordNumberRight = (ImageView) this.m_root.findViewById(R.id.summary_keywords_key_number_right);
        }
        return this.m_keywordNumberRight;
    }

    private ImageView getKeywordNumberRightLight() {
        if (this.m_keywordNumberRightLight == null) {
            this.m_keywordNumberRightLight = (ImageView) this.m_root.findViewById(R.id.summary_keywords_key_number_right_light);
        }
        return this.m_keywordNumberRightLight;
    }

    private TextView getKeywordTitle() {
        if (this.m_keywordTitle == null) {
            this.m_keywordTitle = (TextView) this.m_root.findViewById(R.id.summary_keywords_key_title_level);
        }
        return this.m_keywordTitle;
    }

    private TextView getKeywordView() {
        if (this.m_keywordView == null) {
            this.m_keywordView = (TextView) this.m_root.findViewById(R.id.summary_keyword);
        }
        return this.m_keywordView;
    }

    private ImageView getMostImage() {
        if (this.m_mostImage == null) {
            this.m_mostImage = (ImageView) this.m_root.findViewById(R.id.summary_keywords_most_icon);
        }
        return this.m_mostImage;
    }

    private TextView getMostTimeHour() {
        if (this.m_mostTimeHour == null) {
            this.m_mostTimeHour = (TextView) this.m_root.findViewById(R.id.summary_keywords_most_hour);
        }
        return this.m_mostTimeHour;
    }

    private TextView getMostTimeMinite() {
        if (this.m_mostTimeMinite == null) {
            this.m_mostTimeMinite = (TextView) this.m_root.findViewById(R.id.summary_keywords_most_minite);
        }
        return this.m_mostTimeMinite;
    }

    private TextView getMostTitle() {
        if (this.m_mostTitle == null) {
            this.m_mostTitle = (TextView) this.m_root.findViewById(R.id.summary_keywords_most_title);
        }
        return this.m_mostTitle;
    }

    private TextView getMostUnitHour() {
        if (this.m_mostUnitHour == null) {
            this.m_mostUnitHour = (TextView) this.m_root.findViewById(R.id.summary_keywords_most_unit_hour);
        }
        return this.m_mostUnitHour;
    }

    private TextView getMostUnitMinite() {
        if (this.m_mostUnitMinite == null) {
            this.m_mostUnitMinite = (TextView) this.m_root.findViewById(R.id.summary_keywords_most_unit_minite);
        }
        return this.m_mostUnitMinite;
    }

    private Interpolator getScrollTipInterpolator() {
        if (this.m_scrollTipInterpolator == null) {
            float[] fArr = INDICATOR_CURVE;
            this.m_scrollTipInterpolator = new MultiStateInterpolator(new PathInterpolator(fArr[0], fArr[1], fArr[2], fArr[3]), 0.47916666f, 0.8333333f);
        }
        return this.m_scrollTipInterpolator;
    }

    private TextView getTotalHour() {
        if (this.m_totalHour == null) {
            this.m_totalHour = (TextView) this.m_root.findViewById(R.id.summary_keywords_total_hour);
        }
        return this.m_totalHour;
    }

    private TextView getTotalMin() {
        if (this.m_totalMin == null) {
            this.m_totalMin = (TextView) this.m_root.findViewById(R.id.summary_keywords_total_minite);
        }
        return this.m_totalMin;
    }

    private TextView getTotalTime() {
        if (this.m_totalTime == null) {
            this.m_totalTime = (TextView) this.m_root.findViewById(R.id.summary_keywords_total_time);
        }
        return this.m_totalTime;
    }

    private String levelToKeyword(SummaryDataHelper.LEVEL level) {
        return getString(level == SummaryDataHelper.LEVEL.TIRED ? R.string.gcs_summary_keyword_tip_2 : level == SummaryDataHelper.LEVEL.EXCESS ? R.string.gcs_summary_keyword_tip_3 : R.string.gcs_summary_keyword_tip_1);
    }

    private int levelToKeywordImage(SummaryDataHelper.LEVEL level, SummaryDataHelper.LEVEL level2) {
        if (level2 != SummaryDataHelper.LEVEL.EXCESS ? level2 != SummaryDataHelper.LEVEL.TIRED || level == SummaryDataHelper.LEVEL.EXCESS || level == SummaryDataHelper.LEVEL.TIRED : level == SummaryDataHelper.LEVEL.EXCESS) {
            return level2 == SummaryDataHelper.LEVEL.EXCESS ? R.drawable.gcs_gamecenter_summarycenter_3 : level2 == SummaryDataHelper.LEVEL.TIRED ? R.drawable.gcs_gamecenter_summarycenter_2 : R.drawable.gcs_gamecenter_summarycenter_1;
        }
        return 0;
    }

    private int levelToKeywordNumber(int i, SummaryDataHelper.LEVEL level) {
        return levelToKeywordNumber(i, level, NUMBER_STATEs);
    }

    private int levelToKeywordNumber(int i, SummaryDataHelper.LEVEL level, int[][] iArr) {
        int[] iArr2;
        char c = level == SummaryDataHelper.LEVEL.TIRED ? (char) 1 : level == SummaryDataHelper.LEVEL.EXCESS ? (char) 2 : (char) 0;
        if (i < 0 || i > 3) {
            i = 0;
        }
        if (iArr == null || iArr.length < 3 || (iArr2 = iArr[c]) == null || iArr2.length < 3) {
            return 0;
        }
        return iArr2[i];
    }

    private int levelToKeywordNumberLight(int i, SummaryDataHelper.LEVEL level) {
        return levelToKeywordNumber(i, level, NUMBER_LIGHT_STATEs);
    }

    private String levelToKeywordTitle(SummaryDataHelper.LEVEL level) {
        return getString(level == SummaryDataHelper.LEVEL.TIRED ? R.string.gcs_summary_keyword_title_2 : level == SummaryDataHelper.LEVEL.EXCESS ? R.string.gcs_summary_keyword_title_3 : R.string.gcs_summary_keyword_title_1);
    }

    private String minToTime(int i) {
        return Integer.toString(minToTimeInt(i));
    }

    private int minToTimeInt(int i) {
        return i < 60 ? i : i / 60;
    }

    private String minToUnit(int i) {
        return getString(i < 60 ? R.string.gcs_minite : R.string.gcs_hour);
    }

    private void startAllAnimations(int i, int i2, int i3, int i4, View view) {
        int i5;
        if (view != null) {
            AnimatorHelper[] animatorHelperArr = this.m_helpers;
            if (i4 >= animatorHelperArr.length || i4 >= this.ITEMS.length) {
                return;
            }
            if (animatorHelperArr[i4] == null) {
                animatorHelperArr[i4] = new AnimatorHelper(view, this.ITEMS[i4]);
            }
            AnimatorHelper.Item[] itemArr = this.ITEMS_NUMBER;
            if (i4 >= itemArr.length || itemArr[i4] == null) {
                i5 = 0;
            } else {
                i5 = minToTimeInt(i);
                updateNumberAnimation(i == -1 ? -1 : i5, this.ITEMS_NUMBER[i4]);
            }
            AnimatorHelper.Item[][] itemArr2 = this.ITEMS_ROTATE;
            if (i4 < itemArr2.length) {
                updateRotateAnimation(i != -1 ? i5 : 0, i2, i3, itemArr2[i4]);
            }
            this.m_helpers[i4].start();
        }
    }

    private void startAllAnimations(SummaryDataHelper summaryDataHelper) {
        if (this.m_root == null) {
            return;
        }
        startAllAnimations((summaryDataHelper.getTotalTime() / 60) * 60, summaryDataHelper.getTotalTime(), summaryDataHelper.getMaxTimeRange(), 0, this.m_root.findViewById(R.id.summary_keywords_total));
        startAllAnimations(-1, -1, -1, 1, this.m_root.findViewById(R.id.summary_keywords_key));
        int mostTime = (summaryDataHelper.getMostTime() / 60) * 60;
        startAllAnimations(mostTime == 0 ? -1 : mostTime, summaryDataHelper.getMostTime(), summaryDataHelper.getMaxTimeRange(), 2, this.m_root.findViewById(R.id.summary_keywords_most));
        int mostTime2 = summaryDataHelper.getMostTime() % 60;
        startAllAnimations((mostTime2 != 0 || summaryDataHelper.getMostTime() == 0) ? mostTime2 : -1, -1, -1, 3, this.m_root.findViewById(R.id.summary_keywords_most));
        startIndicatorAnimation(this.m_root);
    }

    private void startIndicatorAnimation(View view) {
        if (this.m_indicator == null) {
            this.m_scroll_tip_move.setRepeatCount(-1);
            this.m_scroll_tip_alpha.setRepeatCount(-1);
            this.m_scroll_tip_move.setInterpolator(getScrollTipInterpolator());
            this.m_scroll_tip_alpha.setInterpolator(getScrollTipInterpolator());
            this.m_indicator = new AnimatorHelper(view, this.ITEMs_indicator);
        }
        this.m_indicator.start();
    }

    private void updateKeyword(SummaryDataHelper.LEVEL level) {
        if (getKeywordView() != null) {
            getKeywordView().setText(levelToKeyword(level));
        }
        if (getKeywordImage(0) != null) {
            getKeywordImage(0).setImageResource(levelToKeywordImage(level, SummaryDataHelper.LEVEL.NORMAL));
        }
        if (getKeywordImage(1) != null) {
            getKeywordImage(1).setImageResource(levelToKeywordImage(level, SummaryDataHelper.LEVEL.TIRED));
        }
        if (getKeywordImage(2) != null) {
            getKeywordImage(2).setImageResource(levelToKeywordImage(level, SummaryDataHelper.LEVEL.EXCESS));
        }
        if (getKeywordTitle() != null) {
            getKeywordTitle().setText(levelToKeywordTitle(level));
        }
        if (getKeywordNumberCenter() != null) {
            getKeywordNumberCenter().setImageResource(levelToKeywordNumber(1, level));
        }
        if (getKeywordNumberRight() != null) {
            getKeywordNumberRight().setImageResource(levelToKeywordNumber(2, level));
        }
        if (getKeywordNumberLeft() != null) {
            getKeywordNumberLeft().setImageResource(levelToKeywordNumber(0, level));
        }
        if (getKeywordNumberCenterLight() != null) {
            getKeywordNumberCenterLight().setImageResource(levelToKeywordNumberLight(1, level));
        }
        if (getKeywordNumberRightLight() != null) {
            getKeywordNumberRightLight().setImageResource(levelToKeywordNumberLight(2, level));
        }
        if (getKeywordNumberLeftLight() != null) {
            getKeywordNumberLeftLight().setImageResource(levelToKeywordNumberLight(0, level));
        }
    }

    private void updateMost(Drawable drawable, String str, int i) {
        if (getMostImage() != null) {
            if (i == 0) {
                getMostImage().setImageResource(R.drawable.gcs_gamecenter_percent_icon_default);
            } else {
                getMostImage().setImageDrawable(drawable);
            }
        }
        if (getMostTitle() != null) {
            if (i == 0) {
                getMostTitle().setText(R.string.gcs_summary_percent_no_game);
            } else {
                getMostTitle().setText(str);
            }
        }
        if (getMostTimeHour() == null || getMostTimeMinite() == null || getMostUnitHour() == null || getMostUnitMinite() == null) {
            return;
        }
        if (i < 60) {
            getMostTimeHour().setText("");
            getMostTimeHour().setVisibility(8);
            getMostUnitHour().setText("");
            getMostUnitHour().setVisibility(8);
        } else {
            getMostTimeHour().setText("0");
            getMostTimeHour().setVisibility(0);
            getMostUnitHour().setText(minToUnit(60));
            getMostUnitHour().setVisibility(0);
        }
        if (i % 60 != 0 || i == 0) {
            getMostTimeMinite().setText("0");
            getMostTimeMinite().setVisibility(0);
            getMostUnitMinite().setText(minToUnit(1));
            getMostUnitMinite().setVisibility(0);
            return;
        }
        getMostTimeMinite().setText("");
        getMostTimeMinite().setVisibility(8);
        getMostUnitMinite().setText("");
        getMostUnitMinite().setVisibility(8);
    }

    private void updateNumberAnimation(int i, AnimatorHelper.Item item) {
        if (i < 0) {
            item.setDuration(70);
            item.setParams(new float[]{-1.0f, -1.0f});
        } else {
            item.setDuration(i * 70);
            item.setParams(new float[]{0.0f, i});
        }
    }

    private void updateRotateAnimation(int i, int i2, int i3, AnimatorHelper.Item[] itemArr) {
        if (itemArr == null || itemArr.length == 0) {
            return;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        if (i3 <= 0) {
            i2 = 0;
            i3 = 1;
        }
        if (i2 > i3) {
            i2 = i3;
        }
        if (i < 0) {
            i = 0;
        }
        float f = (i2 / i3) * 360.0f;
        int i4 = i * 70;
        for (AnimatorHelper.Item item : itemArr) {
            item.setDuration(i4);
            item.setParams(new float[]{0.0f, f});
        }
    }

    private void updateTotal(int i) {
        if (getTotalTime() != null) {
            getTotalTime().setText("0");
        }
        if (getTotalHour() != null) {
            getTotalHour().setText(minToUnit(60));
        }
        if (getTotalMin() != null) {
            getTotalMin().setText(Integer.toString(i % 60) + minToUnit(1));
        }
    }

    public void releaseAnimatorRes() {
        for (AnimatorHelper animatorHelper : this.m_helpers) {
            animatorHelper.cancel();
        }
        AnimatorHelper animatorHelper2 = this.m_indicator;
        if (animatorHelper2 != null) {
            animatorHelper2.cancel();
        }
    }

    @Override // cn.nubia.gamecenter.settings.summary.SummaryPageHelper
    public void update(SummaryDataHelper summaryDataHelper) {
        updateKeyword(summaryDataHelper.getKeywordLevel());
        updateTotal(summaryDataHelper.getTotalTime());
        updateMost(summaryDataHelper.getMostIcon(), summaryDataHelper.getMostTitle(), summaryDataHelper.getMostTime());
        startAllAnimations(summaryDataHelper);
    }
}
