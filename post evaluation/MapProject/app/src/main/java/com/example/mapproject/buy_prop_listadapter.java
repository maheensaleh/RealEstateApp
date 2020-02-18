package com.example.mapproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import java.util.ArrayList;

public class buy_prop_listadapter  extends ArrayAdapter<property> {
    private Context mcontext;
    int mresource;

    private  static  class ViewHolder{
        ImageView img;
        TextView addr;
    }

    /**
     * Constructor
     *  @param context  The current context.
     * @param resource The resource ID for a layout file containing a TextView to use when*/
    public buy_prop_listadapter(@NonNull FragmentActivity context, int resource, ArrayList<property>objects) {

        super(context, resource,objects);
        mcontext = context;
        mresource = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        byte[] img = getItem(position).getImg();
        String addr = getItem(position).getAddr();
        property prop = new property(img,addr);
        View result;
        ViewHolder holder;
        if (convertView == null){
            LayoutInflater layoutInflater = LayoutInflater.from(mcontext);
            convertView = layoutInflater.inflate(mresource,parent,false);
            holder = new ViewHolder();
//            holder2 = new  ViewHolder();
            holder.img = (ImageView) convertView.findViewById(R.id.AApic1);
            holder.addr = (TextView) convertView.findViewById(R.id.AAaddress);

            result = convertView;
            convertView.setTag(holder);
//            convertView.setTag(holder2);

        }

        else {
            holder = (ViewHolder) convertView.getTag();
//            holder2 = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        System.out.println("setting images in list view");

        Bitmap b = BitmapFactory.decodeByteArray(prop.getImg(),0,prop.getImg().length);
        holder.img.setImageBitmap(Bitmap.createScaledBitmap(b, 450, 450, false));
        holder.addr.setText(prop.getAddr());
        return convertView;
    }
}
