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
    private final int REG_REQ=5;

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
                String url = ((EditText)findViewById(R.id.surf)).getText().toString();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                break;
            case R.id.buttonEMail:
                String sendTo = ((EditText)findViewById(R.id.eMail)).getText().toString();
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/html");
                intent.putExtra(Intent.EXTRA_EMAIL,new String[] {sendTo});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                intent.putExtra(Intent.EXTRA_TEXT, "I'm email body.");

                startActivity(Intent.createChooser(intent, "Send Email"));
                break;
            case R.id.buttonRegister:
                Intent reg = new Intent("com.EX7.action.Register");
                startActivityForResult(reg,REG_REQ);
                break;
        }
    }
    @SuppressWarnings({"MissingPermission"})
    private void callPhone(String phoneNumber)
    {

        Uri call = Uri.parse("tel:" + phoneNumber);
        Intent surf = new Intent(Intent.ACTION_CALL, call);
        startActivity(surf);

    }

  /*  private void surfPhone(String url)
    {

        Uri address = Uri.parse(url);
        Intent surf = new Intent(Intent.ACTION_VIEW, address);
        startActivity(surf);

    }*/


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
