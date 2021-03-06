package regi.italishpizza;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class Management extends Activity implements View.OnClickListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management);

        LinearLayout gl = (LinearLayout) findViewById(R.id.ManagementLinearLayout);
        List<String> menuChoices = Arrays.asList("Add Product", "Add User");


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

    public void onClick(View view) {
        Toast.makeText(getApplicationContext(), "Tapped", Toast.LENGTH_LONG).show();
    }


    



}
