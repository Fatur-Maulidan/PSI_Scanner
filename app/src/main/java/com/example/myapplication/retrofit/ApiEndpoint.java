package com.example.myapplication.retrofit;

import com.example.myapplication.model.BarangModel;
import com.example.myapplication.model.DefaultResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiEndpoint {
    @GET("barang")
    Call<BarangModel> getDataBarang();

    @POST("barang")
    Call<DefaultResponse> createBarang(@Body BarangModel.Barang dataBarang);

    @FormUrlEncoded
    @PATCH("barang/kurang/{kode}")
    Call<DefaultResponse> kurangStokBarang(@Path("kode") String kode, @Field("stok") int stok);

    @FormUrlEncoded
    @PATCH("barang/tambah/{kode}")
    Call<DefaultResponse> tambahStokBarang(@Path("kode") String kode, @Field("stok") int stok);

    @DELETE("barang/{kode}")
    Call<DefaultResponse> deleteBarang(@Path("kode") String kode);
}
