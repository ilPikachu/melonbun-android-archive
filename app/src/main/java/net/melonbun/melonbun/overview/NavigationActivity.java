package net.melonbun.melonbun.overview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import net.melonbun.melonbun.R;
import net.melonbun.melonbun.common.BaseFragment;
import net.melonbun.melonbun.explore.ExploreFragment;
import net.melonbun.melonbun.post.PostRequestFragment;
import net.melonbun.melonbun.profile.ProfileFragment;

public class NavigationActivity extends AppCompatActivity implements NavigationView {

    //TODO: SnackBar for deleting/redo requests https://developer.android.com/training/snackbar/showing
    private NavHostFragment navHostFragment;

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        ButterKnife.bind(this);

        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();
        setUpBottomNavigation(navController);
    }

    private void setUpBottomNavigation(NavController navController) {
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }

    //TODO: click explore nav item to scroll to the top
    //TODO: retain state of nav fragments instead of recreating every time when nav item is clicked.
}
