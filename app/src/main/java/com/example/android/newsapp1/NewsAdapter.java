package com.example.android.newsapp1;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class NewsAdapter extends ArrayAdapter<News> {
    private static final String DATE_SEPARATOR = "T";
    private static final String DATE_END = "Z";

    /**
     * @param NewsAdapter The current context. Used to inflate the layout file.
     * @param news        A List of AndroidFlavor objects to display in a list
     */
    public NewsAdapter(Activity NewsAdapter, ArrayList<News> news) {

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
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_item, parent, false);
        }

        News currentNews = getItem(position);

        TextView sectionView = (TextView) convertView.findViewById(R.id.sectionName);
        sectionView.setText(currentNews.getSectionName());

        TextView titleView = (TextView) convertView.findViewById(R.id.title);
        titleView.setText(currentNews.getTitle());

        TextView authorView = (TextView) convertView.findViewById(R.id.author);
        if (currentNews.hasAuthor()) {
            authorView.setText(currentNews.getAuthor());
            authorView.setVisibility(View.VISIBLE);
        } else {
            authorView.setVisibility(View.GONE);
        }

        String originalDate = currentNews.getDate();
        String date;
        String time;
        if (originalDate.contains(DATE_SEPARATOR)) {
            String[] parts = originalDate.split(DATE_SEPARATOR);
            date = parts[0];
            time = parts[1];
            if (originalDate.contains(DATE_END)) {
                String[] end = time.split(DATE_END);
                time = end[0];
            }

        } else {
            date = getContext().getString(R.string.recent);
            time = getContext().getString(R.string.unknown);
        }

        TextView dateView = (TextView) convertView.findViewById(R.id.date);
        dateView.setText(date);

        TextView timeView = (TextView) convertView.findViewById(R.id.time);
        timeView.setText(time);

        return convertView;
    }
}

