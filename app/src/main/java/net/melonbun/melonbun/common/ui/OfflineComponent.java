package net.melonbun.melonbun.common.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;

import net.melonbun.melonbun.R;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;

public class OfflineComponent extends LinearLayout {

    @BindView(R.id.offline_retry_button)
    protected Button retryButton;

    public OfflineComponent(Context context) {
        super(context);
        inflateLayout(context);
    }

    public OfflineComponent(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflateLayout(context);
    }

    public OfflineComponent(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflateLayout(context);
    }

    public OfflineComponent(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        inflateLayout(context);
    }

    private void inflateLayout(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_offline, this, true);
        ButterKnife.bind(this, this);
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);
    }

    public void setRetryButtonOnClickListener(OnClickListener onClickListener) {
        retryButton.setOnClickListener(onClickListener);
    }
}
