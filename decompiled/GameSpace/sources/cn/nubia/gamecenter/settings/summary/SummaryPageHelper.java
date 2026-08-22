package cn.nubia.gamecenter.settings.summary;

import android.view.View;
import cn.nubia.gamecenter.settings.R;

/* loaded from: classes.dex */
public abstract class SummaryPageHelper implements View.OnClickListener {
    private static final String TAG = "SummaryPageHelper";
    private final Callback m_callback;
    private final int m_nPage;
    protected final View m_root;

    public interface Callback {
        boolean isCurrentPage(int i);

        void toNextPage();
    }

    SummaryPageHelper(View view, Callback callback, int i) {
        this.m_root = view;
        this.m_callback = callback;
        this.m_nPage = i;
        init();
    }

    private void init() {
        View findViewById = this.m_root.findViewById(R.id.summary_scroll_tip);
        if (findViewById != null) {
            findViewById.setOnClickListener(this);
        }
    }

    protected String getString(int i) {
        return this.m_root.getResources().getString(i);
    }

    protected boolean isCurrentPage() {
        Callback callback = this.m_callback;
        if (callback != null) {
            return callback.isCurrentPage(this.m_nPage);
        }
        return false;
    }

    protected String minToFormatTime(int i) {
        return minToFormatTime(true, i);
    }

    protected String minToFormatTime(boolean z, int i) {
        String str = z ? "" + getString(R.string.gcs_total_time) + "：" : "";
        if (i >= 60) {
            str = str + Integer.toString(i / 60) + getString(R.string.gcs_hour);
        }
        int i2 = i % 60;
        return (i2 != 0 || i < 60) ? str + Integer.toString(i2) + getString(R.string.gcs_minite) : str;
    }

    protected String msToFormatTime(boolean z, int i) {
        return minToFormatTime(z, SummaryDataHelper.msToMinite(i));
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        toNextPage();
    }

    protected void toNextPage() {
        Callback callback = this.m_callback;
        if (callback != null) {
            callback.toNextPage();
        }
    }

    public abstract void update(SummaryDataHelper summaryDataHelper);
}
