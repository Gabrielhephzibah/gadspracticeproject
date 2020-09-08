package com.cherish.gadspracticeproject.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.cherish.gadspracticeproject.R;
import com.cherish.gadspracticeproject.data.remote.APIService;
import com.cherish.gadspracticeproject.data.remote.APIUtils;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmissionActivity extends AppCompatActivity {
    Button submitBtn;
    ImageView backImage;
    APIService mApiService;
    EditText firstName,lastName, emailAddress, githubLink;
    String firstNameText, lastNameText, emailText, githubText;
    ProgressDialog progressDialog;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        mApiService = APIUtils.getAPIService();
        submitBtn = findViewById(R.id.submitBtn);
        backImage = findViewById(R.id.back_button);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        emailAddress = findViewById(R.id.emailAddress);
        githubLink = findViewById(R.id.githubLink);
        progressDialog = new ProgressDialog(SubmissionActivity.this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard(view);
                firstNameText = firstName.getText().toString();
                lastNameText = lastName.getText().toString();
                emailText = emailAddress.getText().toString();
                githubText = githubLink.getText().toString();

                if (TextUtils.isEmpty(firstNameText)){
                    firstName.setError("Required");
                    firstName.requestFocus();
                }else if (TextUtils.isEmpty(lastNameText)){
                    lastName.setError("Required");
                    lastName.requestFocus();
                }else if (TextUtils.isEmpty(emailText)){
                    emailAddress.setError("Required");
                    emailAddress.requestFocus();
                }else if (TextUtils.isEmpty(githubText)){
                    githubLink.setError("Required");
                    githubLink.requestFocus();
                }else {
                    final MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(SubmissionActivity.this, R.style.MyRoundedAlertDialog);
                    LayoutInflater inflater = SubmissionActivity.this.getLayoutInflater();
                    View myView = inflater.inflate(R.layout.confirmation_layout, null);
                    builder.setView(myView);
                    Button yes = myView.findViewById(R.id.yes);
                    final AlertDialog alert = builder.create();
                    alert.show();
                    yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            alert.cancel();
                            progressDialog.show();
                            mApiService.submitProject(emailText,firstNameText,lastNameText,githubText,"https://docs.google.com/forms/d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse").enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> call, Response<Void> response) {
                                    if (response.isSuccessful()){
                                        progressDialog.cancel();
                                        LayoutInflater inflater = SubmissionActivity.this.getLayoutInflater();
                                        View secondView = inflater.inflate(R.layout.submisssion_successful_layout, null);
                                        builder.setView(secondView);
                                        AlertDialog alertTwo = builder.create();
                                        alertTwo.show();
                                    }

                                }

                                @Override
                                public void onFailure(Call<Void> call, Throwable t) {
                                    progressDialog.cancel();
                                    LayoutInflater inflater = SubmissionActivity.this.getLayoutInflater();
                                    View secondView = inflater.inflate(R.layout.submission_failed_layout, null);
                                    builder.setView(secondView);
                                    AlertDialog alertThree = builder.create();
                                    alertThree.show();

                                }
                            });

                        }
                    });

                }
            }
        });

        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });


    }


    public void hideKeyboard(View view){
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
    }
}
