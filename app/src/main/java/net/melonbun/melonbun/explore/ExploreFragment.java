package net.melonbun.melonbun.explore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewSwitcher;

import net.melonbun.melonbun.MelonbunApplication;
import net.melonbun.melonbun.R;
import net.melonbun.melonbun.common.model.Request;
import net.melonbun.melonbun.common.ui.OfflineComponent;
import net.melonbun.melonbun.explore.adapter.RequestAdapter;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * This is the fragment for the explore page
 */
public class ExploreFragment extends Fragment implements ExploreView {

    private static final int SWITCH_DEFAULT_VIEW = 0;
    private static final int SWITCH_OFFLINE_VIEW = 1;

    @BindView(R.id.explore_content_switch)
    ViewSwitcher viewSwitcher;
    @BindView(R.id.posted_request_list)
    RecyclerView requestList;
    @BindView(R.id.offline_view)
    OfflineComponent offlineView;

    @Inject
    ExplorePresenter presenter;

    private Unbinder unbinder;
    private RequestAdapter requestAdapter;

    public static ExploreFragment newInstance() {
        ExploreFragment exploreFragment = new ExploreFragment();
        return exploreFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.fragment_explore, container, false);
        unbinder = ButterKnife.bind(this, inflatedView);
        return inflatedView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        offlineView.setRetryButtonOnClickListener(view1 -> presenter.decorateView());

        MelonbunApplication.getApplicationComponent().inject(this);
        presenter.bindView(this);
        presenter.decorateView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.bindView(this);
        presenter.updateState();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.unbindView();
    }

    @Override
    public void showRequests(List<Request> requests) {
        requestList.setLayoutManager(new LinearLayoutManager(getContext()));
        requestAdapter = new RequestAdapter(requests);
        requestList.setAdapter(requestAdapter);
        viewSwitcher.setDisplayedChild(SWITCH_DEFAULT_VIEW);
    }

    @Override
    public void showOfflineView() {
        viewSwitcher.setDisplayedChild(SWITCH_OFFLINE_VIEW);
    }
}
