package net.melonbun.melonbun.request;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;

import net.melonbun.melonbun.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class PostedRequestAdapter extends RecyclerView.Adapter<PostedRequestAdapter.ViewHolder> {

    private List<Request> requests;

    public PostedRequestAdapter(List<Request> requests) {
        this.requests = requests;
    }

    @Override
    public PostedRequestAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MaterialCardView cardView = (MaterialCardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_request, parent, false);

        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.requestCardTitle.setText(requests.get(position).getRequestTitle());
        holder.requestCardDate.setText(requests.get(position).getRequestDate());
    }

    @Override
    public int getItemCount() {
        return requests.size();
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
            requestCardTitle = requestCardView.findViewById(R.id.request_card_title);
            requestCardDate = requestCardView.findViewById(R.id.request_card_date);
        }
    }
}
