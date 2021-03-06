package com.example.android.sunshine;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class Forecast extends Fragment implements MainActivityFragment {

    private ArrayAdapter<String> adapter;//=new ArrayAdapter<String>(getActivity(),R.layout.list_item_forecast,R.id.list_item_forecast_textview);

    public Forecast() {
    }

    @Override
    public void OnCreate(Bundle savedInstanceState) {
        hasOptionsMenu();
    }

    @Override
    public void OnCreateOptionsMenu(Menu menu,MenuInflater inflater){
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.action_refresh) {
            FetchWeatherTask fetchWeatherTask=new FetchWeatherTask();
            fetchWeatherTask.execute("94343");
            return true;
        }
        return super.onOptionsItemSelected(item);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        String[] forecast={"Today-Sunny 88/63 C","Tomorrow-Sunny 89/64 C","Monday-Cloudy 75/50 C"};
        List<String> arrayList=new ArrayList<String>(Arrays.asList(forecast));
        adapter=new ArrayAdapter<String>(getActivity(),R.layout.list_item_forecast,R.id.list_item_forecast_textview,arrayList);
        ListView listView=(ListView)rootView.findViewById(R.id.list_view_forecast);
        listView.setAdapter(adapter);
        /*arrayList.add("Today-Sunny 88/63 C");
        arrayList.add("Today-Sunny 88/63 C");
        arrayList.add("Today-Sunny 88/63 C");
        arrayList.add("Today-Sunny 88/63 C");
        arrayList.add("Today-Sunny 88/63 C");*/
       // return inflater.inflate(R.layout.fragment_main, container, false);


        return rootView;
    }
    public class FetchWeatherTask extends AsyncTask<String,Void,String []> {




        /* The date/time conversion code is going to be moved outside the asynctask later,
  * so for convenience we're breaking it out into its own method now.
  */
        public String mainStr[];

        private String getReadableDateString(long time){
            // Because the API returns a unix timestamp (measured in seconds),
            // it must be converted to milliseconds in order to be converted to valid date.
            SimpleDateFormat shortenedDateFormat = new SimpleDateFormat("EEE MMM dd");
            return shortenedDateFormat.format(time);
        }

        /**
         * Prepare the weather high/lows for presentation.
         */
        private String formatHighLows(double high, double low) {
            // For presentation, assume the user doesn't care about tenths of a degree.
            long roundedHigh = Math.round(high);
            long roundedLow = Math.round(low);

            String highLowStr = roundedHigh + "/" + roundedLow;
            return highLowStr;
        }

        /**
         * Take the String representing the complete forecast in JSON Format and
         * pull out the data we need to construct the Strings needed for the wireframes.
         *
         * Fortunately parsing is easy:  constructor takes the JSON string and converts it
         * into an Object hierarchy for us.
         */
        private String[] getWeatherDataFromJson(String forecastJsonStr, int numDays)
                throws JSONException {

            // These are the names of the JSON objects that need to be extracted.
            final String OWM_LIST = "list";
            final String OWM_WEATHER = "weather";
            final String OWM_TEMPERATURE = "temp";
            final String OWM_MAX = "max";
            final String OWM_MIN = "min";
            final String OWM_DESCRIPTION = "main";

            JSONObject forecastJson = new JSONObject(forecastJsonStr);
            JSONArray weatherArray = forecastJson.getJSONArray(OWM_LIST);

            // OWM returns daily forecasts based upon the local time of the city that is being
            // asked for, which means that we need to know the GMT offset to translate this data
            // properly.

            // Since this data is also sent in-order and the first day is always the
            // current day, we're going to take advantage of that to get a nice
            // normalized UTC date for all of our weather.

            Time dayTime = new Time();
            dayTime.setToNow();

            // we start at the day returned by local time. Otherwise this is a mess.
            int julianStartDay = Time.getJulianDay(System.currentTimeMillis(), dayTime.gmtoff);

            // now we work exclusively in UTC
            dayTime = new Time();

            String[] resultStrs = new String[numDays];
            for(int i = 0; i < weatherArray.length(); i++) {
                // For now, using the format "Day, description, hi/low"
                String day;
                String description;
                String highAndLow;

                // Get the JSON object representing the day
                JSONObject dayForecast = weatherArray.getJSONObject(i);

                // The date/time is returned as a long.  We need to convert that
                // into something human-readable, since most people won't read "1400356800" as
                // "this saturday".
                long dateTime;
                // Cheating to convert this to UTC time, which is what we want anyhow
                dateTime = dayTime.setJulianDay(julianStartDay+i);
                day = getReadableDateString(dateTime);

                // description is in a child array called "weather", which is 1 element long.
                JSONObject weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);
                description = weatherObject.getString(OWM_DESCRIPTION);

                // Temperatures are in a child object called "temp".  Try not to name variables
                // "temp" when working with temperature.  It confuses everybody.
                JSONObject temperatureObject = dayForecast.getJSONObject(OWM_TEMPERATURE);
                double high = temperatureObject.getDouble(OWM_MAX);
                double low = temperatureObject.getDouble(OWM_MIN);

                highAndLow = formatHighLows(high, low);
                resultStrs[i] = day + " - " + description + " - " + highAndLow;
            }

            for (String s : resultStrs) {
                Log.v("LOG_TAG", "Forecast entry: " + s);
            }
            String mainStr[]=new String[resultStrs.length];
            int count=0;
            for (String element : resultStrs)
                mainStr[count++]=element;

            return resultStrs;

        }


        @Override
        protected String[] doInBackground(String... params) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String jsonFormatter = "";
            String format="json";
            String units="metric";
            int numDays=7;

            try {
                //URL url = new URL("http://api.openweathermap.org/data/2.5/forecast?q=Kolkata,in&mode=json&units=metric&cnt=7&appid=2de143494c0b295cca9337e1e96b00e0");
                final String FORECAST_BASE_URL="http://api.openweathermap.org/data/2.5/forecast/daily?";
                final String queryParam="q";
                final String FORMAT_PARAM="mode";
                final String UNITS_PARAM="units";
                final String DAYS_PARAM="cnt";
                final String APPID_PARAM="appid";

                Uri builtUri = Uri.parse(FORECAST_BASE_URL).buildUpon().appendQueryParameter(queryParam,params[0]).appendQueryParameter(FORMAT_PARAM,format)
                        .appendQueryParameter(UNITS_PARAM,units).appendQueryParameter(DAYS_PARAM,Integer.toString(numDays))
                        .appendQueryParameter(APPID_PARAM,BuildConfig.OPEN_WEATHER_MAP_API_KEY).build();

                URL url=new URL(builtUri.toString());

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                if (inputStream == null) {
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer buffer = new StringBuffer();
                String line;
                if ((line = reader.readLine().toString()) != null) {
                    buffer.append(line + "\n");
                }
                if (buffer.length() == 0) {
                    return null;
                }
                jsonFormatter = buffer.toString();
                Log.e("Files : ",jsonFormatter);


            } catch (IOException e) {
                Log.e("Placeholder Fragment", String.valueOf(e));
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        Log.e("Placeholder", e.toString());
                    }
                }
            }
            try {
                return getWeatherDataFromJson(jsonFormatter, numDays);
            }catch (JSONException e) {
                Log.v("JSON ",e.toString());
                e.printStackTrace();
            }

            return null;
        }

        public void tempFunc() {

            if(mainStr != null) {
                adapter.clear();
                for(String dayForecastStr : mainStr) {
                    adapter.add(dayForecastStr);
                }
            }
        }

        @Override
        protected void onPostExecute(String[] strings) throws NullPointerException{
            //super.onPostExecute(mainStr);
            tempFunc();
        }
    }



}
