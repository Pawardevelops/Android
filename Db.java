package com.example.test123;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.test123.datas.MyDbHandler;
import com.example.test123.models.Contacts;

import java.util.List;

public class Db extends AppCompatActivity {
    EditText name,number;
    Button submit,viewContacts;
    MyDbHandler db = new MyDbHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        name = findViewById(R.id.name);
        number = findViewById(R.id.number);
        submit = findViewById(R.id.submitBtn);
        viewContacts = findViewById(R.id.viewContactsBtn);
        viewContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Db.this,Users.class);
                startActivity(intent);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String contactName = name.getText().toString();
                String contactNumber = number.getText().toString();
                if(contactName.equals("") || contactNumber.equals("")){
                    Toast.makeText(Db.this,"Data Not Entered",Toast.LENGTH_LONG).show();
                    return ;
                }
                Contacts contact = new Contacts(contactName,contactNumber);
                db.createContact(contact);
                name.setText("");
                number.setText("");
                try {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Db.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {
                    // TODO: handle exception
                }
                Toast.makeText(Db.this,"DB updated with : "+contactName +" "+contactNumber,Toast.LENGTH_LONG).show();
                //just to test the entries
                List<Contacts> allContacts = db.getAllContacts();

                for(Contacts cont : allContacts){
                    Log.d("ALLCONTACT","ID : "+cont.getId()+"\n"+
                            "Name : "+cont.getName()+ "\n"+
                            "phoneNumber : "+cont.getPhoneNumber()+"\n"
                    );
                }
                Log.d("dbCount",""+db.getCount());
                //laSt line of testing
            }
        });



    }
}