package com.example.practice_r3;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements PassengerAdapter.PassengerClicked {

    ListView lvPassengers;
    PassengerAdapter adapter;
    ArrayList<Passenger> passengers;
    FloatingActionButton fabAddPassenger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        fabAddPassenger = findViewById(R.id.fabAddPassenger);
        lvPassengers = findViewById(R.id.lvPassengers);
        passengers = new ArrayList<>();
        passengers.add(new Passenger("Ali Raza", "03234677035", "bus"));
        passengers.add(new Passenger("Muzammil", "03234677037", "plane"));
        passengers.add(new Passenger("Murtaza", "03234677034", "train"));

        adapter = new PassengerAdapter(this, passengers);
        lvPassengers.setAdapter(adapter);



        fabAddPassenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder addDialog = new AlertDialog.Builder(MainActivity.this);
                addDialog.setTitle("Add New Passenger");
                View view = LayoutInflater
                        .from(MainActivity.this)
                        .inflate(R.layout.design_new_passenger_form, null, false);
                addDialog.setView(view);


                TextInputEditText etName, etPhone, etPreference;
                etName = view.findViewById(R.id.etName);
                etPhone = view.findViewById(R.id.etPhone);
                etPreference = view.findViewById(R.id.etPreference);

                addDialog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String name  = etName.getText().toString().trim();
                        String phone = etPhone.getText().toString();
                        String preference = etPreference.getText().toString().trim();

                        if(name.isEmpty() || phone.isEmpty() || preference.isEmpty())
                        {
                            Toast.makeText(MainActivity.this, "Something is missing", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            passengers.add(new Passenger(name, phone, preference));
                            adapter.notifyDataSetChanged();
                        }

                    }
                });
                addDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });


                addDialog.show();

            }
        });

    }

    @Override
    public void deletePassenger(int index) {
        passengers.remove(index);
        adapter.notifyDataSetChanged();
    }
}