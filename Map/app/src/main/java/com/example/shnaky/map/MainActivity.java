package com.example.shnaky.map;

import android.location.Location;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends ActionBarActivity {
    LinearLayout ll;
    int[] img_src;
    String[] values;
    GoogleMap googleMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar((Toolbar)findViewById(R.id.actionbar));
        //initial map
        createMapView();
        populateiews();



    }

    void populateiews() {
        ll = (LinearLayout) findViewById(R.id.ll);

        img_src = new int[11];
        values = new String[11];

        setvalues();

        // Creating a new RelativeLayout
        RelativeLayout relativeLayout = new RelativeLayout(this);

        int id = R.id.tvhello;

        for (int i = 0; i < values.length; i++) {
            if (i > 0) {
                // Creating a new TextView
                ImageView img = new ImageView(this);
                img.setImageResource(img_src[i]);
                img.setId(i);
                // Defining the layout parameters of the ImageView
                RelativeLayout.LayoutParams img_lp = new RelativeLayout.LayoutParams(80, 80);
                img_lp.setMargins(0, 10, 0, 0);
                img_lp.addRule(RelativeLayout.BELOW, id);
                // Setting the parameters on the ImageView
                img.setLayoutParams(img_lp);
                id = img.getId();
                // Adding the ImageView to the RelativeLayout as a child
                relativeLayout.addView(img);

                // Creating a new TextView
                TextView tv = new TextView(this);
                tv.setText("" + values[i]);
                tv.setTextSize(20);
                // Defining the layout parameters of the TextView
                RelativeLayout.LayoutParams tv_lp = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
                tv_lp.setMargins(5, 10, 0, 0);
                tv_lp.addRule(RelativeLayout.RIGHT_OF, id);

                tv_lp.addRule(RelativeLayout.ALIGN_TOP, id);
                // Setting the parameters on the TextView
                tv.setLayoutParams(tv_lp);
                // Adding the TextView to the RelativeLayout as a child
                relativeLayout.addView(tv);

                // Adding CheckBox
                CheckBox cb = new CheckBox(this);
                cb.setWidth(50);
                RelativeLayout.LayoutParams cb_lp = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

                cb_lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                cb_lp.setMargins(0, 10, 10, 0);
                cb_lp.addRule(RelativeLayout.ALIGN_BOTTOM, id);

                cb.setLayoutParams(cb_lp);

                relativeLayout.addView(cb);
            }
        }

        ll.addView(relativeLayout);
    }


    void setvalues() {

        img_src[1] = R.drawable.on;
        img_src[2] = R.drawable.on;
        img_src[3] = R.drawable.on;
        img_src[4] = R.drawable.on;
        img_src[5] = R.drawable.on;
        img_src[6] = R.drawable.on;
        img_src[7] = R.drawable.on;
        img_src[8] = R.drawable.on;
        img_src[9] = R.drawable.on;
        img_src[10] = R.drawable.on;


        values[1] = "Shopping Mall";
        values[2] = "Park";
        values[3] = "Hospital";
        values[4] = "Bar";
        values[5] = "Bank";
        values[6] = "ATM";
        values[7] = "Movie Theater";
        values[8] = "Night Club";
        values[9] = "Resturant";
        values[10] = "Grocery or Supermarket";

    }


    /**
     * Initialises the mapview
     */
    private void createMapView() {
        try {
            if (null == googleMap) {
                googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                        R.id.mapView)).getMap();
                googleMap.setMyLocationEnabled(true);

                if (null != googleMap) {
                    googleMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
                        @Override
                        public void onMyLocationChange(Location arg0) {
                            // TODO Auto-generated method stub
                            googleMap.addMarker(new MarkerOptions().position(new LatLng(arg0.getLatitude(), arg0.getLongitude())));
                        }
                    });
                }
            }
        } catch (NullPointerException exception) {
            Toast.makeText(getApplicationContext(),
                    "Error creating map", Toast.LENGTH_SHORT).show();
        }
    }
}