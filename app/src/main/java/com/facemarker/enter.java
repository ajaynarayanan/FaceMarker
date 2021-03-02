package com.facemarker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.facemarker.customlistview.activities.MainActivity2;
import com.facemarker.display.display;
import com.facemarker.scan.Classifier;
import com.facemarker.scan.MainActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class enter extends AppCompatActivity {

    public AlertDialog editDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        findViewById(R.id.scan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                view.getContext().startActivity(intent);}
        });
        findViewById(R.id.display).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity2.class);
                view.getContext().startActivity(intent);}
        });


        // For changing the threshold value
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_edittext, null);

        // Find the editText and set input to only numbers
        EditText editText = dialogView.findViewById(R.id.edit_text);
//        editText.setInputType(InputType.TYPE_CLASS_NUMBER |
//                InputType.TYPE_NUMBER_FLAG_DECIMAL |
//                InputType.TYPE_NUMBER_FLAG_SIGNED);
        editText.setText("0.25");

        editDialog = new AlertDialog.Builder(this)
                .setTitle("Threshold")
                .setMessage("Enter threshold value")
                .setView(dialogView)
                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String text = editText.getText().toString();
                        Classifier.threshold = Double.valueOf(text);
                        Toast.makeText(getBaseContext(), "Threshold set to " + Classifier.threshold.toString(), Toast.LENGTH_LONG).show();
                    }
                })
                .create();


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)){
            editDialog.show();
        }
        return true;
    }

}
