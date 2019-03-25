package net.melonbun.melonbun.overview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import net.melonbun.melonbun.R;
import net.melonbun.melonbun.explore.ExploreFragment;
import net.melonbun.melonbun.post.PostRequestFragment;

public class NavigationActivity extends AppCompatActivity implements NavigationView {

    //TODO: SnackBar for deleting/redo requests https://developer.android.com/training/snackbar/showing
    @BindView(R.id.content_frame)
    FrameLayout contentFrame;
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        ButterKnife.bind(this);
        setUpBottomNavigation();
        setupFragments();
    }

    private void setupFragments() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment exploreFragment = ExploreFragment.newInstance();

        fragmentTransaction.
                add(R.id.content_frame, exploreFragment)
                .commit();
    }

    private void setUpBottomNavigation() {
        bottomNavigationView.setOnNavigationItemSelectedListener(
                item -> {
                    switch (item.getItemId()) {
                        case R.id.action_explore:
                            navigateToExplore();
                            break;
                        case R.id.action_post:
                            navigateToPost();
                            break;
                        case R.id.action_profile:
                            navigateToProfile();
                            break;
                        default:
                            navigateToExplore();
                    }
                    return true;
                });
    }

    @Override
    public void navigateToExplore() {
        replaceFragment(ExploreFragment.class);
        Toast.makeText(NavigationActivity.this, "Action clicked explore", Toast.LENGTH_LONG).show();
    }

    @Override
    public void navigateToPost() {
        replaceFragment(PostRequestFragment.class);
        Toast.makeText(NavigationActivity.this, "Action clicked post", Toast.LENGTH_LONG).show();
    }

    @Override
    public void navigateToProfile() {
        Toast.makeText(NavigationActivity.this, "Action clicked profile", Toast.LENGTH_LONG).show();
    }

    private <T extends Fragment> void replaceFragment(Class<T> fragmentClass) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment;

        try {
            fragment = fragmentClass.newInstance();
        } catch (IllegalAccessException | InstantiationException error) {
            throw new IllegalArgumentException("Illegal fragment newInstance argument");
        }

        fragmentTransaction.
                replace(R.id.content_frame, fragment)
                .commit();
    }
}
