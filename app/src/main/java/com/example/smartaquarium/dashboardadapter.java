package com.example.smartaquarium;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class dashboardadapter extends RecyclerView.Adapter<dashboardadapter.dashboardholder> {

    private ArrayList<SetterGetter> listdata;

    public dashboardadapter(ArrayList<SetterGetter> listdata){
        this.listdata   =   listdata;
    }
    @NonNull
    @Override
    public dashboardholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view               =   LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dashboard,parent,false);
        dashboardholder holder  =   new dashboardholder(view);
                return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull dashboardholder holder, int position) {

        final SetterGetter getData      = listdata.get(position);
        String titlemenu                = getData.getTitle();
        String logomenu                 =  getData.getImg();

        holder.titleMenu.setText(titlemenu);
        if (logomenu.equals("cleaning")){
            holder.imgMenu.setImageResource(R.drawable.cleaning);
        }else if (logomenu.equals("feed")){
            holder.imgMenu.setImageResource(R.drawable.feed);
        }else if (logomenu.equals("test")){
            holder.imgMenu.setImageResource(R.drawable.test);
        }
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public class dashboardholder extends RecyclerView.ViewHolder {

        TextView titleMenu;
        ImageView imgMenu;

        public dashboardholder(@NonNull View itemView) {
            super(itemView);

            titleMenu   =   itemView.findViewById(R.id.title_menu);
            imgMenu     =   itemView.findViewById(R.id.logo_menu);
        }
    }
}
