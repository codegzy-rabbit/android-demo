package top.codegzy.gridviewexample;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {

    private ArrayList<String> text;
    private ArrayList<Integer> image;
    private Context context;

    public GridAdapter(ArrayList<String> text, ArrayList<Integer> image, Context context) {
        this.text = text;
        this.image = image;
        this.context = context;
    }

    @Override
    public int getCount() {
        return text.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        @SuppressLint("ViewHolder") View gridView = LayoutInflater.from(context).inflate(R.layout.grid_card_view, viewGroup, false);
        TextView textView = gridView.findViewById(R.id.textView);
        ImageView imageView = gridView.findViewById(R.id.imageView);
        textView.setText(text.get(i));
        imageView.setImageResource(image.get(i));
        return gridView;
    }
}
