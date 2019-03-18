package net.melonbun.melonbun.explorer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;

import net.melonbun.melonbun.R;
import net.melonbun.melonbun.common.model.Request;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class PostedRequestAdapter extends RecyclerView.Adapter<PostedRequestAdapter.ViewHolder> {

    private final static int FADE_DURATION = 250;

    private List<Request> requests;

    public PostedRequestAdapter(List<Request> requests) {
        this.requests = requests;
    }

    @Override
    public PostedRequestAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MaterialCardView cardView = (MaterialCardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_request_card, parent, false);

        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.requestCardTitle.setText(requests.get(position).getRequestTitle());
        holder.requestCardDate.setText(requests.get(position).getRequestDate());
        setFadeAnimation(holder.requestCardView);
    }

    @Override
    public int getItemCount() {
        return requests.size();
    }

    private void setFadeAnimation(View view) {
        AlphaAnimation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(FADE_DURATION);
        view.startAnimation(animation);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        MaterialCardView requestCardView;
        ImageView requestCardImage;
        TextView requestCardTitle;
        TextView requestCardDate;

        ViewHolder(MaterialCardView requestCardView) {
            super(requestCardView);

            this.requestCardView = requestCardView;
            requestCardImage = requestCardView.findViewById(R.id.request_card_image);
            //requestCardTitle = requestCardView.findViewById(R.id.request_card_title);
            //requestCardDate = requestCardView.findViewById(R.id.request_card_date);
        }
    }
}
