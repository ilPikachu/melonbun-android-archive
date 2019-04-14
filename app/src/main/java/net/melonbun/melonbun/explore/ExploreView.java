package net.melonbun.melonbun.explore;

import net.melonbun.melonbun.common.BaseView;
import net.melonbun.melonbun.common.model.RequestResponse;

import java.util.List;

public interface ExploreView extends BaseView {

    void showProgressBar();

    void hideProgressBar();

    void showRequests(List<RequestResponse> requestResponses);

    void hideRequests();

    void showErrorView();

    void hideErrorView();

    void showOfflineView();

    void hideOfflineView();

    void scrollToTop();
}
