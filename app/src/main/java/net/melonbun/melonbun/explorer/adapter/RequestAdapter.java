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

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.ViewHolder> {

    private final static int FADE_DURATION = 250;

    private List<Request> requests;

    public RequestAdapter(List<Request> requests) {
        this.requests = requests;
    }

    @Override
    public RequestAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //TODO: Create custom cardView so it's easier to set listeners
        MaterialCardView cardView = (MaterialCardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_request_card, parent, false);

        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.requestCardUserId.setText(requests.get(position).getId());
        holder.requestCardDate.setText(requests.get(position).getDate());
        holder.requestCardTitle.setText(requests.get(position).getTitle());
        holder.requestCardFavorite.setOnClickListener(view -> {
            // TODO: favourite locally, if has network, POST call to favourites, changes icon to solid heart
        });
        holder.requestCardShare.setOnClickListener(view -> {
            // TODO: launches intent with share
        });
        holder.requestCardView.setOnClickListener(view -> {
            // TODO: launches RequestPage fragment
        });

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
        ImageView requestCardUserProfileImage;
        TextView requestCardUserId;
        TextView requestCardDate;
        TextView requestCardTitle;
        ImageView requestCardImage;
        ImageView requestCardFavorite;
        ImageView requestCardShare;

        ViewHolder(MaterialCardView requestCardView) {
            super(requestCardView);

            this.requestCardView = requestCardView;
            requestCardUserProfileImage = requestCardView.findViewById(R.id.request_card_user_profile_image);
            requestCardUserId = requestCardView.findViewById(R.id.request_card_user_id);
            requestCardDate = requestCardView.findViewById(R.id.request_card_posted_date);
            requestCardTitle = requestCardView.findViewById(R.id.request_card_title);
            requestCardImage = requestCardView.findViewById(R.id.request_card_image);
            requestCardFavorite = requestCardView.findViewById(R.id.request_card_favorite);
            requestCardShare = requestCardView.findViewById(R.id.request_card_share);
        }
    }
}
