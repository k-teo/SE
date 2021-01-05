package com.studies.se;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EditEmployee extends AppCompatActivity {

    private EmployeeDB dataBase;
    private TextView nameText;
    private TextView surnameText;
    private TextView rateText;

    private String id;
    private String name;
    private String surname;
    private String rate;

    private String nameNew;
    private String surnameNew;
    private String rateNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_employee);

        dataBase = new EmployeeDB(this);

        nameText = (TextView) findViewById(R.id.nameEdit);
        surnameText = (TextView) findViewById(R.id.surnameEdit);
        rateText = (TextView) findViewById(R.id.rateEdit);

        Bundle b = getIntent().getExtras();
        if(b != null)
        {
            id = b.getString("id");
            name = b.getString("name");
            surname = b.getString("surname");
            rate = b.getString("rate");
        }

        Button editButton = (Button) findViewById(R.id.buttonEditEmployee);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameNew = (nameText.getText().toString().equals("")) ? name : nameText.getText().toString();
                surnameNew = (surnameText.getText().toString().equals("")) ? surname : surnameText.getText().toString();
                rateNew = (rateText.getText().toString().equals("")) ? rate : rateText.getText().toString();

                if (dataBase.updateEmployee(id, nameNew, surnameNew, rateNew, "1"))
                {
                    startActivity(new Intent(EditEmployee.this, Workers.class));
                }
            }
        });
    }

}