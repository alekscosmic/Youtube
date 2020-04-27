package aleksandrofreire.cursoandroid.youtube.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

import aleksandrofreire.cursoandroid.youtube.R;
import aleksandrofreire.cursoandroid.youtube.adapter.AdapterVideo;
import aleksandrofreire.cursoandroid.youtube.api.YoutubeService;
import aleksandrofreire.cursoandroid.youtube.helper.RetrofitConfig;
import aleksandrofreire.cursoandroid.youtube.helper.YoutubeConfig;
import aleksandrofreire.cursoandroid.youtube.model.Item;
import aleksandrofreire.cursoandroid.youtube.model.Resultado;
import aleksandrofreire.cursoandroid.youtube.model.Video;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    //Widgets
    private RecyclerView recyclerVideos;
    private MaterialSearchView searchView;

    private List<Item> videos = new ArrayList<>();
    private Resultado resultado;

    private AdapterVideo adapterVideo;

    //Retrofit
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializar componentes
        recyclerVideos = findViewById(R.id.recyclerVideos);
        searchView = findViewById(R.id.serchView);

        //Configurações iniciais
        retrofit = RetrofitConfig.getRetrofit();

        //Configura toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Youtube");
        setSupportActionBar( toolbar );

        //Recupera Vídeos
        recuperarVideos();

        //Configura métodos para SearchView
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {

            }
        });

    }

    private void recuperarVideos(){

        YoutubeService youtubeService = retrofit.create(YoutubeService.class);

        youtubeService.recuperarVideos(
            "snippet","date","20",
                YoutubeConfig.CHAVE_YOUTUBE_API,YoutubeConfig.CANAL_ID

        ).enqueue(new Callback<Resultado>() {
            @Override
            public void onResponse(Call<Resultado> call, Response<Resultado> response) {
                //Log.d("resultado","resultado "+response.toString());
                if(response.isSuccessful()){
                   resultado = response.body();
                   videos = resultado.items;
                   configurarRecyclerView();
                   //Log.d("resultado","resultado "+resultado.items.get(0).id.videoId);
                }


            }

            @Override
            public void onFailure(Call<Resultado> call, Throwable t) {

            }
        });

    }
    public void configurarRecyclerView(){

        adapterVideo = new AdapterVideo(videos, this);
        recyclerVideos.setHasFixedSize( true );
        recyclerVideos.setLayoutManager(new LinearLayoutManager(this));
        //Configurar o adapter
        recyclerVideos.setAdapter( adapterVideo );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        MenuItem item = menu.findItem(R.id.menu_search);
        searchView.setMenuItem(item);

        return true;
    }

}

