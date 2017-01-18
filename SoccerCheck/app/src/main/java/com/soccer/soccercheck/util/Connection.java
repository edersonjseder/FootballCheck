package com.soccer.soccercheck.util;

import com.soccer.soccercheck.model.FixturesData;

/**
 * Created by root on 19/11/16.
 */

public class Connection {
    private final String TAG = "Connection";

    private FixturesData fixturesDataJson;

    public FixturesData getFixturesData() {
/**
        try {
            Log.i(TAG, "getFixturesData() inside method - inside try/catch bock");
            //Execute connection to get the JSON Object
            Call<FixturesData> call = FixturesDataService.Factory.create().getFixtures();

            Log.i(TAG, "getFixturesData() inside method - inside try/catch bock - call");
            call.enqueue(new Callback<FixturesData>() {
                @Override
                public void onResponse(Call<FixturesData> call, Response<FixturesData> response) {
                    Log.d("EDERSON", "SIZE = " + response.body());

                    fixturesDataJson = response.body();

                    Log.d(TAG, "inside call - response.body() = " + fixturesDataJson.getFixtures().size());
                    Log.d(TAG, "inside call - response.body() = " + fixturesDataJson.getCount());
                    Log.d(TAG, "inside call - response.body() = " + fixturesDataJson.getTimeFrameStart());
                    Log.d(TAG, "inside call - fixturesDataJson = " + fixturesDataJson.getFixtures().size());

/**

                     if (fixturesDataJson.getFixtures() != null) {
                     textViewFixturesEmpty.setVisibility(View.INVISIBLE);

                     } else {
                     textViewFixturesEmpty.setVisibility(View.VISIBLE);
                     }
**/
 /**               }

                @Override
                public void onFailure(Call<FixturesData> call, Throwable t) {
                    Log.i(TAG, "getFixturesData() inside method - inside onFailure");
                    Log.i(TAG, "getFixturesData() inside method - inside onFailure " + t.getMessage());
                }

            });

            Log.i(TAG, "getFixturesData() out of onResponse() method - inside try/catch bock");

        } catch (Exception e) {
            e.printStackTrace();
            fixturesDataJson = null;

        } **/

        return fixturesDataJson;
    }
}
