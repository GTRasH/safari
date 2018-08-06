package com.safari.apps.itraffic_system;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.safari.apps.itraffic_system.Utils.SafariService;

public class LoginActivity extends AppCompatActivity {

    //Declaring variables
    Button loginButton;
    EditText usernameText;
    EditText passwordText;

    SafariService sService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //login(view);
                moveToMain();

            }
        });


//*******Using the loginDaten to move to mainActivity if we get an ok from server*******

/*        mService = ApiUtils.getEwesService();
        String savedUser = ApiUtils.getInstance().preferences.getString("savedUser","");

        if (savedUser.length()>0) {
            Gson gson = new Gson();
            LoginResponse loginResponse = gson.fromJson(savedUser, LoginResponse.class);
            if (loginResponse.getOk()){

                moveToMain();
            }
        }*/

    }


    public void moveToMain(){

        startActivity(new Intent(LoginActivity.this, MainActivity.class));


    }

//*******The logic of Login*******

/*    public void login(View view ) {

        final String user = usernameText.getText().toString();
        final String pass = passwordText.getText().toString();

        if (user.length()>0 && pass.length()>0){


            Login l = new Login("User1", "password");

            Login login = new Login(user,pass);

            sService.login(login).enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                    if (response.isSuccessful()) {

                        //Toast.makeText(me, "Loading", Toast.LENGTH_SHORT).show();

                        LoginResponse loginResponse = response.body();

                        String cookie = response.headers().get("Set-Cookie");
                        //  cookie =  cookie.substring(cookie.indexOf("="),cookie.indexOf(";"));
                        //  cookie = cookie.replace("=","");

                        Gson gson = new Gson();
                        ApiUtils.getInstance().editor.putString("savedUser", gson.toJson(loginResponse));
                        ApiUtils.getInstance().editor.putString("AuthSession", cookie);

                        ApiUtils.getInstance().editor.putString("user", user);
                        ApiUtils.getInstance().editor.putString("password", pass);


                        ApiUtils.getInstance().editor.commit();

                        moveToMain();

                    } else {
                        // int statusCode = response.code();
                        try {


                            Gson gson = new Gson();
                            ErrorResponse error = gson.fromJson(response.errorBody().string(), ErrorResponse.class);

                            Toast.makeText(me, error.reason, Toast.LENGTH_LONG).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }


                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {

                    Toast.makeText(me, "Keine Internet Verbindung", Toast.LENGTH_LONG).show();
                }
            });
        }else{
            Toast.makeText(me, "Bitte Username und Password eingeben", Toast.LENGTH_LONG).show();

        }


    }*/



}
