package net.melonbun.melonbun.explore;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import net.melonbun.melonbun.MelonbunApplication;
import net.melonbun.melonbun.R;
import net.melonbun.melonbun.common.BaseFragment;
import net.melonbun.melonbun.common.model.RequestResponse;
import net.melonbun.melonbun.common.ui.ErrorComponent;
import net.melonbun.melonbun.common.ui.FastScrollLinearLayoutManager;
import net.melonbun.melonbun.common.ui.OfflineComponent;
import net.melonbun.melonbun.explore.adapter.RequestAdapter;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * This is the fragment for the explore page
 */
public class ExploreFragment extends BaseFragment implements ExploreView {

    private static final String BUNDLE_RECYCLER_LAYOUT = "BUNDLE_RECYCLER_LAYOUT";

    @BindView(R.id.progress_bar_loading)
    ProgressBar progressBar;
    @BindView(R.id.posted_request_list)
    RecyclerView requestList;
    @BindView(R.id.error_view)
    ErrorComponent errorComponent;
    @BindView(R.id.offline_view)
    OfflineComponent offlineComponent;

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

        errorComponent.setRetryButtonOnClickListener(view1 -> presenter.decorateView());
        offlineComponent.setRetryButtonOnClickListener(view1 -> presenter.decorateView());

        MelonbunApplication.getApplicationComponent().inject(this);
        presenter.bindView(this);
        presenter.decorateView();

        requestList.setLayoutManager(new FastScrollLinearLayoutManager(getContext(), FastScrollLinearLayoutManager.VERTICAL, false));
        setBottomNavEventListener(this::scrollToTop);
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
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if (savedInstanceState != null) {
            Parcelable savedRecyclerLayoutState = savedInstanceState.getParcelable(BUNDLE_RECYCLER_LAYOUT);
            if (savedRecyclerLayoutState != null && requestList.getLayoutManager() != null) {
                requestList.getLayoutManager().onRestoreInstanceState(savedRecyclerLayoutState);
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        if (requestList.getLayoutManager() != null && requestList.getLayoutManager().onSaveInstanceState() != null) {
            outState.putParcelable(BUNDLE_RECYCLER_LAYOUT, requestList.getLayoutManager().onSaveInstanceState());
        }
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showRequests(List<RequestResponse> requestResponses) {
        requestAdapter = new RequestAdapter(requestResponses);
        requestList.setAdapter(requestAdapter);
        requestList.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRequests() {
        requestList.setVisibility(View.GONE);
    }

    @Override
    public void showErrorView() {
        errorComponent.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideErrorView() {
        errorComponent.setVisibility(View.GONE);
    }

    @Override
    public void showOfflineView() {
        offlineComponent.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideOfflineView() {
        offlineComponent.setVisibility(View.GONE);
    }

    @Override
    public void scrollToTop() {
        if (requestList.getLayoutManager() != null) {
            requestList.smoothScrollToPosition(0);
        }
    }

}
