package com.example.profile;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        TextView name = findViewById(R.id.nameTV);
        TextView email = findViewById(R.id.emailTV);
        TextView num = findViewById(R.id.numTV);

        ImageView pfp = findViewById(R.id.profile_image);

        ImageButton linkedin = findViewById(R.id.linkedinBTN);
        ImageButton github = findViewById(R.id.githubBTN);
        Button edit = findViewById(R.id.editBTN);


        //getting values from previous activity
        Intent intent2 = getIntent();
        String nameS = intent2.getStringExtra("name");
        String emailS = intent2.getStringExtra("email");
        String numS = intent2.getStringExtra("num");
        String linkedinS = intent2.getStringExtra("linkedin");
        String githubS = intent2.getStringExtra("github");
        Bitmap bitmap = intent2.getParcelableExtra("bitmap");

        //setting values
        name.setText(nameS);
        email.setText(emailS);
        num.setText(numS);
        pfp.setImageBitmap(bitmap);

        String gh = "https://github.com/" + githubS;


        //clicking LinkedIn
        linkedin.setOnClickListener(view -> {
            Uri uri = Uri.parse(linkedinS);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });

        //clicking Github
        github.setOnClickListener(view -> {
            Uri uri = Uri.parse(gh);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });


        //clicking Edit Profile button
        edit.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), MainActivity.class);
            view.getContext().startActivity(intent);
        });



    } //onCreate
}