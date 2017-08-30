package com.zack.tinga.satvguide.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.zack.tinga.satvguide.R;
import com.zack.tinga.satvguide.classes.TVClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/08/14.
 */

public class Etv_Fragment extends Fragment {

    public RecyclerView recyclerView;
    public ListView listView;
    private List<TVClass> tvClass;

    Activity context;

    public DatabaseReference mDatabaseReference;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.global_list, container, false);

        tvClass = new ArrayList<>();

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("e_tv");
        recyclerView = (RecyclerView) rootView.findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<TVClass, tvShowsAdapter>  firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<TVClass, tvShowsAdapter>(
                TVClass.class,
                R.layout.list_layout,
                tvShowsAdapter.class,
                mDatabaseReference
        ) {
            @Override
            protected void populateViewHolder(tvShowsAdapter viewHolder, TVClass model, int position) {
                final String key = getRef(position).getKey();
                viewHolder.setName(model.getName());
                viewHolder.setTime(model.getTime());
                viewHolder.setImg(model.getImg(), getActivity());

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), "Key: " + key, Toast.LENGTH_SHORT).show();

                        View mView = LayoutInflater.from(getActivity()).inflate(R.layout.activity_view, null);
                        AlertDialog.Builder aBuilder = new AlertDialog.Builder(getActivity(), android.R.style.Theme_Wallpaper_NoTitleBar_Fullscreen);
                        aBuilder.setView(mView);
                        aBuilder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        AlertDialog alert = aBuilder.create();
                        alert.show();

                    }
                });
            }
        };

        recyclerView.setAdapter(firebaseRecyclerAdapter);

    }

    private static class tvShowsAdapter extends RecyclerView.ViewHolder{

        private TextView textViewName, textViewTime;
        private ImageView imageView_img;
        View mView;

        public tvShowsAdapter(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setName(String name) {
            textViewName = (TextView) mView.findViewById(R.id.tv_show_name);
            textViewName.setText(name);
        }

        public void setTime(String time) {
            textViewTime = (TextView) mView.findViewById(R.id.tv_show_time);
            textViewTime.setText(time);
        }

        public void setImg(String img, Context c) {
            imageView_img = (ImageView) mView.findViewById(R.id.imageView_img);
            Picasso.with(c).load(img).into(imageView_img);
        }
    }
}
