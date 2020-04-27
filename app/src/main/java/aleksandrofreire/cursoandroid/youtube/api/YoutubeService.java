package aleksandrofreire.cursoandroid.youtube.api;

import aleksandrofreire.cursoandroid.youtube.model.Resultado;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YoutubeService {

    /*
    * A pesquisa que faremos
    *
    * https://www.googleapis.com/youtube/v3/
    * search
    * ?part=snippet
    * &order=date
    * &maxResuls=20
    * &key=AIzaSyB-e8bzqShpcV9vSokwmwz-w65-vZz5Hws
    * &channelId=UCVHFbqXqoYvEWM1Ddxl0QDg
    * &q=desenvolvimento+android
    *
    * URL
    *  https://www.googleapis.com/youtube/v3/search?part=snippet&order=date&maxResuls=20&key=AIzaSyB-e8bzqShpcV9vSokwmwz-w65-vZz5Hws&channelId=UCVHFbqXqoYvEWM1Ddxl0QDg
    */


    @GET("search")
    Call<Resultado> recuperarVideos(
                    @Query("part") String part,
                    @Query("order") String order,
                    @Query("maxResult") String maxResult,
                    @Query("key") String key,
                    @Query("channelId") String channelId,
                    @Query("q") String q
                    //O Restrofit usando o @Query vai montar automaticamente a URL para nos
    );

}
