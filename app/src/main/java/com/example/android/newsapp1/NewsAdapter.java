package com.example.android.newsapp1;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NewsAdapter extends ArrayAdapter<News> {
    private static final String DATE_SEPARATOR = "T";

    /**
     * @param NewsAdapter The current context. Used to inflate the layout file.
     * @param news        A List of AndroidFlavor objects to display in a list
     */
    public NewsAdapter(Activity NewsAdapter, ArrayList<News> news) {

        // Initializing the ArrayAdapter's internal storage for the context and the list.
        super(NewsAdapter, 0, news);
    }


    /**
     * Provides a view for an AdapterView
     *
     * @param position    The position in the list of data that should be displayed in the
     *                    list item view.
     * @param convertView The recycled view to populate.
     * @param parent      The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_item, parent, false);
        }


        // Get the {@link AndroidFlavor} object located at this position in the list
        News currentNews = getItem(position);


        // Find the TextView with view ID magazine
        TextView magazineView = (TextView) listItemView.findViewById(R.id.magazine);
        // Display the section name of the article
        magazineView.setText(currentNews.getMagazine());

        // Find the TextView with view ID title
        TextView titleView = (TextView) listItemView.findViewById(R.id.title);
        // Display the location of the current title in that TextView
        titleView.setText(currentNews.getTitle());


        // Create a new Date object
        String originalDate = currentNews.getDate();
        String date;
        String time;

        if (originalDate.contains(DATE_SEPARATOR)) {
            String[] parts = originalDate.split(DATE_SEPARATOR);
            date = parts[0];
            time = parts[1];
        } else {
            date = getContext().getString(R.string.recent);
            time = getContext().getString(R.string.unknown);
        }

        // Find the TextView with view ID date
        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        // Display the date of the current news publish in that TextView
        dateView.setText(date);


        // Find the TextView with view ID time
        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        // Display the time of the current news publish in that TextView
        timeView.setText(time);

        return listItemView;

    }

}



