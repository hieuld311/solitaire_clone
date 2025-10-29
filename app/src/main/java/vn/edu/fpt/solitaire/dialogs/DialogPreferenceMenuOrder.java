
package vn.edu.fpt.solitaire.dialogs;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import vn.edu.fpt.solitaire.R;
import vn.edu.fpt.solitaire.classes.CustomDialogPreference;
import vn.edu.fpt.solitaire.classes.DynamicListView;
import vn.edu.fpt.solitaire.classes.StableArrayAdapter;

import static vn.edu.fpt.solitaire.SharedData.*;

/**
 * dialog for changing the rows shown in the menu. It uses different values for portrait and landscape
 */

public class DialogPreferenceMenuOrder extends CustomDialogPreference {

    private ArrayList<String> gameList;
    StableArrayAdapter adapter;

    public DialogPreferenceMenuOrder(Context context, AttributeSet attrs) {
        super(context, attrs);
        setDialogLayoutResource(R.layout.dialog_settings_menu_order);
        setDialogIcon(null);
    }

    @Override
    protected void onBindDialogView(View view) {
        gameList = new ArrayList<>();
        ArrayList<String> sortedGameList = lg.getOrderedGameNameList(getContext().getResources());

        gameList.addAll(sortedGameList);

        adapter = new StableArrayAdapter(getContext(), R.layout.text_view, gameList);
        DynamicListView listView = view.findViewById(R.id.listview);

        listView.setList(gameList);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        super.onBindDialogView(view);
    }


    @Override
    protected void onDialogClosed(boolean positiveResult) {
        super.onDialogClosed(positiveResult);

        if (positiveResult) {
            ArrayList<Integer> list = new ArrayList<>();
            String[] defaultList = lg.getDefaultGameNameList(getContext().getResources());

            for (String game : defaultList) {
                list.add(gameList.indexOf(game));
            }

            prefs.saveMenuOrderList(list);
        }
    }
}
