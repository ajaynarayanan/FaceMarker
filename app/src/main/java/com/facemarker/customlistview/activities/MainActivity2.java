package com.facemarker.customlistview.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import com.facemarker.R;
import com.facemarker.customlistview.adapters.CustomListAdapter;
import com.facemarker.customlistview.models.UserInfo;
import com.facemarker.display.SendMailTask;
import com.facemarker.display.display;
import com.facemarker.scan.MainActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class MainActivity2 extends AppCompatActivity {

    // Mail related attributes
    private String fromEmail = "test@gmail.com";
    private String fromPassword = "test";
    private List<String> toEmailList = Arrays.asList("test@gmail.com");
    private String emailSubject;
    private String emailBody;


    private ArrayList<UserInfo> userInfos;
    private CustomListAdapter customListAdapter;
    private ListView customListView;
    private ArrayList<String> MarkedNames;

    // Mapping names to roll number
    private Map<String, String> mapName2Roll = new HashMap<String, String>() {{
        put("Ajay", "COE16B044");
        put("Babu", "MDM16B030");
        put("Unknown", "Unknown");
    }};


    // Mapping names to roll number
    private Map<String, Integer> mapName2Pic = new HashMap<String, Integer>() {{
        put("Ajay", R.drawable.noicon);
        put("Babu", R.drawable.noicon);
        put("Unknown", R.drawable.noicon);
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Get the list of names from MainActivity
        MarkedNames = new ArrayList<String>(MainActivity.detected_names);

        // CustomListView stuff
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        // Change title of toolbar
        getSupportActionBar().setTitle("Marked Students");

        customListView = (ListView) findViewById(R.id.custom_list_view);
        userInfos = new ArrayList<>();
        customListAdapter = new CustomListAdapter(userInfos, this);
        customListView.setAdapter(customListAdapter);
        getDatas();
        customListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity2.this, "Name : " + MarkedNames.get(i) + "\n Roll Number : " + mapName2Roll.get(MarkedNames.get(i)), Toast.LENGTH_SHORT).show();
            }
        });


        // Parse content for email data
        parseContent();
    }

    // getting all the datas
    private void getDatas() {

        for (String Name : MarkedNames) {
            Integer picId;
            String rollNo;
            // Assign photo based on name
            if(mapName2Pic.containsKey(Name))
                picId = mapName2Pic.get(Name);
            else
                picId = R.drawable.noicon;
            // Assign roll number
            if(mapName2Roll.containsKey(Name))
                rollNo = mapName2Roll.get(Name);
            else
                rollNo = "COE160XX";

            userInfos.add(new UserInfo(Name, rollNo, picId));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_option, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.search) {
            parseContent();
            Log.d("MainActivity2", fromEmail + " " + fromPassword + " " + emailBody +
                " "+ emailSubject );
            new SendMailTask(MainActivity2.this).execute(fromEmail,
                    fromPassword, toEmailList, emailSubject, emailBody);

            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    public void parseContent()
    {
        String newline = System.getProperty("line.separator");
        emailSubject = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        emailSubject = " Attendance for " + emailSubject;
        emailBody = "Name \t\t\t Roll Number" + newline;
        for(UserInfo user : userInfos){
            String RollNo = user.getProfession();
            String Name = user.getName();
            emailBody = emailBody + Name  + "\t\t\t" + RollNo + newline;
        }
    }
}