package com.example.buttonnavigation;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.buttonnavigation.dao.ProgrammeDAO;
import com.example.buttonnavigation.model.Programme;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class addProg extends AppCompatActivity {

    ProgrammeDAO progdao;

    // @BindView(R.id.input_name)
    EditText _nameText;
    @BindView(R.id.input_date) EditText dateText;
    @BindView(R.id.input_duree) EditText dureeText;
    @BindView(R.id.input_maladie) EditText maladieText;
    @BindView(R.id.btn_signup) Button _signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_prog);
        ButterKnife.bind(this);

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProg();
            }
        });
    }

    public void addProg() {

        if (!validate()) {
            onAddFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(addProg.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Programme...");
        progressDialog.show();

        String date = dateText.getText().toString();
        String duree = dureeText.getText().toString();
        String maladie = maladieText.getText().toString();

        //System.out.println("**************************"+date);
        //System.out.println("**************************"+duree);
        //System.out.println("**************************"+maladie);

        Programme programme=new Programme(null,date,duree,maladie);
        progdao= new ProgrammeDAO(getApplicationContext());
        progdao.addProg(programme);

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        onAddSuccess();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }

    @SuppressLint("ResourceType")
    public void onAddSuccess() {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);

        Toast.makeText(getApplicationContext(), "Programme added.", Toast.LENGTH_SHORT).show();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                finish();
            }
        }, 3 * 1000);
    }

    public void onAddFailed() {
        Toast.makeText(getBaseContext(), "Programme added failed", Toast.LENGTH_LONG).show();
        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String date = dateText.getText().toString();
        String duree = dureeText.getText().toString();
        String maladie = maladieText.getText().toString();

        if (date.isEmpty()) {
            dateText.setError("Format de date invalide. Usage : dd/MM/YYYY");
            valid = false;
        }
        else{
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            dateFormat.setLenient(false);
            Date date_form = null;
            try {
                date_form = dateFormat.parse(date);
                dateText.setError(null);
            }
            catch (Exception e) {
                dateText.setError("Format de date invalide. Usage : dd/MM/YYYY");
                valid = false;
                System.err.println(e.getMessage());
            }
        }

        if (duree.isEmpty()) {
            dureeText.setError("Enter Valid Address");
            valid = false;


        } else {
            try {
                int i = Integer.parseInt(duree);
                dateText.setError(null);
            }
            catch (Exception e) {
                dateText.setError("Format de durée invalide. Usage : number");
                valid = false;
            }
        }


        if (maladie.isEmpty()) {
            maladieText.setError("enter a maladie");
            valid = false;
        } else {
            maladieText.setError(null);
        }

        return valid;
    }

}
