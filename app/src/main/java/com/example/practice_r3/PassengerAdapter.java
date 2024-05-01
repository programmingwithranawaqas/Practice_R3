package com.example.practice_r3;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

public class PassengerAdapter extends ArrayAdapter<Passenger> {

    PassengerClicked parentActivity;
    Context c;

    public interface PassengerClicked{
        public void deletePassenger(int index);
    }


    public PassengerAdapter(Context context, ArrayList<Passenger> list)
    {
        super(context, 0, list);
        parentActivity = (PassengerClicked) context;
        c = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        if(v == null)
        {
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.single_passenger_listitem_design, parent, false);
        }

        ImageView ivPreference, ivEdit;
        TextView tvName, tvPhone;

        tvName = v.findViewById(R.id.tvName);
        tvPhone = v.findViewById(R.id.tvPhone);
        ivPreference = v.findViewById(R.id.ivPreference);
        ivEdit = v.findViewById(R.id.ivEdit);

        Passenger p = getItem(position);

        tvName.setText(p.getName());
        tvPhone.setText(p.getPhone());

        if(p.getPreference().equals("bus"))
        {
            ivPreference.setImageResource(R.drawable.ic_bus);
        }
        else if(p.getPreference().equals("plane"))
        {
            ivPreference.setImageResource(R.drawable.ic_plane);
        }
        else if(p.getPreference().equals("train"))
        {
            ivPreference.setImageResource(R.drawable.ic_train);
        }



        v.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                AlertDialog.Builder deleteDialog = new AlertDialog.Builder(c);
                deleteDialog.setTitle("Confirmation");
                deleteDialog.setMessage("Do you want to delete "+p.getName()+"?");
                deleteDialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        parentActivity.deletePassenger(position);
                    }
                });

                deleteDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //parentActivity.deletePassenger(position);
                    }
                });
                deleteDialog.show();



                return false;
            }
        });

        return v;


    }
}
