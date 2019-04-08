package net.melonbun.melonbun.common.ui;

import android.content.Context;
import android.media.Image;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.melonbun.melonbun.R;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ErrorComponent extends LinearLayout {

    @BindView(R.id.error_image)
    protected ImageView errorImage;
    @BindView(R.id.error_text)
    protected TextView errorText;
    @BindView(R.id.retry_button)
    protected Button retryButton;

    public ErrorComponent(Context context) {
        super(context);
        inflateLayout(context);
    }

    public ErrorComponent(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflateLayout(context);
    }

    public ErrorComponent(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflateLayout(context);
    }

    public ErrorComponent(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        inflateLayout(context);
    }

    private void inflateLayout(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_error, this, true);
        ButterKnife.bind(this, this);
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);
    }

    public void setErrorImage(int drawableId) {
        errorImage.setImageDrawable(getResources().getDrawable(drawableId));
    }

    public void setErrorText(int textId) {
        errorText.setText(getResources().getText(textId));
    }

    public void setRetryButtonOnClickListener(OnClickListener onClickListener) {
        retryButton.setOnClickListener(onClickListener);
    }
}
