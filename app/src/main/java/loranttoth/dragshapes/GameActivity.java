package loranttoth.dragshapes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    GameFrame mCanvas;

    int level;

    View view;
    GameFrame gameFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle b = getIntent().getExtras();
        if(b != null) {
            level = b.getInt("level");
        }

        setContentView(R.layout.shape_game_frame);

        view = getLayoutInflater().inflate(R.layout.sample_game_view, null);
        gameFrame = (GameFrame) view.findViewWithTag("gameView");
        gameFrame.setLevel(level);

        LinearLayout layout = (LinearLayout) findViewById(R.id.layoutgame);

        layout.addView(view);

     //   mCanvas.setParent(this);
    }

    public void showMess(String mess){
        Toast.makeText(getApplicationContext(), mess
                , Toast.LENGTH_LONG).show();

    }
}