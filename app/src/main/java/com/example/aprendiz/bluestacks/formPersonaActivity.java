package com.example.aprendiz.bluestacks;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cad.database;
import model.User;

public class formPersonaActivity extends AppCompatActivity {

    EditText name,document,email,password;
    Button update,delete,search,register;
    long _id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_persona);

        name = ((EditText)findViewById(R.id.txtName));
        document = ((EditText)findViewById(R.id.txtID));
        email = ((EditText)findViewById(R.id.txtEmail));
        password = ((EditText)findViewById(R.id.txtPassword));

        update = ((Button)findViewById(R.id.btnUpdate));
        delete = ((Button)findViewById(R.id.btnDelete));
        update.setEnabled(false);
        delete.setEnabled(false);

    }



    public void register(View view) {
        database db = new database(getApplicationContext());
        User u = new User();


        u.setName(name.getText().toString());
        u.setDocument(document.getText().toString());
        u.setEmail(email.getText().toString());
        u.setPassword(password.getText().toString());

        int r = db.newUser(u);

        if (r == 0){
            message("Registrado",getApplicationContext());
            clear();
        }
        else if(r == 2){
            message("Correo ya Existe",getApplicationContext(),email,"Email");
        }
        else{
            message("Error",getApplicationContext());
        }
    }


    public void delete(View view) {
        User u=new User();
        u.setEmail(email.getText().toString());
        database d=new database(getApplicationContext());

        name.getText().clear();
        email.getText().clear();
        document.getText().clear();
        password.getText().clear();
        name.requestFocus();

        if(d.deleteUser(u)){
            message("Borrado",getApplicationContext());
        }

    }

    public void search(View view){
        database db = new database(getApplicationContext());
        User u = db.searchUser(email.getText().toString());

        if(u != null){
            email.setText(u.getEmail());
            document.setText(u.getDocument());
            name.setText(u.getName());
            password.setText(u.getPassword());
            _id = u.get_id();

            message("Consultado",getApplicationContext());
            update.setEnabled(true);
            delete.setEnabled(true);

        }
        else{
            message("No Existe",getApplicationContext(),email,"Email");
        }
    }

    public void update(View view) {
        database db = new database(getApplicationContext());
        User u = new User();

        u.setName(name.getText().toString());
        u.setDocument(document.getText().toString());
        u.setEmail(email.getText().toString());
        u.setPassword(password.getText().toString());
        u.set_id(_id);
        int r = db.updateUser(u);

        if (r == 0){
            message("Actualizado",getApplicationContext());
            clear();
        }
        else if(r == 2){
            message("Correo ya Existe",getApplicationContext(),email,"Email");
        }
        else{
            message("Error",getApplicationContext());
        }
    }

    private void clear(){
        name.getText().clear();
        email.getText().clear();
        document.getText().clear();
        password.getText().clear();
        name.requestFocus();
    }

    private void message(String message, Context context){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }


    private void message(String message, Context context, EditText field , String messageError){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
        field.setError(messageError);
    }
}
