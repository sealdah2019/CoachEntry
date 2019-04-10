package com.example.coachentry;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText CoachNo;
    Spinner Type;
    Spinner Rly;
    Button Submit;

    DatabaseReference databaseCoaching;

    ListView listViewCoaches;
    List<Coaches>CoachList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseCoaching = FirebaseDatabase.getInstance().getReference("Coaches");

        CoachNo = (EditText)(findViewById(R.id.CoachNo));
        Type = (Spinner)(findViewById(R.id.Type));
        Rly = (Spinner) (findViewById(R.id.rly));
        Submit = (Button)(findViewById(R.id.Submit));

        listViewCoaches = (ListView) findViewById(R.id.listViewCoaches);
        CoachList = new ArrayList<>();

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            addCoach();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseCoaching.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                CoachList.clear();
                for (DataSnapshot coachSnapshot : dataSnapshot.getChildren()) {
                    Coaches coaches = coachSnapshot.getValue(Coaches.class);
                    CoachList.add(coaches);
                }
                CoachList adapter = new CoachList(MainActivity.this, CoachList);
                listViewCoaches.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void addCoach(){
        String coach = CoachNo.getText().toString().trim();
        String types = Type.getSelectedItem().toString();
        String rly =  Rly.getSelectedItem().toString();

        if (!TextUtils.isEmpty(coach)){

            String id = databaseCoaching.push().getKey();
            Coaches coaches = new Coaches (id, coach, types,rly);
            databaseCoaching.child(id).setValue(coaches);
            Toast.makeText(this, "Coach Added", Toast.LENGTH_LONG).show();
        }

        else{
            Toast.makeText(this, "Enter Coach Number", Toast.LENGTH_LONG).show();

        }
    }
}
