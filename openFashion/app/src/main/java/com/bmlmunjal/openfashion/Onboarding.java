package com.bmlmunjal.openfashion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class Onboarding extends AppCompatActivity implements View.OnClickListener{
    private CardView loginCard,fogotPasswordCard,signupCard,setPasswordCard;
    private TextView forgetPassText,GoBack;
    private Button loginButton,signUpBtnLogin,sendLink,continueBtn,signUpBtnForgotPass,signUpBtnNext,LoginBtnSignup,NextBtn;
    private EditText inputName,inputEmail,inputNumber,inputPassword,inputConfirmPassword,loginEmail,loginPassword,loginForgotPassMail;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    FirebaseDatabase database;

    String onBoardingtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        database = FirebaseDatabase.getInstance();
        Intent intent=getIntent();
        onBoardingtext= intent.getStringExtra(MainActivity.EXTRA_NAME);

        loginCard=findViewById(R.id.login_card);
        fogotPasswordCard= findViewById(R.id.forget_password_card);
        signupCard= findViewById(R.id.signup_card);
        setPasswordCard= findViewById(R.id.set_pass_card);
        forgetPassText = findViewById(R.id.forget_password_login_text);
        loginButton = findViewById(R.id.login_button);
        signUpBtnLogin=findViewById(R.id.signUp_btn_logInCard);
        sendLink=findViewById(R.id.send_link_btn);
        continueBtn=findViewById(R.id.continue_btn);
        signUpBtnForgotPass=findViewById(R.id.signUp_btn_forgotPassword);
        signUpBtnNext=findViewById(R.id.signup_next_btn);
        LoginBtnSignup=findViewById(R.id.logIn_btn_signUp);
        GoBack=findViewById(R.id.go_back);
        NextBtn=findViewById(R.id.signup_next_btn);
        inputName=findViewById(R.id.signup_name);
        inputEmail=findViewById(R.id.signup_email);
        inputNumber=findViewById(R.id.signup_phone_number);
        inputPassword=findViewById(R.id.enter_pass_re_edit);
        inputConfirmPassword=findViewById(R.id.re_enter_pass_ree_edit);
        loginEmail=findViewById(R.id.email_address);
        loginPassword=findViewById(R.id.password);
        loginForgotPassMail=findViewById(R.id.forgot_pass_email);


        forgetPassText.setOnClickListener(this);
        loginButton.setOnClickListener(this);
        signUpBtnLogin.setOnClickListener(this);
        sendLink.setOnClickListener(this);
        continueBtn.setOnClickListener(this);
        signUpBtnForgotPass.setOnClickListener(this);
        signUpBtnNext.setOnClickListener(this);
        LoginBtnSignup.setOnClickListener(this);
        GoBack.setOnClickListener(this);
        NextBtn.setOnClickListener(this);
        inputName.setOnClickListener(this);
        inputEmail.setOnClickListener(this);
        inputNumber.setOnClickListener(this);
        inputPassword.setOnClickListener(this);
        inputConfirmPassword.setOnClickListener(this);
        loginEmail.setOnClickListener(this);
        loginPassword.setOnClickListener(this);
        loginForgotPassMail.setOnClickListener(this);


        progressDialog=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();
    }

    @Override
    public void onLocalVoiceInteractionStarted() {
        super.onLocalVoiceInteractionStarted();
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadingFunction(onBoardingtext);
    }

    private void loadingFunction(String onBoardingtext) {
        if(onBoardingtext.equals("signUp")){
            Log.d("xyz1", onBoardingtext);
            signupCard.setVisibility(View.VISIBLE);
            loginCard.setVisibility(View.GONE);
        }
        else{
            Log.d("xyz1", onBoardingtext);
            signupCard.setVisibility(View.GONE);
            loginCard.setVisibility(View.VISIBLE);
        }
        fogotPasswordCard.setVisibility(View.GONE);
        setPasswordCard.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case (R.id.forget_password_login_text):
                loginCard.setVisibility(View.GONE);
                fogotPasswordCard.setVisibility(View.VISIBLE);
                break;
            case(R.id.signUp_btn_logInCard):
                loginCard.setVisibility(View.GONE);
                signupCard.setVisibility(View.VISIBLE);
                break;
            case(R.id.login_button):
                performLogin();
                break;
            case (R.id.send_link_btn):
                validateData();
                startActivity(new Intent(this,MainActivity.class));
                break;
            case(R.id.signUp_btn_forgotPassword):
                fogotPasswordCard.setVisibility(View.GONE);
                signupCard.setVisibility(View.VISIBLE);
                break;
            case(R.id.signup_next_btn):
                BeforeAuth();
                break;
            case(R.id.logIn_btn_signUp):
                signupCard.setVisibility(View.GONE);
                loginCard.setVisibility(View.VISIBLE);
                break;
            case(R.id.go_back):
                setPasswordCard.setVisibility(View.GONE);
                signupCard.setVisibility(View.VISIBLE);
                break;
            case(R.id.continue_btn):
                PerformAuth();
                break;


        }
    }

    private void validateData() {
        String ForgotMail = loginForgotPassMail.getText().toString();
        if(ForgotMail.isEmpty()){
            loginForgotPassMail.setError("Please Enter an Email");
            Toast.makeText(Onboarding.this, "Please Enter an Email", Toast.LENGTH_SHORT).show();
        }
        else{
            mAuth.sendPasswordResetEmail(ForgotMail).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Onboarding.this, "Check your mail", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else{
                        Toast.makeText(Onboarding.this, "Error: "+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }



    private void performLogin() {
        String LoginEmail = loginEmail.getText().toString();
        String LoginPass = loginPassword.getText().toString();

        if(!LoginEmail.matches(emailPattern)){
            loginEmail.setError("Enter Correct Email");
            Toast.makeText(Onboarding.this,"Enter correct Email",Toast.LENGTH_SHORT).show();
        }
        else if(LoginPass.isEmpty() || LoginPass.length()<6){
            loginPassword.setError("Enter Proper Password");
            Toast.makeText(Onboarding.this, "Enter Proper Password", Toast.LENGTH_SHORT).show();
        }
        else{
            progressDialog.setMessage("Logging in...");
            progressDialog.setTitle("Login");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.signInWithEmailAndPassword(LoginEmail,LoginPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();
                        sendUserToNextActivity();
                        Toast.makeText(Onboarding.this,"Login Successful",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        progressDialog.dismiss();
                        Toast.makeText(Onboarding.this,"Invalid Credentials",Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }

    private void BeforeAuth(){
        String Name = inputName.getText().toString();
        String Email = inputEmail.getText().toString();
        String Number= inputNumber.getText().toString();

        if(Name.isEmpty()){
            inputName.setError("Enter a Name");
            Toast.makeText(Onboarding.this,"Enter a Name",Toast.LENGTH_SHORT).show();
        }
        else if(!Email.matches(emailPattern)){
            inputEmail.setError("Enter Correct Email");
            Toast.makeText(Onboarding.this,"Enter Correct Email",Toast.LENGTH_SHORT).show();
        }
        else if(Number.isEmpty() || Number.length()<10){
            inputNumber.setError("Enter a correct mobile number");
            Toast.makeText(Onboarding.this,"Enter a correct mobile number",Toast.LENGTH_SHORT).show();
        }
        else{
            signupCard.setVisibility(View.GONE);
            setPasswordCard.setVisibility(View.VISIBLE);
        }

    }

    private void PerformAuth() {
        String Email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();
        String confirmPass = inputConfirmPassword.getText().toString();

        if(password.isEmpty() || password.length()<6){
            inputPassword.setError("Enter a Password minimum of length 6 digits");
            Toast.makeText(Onboarding.this,"Enter a Password minimum of length 6 digits",Toast.LENGTH_SHORT).show();
        }
        else if(!password.equals(confirmPass)){
            inputConfirmPassword.setError("Re-Entered Password doesn't matches the entered password");
            Toast.makeText(Onboarding.this,"Re-Entered Password doesn't matches the entered password",Toast.LENGTH_SHORT).show();
        }
        else{
            progressDialog.setMessage("Please wait while we register you...");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(Email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();

                        User user = new User(inputName.getText().toString(),inputNumber.getText().toString(),inputEmail.getText().toString());
                        String id = task.getResult().getUser().getUid();

                        database.getReference().child("User").child(id).setValue(user);

                        sendUserToNextActivity();
                        Toast.makeText(Onboarding.this,"Registration Successful",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        progressDialog.dismiss();
                        Toast.makeText(Onboarding.this,""+task.getException(),Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }

    private void sendUserToNextActivity() {
        Intent intent = new Intent(Onboarding.this,HomeActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}