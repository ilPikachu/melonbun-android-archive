package net.melonbun.melonbun.common;

import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {

    private EventListener bottomNavEventListener;

    @FunctionalInterface
    public interface EventListener {
        void onEvent();
    }

    protected void setBottomNavEventListener(EventListener eventListener) {
        bottomNavEventListener = eventListener;
    }

    public void handleOnBottomNavSameItemSelected() {
        if (bottomNavEventListener != null) {
            bottomNavEventListener.onEvent();
        }
    }
}
