package com.example.cf;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MedicalDetailsFragment extends Fragment {

    private static final int REQUEST_CAMERA_PERMISSION = 100;

    private ImageView ivPhoto;
    private EditText etDescription, etHospital;
    private CheckBox cbInsurance;
    private Button btnSubmit;
    private Uri imageUri;
    private String imageUrl;
    private DatabaseReference databaseReference;
    private ActivityResultLauncher<Intent> galleryResultLauncher;
    private ActivityResultLauncher<Intent> cameraResultLauncher;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        ivPhoto = view.findViewById(R.id.ivPhoto);
        etDescription = view.findViewById(R.id.etDescription);
        etHospital = view.findViewById(R.id.etHospital);
        cbInsurance = view.findViewById(R.id.cbInsurance);
        btnSubmit = view.findViewById(R.id.btnSubmit);
        databaseReference = FirebaseDatabase.getInstance().getReference("MedicalDetails");

        registerActivityResultLaunchers();

        ivPhoto.setOnClickListener(v -> showImageSourceDialog());
        btnSubmit.setOnClickListener(v -> {
            if (imageUri != null) {
                uploadImageToFirebase();
            } else {
                Toast.makeText(getActivity(), "Please select or capture an image", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void registerActivityResultLaunchers() {
        galleryResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        imageUri = result.getData().getData();
                        ivPhoto.setImageURI(imageUri);
                    }
                }
        );

        cameraResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Bitmap imageBitmap = (Bitmap) result.getData().getExtras().get("data");
                        if (imageBitmap != null) {
                            ivPhoto.setImageBitmap(imageBitmap);
                            imageUri = saveBitmapToFile(imageBitmap);
                        }
                    }
                }
        );
    }

    private void showImageSourceDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Select Image Source")
                .setItems(new String[]{"Camera", "Gallery"}, (dialog, which) -> {
                    if (which == 0) {
                        checkAndRequestCameraPermission();
                    } else {
                        openGallery();
                    }
                })
                .show();
    }

    private void checkAndRequestCameraPermission() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
        } else {
            openCamera();
        }
    }

    private void openCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraResultLauncher.launch(cameraIntent);
    }

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryResultLauncher.launch(galleryIntent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CAMERA_PERMISSION && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            openCamera();
        } else {
            Toast.makeText(getActivity(), "Camera permission denied", Toast.LENGTH_SHORT).show();
        }
    }

    private Uri saveBitmapToFile(Bitmap bitmap) {
        File imageFile = new File(requireContext().getCacheDir(), "captured_image.jpg");
        try (FileOutputStream fos = new FileOutputStream(imageFile)) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            return Uri.fromFile(imageFile);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Error saving image", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    private void uploadImageToFirebase() {
        if (imageUri != null) {
            StorageReference storageReference = FirebaseStorage.getInstance()
                    .getReference("images/" + System.currentTimeMillis() + ".jpg");

            storageReference.putFile(imageUri)
                    .addOnSuccessListener(taskSnapshot -> storageReference.getDownloadUrl()
                            .addOnSuccessListener(uri -> {
                                imageUrl = uri.toString();
                                saveMedicalDetails();
                            }))
                    .addOnFailureListener(e -> Toast.makeText(getActivity(), "Image upload failed: " + e.getMessage(), Toast.LENGTH_SHORT).show());
        }
    }

    private void saveMedicalDetails() {
        // Retrieve data from arguments
        PersonalDetails personalDetails = (PersonalDetails) getArguments().getSerializable("personalDetails");
        AmountDetails amountDetails = (AmountDetails) getArguments().getSerializable("amountDetails");

        String description = etDescription.getText().toString();
        String hospital = etHospital.getText().toString();
        boolean hasInsurance = cbInsurance.isChecked();

        MedicalDetails medicalDetails = new MedicalDetails(description, hospital, hasInsurance, imageUrl);

        // Use the user's name as the Firebase folder
        String userName = personalDetails.getName();
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Users").child(userName);

        // Save all details under the user's name
        userRef.child("personalDetails").setValue(personalDetails);
        userRef.child("amountDetails").setValue(amountDetails);
        userRef.child("medicalDetails").setValue(medicalDetails)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(getActivity(), "Details saved successfully", Toast.LENGTH_SHORT).show();
                    navigateToNextFragment();
                })
                .addOnFailureListener(e -> Toast.makeText(getActivity(), "Error saving details: " + e.getMessage(), Toast.LENGTH_SHORT).show());
    }

    private void navigateToNextFragment() {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, new BankInfoFragment());
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
