package com.octolearn.se;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class WorkerInfo extends AppCompatActivity implements View.OnClickListener {

    private EmployeeDB dataBase;
    private TextView nameText;
    private TextView surnameText;

    private String id;
    private String name;
    private String surname;
    private String rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_info);

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

        Bundle b = getIntent().getExtras();
        if(b != null)
        {
            id = b.getString("id");
            name = b.getString("name");
            surname = b.getString("surname");
            rate = b.getString("rate");
        }

        nameText.setText(name);
        surnameText.setText(surname);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.statisticsButton:
                startActivity(new Intent(getApplicationContext(), WorkerStatistics.class));
                break;
            case R.id.statusButton:
                startActivity(new Intent(getApplicationContext(), WorkerStatus.class));
                break;
            case R.id.deleteButton:
                if (dataBase.deleteEmployee(id))
                {
                    Toast.makeText(WorkerInfo.this, "Employee deleted", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(WorkerInfo.this, Workers.class));
                }
                else
                {
                    Toast.makeText(WorkerInfo.this, id, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.modifyButton:
                Intent intent = new Intent(WorkerInfo.this, EditEmployee.class);
                Bundle b = new Bundle();
                b.putString("id", id);
                b.putString("name", name);
                b.putString("surname", surname);
                b.putString("rate", rate);
                intent.putExtras(b);
                startActivity(intent);
                break;
        }
    }
}