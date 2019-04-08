package net.melonbun.melonbun.explore;

import net.melonbun.melonbun.common.BasePresenter;
import net.melonbun.melonbun.common.model.RequestResponse;
import net.melonbun.melonbun.common.network.ConnectivityCheck;
import net.melonbun.melonbun.explore.network.ExploreService;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * This is the presenter for {@link ExploreFragment}
 */
public class ExplorePresenter extends BasePresenter<ExploreView> {

    private final ConnectivityCheck connectivityCheck;
    private final ExploreService exploreService;

    @Inject
    public ExplorePresenter(ConnectivityCheck connectivityCheck, ExploreService exploreService) {
        this.connectivityCheck = connectivityCheck;
        this.exploreService = exploreService;
    }

    void decorateView() {
        if (connectivityCheck.isConnected()) {
            exploreService.getRequests().enqueue(new Callback<List<RequestResponse>>() {
                @Override
                public void onResponse(Call<List<RequestResponse>> call, Response<List<RequestResponse>> response) {
                    if (!response.isSuccessful()) {
                        setupErrorView();
                    } else {
                        setupRequests(response.body());
                    }
                }

                @Override
                public void onFailure(Call<List<RequestResponse>> call, Throwable throwable) {
                    setupErrorView();
                }
            });
        } else {
            setupOfflineView();
        }
    }

    private void setupRequests(List<RequestResponse> postedRequestResponses) {
        if (postedRequestResponses != null && !postedRequestResponses.isEmpty()) {
            executeViewOperation(() -> view.hideErrorView());
            executeViewOperation(() -> view.hideOfflineView());
            executeViewOperation(() -> view.showRequests(postedRequestResponses));
        } else {
            setupErrorView();
        }
    }

    private void setupErrorView() {
        executeViewOperation(() -> view.hideRequests());
        executeViewOperation(() -> view.hideOfflineView());
        executeViewOperation(() -> view.showErrorView());
    }

    private void setupOfflineView() {
        executeViewOperation(() -> view.hideRequests());
        executeViewOperation(() -> view.hideErrorView());
        executeViewOperation(() -> view.showOfflineView());
    }
}
