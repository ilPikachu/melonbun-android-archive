package net.melonbun.melonbun.explorer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.melonbun.melonbun.R;
import net.melonbun.melonbun.common.BaseFragment;
import net.melonbun.melonbun.common.model.Request;
import net.melonbun.melonbun.explorer.adapter.PostedRequestAdapter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ExplorerFragment extends BaseFragment implements ExplorerView {

    private static final String SELECTED_TAB_INDEX = "selectedTabIndex";

    @BindView(R.id.posted_request_list) RecyclerView requestList;

    private Unbinder unbinder;
    private PostedRequestAdapter postedRequestAdapter;
    private ExplorerPresenter presenter;

    public static ExplorerFragment newInstance(int selectedTabIndex) {
        ExplorerFragment explorerFragment = new ExplorerFragment();
        Bundle args = new Bundle();
        args.putInt(SELECTED_TAB_INDEX, selectedTabIndex);
        explorerFragment.setArguments(args);
        return explorerFragment;
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
        presenter = new ExplorerPresenter();
        presenter.bindView(this);
        presenter.decorateView();
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    protected Class<ExplorerPresenter> presenterClassInjection() {
        return ExplorerPresenter.class;
    }

    @Override
    public void showRequests(List<Request> requests) {
        requestList.setLayoutManager(new LinearLayoutManager(getContext()));
        postedRequestAdapter = new PostedRequestAdapter(requests);
        requestList.setAdapter(postedRequestAdapter);
    }

}
