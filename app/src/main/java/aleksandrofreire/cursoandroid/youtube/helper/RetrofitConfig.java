package aleksandrofreire.cursoandroid.youtube.helper;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    //Configurando a retrofit
    public static Retrofit getRetrofit(){
       return new Retrofit.Builder()
                .baseUrl(YoutubeConfig.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


    }
}
