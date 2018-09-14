package edu.txstate.cjt71.restrentalcarapp;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.message.BasicHeader;
import edu.txstate.cjt71.restrentalcarapp.Client.RestfulClient;

public class CarListActivity extends ListActivity {

    public static ArrayList<Car> carList = new ArrayList<>();
    public static String CAR_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getCars();

    }

    // Retrieve Cars by REST and parse JSON object to Car object array list
    private void getCars(){
        List<Header> headerCarList = new ArrayList<Header>();
        headerCarList.add(new BasicHeader("Accept", "application/json"));
        RestfulClient.get(CarListActivity.this,"api/rentalcar", headerCarList.toArray(new Header[headerCarList.size()]), null, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                // Parse JSON object and add to List
                super.onSuccess(statusCode, headers, response);
                for (int i = 0; i < response.length(); i++) {
                    try {
                        String tempId = response.getJSONObject(i).getString("CarId");
                        String tempBrand = response.getJSONObject(i).getString("Brand");
                        String tempName = response.getJSONObject(i).getString("CarName");
                        String tempColor = response.getJSONObject(i).getString("Color");
                        double tempCost = response.getJSONObject(i).getDouble("CostPerDay");
                        Car tempCar = new Car(tempId, tempName, tempBrand, tempColor, tempCost);
                        if (checkIfItemExists(tempId)){
                            break;
                        } else {
                            carList.add(tempCar);
                        }
                    } catch (JSONException e) {
                        Toast.makeText(CarListActivity.this, "Error getting JSON Object", Toast.LENGTH_LONG).show();
                    }
                }
                setListAdapter(new ArrayAdapter<Car>(CarListActivity.this, android.R.layout.simple_list_item_1, carList));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Toast.makeText(CarListActivity.this, responseString, Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(CarListActivity.this);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(CAR_ID, position);
        editor.commit();

        startActivity(new Intent(CarListActivity.this, SelectedCarActivity.class));
    }

    // Check if item is already in Car List
    private boolean checkIfItemExists(String tempId){
        ArrayList<String> carID = new ArrayList<>();
        for (int i = 0; i < carList.size(); i++){
            carID.add(carList.get(i).getId());
        }
        if (carID.contains(tempId)){
            return true;
        } else {
            return false;
        }
    }

}
