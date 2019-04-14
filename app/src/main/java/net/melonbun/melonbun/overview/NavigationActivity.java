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
import net.melonbun.melonbun.common.BaseFragment;
import net.melonbun.melonbun.explore.ExploreFragment;
import net.melonbun.melonbun.post.PostRequestFragment;
import net.melonbun.melonbun.profile.ProfileFragment;

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

        // setupFragments only if it's fresh start, no savedInstanceState from a configuration change
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
    }

    @Override
    public void navigateToPost() {
        showCurrentFragment(PostRequestFragment.class);
    }

    @Override
    public void navigateToProfile() {
        showCurrentFragment(ProfileFragment.class);
    }

    @Override
    public void onBackPressed() {
        int selectedItemId = bottomNavigationView.getSelectedItemId();

        //TODO: We might have to change this logic once we have child fragments adding to the backstack, such as adding of getFragmentManager().getBackStackEntryCount() == 0 condition
        if (R.id.action_explore != selectedItemId) {
            bottomNavigationView.setSelectedItemId(R.id.action_explore);
        } else {
            super.onBackPressed();
        }
    }

    private <T extends Fragment> void replaceFragment(Class<T> fragmentClass) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        BaseFragment fragment = (BaseFragment) instantiateFragment(fragmentClass);

        fragmentTransaction.
                replace(R.id.content_frame, fragment, fragment.getClass().getCanonicalName())
                .commit();
    }

    //TODO: Implement a way you can modify the backstack order, to achieve similar to what youtube android has, currently it's not supported by fragmentManager
    //TODO: If it's added to the backstack, click on the bottom nav item move the current one to the top of the stack
    private <T extends Fragment> void showCurrentFragment(Class<T> fragmentClass) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        BaseFragment fragment = (BaseFragment) fragmentManager.findFragmentByTag(fragmentClass.getCanonicalName());

        if (fragment != null && !fragment.isVisible()) {
            fragmentTransaction.show(fragment);
        } else if (fragment == null) {
            fragment = (BaseFragment) instantiateFragment(fragmentClass);
            fragmentTransaction.add(R.id.content_frame, fragment, fragment.getClass().getCanonicalName());
        } else if (fragment.isVisible()) {
            // handling the case where the user click on the same navigation item as the one that's currently displayed
            fragment.handleOnBottomNavSameItemSelected();
            return;
        }

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
