package com.example.profile;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView pfp;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    private static final int PICK_IMAGE = 100;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EditText name = findViewById(R.id.nameET);
        EditText email = findViewById(R.id.emailET);
        EditText num = findViewById(R.id.numET);
        EditText linkedin = findViewById(R.id.linkedinET);
        EditText github = findViewById(R.id.githubET);

        pfp = findViewById(R.id.pfpIV);

        Button create = findViewById(R.id.createBTN);


        //clicking pfp opens gallery
        pfp.setOnClickListener(v -> openGallery());


        //clicking Create Profile button
        create.setOnClickListener(view -> {

            String nameS = name.getText().toString();
            String emailS = email.getText().toString();
            String numS = num.getText().toString();
            String linkedinS = linkedin.getText().toString();
            String githubS = github.getText().toString();

            pfp.buildDrawingCache();
            Bitmap bitmap = pfp.getDrawingCache();

            if (nameS.isEmpty() || emailS.isEmpty() || linkedinS.isEmpty() || githubS.isEmpty()) {
                Toast toast = Toast.makeText(this, "Fill all the required fields!", Toast.LENGTH_SHORT);
                toast.show();
            }else{
                if (emailS.trim().matches(emailPattern)) {
                    if (Patterns.WEB_URL.matcher(linkedinS).matches()) {
                        Intent intent = new Intent(view.getContext(), ProfileActivity.class);
                        intent.putExtra("name", nameS);
                        intent.putExtra("email", emailS);
                        intent.putExtra("num", numS);
                        intent.putExtra("linkedin", linkedinS);
                        intent.putExtra("github", githubS);
                        intent.putExtra("bitmap", bitmap);
                        view.getContext().startActivity(intent);
                    }else{
                        Toast toast = Toast.makeText(this, "Invalid url!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }else{
                    Toast toast = Toast.makeText(this, "Invalid email!", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

        });








    } //onCreate


    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            pfp.setImageURI(imageUri);
        }
    }


}








