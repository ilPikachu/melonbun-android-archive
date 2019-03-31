package net.melonbun.melonbun.explore;

import net.melonbun.melonbun.common.BasePresenter;
import net.melonbun.melonbun.common.model.Price;
import net.melonbun.melonbun.common.model.Request;
import net.melonbun.melonbun.common.network.ConnectivityCheck;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import javax.inject.Inject;

//TODO: RxAndroid for API calls https://github.com/ReactiveX/RxAndroid

/**
 * This is the presenter for {@link ExploreFragment}
 */
public class ExplorePresenter extends BasePresenter<ExploreView> {

    private final ConnectivityCheck connectivityCheck;

    @Inject
    public ExplorePresenter(ConnectivityCheck connectivityCheck) {
        this.connectivityCheck = connectivityCheck;
    }

    public void decorateView() {
        List<Request> mockPostedRequests = populateMockRequests(20);
        setupRequests(mockPostedRequests);
    }

    public void setupRequests(List<Request> postedRequests) {
        if (postedRequests != null && !postedRequests.isEmpty() && connectivityCheck.isConnected()) {
            executeViewOperation(() -> view.showRequests(postedRequests));
        } else {
            executeViewOperation(() -> view.showOfflineView());
        }
    }

    //TODO: Mock purposes only, delete later, reuse it in presenter tests, replace with builder pattern after models are constructed
    private List<Request> populateMockRequests(int size) {
        List<Request> postedRequests = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            String id = UUID.randomUUID().toString();
            String title = UUID.randomUUID().toString();
            String body = UUID.randomUUID().toString();
            String date = new SimpleDateFormat("yyyy/MM/dd", new Locale("en", "CA")).format(new Date());
            String status = getAStatus();
            Price price = new Price(100.0, "CAD");
            List<String> tags = new ArrayList<>();
            tags.add("tag1");
            tags.add("tag2");
            tags.add("tag3");

            postedRequests.add(new Request(id, title, body, date, status, price, tags));
        }

        return postedRequests;
    }

    private String getAStatus() {
        int randomNumber = ThreadLocalRandom.current().nextInt(4);

        switch (randomNumber) {
            case 0:
                return Status.COMPLETE.name();
            case 1:
                return Status.FULFILLED.name();
            case 2:
                return Status.INCOMPLETE.name();
            case 3:
                return Status.INCOMPLETE.name();
            default:
                return "";
        }
    }

    public enum Status {
        PENDING,
        FULFILLED,
        COMPLETE,
        INCOMPLETE
    }

}
