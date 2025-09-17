package com.example.appbasic;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    List<User> listeUser = new ArrayList<User>();
    List<User> fullUserList;

    public MyAdapter(List<User> listeUser) {
        this.listeUser = listeUser;
        this.fullUserList = new ArrayList<>(listeUser);
    }

    public void setListeUser(List<User> listeUser) {
        this.listeUser = listeUser;
        notifyDataSetChanged();
        this.fullUserList = new ArrayList<>(listeUser);
    }


    public  void filter(String text){
        listeUser.clear();

        if (text.isEmpty()){
            listeUser.addAll(fullUserList);
        }
        else {
            for (User user : fullUserList){
                if ( user.getPrenom().toLowerCase().contains(text) || user.getNom().toLowerCase().contains(text)){
                    listeUser.add(user);
                }
            }
        }
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_contact_layout,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        User user = listeUser.get(position);
        holder.image.setImageResource(user.getImage());
        holder.firsname.setText(user.getPrenom());
        holder.lastname.setText(user.getNom());
        holder.email.setText(user.getEmail());
        int userId = user.getUid();
        holder.imageButton.setOnClickListener(view -> {
            showMenu(view,position);

        });
        holder.linearLayout.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(),AddOrEditActivity.class);
            intent.putExtra("item",userId);
            view.getContext().startActivity(intent);
        });
//        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                showMenu(view,position);
//                return false;
//            }
//        });

    }

    private void showMenu(View view, int position) {
        PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
        popupMenu.getMenuInflater().inflate(R.menu.context_menu,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if(menuItem.getItemId() == R.id.viewItem){
                    Intent intent = new Intent(view.getContext(),AddOrEditActivity.class);
                    intent.putExtra("item",position);
                    view.getContext().startActivity(intent);
                }else if (menuItem.getItemId() == R.id.editItem){
                    Intent intent = new Intent(view.getContext(),AddOrEditActivity.class);
                    intent.putExtra("item",position);
                    view.getContext().startActivity(intent);

                }else if (menuItem.getItemId() == R.id.deleteItem){
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setTitle("Confirmation");
                    builder.setMessage("Voulez-vous vraiment supprimer "+listeUser.get(position).getNom()+" "+listeUser.get(position).getPrenom());
                    builder.setCancelable(false);
                    builder.setPositiveButton("Yes", (dialogInterface, i) -> {

                    });
                    builder.setNegativeButton("No", (dialogInterface, i) ->
                            dialogInterface.dismiss());
                    AlertDialog alertDialog = builder.create();

                    alertDialog.show();

                }
                return false;
            }
        });
        popupMenu.show();

    }

    @Override
    public int getItemCount() {
        return listeUser.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageButton imageButton;
        TextView firsname,lastname,email;
        ImageView image;
        LinearLayout linearLayout;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            firsname = itemView.findViewById(R.id.firstnameContact);
            lastname = itemView.findViewById(R.id.lastnameContact);
            email = itemView.findViewById(R.id.emailContact);
            image = itemView.findViewById(R.id.imageConctact);
            imageButton = itemView.findViewById(R.id.menuMore);
            linearLayout = itemView.findViewById(R.id.linearContact);
        }
    }
}
