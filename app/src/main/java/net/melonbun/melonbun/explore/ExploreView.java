package net.melonbun.melonbun.explore;

import net.melonbun.melonbun.common.BaseView;
import net.melonbun.melonbun.common.model.Request;

import java.util.List;

public interface ExploreView extends BaseView {

    void showRequests(List<Request> requests);

}
