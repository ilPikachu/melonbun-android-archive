package net.melonbun.melonbun.overview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import net.melonbun.melonbun.R;

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
    }

    private void setUpBottomNavigation(){
        bottomNavigationView.setOnNavigationItemSelectedListener(
                item -> {
                    switch(item.getItemId()) {
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
        Toast.makeText(NavigationActivity.this, "Action clicked explore", Toast.LENGTH_LONG).show();
    }

    @Override
    public void navigateToPost() {
        Toast.makeText(NavigationActivity.this, "Action clicked post", Toast.LENGTH_LONG).show();
    }

    @Override
    public void navigateToProfile() {
        Toast.makeText(NavigationActivity.this, "Action clicked profile", Toast.LENGTH_LONG).show();
    }


}
