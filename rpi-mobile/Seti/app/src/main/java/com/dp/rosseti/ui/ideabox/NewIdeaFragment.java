package com.dp.rosseti.ui.ideabox;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.dp.rosseti.R;
import com.dp.rosseti.data.db.entities.ShortIdea;
import com.dp.rosseti.data.repos.ShortIdeasRepository;
import com.google.android.material.textfield.TextInputLayout;

import java.net.URISyntaxException;
import java.util.Random;

public class NewIdeaFragment extends Fragment {

    private NewIdeaViewModel newIdeaViewModel;
    private EditText titleEditText;
    private EditText shortDescriptionEditText;
    private TextInputLayout detailDescriptionInputLayout;
    private Button attachmentButton;
    private TextView pathTextView;

    private String deviceMan = Build.MANUFACTURER;
    private final int REQUEST_CODE_GET_IMAGE_FROM_GALLERY = 1;
    String mAttachPath = "none";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        newIdeaViewModel =
                new ViewModelProvider(this).get(NewIdeaViewModel.class);
        View root = inflater.inflate(R.layout.fragment_newidea, container, false);

        titleEditText = root.findViewById(R.id.et_newidea_title);
        shortDescriptionEditText = root.findViewById(R.id.et_newidea_shortdescription);
        detailDescriptionInputLayout = root.findViewById(R.id.ti_detaildescription);
        attachmentButton = root.findViewById(R.id.bt_newidea_attachment);
        pathTextView = root.findViewById(R.id.tv_attachedfile);

        Button save_button = root.findViewById(R.id.bt_newidea_save);
        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = titleEditText.getText().toString();
                String shortDescription = shortDescriptionEditText.getText().toString();
                String detailDescription = detailDescriptionInputLayout.getEditText().getText().toString();
                ShortIdeasRepository mRepository = new ShortIdeasRepository(getActivity().getApplication());
                Random random = new Random();
                int id = random.nextInt(1000);

                mRepository.insert(new ShortIdea(id, title,"Alexander Smirnov", shortDescription, mAttachPath,"NEW"));
                Navigation.findNavController(view).navigate(R.id.action_nav_newidea_to_nav_ideabox);
            }
        });

        attachmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGalleryApp();
            }
        });

        return root;
    }

    private void startGalleryApp() {
        if (deviceMan.equals("LGE")) {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            Intent galleryIntent = getActivity().getPackageManager().getLaunchIntentForPackage("com.android.gallery3d");
            if (galleryIntent != null) {
                intent.setPackage("com.android.gallery3d");
            }
            startActivityForResult(intent, REQUEST_CODE_GET_IMAGE_FROM_GALLERY);
        } else {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            Intent galleryIntent = Intent.createChooser(intent, "Select Image");
            startActivityForResult(galleryIntent, REQUEST_CODE_GET_IMAGE_FROM_GALLERY);
        }
    }

    @SuppressLint("NewApi")
    public static String getPath(Context context, Uri uri) throws URISyntaxException {
        final boolean needToCheckUri = Build.VERSION.SDK_INT >= 19;
        String selection = null;
        String[] selectionArgs = null;
        // Uri is different in versions after KITKAT (Android 4.4), we need to
        // deal with different Uris.
        if (needToCheckUri && DocumentsContract.isDocumentUri(context.getApplicationContext(), uri)) {
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                return Environment.getExternalStorageDirectory() + "/" + split[1];
            } else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                uri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
            } else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                if ("image".equals(type)) {
                    uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                selection = "_id=?";
                selectionArgs = new String[]{ split[1] };
            }
        }
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = { MediaStore.Images.Media.DATA };
            Cursor cursor = null;
            try {
                cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CODE_GET_IMAGE_FROM_GALLERY) {
                if (data != null) {
                    Uri uriData = data.getData();
                    if (uriData != null) {
                        ContentResolver cR = getActivity().getContentResolver();
                        MimeTypeMap mime = MimeTypeMap.getSingleton();
                        if (mime.getExtensionFromMimeType(cR.getType(uriData)) == "gif") {
                            Toast.makeText(
                                    getActivity(),
                                    getString(R.string.gif_is_not_supported_toast),
                                    Toast.LENGTH_SHORT
                            ).show();
                        } else {
                            try {
                                String path = getPath(getActivity(), uriData);
                                if(path != null) {
                                    this.mAttachPath = path;
                                    pathTextView.setText("Attached: " + path);
                                    pathTextView.setVisibility(View.VISIBLE);
                                } else {
                                    Toast.makeText(
                                            getActivity(),
                                            "Can't determine file path",
                                            Toast.LENGTH_SHORT
                                    ).show();
                                }
                            } catch (Exception e) {
                                Toast.makeText(
                                        getActivity(),
                                        "Can't determine file path",
                                        Toast.LENGTH_SHORT
                                ).show();
                            }
                        }
                    }
                }
            }
        }
    }
}