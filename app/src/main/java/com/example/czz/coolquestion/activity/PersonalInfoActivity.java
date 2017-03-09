package com.example.czz.coolquestion.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.czz.coolquestion.R;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/3/6.
 */

public class PersonalInfoActivity extends Activity {
    private ImageView personal_info_hand_img, personal_info_name_img;
    private View useraccount_layout;
    private View username_layout;
    private View userqq_layout;
    private View userphone_layout;
    private View useraddress_layout;
    private View personal_info_name_layout;
    private Button tuku_btn, paizhao_btn, quxiao_btn;
    private HttpUtils httputils;
    private Uri fileUri;
    private TextView username_tv, userqq_tv, userphone_tv, useraddress_tv;
    private View view;
    private PopupWindow pw;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        final View v1= LayoutInflater.from(this).inflate(R.layout.activity_personal_info,null);
        username_tv = (TextView) findViewById(R.id.username_tv);
        userqq_tv = (TextView) findViewById(R.id.userqq_tv);
        userphone_tv = (TextView) findViewById(R.id.userphone_tv);
        useraddress_tv = (TextView) findViewById(R.id.useraddress_tv);
        httputils = new HttpUtils();
        personal_info_name_img = (ImageView) findViewById(R.id.personal_info_name_img);
        username_tv.setText("name");
        userqq_tv.setText("222222222");
        userphone_tv.setText("222222");
        useraddress_tv.setText("的步伐加快");
        personal_info_hand_img = (ImageView) findViewById(R.id.personal_info_hand_img);
        personal_info_hand_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        useraccount_layout = findViewById(R.id.useraccount_layout);
        useraccount_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infofuzhi();
            }
        });
        username_layout = findViewById(R.id.username_layout);
        username_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infofuzhi();
            }
        });
        userqq_layout = findViewById(R.id.userqq_layout);
        userqq_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infofuzhi();
            }
        });
        userphone_layout = findViewById(R.id.userphone_layout);
        userphone_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infofuzhi();
            }
        });
        useraddress_layout = findViewById(R.id.useraddress_layout);
        useraddress_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infofuzhi();
            }
        });
        view = getLayoutInflater().inflate(R.layout.touxiangpopupwindow, null);
        personal_info_name_layout = findViewById(R.id.personal_info_name_layout);
        personal_info_name_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pw = new PopupWindow(view, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

                //设置显示动画
                pw.setAnimationStyle(R.style.main_menu_animstyle);
                pw.setBackgroundDrawable(getResources().getDrawable(R.mipmap.touming));
                pw.setOutsideTouchable(true);
                pw.showAtLocation(v1,Gravity.BOTTOM,0,0);
            }
        });
        tuku_btn = (Button) view.findViewById(R.id.tuku_btn);
        tuku_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(intent, 1);

                Intent picture = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(picture, 1);
            }
        });
        paizhao_btn = (Button) view.findViewById(R.id.paizhao_btn);
        paizhao_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileUri = getOutputMediaFileUri(0); //得到存储地址的Uri
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //此action表示进行拍照
                i.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);  //指定图片的输出地址
                startActivityForResult(i, 0);
            }


        });

        quxiao_btn = (Button) view.findViewById(R.id.quxiao_btn);
        quxiao_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               pw.dismiss();
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    //个人信息回传值方法
    private void infofuzhi() {
        Intent intent = new Intent(PersonalInfoActivity.this, UpdateActivity.class);
        String name_string = username_tv.getText().toString().trim();
        intent.putExtra("name", name_string);
        String qq_string = userqq_tv.getText().toString().trim();
        intent.putExtra("qq", qq_string);
        String phone_string = userphone_tv.getText().toString().trim();
        intent.putExtra("phone", phone_string);
        String address_string = useraddress_tv.getText().toString().trim();
        intent.putExtra("address", address_string);
        startActivityForResult(intent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2 && resultCode == RESULT_OK) {
            username_tv.setText(data.getStringExtra("srt_name"));
            userqq_tv.setText(data.getStringExtra("srt_qq"));
            userphone_tv.setText(data.getStringExtra("srt_phone"));
            useraddress_tv.setText(data.getStringExtra("srt_address"));
        }

        String path = "";
        if (requestCode == 0) {
            path = fileUri.getPath(); //取得拍照存储的地址
        } else if (requestCode == 1) { //解析得到所选相册图片的地址
            Uri uri = data.getData();
            Cursor cursor = this.getContentResolver().query(uri, null, null, null, null);
            cursor.moveToFirst();
            path = cursor.getString(1);//图片文件路径

//            //data中自带有返回的uri
//            Uri photoUri = data.getData();
//            //获取照片路径
//            String[] filePathColumn={MediaStore.Audio.Media.DATA};
//            Cursor cursor=getContentResolver().query(photoUri,filePathColumn,null,null,null);
//            cursor.moveToFirst();
//            path=cursor.getString(cursor.getColumnIndex(filePathColumn[0]));

        }
        Log.i("this", path + "-----");
        File f = new File(path);
        Bitmap map = BitmapFactory.decodeFile(f.getPath());
        personal_info_name_img.setImageBitmap(map);

        String uploadHost = "130.0.1.251:8080/CoolTopic/UpdateHeadImg";
        RequestParams params = new RequestParams();
        httputils.send(HttpMethod.POST, uploadHost, params, new RequestCallBack<String>() {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onLoading(long total, long current, boolean isUploading) {
                super.onLoading(total, current, isUploading);
            }

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {

            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(PersonalInfoActivity.this, "success", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 为保存图片或视频创建一个文件地址
     */
    private static Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /**
     * Create a File for saving an image or video
     */
    private static File getOutputMediaFile(int type) {

        // 首先检测外部SDCard是否存在并且可读可写
        if (!Environment.getExternalStorageState().equals("mounted")) {
            return null;
        }
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory().getPath());


        // 如果存储路径不存在，则创建
        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == 0) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_" + timeStamp + ".jpg");
        } else {
            return null;
        }

        return mediaFile;
    }


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("PersonalInfo Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
