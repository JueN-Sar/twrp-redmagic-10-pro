package cn.nubia.gameassist.utils;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.gameassist.R;
import com.zte.gameassist.common.RotationMgr;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes.dex */
public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

    /* renamed from: a, reason: collision with root package name */
    private int f7659a;

    /* renamed from: b, reason: collision with root package name */
    private int f7660b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f7661c;

    /* renamed from: d, reason: collision with root package name */
    private Context f7662d;

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        int f0 = recyclerView.f0(view);
        int i2 = f0 % this.f7659a;
        GaLog.a("GridSpacingItemDecoration", " [LYH] getItemOffsets: spacing : " + this.f7660b + ";; spanCount : " + this.f7659a + " ;;column : " + i2);
        if (this.f7661c) {
            int i3 = this.f7660b;
            int i4 = this.f7659a;
            rect.left = i3 - ((i2 * i3) / i4);
            rect.right = ((i2 + 1) * i3) / i4;
            if (f0 < i4) {
                rect.top = i3;
            }
            rect.bottom = i3;
            return;
        }
        int i5 = this.f7660b;
        int i6 = this.f7659a;
        rect.left = (i2 * i5) / i6;
        rect.right = i5 - (((i2 + 1) * i5) / i6);
        GaLog.a("GridSpacingItemDecoration", " [LYH] getItemOffsets: outRect.left : " + rect.left + ";; outRect.right : " + rect.right + " ;;column : " + i2);
        if (f0 >= this.f7659a) {
            rect.top = this.f7662d.getResources().getDimensionPixelSize(RotationMgr.j() ? R.dimen.pip_icon_margin_top_padding_land : R.dimen.pip_icon_margin_top_padding);
        }
    }
}
