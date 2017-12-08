package com.example.magshimim.ex7client;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private final int MY_CALL_REQUEST=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClick(View view){

        Toast toast = Toast.makeText(this, "Hello", Toast.LENGTH_LONG);
        toast.show();
        switch (view.getId())
        {
            case R.id.buttonCall:
                if(checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    EditText phoneNum = (EditText) findViewById(R.id.phone);
                    String number = phoneNum.getText().toString();
                    callPhone(number);
                }
                else
                {
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE},MY_CALL_REQUEST);
                }

                break;
            case R.id.buttonSurf:
                break;
        }
    }
    private void callPhone(String phoneNumber)
    {

        Uri call = Uri.parse("tel:" + phoneNumber);
        Intent surf = new Intent(Intent.ACTION_CALL, call);
        startActivity(surf);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case MY_CALL_REQUEST:
                if(grantResults.length > 0 &&
                        permissions[0].equals(Manifest.permission.CALL_PHONE) &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {

                }
        }
    }
}
