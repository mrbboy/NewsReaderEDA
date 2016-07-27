package uk.ac.kent.gacc2.newsreadereda;

/**
 * Created by gacc2 on 21/04/16.
 */

import android.app.Fragment;
import android.widget.TextView;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;

public class AboutFragment extends Fragment {
    private TextView desc;
    private TextView contact;

    public AboutFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        desc = (TextView) view.findViewById(R.id.about_desc);
        desc.setText("EDA : European Daily Associaton \n\n" +
                        "The School of Engineering And Digital Arts of the University of Kent\n\n" +
                        "This is a mini project for the module of Android Applicatoin Design of Mobile Application Design course");

        contact = (TextView) view.findViewById(R.id.about_developer);
        contact.setText("Developped by George Christoforou undertaking MSc Mobile Application Design at Kent\n\n" +
                "Contact: gacc2@kent.ac.uk");

        return view;
    }

}

