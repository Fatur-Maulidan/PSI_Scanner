package com.example.myapplication.retrofit;

import com.example.myapplication.model.BarangModel;
import com.example.myapplication.model.DefaultResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiEndpoint {
    @GET("barang")
    Call<BarangModel> getDataBarang();

    @POST("barang")
    Call<DefaultResponse> createBarang(@Body BarangModel.Barang dataBarang);

    @PATCH("barang/{kode}")
    Call<DefaultResponse> updateStokbarang(@Path("kode") String kode, @Body int stok);

    @DELETE("barang/{kode}")
    Call<DefaultResponse> deleteBarang(@Path("kode") String kode);
}
