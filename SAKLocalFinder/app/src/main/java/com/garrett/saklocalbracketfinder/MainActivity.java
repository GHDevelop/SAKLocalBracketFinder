/***************************************************************************************************
The activity script (MainActivity) is responsible for maintaining the input fields, with the
 exception of the ImageButton being maintained by BracketLoader, and for setting up the Spinner
 Events. Additionally LoadBracketURL, which is called by the ImageButton, is located here.
 Requires both SpinnerActivityEvents and BracketLoader in order to function.
 **************************************************************************************************/
package com.garrett.saklocalbracketfinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    EditText eventNumber;

    SpinnerActivityEvents spinnerActivityEvents = new SpinnerActivityEvents();
    BracketLoader bracketLoader = new BracketLoader();


    /**
     * Sets up the activity input, spinner event listener, and bracket loader
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SetupSpinner();
        SetupEventNumber();
        SetupBracketLoader();
        SetupSpinnerActivityEvents();
    }

    /**
     * Called by the ImageButton in the activity UI. Loads the url for the selected event.
     *
     * @param view
     */
    public void LoadBracketURL(View view){
        if (eventNumber.getText().toString().equals(""))
        {
            bracketLoader.LoadURL(0, view);
        }
        else
        {
            Integer number = Integer.parseInt(eventNumber.getText() + "");
            bracketLoader.LoadURL(number, view);
        }
    }

    /**
     * Gives the Spinner Activity Events Listener the ability to call methods from the Bracket Loader
     */
    private void SetupSpinnerActivityEvents() {
        spinnerActivityEvents.setBracketLoader(bracketLoader);
    }

    /**
     * Initializes the Bracket Loader's event list and gives it access to the image button.
     */
    private void SetupBracketLoader() {
        bracketLoader.InitializeEventsList();
        bracketLoader.setEventImage(findViewById(R.id.imageButton));
    }

    /**
     * Gets the event number text box for use in LoadURL
     */
    private void SetupEventNumber() {
        eventNumber = (EditText) findViewById(R.id.eventNumber);
    }

    /**
     * Configures the spinner's items list for selecting and connects the Spinner event listener to
     * the spinner itself
     */
    private void SetupSpinner() {
        AddEntriesToSpinner();
        spinner.setOnItemSelectedListener(spinnerActivityEvents);
    }

    /**
     * Populates the spinner's item list for selection. Used by SetupSpinner
     */
    private void AddEntriesToSpinner() {
        spinner = (Spinner) findViewById(R.id.EventSpinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.tournaments_array, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}
