package rrdl.com.uptown;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.SearchView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class adapter extends RecyclerView.Adapter<adapter.ViewHolder> implements Filterable {
    private static final int HEADER_VIEW = 0;
    private static final int CONTENT_VIEW = 1;
    private List<Trip> response;
    private List<Trip> responsefiltered;
    private Context mContext;
    private ArrayList<Trip> responsecopy;


    @SuppressLint("NewApi")
    public adapter(Context context, List<Trip> body, SearchView sv) {
        mContext = context;
        this.response = body;
        responsefiltered=response;
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                getFilter().filter(s);
                return false;
            }
        });


    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charstring = charSequence.toString();
                if (charstring.isEmpty()) {
                    responsefiltered.clear();
                    responsefiltered = response;

                } else {
                    List<Trip> filteredlist = new ArrayList<>();
                    for (Trip row : response) {
                        if (row.getDr().toString().toLowerCase().contains(charstring.toLowerCase())) {
                            filteredlist.add(row);
                        }
                    }
                    responsefiltered = filteredlist;

                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = responsefiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                responsefiltered = (ArrayList<Trip>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

        return new adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder._date.setText(responsefiltered.get(position).getDate()
                .substring(0, Math.min(responsefiltered.get(position).getDate().length(), 10)));
        holder._type.setText(responsefiltered.get(position).getTitle());
        // responsefiltered.get(positi ).getDr()
        holder._source.setText(responsefiltered
                .get(position).getPrice() + " dt , " + responsefiltered.get(position).getPlaces()+" Places");

        Glide.with(mContext).load("http://lorempixel.com/640/480/city")
                .override(75, 75).diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(holder._thumb);



    }

    @Override
    public int getItemCount() {
        return responsefiltered.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView _date, _type, _source;
        private ImageView _thumb, _star;

        public ViewHolder(final View itemView) {
            super(itemView);
            _date = itemView.findViewById(R.id.date);
            _type = itemView.findViewById(R.id.type);
            _source = itemView.findViewById(R.id.source);
            _thumb = itemView.findViewById(R.id.documentthumb);
            _star = itemView.findViewById(R.id.star);
        }
    }
}