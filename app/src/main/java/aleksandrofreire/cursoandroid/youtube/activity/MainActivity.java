package aleksandrofreire.cursoandroid.youtube.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import aleksandrofreire.cursoandroid.youtube.R;
import aleksandrofreire.cursoandroid.youtube.adapter.AdapterVideo;
import aleksandrofreire.cursoandroid.youtube.model.Video;

public class MainActivity extends AppCompatActivity {

    //Widgets
    private RecyclerView recyclerVideos;

    private List<Video> videos = new ArrayList<>();
    private AdapterVideo adapterVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializar componentes
        recyclerVideos = findViewById(R.id.recyclerVideos);


        //Configura toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Youtube");
        setSupportActionBar( toolbar );

        //Configura Recyclerview
        recuperarVideos();
        adapterVideo = new AdapterVideo(videos, this);
        recyclerVideos.setHasFixedSize( true );
        recyclerVideos.setLayoutManager(new LinearLayoutManager(this));
        //Configurar o adapter
        recyclerVideos.setAdapter( adapterVideo );

    }

    private void recuperarVideos(){

        Video video1 = new Video();
        video1.setTitulo("Vídeo 1 muito interessante!");
        videos.add( video1 );

        Video video2 = new Video();
        video2.setTitulo("Vídeo 2 muito interessante!");
        videos.add( video2 );

    }

}

