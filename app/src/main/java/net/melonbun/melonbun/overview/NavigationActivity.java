package net.melonbun.melonbun.overview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import net.melonbun.melonbun.R;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class NavigationActivity extends AppCompatActivity implements NavigationView {

    private static final int POSTED_TAB_INDEX = 0;
    private static final int FAVOURITE_TAB_INDEX = 1;

    @BindView(R.id.request_toolbar) Toolbar requestToolbar;
    @BindView(R.id.request_viewpager) ViewPager requestViewPager;
    @BindView(R.id.request_tabs) TabLayout requestTabLayout;
    @BindView(R.id.request_fab) FloatingActionButton requestFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        ButterKnife.bind(this);

        setSupportActionBar(requestToolbar);
        setupViewPager();
        requestFab.setOnClickListener(view -> Toast.makeText(this, "Action clicked add request", Toast.LENGTH_LONG).show());
    }

    private void setupViewPager() {
        RequestViewPagerAdapter requestViewPagerAdapter = new RequestViewPagerAdapter(getSupportFragmentManager(), this);
        requestViewPager.setAdapter(requestViewPagerAdapter);
        requestViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case POSTED_TAB_INDEX:
                        showRequestFab();
                        break;
                    case FAVOURITE_TAB_INDEX:
                        hideRequestFab();
                        break;
                    default:
                        hideRequestFab();
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        requestTabLayout.setupWithViewPager(requestViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_request, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                Toast.makeText(this, "Action clicked search", Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_profile:
                Toast.makeText(this, "Action clicked profile", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showRequestFab() {
        requestFab.show();
    }

    @Override
    public void hideRequestFab() {
        requestFab.hide();
    }
}
