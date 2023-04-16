package com.example.tracking;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class bus_details extends AppCompatActivity {
    FirebaseFirestore db;
    EditText bus_num,driver_name,license_num,password;
    Button save;
    TableLayout tl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_details);
        db= FirebaseFirestore.getInstance();
        bus_num=findViewById(R.id.bus_num);
        driver_name=findViewById(R.id.driver_name);
        license_num=findViewById(R.id.license_num);
        password=findViewById(R.id.password);
        tl=findViewById(R.id.tl);




        save=findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Map<String,Object> details=new HashMap<>();
                details.put("busnumber",bus_num.getText().toString());
                details.put("Drivername",driver_name.getText().toString());
                details.put("Licensenumber",license_num.getText().toString());
                details.put("password",password.getText().toString());
                FirebaseFirestore.setLoggingEnabled(true);
                db.collection("Buses").document(bus_num.getText().toString()).set(details).addOnSuccessListener(new OnSuccessListener<Void>() {

                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(bus_details.this, "Data saved", Toast.LENGTH_SHORT).show();                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(bus_details.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });


    }
}