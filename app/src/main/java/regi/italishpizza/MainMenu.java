package regi.italishpizza;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.CardView;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class MainMenu extends Activity implements View.OnClickListener{

    TextView tv_username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString("CURRENTUSER","");

        tv_username = (TextView) findViewById(R.id.TV_username);
        tv_username.setText(name);

        tv_username.setOnTouchListener(new View.OnTouchListener() {
            private GestureDetector gestureDetector = new GestureDetector(MainMenu.this, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onDoubleTap(MotionEvent e) {
                    createLogoutPopup();
                    return super.onDoubleTap(e);
                }
            });

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return true;
            }
        });






        LinearLayout gl = (LinearLayout) findViewById(R.id.MainLinearLayout);
        List<String> menuChoices = Arrays.asList("Management", "Order", "Inventory", "Bookings");




        for (int i = 0; i < menuChoices.size(); i++) {
            CardView cv = new CardView(this);

            cv.setRadius(4);

            ActionBar.LayoutParams lp = new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);

            final float scale = getResources().getDisplayMetrics().density;
            lp.width = (int) (200 * scale + 0.5f);
            lp.height = (int) (200 * scale + 0.5f);
            int margin = (int) (8 * scale + 0.5f);
            lp.setMargins(margin, margin, margin, margin);
            cv.setLayoutParams(lp);


            TextView tv = new TextView(this);
            tv.setText(menuChoices.get(i));
            tv.setGravity(Gravity.CENTER);
            tv.setTextColor(Color.BLACK);

            cv.addView(tv);

            cv.setTag(menuChoices.get(i));
            cv.setOnClickListener(this);
            gl.addView(cv);


            View v = new View(getApplicationContext());

            ActionBar.LayoutParams lp2 = new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
            lp2.width = (int) (80 * scale + 0.5f);
            lp2.height = (int) (175 * scale + 0.5f);

            v.setLayoutParams(lp2);

            gl.addView(v);


        }
    }


    public void createLogoutPopup(){
        new AlertDialog.Builder(this)
                .setTitle("Delete entry")
                .setMessage("Are you sure you want to delete this entry?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainMenu.this);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.remove("CURRENTUSER");
                        editor.apply();
                        Intent x = new Intent(MainMenu.this, LoginActivity.class);
                        startActivity(x);
                        finish();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"NO",Toast.LENGTH_LONG).show();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }



    public void onClick(View view) {
        Intent x = new Intent(MainMenu.this, Order.class);
        startActivity(x);
    }
}
