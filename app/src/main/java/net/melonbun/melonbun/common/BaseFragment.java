package net.melonbun.melonbun.common;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView {

    protected T presenter;

    /**
     * Abstract class implemented by every presenter such that presenter DI and binding/unbinding
     * can be done in here
     *
     * @return the class type of the presenter T
     */
    abstract protected Class<T> presenterClassInjection();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Presenter injection
    }

    /*
    // Re enable after Presenter injection logic is done
    @Override
    public void onStart() {
        super.onStart();
        presenter.bindView(this);
        presenter.updateState();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.unbindView();
    }
    */
}
