package net.melonbun.melonbun.request;

import net.melonbun.melonbun.common.BaseView;

import java.util.List;

public interface PostedRequestsView extends BaseView {

    void showRequests(List<Request> requests);

}
