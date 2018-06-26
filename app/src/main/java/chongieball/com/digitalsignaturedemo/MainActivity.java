package chongieball.com.digitalsignaturedemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.github.gcacace.signaturepad.views.SignaturePad;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity implements ResultDialog.OnResultDialogListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    private SignaturePad signaturePad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signaturePad = findViewById(R.id.signature);
        Button save = findViewById(R.id.save);
        Button reset = findViewById(R.id.clear);

        signaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {

            }

            @Override
            public void onSigned() {

            }

            @Override
            public void onClear() {

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putParcelable("result_bitmap", signaturePad.getSignatureBitmap());

                ResultDialog dialog = new ResultDialog();
                dialog.setArguments(args);
                dialog.setCancelable(false);
                dialog.show(getSupportFragmentManager(), "result_dialog");
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signaturePad.clear();
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onBackToSignActivity() {
        signaturePad.clear();
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

}
