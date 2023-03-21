package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.model.BarangModel;
import com.example.myapplication.model.DefaultResponse;
import com.example.myapplication.retrofit.ApiService;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText et_stok;
    RadioButton rb1,rb2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_stok = findViewById(R.id.stok);
        rb1 = findViewById(R.id.tambah);
        rb2 = findViewById(R.id.kurang);

        Button btnScan = findViewById(R.id.btnScan);
        btnScan.setOnClickListener(v -> scanCode());
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

            if(rb1.isChecked()){
                prosesTambahBarangViaRetrofit(kode,Integer.parseInt(et_stok.getText().toString()));
            }else if (rb2.isChecked()){
                prosesKurangBarangViaRetrofit(kode,Integer.parseInt(et_stok.getText().toString()));
            }

        }
    });

    private void prosesKurangBarangViaRetrofit(String kode, int stok){
        ApiService.endpoint().kurangStokBarang(kode, stok).enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if(response.isSuccessful()){
                    Log.d("TAG", "Masuk Sini2");
                    Toast.makeText(MainActivity.this, "Berhasil", Toast.LENGTH_LONG).show();
                } else {
                    Log.d("TAG", "Masuk Sini3");
                    Toast.makeText(MainActivity.this, "Gagal", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                Log.d("TAG", "Masuk Sini4");
                Log.d("MainActivity", t.getMessage());
            }
        });
    }

    private void prosesTambahBarangViaRetrofit(String kode, int stok){
        ApiService.endpoint().tambahStokBarang(kode, stok).enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if(response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Berhasil", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Gagal", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                Log.d("MainActivity", t.getMessage());
            }
        });
    }
}