package com.example.firebasecrud;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    ArrayList<Bus> list = new ArrayList<>();

    public RVAdapter(Context ctx){
        this.context = ctx;

    }

    public void setItems(ArrayList<Bus> bs){
        list.addAll(bs);
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item,parent,false);
        return new BusVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BusVH vh = (BusVH) holder;
        Bus bs = list.get(position);
        vh.txt_whereto.setText(bs.getWhereto());
        vh.txt_type.setText(bs.getType());
        vh.txt_count.setText(bs.getCount());
        vh.txt_option.setOnClickListener(v->{
            PopupMenu popupMenu = new PopupMenu(context,vh.txt_option);
            popupMenu.inflate(R.menu.option_menu);
            popupMenu.setOnMenuItemClickListener(item -> {
                switch (item.getItemId())
                {
                    case R.id.menu_edit:
                        Intent intent = new Intent(context,MainActivity.class);
                        intent.putExtra("Edit", (Parcelable) bs);
                        context.startActivity(intent);
                        break;
                    case R.id.menu_remove:
                        DAOBus dao = new DAOBus();
                        dao.remove(bs.getKey()).addOnSuccessListener(suc->{
                            //Toast.makeText(this,"record is removed",Toast.LENGTH_SHORT).show();
                            notifyItemRemoved(position);
                        }).addOnFailureListener(er->{
                            //Toast.makeText(this,""+er.getMessage(),Toast.LENGTH_SHORT);
                        });
                        break;
                }
                return false;
            });
            popupMenu.show();
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
