package net.melonbun.melonbun.explore;

import android.annotation.SuppressLint;

import net.melonbun.melonbun.common.BasePresenter;
import net.melonbun.melonbun.common.model.RequestResponse;
import net.melonbun.melonbun.common.network.ConnectivityCheck;
import net.melonbun.melonbun.explore.network.ExploreService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * This is the presenter for {@link ExploreFragment}
 */
public class ExplorePresenter extends BasePresenter<ExploreView> {

    private final ConnectivityCheck connectivityCheck;
    private final ExploreService exploreService;
    private final Single<List<RequestResponse>> requestListObservable;

    @Inject
    public ExplorePresenter(ConnectivityCheck connectivityCheck, ExploreService exploreService) {
        this.connectivityCheck = connectivityCheck;
        this.exploreService = exploreService;
        this.requestListObservable = exploreService.getRequests().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @SuppressLint("CheckResult")
    void decorateView() {
        if (connectivityCheck.isConnected()) {
            setupProgressBar();
            requestListObservable.subscribeWith(new DisposableSingleObserver<List<RequestResponse>>() {
                @Override
                public void onSuccess(List<RequestResponse> requestResponses) {
                    setupRequests(requestResponses);
                }

                @Override
                public void onError(Throwable e) {
                    setupErrorView();
                }
            });
        } else {
            setupOfflineView();
        }
    }

    @SuppressLint("CheckResult")
    void refreshView() {
        if (connectivityCheck.isConnected()) {
            requestListObservable.subscribeWith(new DisposableSingleObserver<List<RequestResponse>>() {
                @Override
                public void onSuccess(List<RequestResponse> requestResponses) {
                    setupRefreshRequests(requestResponses);
                }

                @Override
                public void onError(Throwable e) {
                    executeViewOperation(() -> view.showRefreshErrorToast());
                }
            });
        } else {
            executeViewOperation(() -> view.showRefreshOfflineToast());
        }
    }

    private void setupRequests(List<RequestResponse> postedRequestResponses) {
        if (postedRequestResponses != null && !postedRequestResponses.isEmpty()) {
            executeViewOperation(() -> view.hideProgressBar());
            executeViewOperation(() -> view.hideErrorView());
            executeViewOperation(() -> view.hideOfflineView());
            executeViewOperation(() -> view.showRequests(postedRequestResponses));
        } else {
            setupErrorView();
        }
    }

    private void setupRefreshRequests(List<RequestResponse> postedRequestResponses) {
        if (postedRequestResponses != null && !postedRequestResponses.isEmpty()) {
            executeViewOperation(() -> view.updateRequests(postedRequestResponses));
        } else {
            executeViewOperation(() -> view.showRefreshErrorToast());
        }
    }

    private void setupErrorView() {
        executeViewOperation(() -> view.hideProgressBar());
        executeViewOperation(() -> view.hideRequests());
        executeViewOperation(() -> view.hideOfflineView());
        executeViewOperation(() -> view.showErrorView());
    }

    private void setupOfflineView() {
        executeViewOperation(() -> view.hideProgressBar());
        executeViewOperation(() -> view.hideRequests());
        executeViewOperation(() -> view.hideErrorView());
        executeViewOperation(() -> view.showOfflineView());
    }

    private void setupProgressBar() {
        executeViewOperation(() -> view.hideRequests());
        executeViewOperation(() -> view.hideErrorView());
        executeViewOperation(() -> view.hideOfflineView());
        executeViewOperation(() -> view.showProgressBar());
    }
}
