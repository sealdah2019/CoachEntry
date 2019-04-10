package com.example.coachentry;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CoachList extends ArrayAdapter<Coaches> {

    private Activity context;
    private List<Coaches> coachList;

    public CoachList(Activity context, List<Coaches>coachList){
        super(context, R.layout.list_layout, coachList);
        this.context = context;
        this.coachList = coachList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);
        TextView textViewCoach = (TextView) listViewItem.findViewById(R.id.textViewCoach);
        TextView textViewType = (TextView) listViewItem.findViewById(R.id.textViewType);
        TextView textViewRail = (TextView) listViewItem.findViewById(R.id.textViewRail);

        Coaches coaches = coachList.get(position);

        textViewCoach.setText(coaches.getCoachesNumber());
        textViewType.setText(coaches.getCoachesType());
        textViewRail.setText(coaches.getRlycode());


                return listViewItem;

    }


}
