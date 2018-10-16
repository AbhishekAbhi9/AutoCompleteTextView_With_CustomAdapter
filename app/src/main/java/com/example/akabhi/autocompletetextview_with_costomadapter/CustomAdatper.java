package com.example.akabhi.autocompletetextview_with_costomadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdatper extends ArrayAdapter<Pojo_Data> {

    //You can use this adapter to provide views for an AdapterView,
    //Returns a view for each object in a collection of data objects you provide,
    //and can be used with list-based user interface widgets such as ListView or Spinner.

    private Context context;
    private ArrayList<Pojo_Data> pojo_data;

    public CustomAdatper(@NonNull Context context, @NonNull ArrayList<Pojo_Data> pojo_data) {
        super(context, 0, pojo_data);
        this.context = context;

//      this is important to write new ArrayList<>(search_bar_classes) insted of search_bar_classes because
//     search_bar_classes is equals to search_bar_classes and this make same refrence to each other
//     thus when we remove the item from our search_bar_classes then it also remove from search_bar_classes
        this.pojo_data = new ArrayList<>(pojo_data);
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return filter;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_layout, parent, false);
        }
        TextView textView = convertView.findViewById(R.id.textview);
        Pojo_Data pojo_data = getItem(position);
        if (pojo_data != null) {
            textView.setText(pojo_data.string_data);
        }
        return convertView;
    }

//    A filter constrains data with a filtering pattern.
//    Filters are usually created by Filterable classes.
    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            //FilterResult object add result and return result back
            FilterResults filterResults = new FilterResults();

            //we create arraylist for adding of filter element
            ArrayList<Pojo_Data> newAddedValues = new ArrayList<>();

            //for no typing in autocompletetextview
            //checking if there is type something or not if not then add all the data into arraylist of pojo class

            if (constraint.length() == 0 || constraint == null) {
                pojo_data.addAll(newAddedValues);
            } else {

                 //for typed element in autocompletetextview
                 //else checking the for autocompletetextview and storing inside string
                //now itrating through Search_Bar_Class item and find the matching patter
                //if it is there than add into new Arraylist
                String Filter_Pattern = constraint.toString().toLowerCase().trim();
                for (Pojo_Data pojo_data : pojo_data) {
                    if (constraint.toString().toLowerCase().trim().equals(Filter_Pattern)) {
                        newAddedValues.add(pojo_data);
                    }
                }
            }

            filterResults.values = newAddedValues;
            filterResults.count = newAddedValues.size();
            return filterResults;
        }

    //Invoked in the UI thread to publish the filtering results in the user interface.
    //Subclasses must implement this method to display the results computed in performFiltering(CharSequence).
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            try {
                clear();

                //add filter data to the filter
                addAll((ArrayList) results.values);

                notifyDataSetChanged();
            } catch (NullPointerException n) {
            }
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            //this is used to convert resultvalues into actual result values
            return ((Pojo_Data) resultValue).getString_data();
        }
    };

}
