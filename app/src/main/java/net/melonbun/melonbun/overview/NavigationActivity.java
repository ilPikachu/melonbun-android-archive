package net.melonbun.melonbun.overview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import net.melonbun.melonbun.R;

public class NavigationActivity extends AppCompatActivity implements NavigationView {

    @BindView(R.id.request_toolbar) Toolbar requestToolbar;
    @BindView(R.id.request_viewpager) ViewPager requestViewPager;
    @BindView(R.id.request_tabs) TabLayout requestTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        ButterKnife.bind(this);

        setSupportActionBar(requestToolbar);
        RequestViewPagerAdapter requestViewPagerAdapter = new RequestViewPagerAdapter(getSupportFragmentManager(), this);
        requestViewPager.setAdapter(requestViewPagerAdapter);
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
}
