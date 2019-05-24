package net.melonbun.melonbun.explore.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;

import net.melonbun.melonbun.R;
import net.melonbun.melonbun.common.model.RequestResponse;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.ViewHolder> {

    private final static int FADE_DURATION = 250;
    private final static int REQUEST_CARD_BODY_MAX_LINES = 2;

    private List<RequestResponse> requestResponses;

    public RequestAdapter(List<RequestResponse> requestResponses) {
        this.requestResponses = requestResponses;
    }

    @Override
    public RequestAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //TODO: Create custom cardView so it's easier to set listeners
        MaterialCardView cardView = (MaterialCardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_request_card, parent, false);

        final ViewHolder holder = new ViewHolder(cardView);

        holder.requestCardBody.setOnClickListener(view -> {
            //TODO: Optimization required
            if (holder.requestCardBody.getMaxLines() == REQUEST_CARD_BODY_MAX_LINES) {
                holder.requestCardBody.setMaxLines(Integer.MAX_VALUE);
                holder.requestCardBody.setEllipsize(null);
            } else {
                holder.requestCardBody.setMaxLines(REQUEST_CARD_BODY_MAX_LINES);
                holder.requestCardBody.setEllipsize(TextUtils.TruncateAt.END);
            }
            TransitionManager.beginDelayedTransition(holder.requestCardView);
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.requestCardUserId.setText(requestResponses.get(position).getId());
        holder.requestCardDate.setText(requestResponses.get(position).getDate());
        holder.requestCardTitle.setText(requestResponses.get(position).getTitle());
        holder.requestCardBody.setText(requestResponses.get(position).getBody());
        final ImageView requestCardFavorite = holder.requestCardFavorite;

        // TODO: Set ImageDrawable according to fav boolean back from API response
        if (requestResponses.get(position).getFavState()) {
            requestCardFavorite.setImageDrawable(requestCardFavorite.getContext().getResources().getDrawable(R.drawable.ic_favorite_fill));
            requestCardFavorite.setTag(R.drawable.ic_favorite_fill);
        } else if (!requestResponses.get(position).getFavState()) {
            requestCardFavorite.setImageDrawable(requestCardFavorite.getContext().getResources().getDrawable(R.drawable.ic_favorite_border));
            requestCardFavorite.setTag(R.drawable.ic_favorite_border);
        }

        requestCardFavorite.setOnClickListener(view -> {
            if ((int) requestCardFavorite.getTag() == R.drawable.ic_favorite_border) {
                requestCardFavorite.setImageDrawable(requestCardFavorite.getContext().getResources().getDrawable(R.drawable.ic_favorite_fill));
                requestCardFavorite.setTag(R.drawable.ic_favorite_fill);
                requestResponses.get(position).setFavState(true);
            } else if ((int) requestCardFavorite.getTag() == R.drawable.ic_favorite_fill) {
                requestCardFavorite.setImageDrawable(requestCardFavorite.getContext().getResources().getDrawable(R.drawable.ic_favorite_border));
                requestCardFavorite.setTag(R.drawable.ic_favorite_border);
                requestResponses.get(position).setFavState(false);
            }
            // TODO: favourite locally, if has network, POST call to favourites, changes icon to solid heart
        });

        holder.requestCardView.setOnClickListener(view -> {
            // TODO: launches RequestPage fragment
        });

        holder.requestCardShare.setOnClickListener(view -> {
            // TODO: launches intent with share
        });

        setFadeAnimation(holder.requestCardView);
    }

    @Override
    public int getItemCount() {
        return requestResponses.size();
    }

    public void clear() {
        requestResponses.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<RequestResponse> requestResponses) {
        this.requestResponses.addAll(requestResponses);
        notifyDataSetChanged();
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
        TextView requestCardBody;

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
            requestCardBody = requestCardView.findViewById(R.id.request_card_body);
        }
    }
}
