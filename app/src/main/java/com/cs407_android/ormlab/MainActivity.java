package com.cs407_android.ormlab;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity
    implements calendar.OnFragmentInteractionListener, event.OnFragmentInteractionListener{

    ArrayAdapter adapter;
    Context context;

    public static ArrayList<String> guestList;

    //Uncomment once ready
    DaoMaster.DevOpenHelper guestBookDBHelper;
    SQLiteDatabase guestBookDB;
    DaoMaster daoMaster;
    DaoSession daoSession;
    GuestDao guestDao;
    List<Guest> guestListFromDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instantiate objects
        context = this;
        guestList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, guestList);

        //set up ListView

        initDatabase();
        adapter.notifyDataSetChanged();


        //set up submit button

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container, calendar.newInstance())
                .addToBackStack(null)
                .commit();


    }

    private void initDatabase()
    {
        guestBookDBHelper = new DaoMaster.DevOpenHelper(this, "ORM.sqlite", null);
        guestBookDB = guestBookDBHelper.getWritableDatabase();

        //Get DaoMaster
        daoMaster = new DaoMaster(guestBookDB);

        //Create database and tables
        daoMaster.createAllTables(guestBookDB, true);

        //Create DaoSession
        daoSession = daoMaster.newSession();

        //Create customer addition/removal instances
        guestDao = daoSession.getGuestDao();


        if (guestDao.queryBuilder().where(
            GuestDao.Properties.Display.eq(true)).list() == null)
        {
            closeReopenDatabase();
        }
        guestListFromDB = guestDao.queryBuilder().where(
                GuestDao.Properties.Display.eq(true)).list();

        if (guestListFromDB != null) {

            for (Guest guest : guestListFromDB)
            {
                if (guest == null)
                {
                    return;
                }
                Toast.makeText(context, "Added Guests from Database", Toast.LENGTH_SHORT).show();
                guestList.add(guest.getFirstName() + " " + guest.getLastName());
            }
            adapter.notifyDataSetChanged();
        }
    }


    private void closeDatabase()
    {
        daoSession.clear();
        guestBookDB.close();
        guestBookDBHelper.close();
    }

    private void closeReopenDatabase()
    {

        closeDatabase();

        guestBookDBHelper = new DaoMaster.DevOpenHelper(this, "ORM.sqlite", null);
        guestBookDB = guestBookDBHelper.getWritableDatabase();

        //Get DaoMaster
        daoMaster = new DaoMaster(guestBookDB);

        //Create database and tables
        daoMaster.createAllTables(guestBookDB, true);

        //Create DaoSession
        daoSession = daoMaster.newSession();

        //Create customer addition/removal instances
        guestDao = daoSession.getGuestDao();
    }

    public void onFragmentInteraction(Uri uri){
        //you can leave it empty
    }

}
