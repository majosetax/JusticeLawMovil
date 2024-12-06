package com.example.justicelawmovil.service

import androidx.compose.ui.geometry.Offset
import com.example.justicelawmovil.model.ForumQuestion
//import com.example.justicelawmovil.model.UsersListModel
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
 @GET("users")
 suspend fun obtenerUsuarios(
@Query("offset") offset: Int,
 @Query("limit") limit:Int
)

    @GET("questions")
    suspend fun getForumQuestions(): List<ForumQuestion>

 @GET("users/{name}")
suspend fun obtenerUsuarioPorId(@Path("name") name: String)
}

fun getRetrofitClient(): ApiService {

 val client = Retrofit.Builder()
    .baseUrl("https://apijusticelaw-production.up.railway.app/v1/")
     .addConverterFactory(GsonConverterFactory.create())
    .client(OkHttpClient())
     .build()
 return client.create(ApiService::class.java)
}

object RetrofitClient {
    private const val BASE_URL = ("https://apijusticelaw-production.up.railway.app/v1/")

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
