package com.bkacad.nnt.contact1;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    private List<String> dataContact;
    private Context context;

    public MyAdapter(Context context, List<String> dataContact){
        this.dataContact = dataContact;
        this.context = context;
    }

    @Override
    public int getCount() {
        return dataContact.size();
    }

    @Override
    public Object getItem(int position) {
        return dataContact.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_contact,parent,false);
        }
        // Gán dữ liệu
        TextView tvContact = convertView.findViewById(R.id.tvContact);
        tvContact.setText(dataContact.get(position));

        ImageView imgMenu = convertView.findViewById(R.id.imgMenu);

        // Tạo popup menu
        PopupMenu popupMenu = new PopupMenu(context, imgMenu);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());

        imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hiển thị 1 popup menu
                popupMenu.show();
            }
        });

        AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setTitle("Thông báo")
                .setMessage("Bạn có muốn xoá?")
                .setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dataContact.remove(position);
                        notifyDataSetChanged();
                    }
                })
                .setNegativeButton("Huỷ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "Huỷ thay đổi",Toast.LENGTH_SHORT).show();
                    }
                })
                .create();

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.popup_menu_delete:
                        // Hiển thị 1 dialog, hỏi xem có xoá hay ko
                        alertDialog.show();
                        break;
                    case R.id.popup_menu_view:
                        Toast.makeText(context, "Xem chi tiết",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.popup_menu_edit:
                        Toast.makeText(context, "Hiển thị dialog sửa", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });

        return convertView;
    }
}
