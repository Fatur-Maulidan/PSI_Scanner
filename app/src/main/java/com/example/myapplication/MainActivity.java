package com.example.myapplication;

import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class MainActivity extends AppCompatActivity {

    int indomie = 3;
    int nutrisari = 5;
    int beng_beng = 10;
    int aqua = 9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnScan = findViewById(R.id.btnScan);
        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanCode();
            }
        });
    }

    private void scanCode(){
        ScanOptions scanOptions = new ScanOptions();
        scanOptions.setPrompt("Volume up to flash on");
        scanOptions.setBeepEnabled(true);
        scanOptions.setOrientationLocked(true);
        scanOptions.setCaptureActivity(CaptureAct.class);
        barLauncher.launch(scanOptions);
    }

    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(), result -> {
        if(result.getContents() != null){
            String res = result.getContents();
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Result");
            if(res.equals("OQ89UYT7XMKL")){ // Indomie
                if(indomie <= 0){
                    builder.setMessage("Indomie sudah habis");
                } else {
                    indomie = indomie - 1;
                    builder.setMessage("Indomie yang tersedia tersisa : "+String.valueOf(indomie));
                }
            } else if (res.equals("0098OPLKJHLO")){ // Nutrisari
                if(nutrisari <= 0){
                    builder.setMessage("Nutrisari sudah habis");
                } else {
                    nutrisari = nutrisari - 1;
                    builder.setMessage("Nutrisari yang tersedia tersisa : "+String.valueOf(nutrisari));
                }
            } else if (res.equals("998KALDSKASI")){ // Aqua
                if(aqua <= 0){
                    builder.setMessage("Aqua sudah habis");
                } else {
                    aqua = aqua - 1;
                    builder.setMessage("Aqua yang tersedia tersisa : "+String.valueOf(aqua));
                }
            } else if (res.equals("ASLQEI091239")) { // Beng Beng
                if(beng_beng <= 0){
                    builder.setMessage("Beng Beng sudah habis");
                } else {
                    beng_beng = beng_beng - 1;
                    builder.setMessage("Beng Beng yang tersedia tersisa : "+String.valueOf(beng_beng));
                }
            }
            else builder.setMessage("Data tidak ada");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).show();
        }
    });
}