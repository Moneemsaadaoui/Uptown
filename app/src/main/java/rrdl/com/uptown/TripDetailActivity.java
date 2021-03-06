package rrdl.com.uptown;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

public class TripDetailActivity extends AppCompatActivity {
    EditText destination,start,place,price,date;
    Trip trp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_detail);
        setTitle("MORE DETAILS");
        Gson gson=new Gson();
        ImageView pic=findViewById(R.id.picture);
        Glide.with(this).load("http://lorempixel.com/640/480/city")
                .override(80, 80)
                .into(pic);
        trp =gson.fromJson(getIntent().getStringExtra("REF"),Trip.class);
        destination=findViewById(R.id.destination2);
        start=findViewById(R.id.start2);
        place=findViewById(R.id.places2);
        price=findViewById(R.id.price2);
        date=findViewById(R.id.date2);

        destination.setText(trp.getTitle());
        start.setText(trp.getStartadress());
        place.setText(trp.getPlaces().toString());
        price.setText(trp.getPrice().toString() + " dt");
        date.setText(trp.getDate());
        destination.setEnabled(false);
        start.setEnabled(false);
        place.setEnabled(false);
        price.setEnabled(false);
        date.setEnabled(false);
    }
}
