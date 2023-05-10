package com.example.test123;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test123.models.Contacts;

import java.util.ArrayList;
import java.util.List;

public class Adopter extends ArrayAdapter<Contacts> {
    Context context;
    ArrayList<Contacts> users;

    public Adopter(@NonNull Context context, ArrayList<Contacts> users) {
        super(context, R.layout.customlist, (List<Contacts>) users);
        this.context=context;
        this.users=users;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View rowView =inflater.inflate(R.layout.customlist,null,true);
        TextView number = rowView.findViewById(R.id.number);
        TextView name = rowView.findViewById(R.id.name);

        name.setText(users.get(position).getName());
        number.setText(users.get(position).getPhoneNumber());

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"tapped "+users.get(position).getName(),Toast.LENGTH_LONG).show();
            }
        });
        return rowView;
    }
}
