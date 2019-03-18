package net.melonbun.melonbun.overview;

import android.content.Context;

import net.melonbun.melonbun.R;
import net.melonbun.melonbun.request.FavouriteRequestFragment;
import net.melonbun.melonbun.explorer.ExplorerFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class RequestViewPagerAdapter extends FragmentStatePagerAdapter {
    private static final int NUM_ITEMS = 2;

    private Context context;

    public RequestViewPagerAdapter(FragmentManager fragmentManager, Context context) {
        super(fragmentManager);
        this.context = context;
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ExplorerFragment.newInstance(0);
            case 1:
                return FavouriteRequestFragment.newInstance(1);
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return context.getString(R.string.posted_request_tab_title);
            case 1:
                return context.getString(R.string.favourite_request_tab_title);
            default:
                return null;
        }
    }
}
