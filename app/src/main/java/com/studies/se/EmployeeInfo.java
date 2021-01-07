package com.studies.se;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EmployeeInfo extends AppCompatActivity implements View.OnClickListener, DeleteDialog.DialogListener {

    private EmployeeDB dataBase;
    private TextView nameText;
    private TextView surnameText;
    private TextView ageText;
    private TextView experienceText;

    private String id;
    private String name;
    private String surname;
    private String rate;
    private String experience;
    private String birth;
    private String owner;
    private String station;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_info);

        dataBase = new EmployeeDB(this);

        Button statistics = findViewById(R.id.statisticsButton);
        statistics.setOnClickListener(this);
        Button status = findViewById(R.id.statusButton);
        status.setOnClickListener(this);
        Button delete = findViewById(R.id.deleteButton);
        delete.setOnClickListener(this);
        Button modify = findViewById(R.id.modifyButton);
        modify.setOnClickListener(this);

        nameText = (TextView) findViewById(R.id.nameProfile);
        surnameText = (TextView) findViewById(R.id.surnameProfile);
        ageText = (TextView) findViewById(R.id.phoneProfile);
        experienceText = (TextView) findViewById(R.id.experienceProfile);

        Bundle b = getIntent().getExtras();
        if(b != null)
        {
            id = b.getString("id");
            name = b.getString("name");
            surname = b.getString("surname");
            rate = b.getString("rate");
            birth = b.getString("phone");
            experience = b.getString("experience");
            owner = b.getString("owner");
            station = b.getString("station");
        }

        nameText.setText("Name: " + name);
        surnameText.setText("Surname: " + surname);
        ageText.setText("Phone number : " + birth);
        experienceText.setText("Experience : " + experience + "years");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.statisticsButton:
                startActivity(new Intent(getApplicationContext(), EmployeeStatistic.class));
                break;
            case R.id.statusButton:
                startActivity(new Intent(getApplicationContext(), EmployeeStatus.class));
                break;
            case R.id.deleteButton:
                openDialog();
                break;
            case R.id.modifyButton:
                Intent intent = new Intent(EmployeeInfo.this, EditEmployee.class);
                Bundle b = new Bundle();
                b.putString("id", id);
                b.putString("name", name);
                b.putString("surname", surname);
                b.putString("rate", rate);
                b.putString("phone", birth);
                b.putString("experience", experience);
                b.putString("owner", owner);
                b.putString("station", station);
                intent.putExtras(b);
                startActivity(intent);
                break;
        }
    }
    public void openDialog(){
        DeleteDialog deleteDialog = new DeleteDialog();
        deleteDialog.show(getSupportFragmentManager(), "deleteDialog");
    }

    @Override
    public void onYesClicked() {
        if (dataBase.deleteEmployee(id))
        {
            Intent intent = new Intent(EmployeeInfo.this, Employees.class);
            Bundle b = new Bundle();
            b.putString("owner", owner);
            b.putString("station", station);
            intent.putExtras(b);
            startActivity(intent);
        }
    }
}