package com.mir.qrcodescanner;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Button btn = findViewById(R.id.btnPermission);

        btn.setOnClickListener(v -> {
            permissionC2();
        });


    }

    private void permissionC2() {
        ActivityCompat.requestPermissions(SecondActivity.this, new String[]{Manifest.permission.CAMERA}, 1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:{
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "Granted", Toast.LENGTH_SHORT).show();
                    Intent intent = new  Intent(this,MainActivity.class);
                    startActivity(intent);
                }else{

                  runOnUiThread(() -> {

                      AlertDialog.Builder builder = new AlertDialog.Builder(this)
                              .setTitle("Permission")
                              .setMessage("Permission are necessary")
                              .setPositiveButton("Setting", (dialog, which) -> {
                                  Toast.makeText(this, "Denied", Toast.LENGTH_SHORT).show();
                                  Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                          Uri.fromParts("package", getPackageName(), null));
                                  intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                  startActivity(intent);
                              });

                      AlertDialog alertDialog = builder.create();
                      alertDialog.show();

                  });


                }
                return;
            }
        }
    }


}