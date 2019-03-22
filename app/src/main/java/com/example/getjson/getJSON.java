package com.example.getjson;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.cert.LDAPCertStoreParameters;

public class getJSON extends AsyncTask<Void, Void, Void> {

    String jsonData = "";
    @Override
    protected Void doInBackground(Void... voids) {

        JSONObject obj = new JSONObject();

        try
        {
            obj.put("userId", "5c78ce86a484a23550339d6a");
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject ret = JsonIo.doJsonIo("http://localhost:4000/expenses/all", obj.toString());

        try
        {
            jsonData = ret.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        MainActivity.data.setText(jsonData + " finished");
    }
}
