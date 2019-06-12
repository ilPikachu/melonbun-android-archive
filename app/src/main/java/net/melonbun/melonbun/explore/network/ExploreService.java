package net.melonbun.melonbun.explore.network;

import net.melonbun.melonbun.common.model.RequestResponse;

import java.util.List;

import io.reactivex.Single;

public class ExploreService {

    private final ExploreApi exploreApi;

    public ExploreService(ExploreApi exploreApi) {
        this.exploreApi = exploreApi;
    }

    public Single<RequestResponse> getRequest(String requestId) {
        return exploreApi.getRequest(requestId);
    }

    public Single<List<RequestResponse>> getRequests() {
        return exploreApi.getRequests();
    }
}
