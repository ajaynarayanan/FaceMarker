package com.facemarker.customlistview.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import com.facemarker.R;
import com.facemarker.customlistview.models.UserInfo;

import java.util.ArrayList;

public class CustomListAdapter extends BaseAdapter implements View.OnClickListener{
    private ArrayList<UserInfo> userInfos;
    private Context context;

    public CustomListAdapter(ArrayList<UserInfo> userInfos, Context context) {
        this.userInfos = userInfos;
        this.context = context;
    }

    @Override
    public int getCount() {
        return userInfos.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=layoutInflater.inflate(R.layout.custom_list_view_layout,null);
        ImageView photo,option;
        if(view==null){
            photo=new ImageView(context);
        }
        UserInfo userInfo=userInfos.get(i);
        photo=(ImageView)view.findViewById(R.id.photo);
        TextView name=(TextView)view.findViewById(R.id.name);
        TextView profession=(TextView)view.findViewById(R.id.profession);
        //photo.setImageResource(userInfo.getPhoto());
        Glide.with(this.context)
                .load(userInfo.getPhoto())
                .override(60, 60)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(photo);
        name.setText(userInfo.getName());
        profession.setText(userInfo.getProfession());
        return view;
    }

    @Override
    public void onClick(View view) {
    }
//    Two dots in the layout
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.option:
//                showPopupMenu(view);
//                break;
//        }
//    }

    // getting the popup menu
    private void showPopupMenu(View view){
        PopupMenu popupMenu=new PopupMenu(context,view);
        popupMenu.getMenuInflater().inflate(R.menu.option_menu,popupMenu.getMenu());
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.edit:
                        Toast.makeText(context, "Edit !", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.remove:
                        Toast.makeText(context, "Remove !", Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    //file search result
    public void filterResult(ArrayList<UserInfo> newUserInfos){
        userInfos=new ArrayList<>();
        userInfos.addAll(newUserInfos);
        notifyDataSetChanged();
    }
}
