package net.melonbun.melonbun.explore.network;

import net.melonbun.melonbun.common.model.RequestResponse;

import java.util.List;

import retrofit2.Call;

public class ExploreService {

    private final ExploreApi exploreApi;

    public ExploreService(ExploreApi exploreApi) {
        this.exploreApi = exploreApi;
    }

    public Call<RequestResponse> getRequest(String requestId) {
        return exploreApi.getRequest(requestId);
    }

    public Call<List<RequestResponse>> getRequests() {
        return exploreApi.getRequests();
    }
}
