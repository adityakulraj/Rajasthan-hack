package aarnav100.developer.rajafair;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyArrayAdapter extends ArrayAdapter<String> {
    Context context;
    ArrayList<String> list;

    public MyArrayAdapter(Context context, int lay_id, ArrayList<String> list) {
        super(context, lay_id);
        this.context = context;
        this.list = new ArrayList<>();
        this.list.addAll(list);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final String info = getItem(position);
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = mInflater.inflate(R.layout.place_name, null);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity)context).finish();
            }
        });
        ((TextView)v.findViewById(R.id.name)).setText(info);
        return v;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return mFilter;
    }

    private Filter mFilter = new Filter() {
        @Override
        public String convertResultToString(Object resultValue) {
            return ((String)resultValue);
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null) {
                ArrayList<String> suggestions = new ArrayList<>();
                for (String info : list) {
                    if (info.toLowerCase().contains(constraint.toString().toLowerCase())) {
                        suggestions.add(info);
                    }
                }
                results.values = suggestions;
                results.count = suggestions.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            ArrayList<String> filteredList = (ArrayList<String>) results.values;
            if (results != null && results.count > 0) {
                clear();
                for (String c : filteredList) {
                    add(c);
                }
                notifyDataSetChanged();
            }

        }
    };
}
