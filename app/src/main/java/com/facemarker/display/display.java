package com.facemarker.display;

import androidx.appcompat.app.AppCompatActivity;
import com.facemarker.scan.MainActivity;
import com.facemarker.R;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class display extends AppCompatActivity {

    ArrayList<String> animalsNameList;
    private String fromEmail = "test@gmail.com";
    private String fromPassword = "test";
    private List<String> toEmailList = Arrays.asList("test@gmail.com");
    private String emailSubject;
    private String emailBody;

//    private RecyclerView recyclerView;
//    private RecyclerView.Adapter adapter;
//    private RecyclerView.LayoutManager layoutManager;
//    private ArrayList<String> planetList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
//        planetList = new ArrayList<String>(MainActivity.detected_names);
//        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
//        layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//
//        adapter = new StudentAdapter(planetList,getApplicationContext());
//        recyclerView.setAdapter(adapter);

        ListView animalList=(ListView)findViewById(R.id.listViewAnimals);
        animalsNameList = new ArrayList<String>(MainActivity.detected_names);
        // Create The Adapter with passing ArrayList as 3rd parameter
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, animalsNameList){

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View view =super.getView(position, convertView, parent);

                        TextView textView=(TextView) view.findViewById(android.R.id.text1);

                        /*YOUR CHOICE OF COLOR*/
                        textView.setTextColor(Color.WHITE);
//                        textView.setBackgroundResource(R.color.textColor);
                        textView.setTypeface(null, Typeface.BOLD);
                        textView.setAllCaps(true);

                        return view;
                    }
                };
        // Set The Adapter
        animalList.setAdapter(arrayAdapter);

        // register onClickListener to handle click events on each item
        animalList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            // argument position gives the index of item which is clicked
            public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3)
            {

                String selectedAnimal=animalsNameList.get(position);
                Toast.makeText(getApplicationContext(), "Student selected : "+selectedAnimal,   Toast.LENGTH_LONG).show();
            }
        });

        // Generate the content of the body
        parseContent();
        ImageButton send_mail = (ImageButton) findViewById(R.id.send_mail);
        send_mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new SendMailTask(display.this).execute(fromEmail,
                        fromPassword, toEmailList, emailSubject, emailBody);

            }
        });

    }

    public void parseContent()
    {
        emailSubject = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        emailSubject = " Attendance for " + emailSubject;
        emailBody = "Roll Number \t Name";

    }
}
