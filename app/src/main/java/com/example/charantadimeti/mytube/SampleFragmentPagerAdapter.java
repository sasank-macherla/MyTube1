package com.example.charantadimeti.mytube;

//import android.app.FragmentManager;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
/**
 * Created by sasankmacherla on 10/18/15.
 */
public class SampleFragmentPagerAdapter extends FragmentPagerAdapter{
    final int Tabs = 2;
    private String title[] = new String[] {"Search", "Favorites"};
    private Context context;

    public SampleFragmentPagerAdapter(FragmentManager fragmentManager, Context context){
        super(fragmentManager);
        this.context = context;

    }

    @Override
    public int getCount() {
        return Tabs;
    }

    @Override
    public Fragment getItem(int pos) {
        if (pos == 1) {
            return FavoriteFragment.newInstance(pos);
        } else {
            return SearchFragment.newInstance(pos);
        }
    }

    @Override
    public CharSequence getPageTitle(int pos) {
        return title[pos];
    }


}
