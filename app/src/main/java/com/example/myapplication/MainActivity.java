package com.example.myapplication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    HashMap<String, Barang> barang = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setDataBarang();

        Button btnScan = findViewById(R.id.btnScan);
        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanCode();
            }
        });
    }

    private void setDataBarang() {
        barang.put("OQ89UYT7XMKL", new Barang("Indomie Rebus Ayam Bawang", 3500, 4));
        barang.put("0098OPLKJHLO", new Barang("Nutrisari Jeruk", 2500, 5));
        barang.put("998KALDSKASI", new Barang("Aqua", 3500, 9));
        barang.put("ASLQEI091239", new Barang("Beng Beng", 5500, 10));
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
            String kode = result.getContents();
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

            builder.setTitle("Result");
            builder.setMessage(prosesBarang(kode));
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).show();
        }
    });

    private String prosesBarang(String kode) {
        if (! barang.containsKey(kode)) {
            return "Barang tidak tersedia.";
        }

        Barang brg = barang.get(kode);
        if(brg.getStok() == 0){
            return brg.getNama() + " sudah habis.";
        }
        brg.setStok(brg.getStok() - 1);

        return brg.getNama() + " yang tersedia tersisa " + brg.getStok();
    }
}