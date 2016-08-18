package com.supriyalahade.expandable_listview;

import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ExpandListAdapter expandListAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String,List<Data_Details>> listDataChild;


    String[] name_expenses = {"Travel","Fuel","Restaurant","Medical","Groceries","Shopping","Taxes","Loans","Bills","Others"};

    public String date,item_name,cost;

    List<Data_Details> Fuel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expListView = (ExpandableListView)findViewById(R.id.expandableListView);
        Fuel = new ArrayList<Data_Details>();

        prepareListDate();

        expandListAdapter = new ExpandListAdapter(this,listDataHeader,listDataChild);
       // expandListAdapter = new ExpandListAdapter(this,Fuel);


        expListView.setAdapter(expandListAdapter);

        expandListAdapter.notifyDataSetChanged();

    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {



        if (item.getItemId() == R.id.add_id) {
            View view;
            final AlertDialog.Builder itemDialog = new AlertDialog.Builder(this);
            final AlertDialog dialog = itemDialog.create();

            LayoutInflater inflater = LayoutInflater.from(this);

            view = inflater.inflate(R.layout.item_dialog, null);

            TextView itemName = (TextView) view.findViewById(R.id.item_name_textView);
            TextView itemCost = (TextView) view.findViewById(R.id.item_cost_textView);
            TextView itemdate = (TextView) view.findViewById(R.id.date_textView);

            final EditText editItem = (EditText) view.findViewById(R.id.item_editText);
            final EditText editCost = (EditText) view.findViewById(R.id.itemCost_editText);
            final EditText editDate = (EditText) view.findViewById(R.id.date_editText);

            Button addButton = (Button) view.findViewById(R.id.button_add);
            Button cancelButton = (Button) view.findViewById(R.id.button_cancel);

            itemDialog.setTitle("Transactions");
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                     item_name = editItem.getText().toString();
                   date = editDate.getText().toString();
                   cost = editCost.getText().toString();
                    float f = Float.parseFloat(cost);




                    Toast.makeText(MainActivity.this, "Item Saved", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();


                }
            });
            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, "Item Cancelled", Toast.LENGTH_SHORT).show();

                    dialog.dismiss();

                }
            });
            dialog.setView(view);
            dialog.show();

            dialog.setCancelable(false);

        }

        return super.onContextItemSelected(item);

        }


    private void prepareListDate() {

        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<Data_Details>>();



        //Adding Header Data

        for (int i = 0; i < name_expenses.length; i++) {
            listDataHeader.add(name_expenses[i]);
        }

        //Adding dateChild

        List<Data_Details> Travel = new ArrayList<Data_Details>();

        Data_Details d = new Data_Details("1/6/16", "Train", "230.45");
         Data_Details d1 = new Data_Details(date,item_name,cost);

        Travel.add(d);
        Travel.add(d1);//child 1
      //  expandListAdapter.notifyDataSetChanged();



        List<Data_Details> Fuel = new ArrayList<Data_Details>();


       Data_Details d2 = new Data_Details("12/6/16", "Fuel", "123.56");
        Data_Details d3 = new Data_Details(date,item_name,cost);
        Fuel.add(d2);
        Fuel.add(d3);//child 2
      //  expandListAdapter.notifyDataSetChanged();



        List<Data_Details> Restaurant = new ArrayList<Data_Details>();

        Data_Details d4 = new Data_Details(date,item_name,cost);
        Restaurant.add(d4);//child 03
        //expandListAdapter.notifyDataSetChanged();


        listDataChild.put(listDataHeader.get(0), Travel);
        listDataChild.put(listDataHeader.get(1), Fuel);
        listDataChild.put(listDataHeader.get(2), Restaurant);




        /*registerForContextMenu(expListView);
        expandListAdapter.notifyDataSetChanged();*/
    }






}


