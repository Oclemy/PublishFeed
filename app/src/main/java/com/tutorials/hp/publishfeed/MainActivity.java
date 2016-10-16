package com.tutorials.hp.publishfeed;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.sromku.simple.fb.SimpleFacebook;
import com.tutorials.hp.publishfeed.mDataObject.MyPost;
import com.tutorials.hp.publishfeed.mFaceBook.LogIn;
import com.tutorials.hp.publishfeed.mFaceBook.MyConfiguration;
import com.tutorials.hp.publishfeed.mPicasso.PicassoClient;


public class MainActivity extends AppCompatActivity {

    EditText nameEditText, msgEditText, descEditText, linkEditText, urlEditText;
    Button sendBtn, loadBtn;
    ImageView img;
    SimpleFacebook fb;
    PicassoClient picassoClient;
    String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        this.initializeFB();
        this.initializeUI();


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayDialog();
            }
        });

        //SEND
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyPost myPost=new MyPost();
                myPost.setName(nameEditText.getText().toString());
                myPost.setMessage(msgEditText.getText().toString());
                myPost.setDescription(descEditText.getText().toString());
                myPost.setImageUrl(picassoClient.getUrl());
                myPost.setLink(linkEditText.getText().toString());

                new LogIn(fb,MainActivity.this,myPost).login();
            }
        });


    }

    //INITIALIZE UI STUFF
    private void initializeUI() {
        nameEditText = (EditText) findViewById(R.id.nameEditTxt);
        msgEditText = (EditText) findViewById(R.id.msgEditTxt);
        urlEditText = (EditText) findViewById(R.id.urlEditTxt);
        descEditText = (EditText) findViewById(R.id.descEditTxt);
        linkEditText = (EditText) findViewById(R.id.linkEditTxt);

        img = (ImageView) findViewById(R.id.movieImage);
        sendBtn = (Button) findViewById(R.id.sendBtn);

        sendBtn.setEnabled(false);

    }

    //DISPLAY DIALOG
    private void displayDialog() {
        Dialog d = new Dialog(this);
        d.setTitle("Load Image");
        d.setContentView(R.layout.dialog_layout);

        urlEditText = (EditText) d.findViewById(R.id.urlEditTxt);
        loadBtn = (Button) d.findViewById(R.id.loadBtn);

        loadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = urlEditText.getText().toString();

                if ((picassoClient = new PicassoClient(MainActivity.this, url)).downloadImage(img)) {
                    sendBtn.setEnabled(true);
                }
            }
        });

        d.show();

    }

    //INITIALIZE FB
    private void initializeFB()
    {
        SimpleFacebook.setConfiguration(new MyConfiguration().getMyConfigs());
        fb=SimpleFacebook.getInstance(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        fb=SimpleFacebook.getInstance(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        fb.onActivityResult(requestCode,resultCode,data);
    }
}











