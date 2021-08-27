package loranttoth.dragshapes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;

public class LevelsActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "DRAGSPAHES_PREFS";
    public static final String LEVELS_NAME = "levelsarr";


    private static final int NUM_ROWS = 20;
    private static final int NUM_COLS = 3;

    int[] levelStars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);

        createLevelButtons();
    }

    void createLevelButtons() {

            final LevelsActivity self = this;

            levelStars = new int[NUM_COLS*NUM_ROWS];
            for (int i = 0; i < levelStars.length; i++) {
                levelStars[i] = -1;
            }
            loadLevelsfromSharedPreference();

            TableLayout table = (TableLayout) findViewById(R.id.tbl_lvl);
            int lvl = 1;
            for (int r = 0; r < NUM_ROWS; r++)  {
                TableRow tableRow = new TableRow(this);
                TableLayout.LayoutParams lp =
                        new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                                TableLayout.LayoutParams.WRAP_CONTENT);

                lp.setMargins(10,10,10,10);
                //tableRow.setLayoutParams(lp);
                //table.addView(tableRow, lp);
                table.addView(tableRow);
                for (int c = 0; c < NUM_COLS; c++) {
                    View levelView = getLayoutInflater().inflate(R.layout.level_view, null);
                    TextView tv = (TextView) levelView.findViewWithTag("tv_lvl");
                    tv.setText(lvl+"");
                    tv.setTextColor(levelStars[lvl-1] == -1 ? Color.parseColor("#999999") : Color.parseColor("#000000"));
                    ImageView btn1 = (ImageView) levelView.findViewWithTag("star1");
                    btn1.setVisibility(levelStars[lvl-1] > 0 ? ImageView.VISIBLE : ImageView.INVISIBLE);
                    ImageView btn2 = (ImageView) levelView.findViewWithTag("star2");
                    btn2.setVisibility(levelStars[lvl-1] > 1 ? ImageView.VISIBLE : ImageView.INVISIBLE);
                    ImageView btn3 = (ImageView) levelView.findViewWithTag("star3");
                    btn3.setVisibility(levelStars[lvl-1] > 2 ? ImageView.VISIBLE : ImageView.INVISIBLE);

                    View imview = getLayoutInflater().inflate(R.layout.sample_level_image_view, null);
                    LevelImageView imageView = (LevelImageView) imview.findViewWithTag("levelImage1");
                    imageView.setLevel(lvl-1);
                    LinearLayout layout = (LinearLayout) levelView.findViewWithTag("imageLayout");
                    layout.addView(imview);

                    levelView.setTag(lvl+"");

                    levelView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //v.setBackgroundColor(Color.BLUE);
                            int indx = Integer.parseInt((String)v.getTag())-1;
                            if (levelStars[indx] < 3) {
                                levelStars[indx]++;
                                int i = levelStars[indx];
                                if (i == 0) {
                                    TextView tv = (TextView) v.findViewWithTag("tv_lvl");
                                    tv.setTextColor(Color.parseColor("#000000"));
                                }
                                if (i > 0 && i < 4) {
                                    ImageView btn1 = (ImageView) v.findViewWithTag("star" + i);
                                    btn1.setVisibility(ImageView.VISIBLE);
                                }
                                writeLevelstoSharedPreference();
                            }
                            Intent intent = new Intent(self, GameActivity.class);
                            Bundle b = new Bundle();
                            b.putInt("level", indx);
                            intent.putExtras(b);
                            startActivity(intent);
                        }
                    });
                    tableRow.addView(levelView);

                    //Button b = new Button(this);
                    //b.setText(lvl+"");
                    //tableRow.addView(b);
                    lvl++;

                }
            }

        }

        void loadLevelsfromSharedPreference() {
            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
            if (settings.contains(LEVELS_NAME)) {
                String s = settings.getString(LEVELS_NAME, "");
                if (s != "") {
                    String[] arr = s.split(",");
                    if (arr.length == levelStars.length) {
                        for (int i = 0; i < arr.length; i++) {
                            try {
                                levelStars[i] = Integer.parseInt(arr[i]);
                            }catch (Exception e ) {}
                        }
                    }
                }
            }

        }

        void writeLevelstoSharedPreference() {
            String s = "";
            for (int i = 0; i < levelStars.length; i++) {
                s+=levelStars[i];
                if ( i < levelStars.length - 1) {
                    s+=",";
                }
            }
            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString(LEVELS_NAME, s);
            editor.commit();
        }


    }
