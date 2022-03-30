package com.example.saveadrop;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

public class smsPage extends AppCompatActivity {
Button Back;
    private EditText txt_message;
    private EditText txt_number;
    private EditText txt_count;

    private Button button_sms;
    private Button button_contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_page);

        txt_message=findViewById(R.id.txt_msg);
        txt_number=findViewById(R.id.txt_num);
        txt_count=findViewById(R.id.txt_cnt);

        button_sms=findViewById(R.id.btn_sms);
        Dexter.withContext(this)
                .withPermissions(
                        Manifest.permission.SEND_SMS,
                        Manifest.permission.READ_CONTACTS
                ).withListener(new MultiplePermissionsListener() {
            @Override public void onPermissionsChecked(MultiplePermissionsReport report) {/* ... */}
            @Override public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {/* ... */}
        }).check();

        Back=(Button)findViewById(R.id.btn_Back);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intsignUp=new Intent(smsPage.this,HomePage.class);
                startActivity(intsignUp);

            }
        });

        button_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                            for (int i = 0; i < Integer.parseInt(txt_count.getText().toString()); i++) {
                                SmsManager smsManager = SmsManager.getDefault();
                                smsManager.sendTextMessage(txt_number.getText().toString(), null, txt_message.getText().toString(), null, null);
                                Toast.makeText(smsPage.this, "SMS Sent : Count" + (i + 1), Toast.LENGTH_SHORT).show();
                            }


                }
                catch (Exception e)
                {
                    Toast.makeText(smsPage.this,"SMS Sending Failed!",Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

}
