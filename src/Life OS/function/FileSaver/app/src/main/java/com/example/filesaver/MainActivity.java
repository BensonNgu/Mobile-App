package com.example.filesaver;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button btnOpenDocument;
    Button btnCreateFile;

    EditText etEntry;
    Button btnSaveFile;

    Button btnShowDialog;

    Button btnStopMedia;

    MediaPlayer player;

    public final int OPEN_FILE = 2;
    public final int CREATE_FILE = 1;
    public File selectedFile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        etEntry = findViewById(R.id.etEntry);

        btnOpenDocument = findViewById(R.id.btnOpenDocument);
        btnOpenDocument.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openFile(v);
            }
        });

        btnCreateFile = findViewById(R.id.btnCreateFile);
        btnCreateFile.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                createFile(v);
            }
        });

        btnSaveFile = findViewById(R.id.btnSaveFile);
        btnSaveFile.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                saveFile(v);
            }

        });

        btnShowDialog = (Button) findViewById(R.id.btnShowDialog);
        btnShowDialog.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(MainActivity.this, "User click this button", Toast.LENGTH_LONG).show();
                try {
                    startMedia(v);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        btnStopMedia = (Button) findViewById(R.id.btnStop);

        btnStopMedia.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v){
                stopMedia(v);
            }
        });

    }

    public void openFile(View view){
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/markdown");
        try {
            startActivityForResult(intent, OPEN_FILE);
        }catch(Exception e){
            Toast.makeText(this, e.getMessage() , Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(this, "File is open successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode){
        super.startActivityForResult(intent, requestCode, null);

    }

    public void createFile(View view){
        Uri pickerInitialUri = null;
        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("application/md");
        intent.putExtra(Intent.EXTRA_TITLE, "README.md");

        intent.putExtra(DocumentsContract.EXTRA_INITIAL_URI, pickerInitialUri);
        // get default storage path
        startActivityForResult(intent, CREATE_FILE);
    }

    public void saveFile(View view){
        //open file
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            selectedFile = new File(Environment.DIRECTORY_DOWNLOADS, "README.txt");
        }

        try{
            FileWriter writer = new FileWriter(selectedFile);
            String content = etEntry.getText().toString();
            writer.write(content);
            writer.flush();
            writer.close();
            Toast.makeText(this, "Successfully open the file", Toast.LENGTH_SHORT);
        }catch (IOException e){
            Toast.makeText(this, "Failed to edit the file", Toast.LENGTH_SHORT);
        }

    }

    public void showDialog(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Quit the app?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){
            Log.i("appinfo", "The user click show dialog button");
                finishAffinity();
            }
        });

        builder.setNegativeButton("Cancel",  new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id){
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void showPopUpMenu(View v){
        PopupMenu popupMenu = new PopupMenu(this, v);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
            public boolean onMenuItemClick(MenuItem item){
                int id = item.getItemId();
                if(id==R.id.item1){
                    Snackbar sb = Snackbar.make(v, "Item 1 selected", Snackbar.LENGTH_SHORT);
                    sb.setAction("Click me",new check());
                    sb.show();
                    return true;
                }else if(id==R.id.item2){
                    Log.i("appinfo", "Item 2 selected");
                    return true;
                }else if(id==R.id.item3){
                    Log.i("appinfo", "Item 3 selected");
                    return true;
                }

                return false;
            }
        });
        popupMenu.show();
    }

    public class check implements View.OnClickListener{
        public void onClick(View v){

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public void startMedia(View v) throws IOException {
        String url = "https://youtu.be/FnV5wmmU0JM?si=fDZ1aX8VgaFlYGjK";
        player = new MediaPlayer();

        player.setAudioAttributes(new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build()
        );

        player.setDataSource(url);
        player.prepare();
        player.start();
        Snackbar.make(v,"The media started", Snackbar.LENGTH_SHORT).show();
    }

    public void stopMedia(View v){
        if(player!=null) player.stop();
    }
}