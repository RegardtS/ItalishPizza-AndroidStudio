package regi.italishpizza;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;


public class LoginActivity extends Activity implements View.OnClickListener {

    MySQLiteHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Intent x = new Intent(LoginActivity.this, TestActivity.class);
        startActivity(x);



        db = new MySQLiteHelper(this);

        LinearLayout gl = (LinearLayout) findViewById(R.id.LoginLinearLayout);

        db.addStaffMember("Regi", "Pass", "1");
        List<String> staff = db.getAllStaffUsernames();


        for (int i = 0; i < staff.size(); i++) {
            CardView cv = new CardView(this);

            cv.setRadius(4);

            LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

            final float scale = getResources().getDisplayMetrics().density;
            lp.width = (int) (200 * scale + 0.5f);
            lp.height = (int) (200 * scale + 0.5f);
            int margin = (int) (8 * scale + 0.5f);
            lp.setMargins(margin, margin, margin, margin);
            cv.setLayoutParams(lp);


            TextView tv = new TextView(this);
            tv.setText(staff.get(i));
            tv.setGravity(Gravity.CENTER);
            tv.setTextColor(Color.BLACK);

            cv.addView(tv);

            cv.setTag(staff.get(i));
            cv.setOnClickListener(this);
            gl.addView(cv);


            View v = new View(getApplicationContext());

            LayoutParams lp2 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            lp2.width = (int) (80 * scale + 0.5f);
            lp2.height = (int) (175 * scale + 0.5f);

            v.setLayoutParams(lp2);

            gl.addView(v);


        }

    }


    public void onClick(View view) {

        final String viewTag = view.getTag() + "";

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter password for " + viewTag);

        // Set up the input
        final EditText input = new EditText(this);

        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (db.verifyUser(viewTag, input.getText().toString())) {
                    Intent x = new Intent(LoginActivity.this, MainMenu.class);
                    startActivity(x);
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

    }
}
