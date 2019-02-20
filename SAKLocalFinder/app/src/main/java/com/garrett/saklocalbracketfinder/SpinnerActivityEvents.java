/***************************************************************************************************
Events that run when a spinner item is selected. Currently just sets the current event when the
            selection changes. Depends on bracketLoader being present to function.
 **************************************************************************************************/
package com.garrett.saklocalbracketfinder;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;

/**
 * Created by Garrett on 2/19/2019.
 */

public class SpinnerActivityEvents extends Activity implements AdapterView.OnItemSelectedListener {
    private BracketLoader bracketLoader;

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        bracketLoader.setCurrentEvent(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        //Do nothing
    }

    /***********************************************************************************************
                                            Getters and Setters
     **********************************************************************************************/
    public BracketLoader getBracketLoader() {
        return bracketLoader;
    }

    public void setBracketLoader(BracketLoader bracketLoader) {
        this.bracketLoader = bracketLoader;
    }
}
