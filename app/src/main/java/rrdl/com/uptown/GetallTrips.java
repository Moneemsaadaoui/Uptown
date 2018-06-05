package rrdl.com.uptown;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetallTrips {
    private Context mContext;
    private SearchView sv;
    private RecyclerView rv;
    private String token;
    public GetallTrips(Context context,SearchView sv,RecyclerView rv,String token){
        mContext=context;
        this.sv=sv;
        this.rv=rv;
        this.token=token;
    }
    public void getall() {
        final SharedPreferences prefs = mContext.getSharedPreferences("GLOBAL", mContext.MODE_PRIVATE);
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://serene-escarpment-65486.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        final apiservice apiservice = retrofit.create(apiservice.class);
        Call<List<Trip>> getall = apiservice.gettrips(token);
        getall.enqueue(new Callback<List<Trip>>() {
            @Override
            public void onResponse(Call<List<Trip>> call, Response<List<Trip>> response) {
                adapter list=new adapter(mContext,response.body(),sv);
                rv.setAdapter(list);

            }

            @Override
            public void onFailure(Call<List<Trip>> call, Throwable t) {
                Toast.makeText(mContext, "Network Error x(", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
