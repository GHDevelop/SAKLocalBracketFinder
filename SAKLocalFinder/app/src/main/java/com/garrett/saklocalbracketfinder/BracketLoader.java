/**************************************************************************************************
 BracketLoader is responsible for loading the url associated with the tournament and updating the
                            image associated with it in the UI.
 **************************************************************************************************/
package com.garrett.saklocalbracketfinder;

import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Garrett on 2/20/2019.
 */

/**
 * Stores the event details, used in place of a struct, which I believe Java does not have.
 */
class Event {
    public String eventName;
    public int eventLogoID;
    public String eventURL;

    public Event(String eventName, int eventLogoID, String eventURL){
        this.eventName = eventName;
        this.eventLogoID = eventLogoID;
        this.eventURL = eventURL;
    }
}

public class BracketLoader {
    Event[] events;

    private ImageButton eventImage;
    private int currentEvent = 0;

    /**
     * Essentially this initializes the events list. The event names in strings.xml are the display
     * names, though I don't believe that the names stored here actually serve a functional purpose
     */
    public void InitializeEventsList(){
        Event[] newEvents = {
                new Event("Sink or Swim", R.drawable.soslogo, "https://smash.gg/tournament/sos"),
                new Event("The Smash Lab", R.drawable.tslogo, "https://challonge.com/TSL>>>Ult")
        };
        events = newEvents;
    }

    /**
     * Gets the url for the selected event and then loads it using startActivity.
     *
     * @param eventNumber
     * @param view
     */
    public void LoadURL (int eventNumber, View view){
        String url = GetURLWithEventNumberInserted(eventNumber);

        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        try
        {
            view.getContext().startActivity(intent);
        }
        catch (Exception ex){
            Log.e("error", ex.getMessage());
        }
    }

    /**
     * Uses regex to split url at special character (in this case ">") and then restore the url with
     * the event number inserted. Used because Smash Lab urls are numbered. Won't change url for
     * Sink or Swim.
     *
     * @param eventNumber
     * @return
     */
    private String GetURLWithEventNumberInserted(int eventNumber) {
        //Splits URL at special character using regex
        Matcher urlMatches = Pattern.compile("[^> ]+").matcher(events[currentEvent].eventURL);

        //Puts url chunks in list
        List<String> urlChunks = new ArrayList<String>();
        while (urlMatches.find())
        {
            urlChunks.add(urlMatches.group());
        }

        //Pieces url back together with event number and returns it
        String url = urlChunks.get(0);
        for (int index = 1; index < urlChunks.size(); index++){
            url += eventNumber + "" + urlChunks.get(index);
        }
        return url;
    }

    /***********************************************************************************************
                                        Getters and Setters
     **********************************************************************************************/
    public ImageButton getEventImage() {
        return eventImage;
    }

    public void setEventImage(View eventImage) {
        this.eventImage = (ImageButton) eventImage;
    }

    public int getCurrentEvent() {
        return currentEvent;
    }

    /**
     * Special setter. In addition to setting the variable, it changes the image in the GUI
     * to match the variable based on the index in the events array.
     *
     * @param currentEvent
     */
    public void setCurrentEvent(int currentEvent) {
        this.currentEvent = currentEvent;
        eventImage.setImageResource(events[currentEvent].eventLogoID);
    }
}
