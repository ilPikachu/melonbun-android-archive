package net.melonbun.melonbun.request;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.melonbun.melonbun.R;
import net.melonbun.melonbun.request.model.Request;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PostedRequestFragment extends Fragment implements PostedRequestsView {

    private static final String SELECTED_TAB_INDEX = "selectedTabIndex";

    @BindView(R.id.posted_request_list) RecyclerView requestList;

    private Unbinder unbinder;
    private PostedRequestAdapter postedRequestAdapter;
    private PostedRequestPresenter presenter;

    public static PostedRequestFragment newInstance(int selectedTabIndex) {
        PostedRequestFragment postedRequestFragment = new PostedRequestFragment();
        Bundle args = new Bundle();
        args.putInt(SELECTED_TAB_INDEX, selectedTabIndex);
        postedRequestFragment.setArguments(args);
        return postedRequestFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.fragment_posted_request, container, false);
        unbinder = ButterKnife.bind(this, inflatedView);
        return inflatedView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new PostedRequestPresenter();
        presenter.bindView(this);
        presenter.decorateView();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.bindView(this);
        presenter.updateState();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.unbindView();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showRequests(List<Request> requests) {
        requestList.setLayoutManager(new LinearLayoutManager(getContext()));
        postedRequestAdapter = new PostedRequestAdapter(requests);
        requestList.setAdapter(postedRequestAdapter);
    }

}
