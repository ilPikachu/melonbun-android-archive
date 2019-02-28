package net.melonbun.melonbun.request;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.melonbun.melonbun.R;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FavouriteRequestFragment extends Fragment implements FavouriteRequestView {

    private static final String SELECTED_TAB_INDEX = "selectedTabIndex";

    private Unbinder unbinder;

    public static FavouriteRequestFragment newInstance(int selectedTabIndex) {
        FavouriteRequestFragment favouriteRequestFragment = new FavouriteRequestFragment();
        Bundle args = new Bundle();
        args.putInt(SELECTED_TAB_INDEX, selectedTabIndex);
        favouriteRequestFragment.setArguments(args);
        return favouriteRequestFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.fragment_posted_request, container, false);
        unbinder = ButterKnife.bind(this, inflatedView);
        return inflatedView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
