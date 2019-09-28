package com.example.firebasedemoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class UploadActivity extends AppCompatActivity {

    EditText quoteEditText, authorEditText;
    Button submitButton;

    FirebaseFirestore db;

    private static final String TAG = "UploadActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        quoteEditText = findViewById(R.id.quote);
        authorEditText = findViewById(R.id.author);
        submitButton = findViewById(R.id.submitQuote);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validation() == true){
                    uploadQuoteToFireStore(quoteEditText.getText().toString(),authorEditText.getText().toString());
                }
                else{
                    Toast.makeText(UploadActivity.this, "Fill all the fields to upload",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validation(){
        if(authorEditText.getText() == null || quoteEditText.getText() == null){
            return false;
        }
        return true;
    }

    private void uploadQuoteToFireStore(String quote,String author){
        db = FirebaseFirestore.getInstance();
        Map<String, Object> quoteAndAuthor = new HashMap<>();
        quoteAndAuthor.put("quote", quote);
        quoteAndAuthor.put("author", author);

        DocumentReference newQuoteRef = db.collection("Quotes").document();

        newQuoteRef.set(quoteAndAuthor).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "Document successfully uploaded!");
                quoteEditText.getText().clear();
                authorEditText.getText().clear();
                Toast.makeText(UploadActivity.this, "Quote Uploaded successfully",
                        Toast.LENGTH_SHORT).show();
                startActivity(new Intent(UploadActivity.this,MainActivity.class));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Error uploading document", e);
            }
        });
    }
}
