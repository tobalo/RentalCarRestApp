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

public class SelectedCarActivity extends AppCompatActivity {

    private Car selectedCar;
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_car);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(SelectedCarActivity.this);
        position = pref.getInt(CarListActivity.CAR_ID, 0);
        selectedCar = CarListActivity.carList.get(position);

        final TextView idTxt = findViewById(R.id.iDTxt);
        final TextView nameTxt = findViewById(R.id.nameTxt);
        final TextView brandTxt = findViewById(R.id.brandTxt);
        final TextView colorTxt = findViewById(R.id.colorTxt);
        final TextView costTxt = findViewById(R.id.costTxt);

        final EditText daysInput = findViewById(R.id.inputDaysTxt);

        final Button update = findViewById(R.id.updateBtn);
        final Button compute = findViewById(R.id.computeBtn);

        // Set features from selected Car
        idTxt.setText("ID: " + selectedCar.getId());
        nameTxt.setText("Name: " + selectedCar.getName());
        brandTxt.setText("Brand: " + selectedCar.getBrand());
        colorTxt.setText("Color: " + selectedCar.getColor());
        costTxt.setText("Cost per day: $" + Double.toString(selectedCar.getCostPerDay()));

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(SelectedCarActivity.this);
                SharedPreferences.Editor editor = pref.edit();
                editor.putInt(CarListActivity.CAR_ID, position);
                editor.commit();
                startActivity(new Intent(SelectedCarActivity.this,UpdateActivity.class));

            }
        });

        compute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    double numOfDays = Double.parseDouble(daysInput.getText().toString());
                    if (numOfDays > 30){
                        Toast.makeText(SelectedCarActivity.this, "Call the phone number 512-777-2222.", Toast.LENGTH_LONG).show();
                    } else {
                        double cost = selectedCar.getCostPerDay() * numOfDays;
                        Toast.makeText(SelectedCarActivity.this, "Total rent cost is: $" + Double.toString(cost), Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e){
                    Toast.makeText(SelectedCarActivity.this, e.getMessage(),Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
