package aleksandrofreire.cursoandroid.youtube.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import aleksandrofreire.cursoandroid.youtube.R;
import aleksandrofreire.cursoandroid.youtube.helper.YoutubeConfig;

public class PlayerActivity extends YouTubeBaseActivity
        implements YouTubePlayer.OnInitializedListener{

    private YouTubePlayerView youTubePlayerView;
    private String idVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        youTubePlayerView = findViewById(R.id.playerVideo);

        //Recuperar o id do vídeo usando um Bundle
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            idVideo = bundle.getString("idVideo");
            //Agora vamos inicializar o play do youtube
            youTubePlayerView.initialize(YoutubeConfig.CHAVE_YOUTUBE_API,this);
        }

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

        youTubePlayer.setFullscreen(true);
        youTubePlayer.setShowFullscreenButton(false);
        youTubePlayer.loadVideo(idVideo);

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }
}
