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
    implements calendar.OnFragmentInteractionListener, event.OnFragmentInteractionListener, viewevent.OnFragmentInteractionListener{

    ArrayAdapter adapter;
    Context context;

    public static ArrayList<AnEvent> eventList;

    //Uncomment once ready
    DaoMaster.DevOpenHelper eventDBHelper;
    SQLiteDatabase eventDB;
    DaoMaster daoMaster;
    DaoSession daoSession;
    AnEventDao eventDao;
    List<AnEvent> eventListFromDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instantiate objects
        context = this;
        eventList = new ArrayList<>();
        initDatabase();

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container, calendar.newInstance())
                .addToBackStack(null)
                .commit();


    }

   public void initDatabase()
    {
        eventDBHelper = new DaoMaster.DevOpenHelper(this, "ORM.sqlite", null);
        eventDB = eventDBHelper.getWritableDatabase();

        daoMaster = new DaoMaster(eventDB);

        daoMaster.createAllTables(eventDB, true);


        daoSession = daoMaster.newSession();

        eventDao = daoSession.getAnEventDao();

        if(eventDao.queryBuilder().where(
                AnEventDao.Properties.Display.eq(true)).list() == null){
            closeReopenDatabase();
        }

        eventListFromDB = eventDao.queryBuilder().where(
                AnEventDao.Properties.Display.eq(true)).list();

        if(eventListFromDB != null){
            for(AnEvent event : eventListFromDB){
                if(event == null){
                    return;
                }
                else{
                    AnEvent tmp = new AnEvent(event.getId(), event.getName(), event.getLocation(),event.getDescription(), event.getDay(),
                            event.getMonth(), event.getYear(),event.getDisplay());

                    eventList.add(tmp);
                    Toast.makeText(this, "Database Populated.", Toast.LENGTH_SHORT).show();
                }
            }
        }


    }

    public void addEvent(String name, int day, int month, int year, String start, String end,
                         String location, String description)
    {
        //Generate random Id for Event object to place in database
        Random rand = new Random();
        AnEvent tmp = new AnEvent(rand.nextLong(), name, location,
                description, day, month,
                year,true);
        eventDao.insert(tmp);
        eventList.add(tmp);

        //Close and reopen database to ensure Guest object is saved
        closeReopenDatabase();
        Toast.makeText(this, "Event Added.", Toast.LENGTH_SHORT).show();
    }

    public void deleteEvent(int index){

        AnEvent ed = eventList.get(index);
        eventDao.delete(ed);
        eventList.remove(ed);
        Toast.makeText(this, "Delete Successful.", Toast.LENGTH_SHORT).show();

    }


    private void closeDatabase()
    {
        daoSession.clear();
        eventDB.close();
        eventDBHelper.close();
    }

    public ArrayList<String> getEventList(){
        ArrayList<String> eventStrings = new ArrayList<>();
        for(AnEvent event : eventList){
            eventStrings.add(event.getMonth() + " / " + event.getDay() + " / " + event.getYear()+
                    "\nName: " + event.getName() + "\nTime: " + "\nLocation: " + event.getLocation()
                    + "\nDescription: " +  event.getDescription());
        }
        return eventStrings;
    }

    private void closeReopenDatabase()
    {

        closeDatabase();

        eventDBHelper = new DaoMaster.DevOpenHelper(this, "ORM.sqlite", null);
        eventDB = eventDBHelper.getWritableDatabase();

        //Get DaoMaster
        daoMaster = new DaoMaster(eventDB);

        //Create DaoSession
        daoSession = daoMaster.newSession();

        //Create customer addition/removal instances
        eventDao = daoSession.getAnEventDao();
    }

    public void onFragmentInteraction(Uri uri){
        //you can leave it empty
    }

}
