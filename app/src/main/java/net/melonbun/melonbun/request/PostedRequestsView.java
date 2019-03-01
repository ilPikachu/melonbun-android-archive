package net.melonbun.melonbun.request;

import net.melonbun.melonbun.common.BaseView;
import net.melonbun.melonbun.request.model.Request;

import java.util.List;

public interface PostedRequestsView extends BaseView {

    void showRequests(List<Request> requests);

}
