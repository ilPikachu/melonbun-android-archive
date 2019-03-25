package net.melonbun.melonbun.post;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.melonbun.melonbun.R;
import net.melonbun.melonbun.common.BaseFragment;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PostRequestFragment extends BaseFragment<PostRequestPresenter> implements PostRequestView {

    private Unbinder unbinder;

    public static PostRequestFragment newInstance() {
        PostRequestFragment postRequestFragment = new PostRequestFragment();
        return postRequestFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.fragment_post_request, container, false);
        unbinder = ButterKnife.bind(this, inflatedView);
        return inflatedView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public Class<PostRequestPresenter> supplyPresenterClass() {
        return PostRequestPresenter.class;
    }
}
