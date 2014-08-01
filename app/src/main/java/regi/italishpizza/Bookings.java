package regi.italishpizza;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.graphics.Outline;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import java.util.ArrayList;
import java.util.Calendar;

public class Bookings extends Activity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookings);


        int size = getResources().getDimensionPixelSize(R.dimen.fab_size);
        Outline outline = new Outline();
        outline.setOval(0, 0, size, size);
        findViewById(R.id.fab).setOutline(outline);



        ArrayList image_details = getListData();
        final ListView lv1 = (ListView) findViewById(R.id.custom_list);
        lv1.setAdapter(new BookingCustomListAdapter(this, image_details));
        lv1.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = lv1.getItemAtPosition(position);
                Booking newsData = (Booking) o;
                Toast.makeText(Bookings.this, "Selected :" + " " + newsData, Toast.LENGTH_LONG).show();
            }

        });

    }

    private ArrayList getListData() {
        ArrayList results = new ArrayList();
        Booking newsData = new Booking();
        newsData.setHeadline("Dance of Democracy");
        newsData.setReporterName("Pankaj Gupta");
        newsData.setDate("May 26, 2013, 13:35");
        results.add(newsData);

        newsData = new Booking();
        newsData.setHeadline("Major Naxal attacks in the past");
        newsData.setReporterName("Pankaj Gupta");
        newsData.setDate("May 26, 2013, 13:35");
        results.add(newsData);

        newsData = new Booking();
        newsData.setHeadline("BCCI suspends Gurunath pending inquiry ");
        newsData.setReporterName("Rajiv Chandan");
        newsData.setDate("May 26, 2013, 13:35");
        results.add(newsData);

        newsData = new Booking();
        newsData.setHeadline("Life convict can`t claim freedom after 14 yrs: SC");
        newsData.setReporterName("Pankaj Gupta");
        newsData.setDate("May 26, 2013, 13:35");
        results.add(newsData);

        newsData = new Booking();
        newsData.setHeadline("Indian Army refuses to share info on soldiers mutilated at LoC");
        newsData.setReporterName("Pankaj Gupta");
        newsData.setDate("May 26, 2013, 13:35");
        results.add(newsData);

        newsData = new Booking();
        newsData.setHeadline("French soldier stabbed; link to Woolwich attack being probed");
        newsData.setReporterName("Sudeep Nanda");
        newsData.setDate("May 26, 2013, 13:35");
        results.add(newsData);

        return results;
    }



    public void tappedView(View v) {
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        Toast.makeText(getApplicationContext(), dayOfMonth + "-" + (monthOfYear + 1) + "-" + year, Toast.LENGTH_LONG).show();

                    }
                }, mYear, mMonth, mDay
        );
        dpd.show();
    }


}
