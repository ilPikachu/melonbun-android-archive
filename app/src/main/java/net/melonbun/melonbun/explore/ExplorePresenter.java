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

//TODO: RxAndroid for API calls https://github.com/ReactiveX/RxAndroid

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
                        executeViewOperation(() -> view.showErrorView());
                    } else {
                        setupRequests(response.body());
                    }
                }

                @Override
                public void onFailure(Call<List<RequestResponse>> call, Throwable throwable) {
                    executeViewOperation(() -> view.showErrorView());
                }
            });
        } else {
            executeViewOperation(() -> view.showOfflineView());
        }
    }

    private void setupRequests(List<RequestResponse> postedRequestResponses) {
        if (postedRequestResponses != null && !postedRequestResponses.isEmpty()) {
            executeViewOperation(() -> view.showRequests(postedRequestResponses));
        } else {
            executeViewOperation(() -> view.showErrorView());
        }
    }

}
