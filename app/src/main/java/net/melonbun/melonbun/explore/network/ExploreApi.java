package net.melonbun.melonbun.explore.network;

import net.melonbun.melonbun.common.model.RequestResponse;
import net.melonbun.melonbun.common.network.ApiBuilder;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ExploreApi {
    class Builder {
        private ApiBuilder apiBuilder;

        public Builder(ApiBuilder apiBuilder) {
            this.apiBuilder = apiBuilder;
        }

        public ExploreApi build() {
            return apiBuilder.useGsonSerializer().useRxJava2().build(ExploreApi.class);
        }
    }

    @GET("requests")
    Single<List<RequestResponse>> getRequests();

    @GET("requests/{requestId}")
    Single<RequestResponse> getRequest(@Path("requestId") String requestId);
}
