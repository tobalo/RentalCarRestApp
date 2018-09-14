package edu.txstate.cjt71.restrentalcarapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    private Car selectedCar;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(UpdateActivity.this);
        position = pref.getInt(CarListActivity.CAR_ID, 0);
        selectedCar = CarListActivity.carList.get(position);

        final TextView carText = findViewById(R.id.updateTxt);
        final EditText updatedTxt = findViewById(R.id.updatedDaysTxt);
        final Button updateBtn = findViewById(R.id.updateCostBtn);
        final Button homeBtn = findViewById(R.id.homeBtn);

        carText.setText("ID: " + selectedCar.getId()+ " Name: " + selectedCar.getName());
        // Update car cost value
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double newCost = Double.parseDouble(updatedTxt.getText().toString());
                CarListActivity.carList.get(position).setCostPerDay(newCost);
                Toast.makeText(UpdateActivity.this, "Updated!", Toast.LENGTH_LONG).show();
                updatedTxt.setText("");
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UpdateActivity.this, MainActivity.class));
            }
        });

    }
}
