package ba.leftor.exercises.imagepick;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ImagePickActivity extends Activity {
    private static final int REQUEST_CODE = 1;
    private Bitmap mBitmap;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_pick);
        mImageView = (ImageView)findViewById(R.id.result);
    }

    public void pickImage(View view){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent,REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        InputStream inputStream = null;
        if(requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK)
            try{
                if(mBitmap != null){
                    mBitmap.recycle();
                }
                inputStream = getContentResolver().openInputStream(intent.getData());
                mBitmap = BitmapFactory.decodeStream(inputStream);
                mImageView.setImageBitmap(mBitmap);
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }finally {
                if(inputStream != null)
                    try{
                        inputStream.close();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
            }
    }
}
