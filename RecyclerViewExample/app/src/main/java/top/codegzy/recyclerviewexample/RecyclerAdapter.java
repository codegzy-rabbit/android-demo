package top.codegzy.recyclerviewexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.CountryViewHolder> {

    private ArrayList<String> countryNames;
    private ArrayList<String> countryDetails;
    private ArrayList<Integer> imageViews;
    private Context context;

    public RecyclerAdapter(ArrayList<String> countryNames, ArrayList<String> countryDetails, ArrayList<Integer> imageViews, Context context) {
        this.countryNames = countryNames;
        this.countryDetails = countryDetails;
        this.imageViews = imageViews;
        this.context = context;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        holder.countryName.setText(countryNames.get(position));
        holder.countryDetail.setText(countryDetails.get(position));
        holder.image.setImageResource(imageViews.get(position));
        holder.cardView.setOnClickListener(v -> {
            Toast.makeText(context,  "this was selected", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return countryNames.size();
    }

    class CountryViewHolder extends RecyclerView.ViewHolder {

        private TextView countryName, countryDetail;
        private ImageView image;
        private CardView cardView;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            countryName = itemView.findViewById(R.id.textView);
            countryDetail = itemView.findViewById(R.id.textView2);
            image = itemView.findViewById(R.id.imageView);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
