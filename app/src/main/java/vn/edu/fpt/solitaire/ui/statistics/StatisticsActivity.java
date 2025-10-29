
package vn.edu.fpt.solitaire.ui.statistics;

import android.os.Bundle;

import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import vn.edu.fpt.solitaire.R;
import vn.edu.fpt.solitaire.classes.CustomAppCompatActivity;
import vn.edu.fpt.solitaire.dialogs.DialogHighScoreDelete;

import static vn.edu.fpt.solitaire.SharedData.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.DialogFragment;
import androidx.viewpager2.widget.ViewPager2;

public class StatisticsActivity extends CustomAppCompatActivity {

    private HideWinPercentage callback;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_statistics);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        TabLayout tabs = findViewById(R.id.tabs);
        ViewPager2 pager = findViewById(R.id.pager);
        TabsPagerAdapter adapter = new TabsPagerAdapter(this);

        pager.setAdapter(adapter);

        // Connect TabLayout with ViewPager2
        new TabLayoutMediator(tabs, pager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(adapter.getPageTitle(position));
            }
        }).attach();
    }


    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_statistics, menu);
        menu.getItem(1).setChecked(prefs.getSavedStatisticsHideWinPercentage());

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_delete) {
            DialogFragment deleteDialog = new DialogHighScoreDelete();
            deleteDialog.show(getSupportFragmentManager(), "high_score_delete");
        } else if (item.getItemId() == R.id.item_hide) {
            boolean checked = !prefs.getSavedStatisticsHideWinPercentage();

            prefs.saveStatisticsHideWinPercentage(checked);
            item.setChecked(checked);
            callback.sendNewState(checked);
        } else if (item.getItemId() == android.R.id.home) {
            finish();
        }


        return true;
    }

    public void setCallback(HideWinPercentage callback) {
        this.callback = callback;
    }

    public interface HideWinPercentage {
        void sendNewState(boolean state);
    }

    /**
     * deletes the data, reloads the activity
     */
    public void deleteHighScores() {
        scores.deleteScores();
        gameLogic.deleteStatistics();
        currentGame.deleteAdditionalStatisticsData();
        showToast(getString(R.string.statistics_button_deleted_all_entries), this);

        finish();
        startActivity(getIntent());
    }
}
