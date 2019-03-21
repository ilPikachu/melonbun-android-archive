package net.melonbun.melonbun.explorer;

import net.melonbun.melonbun.common.BasePresenter;
import net.melonbun.melonbun.common.model.Price;
import net.melonbun.melonbun.common.model.Request;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

//TODO: RxAndroid for API calls https://github.com/ReactiveX/RxAndroid

public class ExplorePresenter extends BasePresenter<ExploreView> {

    public void decorateView() {
        List<Request> mockPostedRequests = populateMockRequests(20);
        setupRequests(mockPostedRequests);
    }

    public void setupRequests(List<Request> postedRequests) {
        if (postedRequests != null && !postedRequests.isEmpty()) {
            executeViewOperation(() -> view.showRequests(postedRequests));
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
            Price price = new Price(100, "CAD");
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
