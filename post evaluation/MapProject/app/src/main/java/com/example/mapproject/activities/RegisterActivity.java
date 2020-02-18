package com.example.mapproject.activities;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.NestedScrollView;

import com.example.mapproject.R;
import com.example.mapproject.helpers.InputValidation;
import com.example.mapproject.model.User;
import com.example.mapproject.sql.DatabaseHelper;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by lalit on 8/27/2016.
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private CircleImageView ProfileImage;
    private static final int PICK_IMAGE = 1;
    private static final int PERMISSION_CODE = 100;

    Uri imageUri;

    private final AppCompatActivity activity = RegisterActivity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutFirstName;
    private TextInputLayout textInputLayoutLastName;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPhone;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutConfirmPassword;
    private TextInputLayout textInputLayoutCnic;

    private TextInputEditText textInputEditTextFirstName;
    private TextInputEditText textInputEditTextLastName;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPhone;
    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditTextConfirmPassword;
    private TextInputEditText textInputEditTextCnic;

    private AppCompatButton appCompatButtonRegister;
    private AppCompatTextView appCompatTextViewLoginLink;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initViews();
        initListeners();
        initObjects();
    }

    /**
     * This method is to initialize views
     */
    private void initViews() {
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutFirstName = (TextInputLayout) findViewById(R.id.textInputLayoutFirstName);
        textInputLayoutLastName = (TextInputLayout) findViewById(R.id.textInputLayoutLastName);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPhone = (TextInputLayout) findViewById(R.id.textInputLayoutPhone);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputLayoutConfirmPassword = (TextInputLayout) findViewById(R.id.textInputLayoutConfirmPassword);
        textInputLayoutCnic = (TextInputLayout) findViewById(R.id.textInputLayoutCnic);

        textInputEditTextFirstName = (TextInputEditText) findViewById(R.id.textInputEditTextFirstName);
        textInputEditTextLastName = (TextInputEditText) findViewById(R.id.textInputEditTextLastName);
        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);

        textInputEditTextPhone = (TextInputEditText) findViewById(R.id.textInputEditTextPhone);
//        textInputEditTextPhone.setInputType(InputType.TYPE_CLASS_NUMBER);

        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);
        textInputEditTextConfirmPassword = (TextInputEditText) findViewById(R.id.textInputEditTextConfirmPassword);
        textInputEditTextCnic = (TextInputEditText) findViewById(R.id.textInputEditTextCnic);
        //      textInputEditTextCnic.setInputType(InputType.TYPE_CLASS_NUMBER);
        appCompatButtonRegister = (AppCompatButton) findViewById(R.id.appCompatButtonRegister);
        ProfileImage = (CircleImageView) findViewById(R.id.ProfileImage);
        appCompatTextViewLoginLink = (AppCompatTextView) findViewById(R.id.appCompatTextViewLoginLink);

    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatButtonRegister.setOnClickListener(this);
        appCompatTextViewLoginLink.setOnClickListener(this);
        ProfileImage.setOnClickListener(this);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(activity);
        user = new User();

    }

    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.appCompatButtonRegister:
                postDataToSQLite();
                break;

            case R.id.appCompatTextViewLoginLink:
                finish();
                break;
            case R.id.ProfileImage:
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
                {
                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_CODE);
                }
                pickImage();

                //break;
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void pickImage() {
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){

            Intent gallery = new Intent(Intent.ACTION_PICK);
            gallery.setType("image/*");
            //     gallery.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(gallery, "Sellect Picture"), PICK_IMAGE);}
    }

    /**
     * This method is to validate the input text fields and post data to SQLite
     */
    private void postDataToSQLite() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextFirstName, textInputLayoutFirstName, "Enter first name")) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextLastName, textInputLayoutLastName, "Enter last name")) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPhone, textInputLayoutPhone, "enter contact number")) {
            return;
        }

        if (!inputValidation.isInputEditTextFilled(textInputEditTextCnic, textInputLayoutCnic, "Enter Cnic")) {
            return;
        }

        if (!inputValidation.isInputEditTextMatches(textInputEditTextPassword, textInputEditTextConfirmPassword,
                textInputLayoutConfirmPassword, getString(R.string.error_password_match))) {
            return;
        }

        if (!inputValidation.isInputEditTextLength(textInputEditTextCnic, textInputLayoutCnic, 10, "Invalid cnic")) {
            return;
        }

        if (!inputValidation.isInputEditTextLength(textInputEditTextPhone, textInputLayoutPhone, 11, "invalid phone")) {
            return;
        }


        if (!databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim())) {

            user.setFirstName(textInputEditTextFirstName.getText().toString().trim());
            user.setLastName(textInputEditTextLastName.getText().toString().trim());
            user.setEmail(textInputEditTextEmail.getText().toString().trim());
            user.setPassword(textInputEditTextPassword.getText().toString().trim());
            user.setPhone((textInputEditTextPhone.getText().toString().trim()));

            user.setCnic((textInputEditTextCnic.getText().toString().trim()));

            databaseHelper.addUser(user);

            // Snack Bar to show success message that record saved successfully
            Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
            emptyInputEditText();


        } else {
            // Snack Bar to show error message that record already exists
            Snackbar.make(nestedScrollView, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show();
        }
    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextFirstName.setText(null);
        textInputEditTextLastName.setText(null);
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
        textInputEditTextPhone.setText(null);
        textInputEditTextConfirmPassword.setText(null);
        textInputEditTextCnic.setText(null);
        ProfileImage.setImageDrawable(null);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();


            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                ProfileImage.setImageBitmap(bitmap);

                //Bitmap bmps = BitmapFactory.decodeStream(bitmap);

                // Bitmap bmp = intent.getExtras().get("data");
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] bite = stream.toByteArray();

                //byte[] bite  = user.getprofile_pic();
                /// Bitmap bmp = BitmapFactory.decodeByteArray(bite,0,bite.length);
                user.setProfile_pic(bite);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
