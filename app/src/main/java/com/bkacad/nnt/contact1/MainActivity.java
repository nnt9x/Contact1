package com.bkacad.nnt.contact1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvContact;
    // Dữ liệu cho Listview
    private List<String> dataContact;
//    private ArrayAdapter<String> myAdapter;
    private MyAdapter myAdapter;
    private ContactDialog contactDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvContact = findViewById(R.id.lvContact);
        // Fake dữ liệu
        dataContact = new ArrayList<>();
        dataContact.add("Contact 1 - 0918.xxx.xxx");
        dataContact.add("Contact 2 - 0918.xxx.xxx");
        dataContact.add("Contact 3 - 0918.xxx.xxx");
        // Tạo Adapter

//        myAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,dataContact);
        myAdapter = new MyAdapter(this,dataContact);
        // Set Adapter cho listview
        lvContact.setAdapter(myAdapter);
        registerForContextMenu(lvContact);

        // Tạo dialog
        contactDialog = new ContactDialog(this) {
            @Override
            public void sendData(String contact) {
                dataContact.add(contact);
                myAdapter.notifyDataSetChanged();
            }
        };


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        // Link đên view menu
        getMenuInflater().inflate(R.menu.main_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.main_menu_add_contact:
                // Xử lý sau
                contactDialog.show();

                break;
            case R.id.main_menu_count:
                Toast.makeText(MainActivity.this, "Số lượng: " + dataContact.size() , Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }
}