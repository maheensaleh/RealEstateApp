package com.example.mapproject.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mapproject.R;
import com.example.mapproject.model.User;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by lalit on 10/10/2016.
 */

public class UsersRecyclerAdapter extends RecyclerView.Adapter<UsersRecyclerAdapter.UserViewHolder> {

    private List<User> listUsers;

    public UsersRecyclerAdapter(List<User> listUsers) {
        this.listUsers = listUsers;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user_recycler, parent, false);

        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.textViewFirstName.setText(listUsers.get(position).getFirstName());
        holder.textViewEmail.setText(listUsers.get(position).getEmail());
        holder.textViewPassword.setText(listUsers.get(position).getPassword());
        holder.textViewLastName.setText(listUsers.get(position).getLastName());
        holder.textViewCnic.setText(String.valueOf((listUsers.get(position).getCnic())));
        holder.textViewPhone.setText(String.valueOf(listUsers.get(position).getPhone()));

        Bitmap bmp = BitmapFactory.decodeByteArray(listUsers.get(position).getprofile_pic(), 0,listUsers.get(position).getprofile_pic().length);

        if (bmp!=null) {
            holder.CircleImageView.setImageBitmap(bmp);
            System.out.println("here in-------------------------------");

        }
        holder.CircleImageView.setVisibility(View.VISIBLE);


    }

    @Override
    public int getItemCount() {
        Log.v(UsersRecyclerAdapter.class.getSimpleName(),""+listUsers.size());
        return listUsers.size();
    }


    /**
     * ViewHolder class
     */
    public class UserViewHolder extends RecyclerView.ViewHolder {
        public de.hdodenhof.circleimageview.CircleImageView CircleImageView;
        public AppCompatTextView textViewLastName;
        public AppCompatTextView textViewEmail;
        public AppCompatTextView textViewCnic;
        // public AppCompatTextView textViewPh;
        // public AppCompatTextView textViewEmail;

        public AppCompatTextView textViewPassword;
        public AppCompatTextView textViewFirstName;
        //  public AppCompatTextView textViewCnic;
        public AppCompatTextView textViewPhone;


        public UserViewHolder(View view) {
            super(view);
            textViewFirstName = (AppCompatTextView) view.findViewById(R.id.textViewFirstName);
            textViewLastName = (AppCompatTextView) view.findViewById(R.id.textViewLastName);
            textViewCnic = (AppCompatTextView) view.findViewById(R.id.textViewCnic);
            textViewPhone = (AppCompatTextView) view.findViewById(R.id.textViewPhone);
            CircleImageView = (CircleImageView) view.findViewById(R.id.CircleImageView);

            textViewEmail = (AppCompatTextView) view.findViewById(R.id.textViewEmail);
            textViewPassword = (AppCompatTextView) view.findViewById(R.id.textViewPassword);
        }
    }
}
