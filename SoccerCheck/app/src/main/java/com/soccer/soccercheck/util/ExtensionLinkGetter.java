package com.soccer.soccercheck.util;

import android.util.Log;

import com.soccer.soccercheck.model.StandingChampionsLeague;

/**
 * Created by ederson.js on 19/01/2017.
 */

public class ExtensionLinkGetter {
    private static final String TAG = "ExtensionLinkGetter";

    public static String getExtensionFromLink(String url) {
        Log.i(TAG, "getExtensionFromLink() inside method");

        String format = "";
        String[] link = null;

        if (url != null){

            link = url.split("\\.");

            for (int i = 0; i < link.length; i++){
                Log.i(TAG, "inside for: " + link[i]);

                if ((link[i].equals("gif")) || (link[i].equals("png")) || (link[i].equals("jpg"))) {
                    format = link[i];
                }

            }

        }

        return format;
    }

}
