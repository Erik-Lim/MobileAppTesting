package com.example.getjson;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class getData extends AsyncTask<Void, Void, Void> {

    String data;
    String dataParsed = "";
    String singleParsed = "";

    @Override
    protected Void doInBackground(Void... voids) {

        try {
            URL url = new URL("https://trackdatcash.herokuapp.com/expenses/");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            InputStream inputStream = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            String line = "";
            while(line != null)
            {
                line = br.readLine();
                data = data + line;
            }

            JSONArray ja = new JSONArray(data);
            for(int i = 0; i < ja.length(); i++)
            {
                JSONObject jo = (JSONObject) ja.get(i);

                singleParsed = "Description: " + jo.getString("description") + "\n" +
                               "Amount: " + jo.getDouble("amount") + "\n" +
                               "Month: " + jo.getString("month") + "\n" +
                               "Day: " + jo.getInt("day") + "\n" +
                               "Year: " + jo.getInt("year") + "\n";

                dataParsed = dataParsed + singleParsed + "\n";
            }
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        MainActivity.data.setText(this.dataParsed);
    }
}
