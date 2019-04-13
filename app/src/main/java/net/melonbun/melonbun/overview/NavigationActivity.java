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

        if (savedInstanceState == null) {
            setupFragments();
        }
    }

    private void setupFragments() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment exploreFragment = ExploreFragment.newInstance();

        fragmentTransaction.
                add(R.id.content_frame, exploreFragment, exploreFragment.getClass().getCanonicalName())
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
        showCurrentFragment(ExploreFragment.class);
        Toast.makeText(NavigationActivity.this, "Action clicked explore", Toast.LENGTH_LONG).show();
    }

    @Override
    public void navigateToPost() {
        showCurrentFragment(PostRequestFragment.class);
        Toast.makeText(NavigationActivity.this, "Action clicked post", Toast.LENGTH_LONG).show();
    }

    @Override
    public void navigateToProfile() {
        Toast.makeText(NavigationActivity.this, "Action clicked profile", Toast.LENGTH_LONG).show();
    }

    private <T extends Fragment> void replaceFragment(Class<T> fragmentClass) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = instantiateFragment(fragmentClass);

        fragmentTransaction.
                replace(R.id.content_frame, fragment, fragment.getClass().getCanonicalName())
                .commit();
    }

    private <T extends Fragment> void showCurrentFragment(Class<T> fragmentClass) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = fragmentManager.findFragmentByTag(fragmentClass.getCanonicalName());

        // if fragment is not null, and not visible show fragment
        if (fragment != null && !fragment.isVisible()) {
            fragmentTransaction.show(fragment);
        } else if (fragment == null) {
            fragment = instantiateFragment(fragmentClass);
            fragmentTransaction.add(R.id.content_frame, fragment, fragment.getClass().getCanonicalName());
        } else if (fragment.isVisible()) {
            //no-op
            //TODO: Fragment specific actions: for example Explore page would be move to the top callback.
            return;
        }

        // if we want to show just one fragment we have to hide all others, show and add will show the fragment, and we hide away all others
        for (Fragment currentFragment : fragmentManager.getFragments()) {
            if (currentFragment != fragment) {
                fragmentTransaction.hide(currentFragment);
            }
        }
        fragmentTransaction.commit();
    }

    private <T extends Fragment> Fragment instantiateFragment(Class<T> fragmentClass) {
        Fragment fragment;

        try {
            fragment = fragmentClass.newInstance();
        } catch (IllegalAccessException | InstantiationException error) {
            throw new IllegalArgumentException("Illegal fragment newInstance argument");
        }

        return fragment;
    }
}
