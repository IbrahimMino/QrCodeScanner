package com.mir.qrcodescanner;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

public class MainActivity extends AppCompatActivity {
    CodeScanner mCodeScaner;
    CodeScannerView scannerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scannerView = findViewById(R.id.scanner_view);
        qrCode();
    }



    public void qrCode(){
        mCodeScaner = new CodeScanner(this,scannerView);

        mCodeScaner.setDecodeCallback(result -> {
            this.runOnUiThread(() -> {
                TextView str = findViewById(R.id.tvTitle);
                str.setText(result.getText());
            });
        });

        scannerView.setOnClickListener(v -> {
            mCodeScaner.startPreview();
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
       mCodeScaner.startPreview();
    }

    @Override
    protected void onPause() {
        mCodeScaner.releaseResources();
        super.onPause();
    }
}
