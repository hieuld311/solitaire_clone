package vn.edu.fpt.solitaire.ui.about;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import vn.edu.fpt.solitaire.R;
import vn.edu.fpt.solitaire.classes.CustomAppCompatActivity;

/**
 * This is created with help of this article: http://simpledeveloper.com/how-to-create-android-swipe-views-tabs/
 * The About activity contains 3 tabs. The content of the tabs is in the fragments
 */

public class AboutActivity extends CustomAppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_about);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        TabLayout tabs = findViewById(R.id.tabs);
        ViewPager2 pager = findViewById(R.id.pager);
        TabsPagerAdapter adapter = new TabsPagerAdapter(this);

        pager.setAdapter(adapter);

        // Connect TabLayout with ViewPager2
        new TabLayoutMediator(tabs, pager, (tab, position) -> {
            // Set tab titles based on position
            tab.setText(adapter.getPageTitle(position));
        }).attach();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //only menu item is the back button in the action bar so finish this activity
        finish();
        return true;
    }
}
