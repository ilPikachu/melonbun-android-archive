package net.melonbun.melonbun.explore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.melonbun.melonbun.R;
import net.melonbun.melonbun.common.BaseFragment;
import net.melonbun.melonbun.common.model.Request;
import net.melonbun.melonbun.explore.adapter.RequestAdapter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * This is the fragment for the explore page
 */
public class ExploreFragment extends BaseFragment<ExplorePresenter> implements ExploreView {

    @BindView(R.id.posted_request_list)
    RecyclerView requestList;

    private Unbinder unbinder;
    private RequestAdapter requestAdapter;
    private ExplorePresenter presenter;

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
        //TODO: Presenter DI
        presenter = new ExplorePresenter();
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
    protected Class<ExplorePresenter> supplyPresenterClass() {
        return ExplorePresenter.class;
    }

    @Override
    public void showRequests(List<Request> requests) {
        requestList.setLayoutManager(new LinearLayoutManager(getContext()));
        requestAdapter = new RequestAdapter(requests);
        requestList.setAdapter(requestAdapter);
    }
}
