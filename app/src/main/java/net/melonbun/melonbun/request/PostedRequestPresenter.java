package net.melonbun.melonbun.request;

import net.melonbun.melonbun.common.BasePresenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

//TODO: RxAndroid for API calls https://github.com/ReactiveX/RxAndroid

public class PostedRequestPresenter extends BasePresenter<PostedRequestsView> {

    public void decorateView() {
        List<Request> mockPostedRequests = populateMockRequests(20);
        setupRequests(mockPostedRequests);
    }

    public void setupRequests(List<Request> postedRequests) {
        if (postedRequests != null && !postedRequests.isEmpty()) {
            executeViewOperation(() -> view.showRequests(postedRequests));
        }
    }

    //Mock purposes only, delete later
    private List<Request> populateMockRequests(int size) {
        List<Request> postedRequests = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            String randomRequestTitle = UUID.randomUUID().toString();
            String requestDate = new SimpleDateFormat("yyyy/MM/dd", new Locale("en", "CA")).format(new Date());
            postedRequests.add(new Request(randomRequestTitle, requestDate));
        }

        return postedRequests;
    }

}
