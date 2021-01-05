package com.octolearn.se;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class EmployeeAdapter extends BaseAdapter {
    LayoutInflater mInflater;
    ArrayList<Employee> employees;

    public EmployeeAdapter(Context context, ArrayList<Employee> employees){
        this.employees = employees;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return employees.size();
    }

    @Override
    public Object getItem(int position) {
        return employees.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mInflater.inflate(R.layout.employee_listview_detail, null);
        TextView nameTextView = (TextView) view.findViewById(R.id.nameDetail);
        TextView surnameTextView = (TextView) view.findViewById(R.id.surnameDetail);
        TextView rateTextView = (TextView) view.findViewById(R.id.rateDetail);

        String name = employees.get(position).getName();
        String surname = employees.get(position).getSurname();
        String rate = employees.get(position).getRate();

        nameTextView.setText(name);
        surnameTextView.setText(surname);
        rateTextView.setText(rate);

        return view;
    }
}
