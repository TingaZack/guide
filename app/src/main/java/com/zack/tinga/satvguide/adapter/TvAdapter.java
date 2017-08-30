package com.zack.tinga.satvguide.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.zack.tinga.satvguide.R;
import com.zack.tinga.satvguide.classes.TVClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/08/14.
 */

public class TvAdapter extends ArrayAdapter<TVClass> {

    private Activity context;
    private List<TVClass> tvClassList;

    public TvAdapter(@NonNull Activity context, ArrayList<TVClass> tvClassList) {
        super(context, R.layout.list_layout, tvClassList);
        this.tvClassList = tvClassList;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View tvShowList = inflater.inflate(R.layout.list_layout, null, false);

        TextView textViewName = (TextView) tvShowList.findViewById(R.id.tv_show_name);
//        TextView textViewRating = skillsViewItems.findViewById(R.id.textViewRating);

        TVClass tvClass = tvClassList.get(position);
        textViewName.setText(tvClass.getName());
//        textViewRating.setText(String.valueOf(skill.getHobbieRating()));

        return tvShowList;
    }
}
