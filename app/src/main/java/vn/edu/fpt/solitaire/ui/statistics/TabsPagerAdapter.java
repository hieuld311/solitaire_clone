package vn.edu.fpt.solitaire.ui.statistics;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import vn.edu.fpt.solitaire.R;

/**
 * Adapter for the tabs
 */

public class TabsPagerAdapter extends FragmentStateAdapter {

    private final String[] TITLES;
    private final Context context;

    public TabsPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        this.context = fragmentActivity;
        TITLES = new String[]{
                context.getString(R.string.settings_other),
                context.getString(R.string.statistics_high_scores),
                context.getString(R.string.statistics_recent_scores)
        };
    }

    public CharSequence getPageTitle(int position) {
        if (position >= 0 && position < TITLES.length) {
            return TITLES[position];
        }
        return "";
    }

    @Override
    public int getItemCount() {
        return TITLES.length;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new StatisticsFragment();
        } else if (position == 1) {
            return new HighScoresFragment();
        } else if (position == 2) {
            return new RecentScoresFragment();
        }

        return new StatisticsFragment(); // Default fallback
    }
}
