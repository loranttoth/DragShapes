package loranttoth.dragshapes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    GameFrame mCanvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shape_game_frame);
        mCanvas = (GameFrame) findViewById(R.id.game_frame);
     //   mCanvas.setParent(this);
    }

    public void showMess(String mess){
        Toast.makeText(getApplicationContext(), mess
                , Toast.LENGTH_LONG).show();

    }
}