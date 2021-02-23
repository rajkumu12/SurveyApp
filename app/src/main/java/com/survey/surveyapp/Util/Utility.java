package com.survey.surveyapp.Util;


import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.URLUtil;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.survey.surveyapp.R;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by AnaadIT on 3/9/2017.
 */

public class Utility {
    public static final int MY_PERMISSIONS_REQUEST = 123;

    private static final String TAG = "Utility";

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static boolean checkPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, permission)) {
                        ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CALL_PHONE, Manifest.permission.GET_ACCOUNTS}, MY_PERMISSIONS_REQUEST);
                    } else {
                        ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CALL_PHONE, Manifest.permission.GET_ACCOUNTS}, MY_PERMISSIONS_REQUEST);
                    }
                    return false;
                }
            }
        }
        return true;
    }



    public static boolean checkAndRequestPermissions(Activity context, int request) {
        int camera = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA);
        int writeExternalStorage = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int readExternalStorage = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE);
        int coarseLocartion = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION);
        int fineLocartion = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION);
        int callPhone = ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE);
        int readContacts = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CONTACTS);
        int recordAudio = ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO);


        List<String> listPermissionsNeeded = new ArrayList<>();

        if (camera != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }
        if (writeExternalStorage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (readExternalStorage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (coarseLocartion != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        }
        if (fineLocartion != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (callPhone != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CALL_PHONE);
        }
        if (readContacts != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_CONTACTS);
        }
        if (recordAudio != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.RECORD_AUDIO);
        }

        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(context, listPermissionsNeeded.toArray
                    (new String[listPermissionsNeeded.size()]), request);
            return false;
        }
        return true;
    }


    public static boolean checkAdditionPermissionsCheck(final Activity splashScreen, String permissions[], int[] grantResults, final int REQUEST_ID_MULTIPLE_PERMISSIONS) {

        Map<String, Integer> perms = new HashMap<>();

        perms.put(Manifest.permission.CAMERA, PackageManager.PERMISSION_GRANTED);
        perms.put(Manifest.permission.ACCESS_FINE_LOCATION, PackageManager.PERMISSION_GRANTED);
        perms.put(Manifest.permission.ACCESS_COARSE_LOCATION, PackageManager.PERMISSION_GRANTED);
        perms.put(Manifest.permission.READ_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
        perms.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
        perms.put(Manifest.permission.CALL_PHONE, PackageManager.PERMISSION_GRANTED);
        perms.put(Manifest.permission.READ_CONTACTS, PackageManager.PERMISSION_GRANTED);
        perms.put(Manifest.permission.RECORD_AUDIO, PackageManager.PERMISSION_GRANTED);

        if (grantResults.length > 0) {
            for (int i = 0; i < permissions.length; i++)
                perms.put(permissions[i], grantResults[i]);
            // Check for both permissions
            if (perms.get(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
                    perms.get(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                    perms.get(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                    perms.get(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                    perms.get(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                    perms.get(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED &&
                    perms.get(Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED &&
                    perms.get(Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED
            ) {
                //   Log.d(TAG, "Please allow access to all asked permissions. Due to the nature of the friendlywagon app, access to these areas on your mobile devices are necessary  for the app to function properly.");
                // process the normal flow
                //else any one or both the permissions are not granted
//                Toast.makeText(splashScreen, "CCCCCCCCCc", Toast.LENGTH_LONG)
//                        .show();
                return true;
            } else {
                //  Log.d(TAG, "Please allow access to all asked permissions. Due to the nature of the friendlywagon app, access to these areas on your mobile devices are necessary  for the app to function properly.");
                if (ActivityCompat.shouldShowRequestPermissionRationale(splashScreen, Manifest.permission.CAMERA) ||
                        ActivityCompat.shouldShowRequestPermissionRationale(splashScreen, Manifest.permission.ACCESS_FINE_LOCATION) ||
                        ActivityCompat.shouldShowRequestPermissionRationale(splashScreen, Manifest.permission.ACCESS_COARSE_LOCATION) ||
                        ActivityCompat.shouldShowRequestPermissionRationale(splashScreen, Manifest.permission.READ_EXTERNAL_STORAGE) ||
                        ActivityCompat.shouldShowRequestPermissionRationale(splashScreen, Manifest.permission.WRITE_EXTERNAL_STORAGE) ||
                        ActivityCompat.shouldShowRequestPermissionRationale(splashScreen, Manifest.permission.CALL_PHONE) ||
                        ActivityCompat.shouldShowRequestPermissionRationale(splashScreen, Manifest.permission.READ_CONTACTS) ||
                        ActivityCompat.shouldShowRequestPermissionRationale(splashScreen, Manifest.permission.RECORD_AUDIO
                        )
                ) {
//                    showDialogOK(splashScreen, "Please allow access to all asked permissions, access to your mobile devices are necessary for the app to function properly.",
                    showDialogOK(splashScreen, "Please allow access to all asked permissions.",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    switch (which) {
                                        case DialogInterface.BUTTON_POSITIVE:
                                            checkAndRequestPermissions(splashScreen, REQUEST_ID_MULTIPLE_PERMISSIONS);
                                            break;
                                        case DialogInterface.BUTTON_NEGATIVE:
                                            break;
                                    }
                                }
                            });
                } else {
                    return true;
//                    Toast.makeText(splashScreen, "Go to settings and enable permissions", Toast.LENGTH_LONG)
//                            .show();
                }
            }
        } else {

        }


        return false;
    }



    private static void showDialogOK(Activity splashScreen, String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(splashScreen)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", okListener)
                .create()
                .show();
    }


    public static boolean checkPermissionsCheck(Activity splashScreen) {
        if (ActivityCompat.checkSelfPermission(splashScreen, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(splashScreen, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(splashScreen, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(splashScreen, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(splashScreen, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(splashScreen, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(splashScreen, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(splashScreen, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED
        ) {
            return true;
        }
        return false;
    }







    public static String isEmptyNull(String address) {
        if(address != null && !address.isEmpty() && !address.equalsIgnoreCase("null")) {
            return address;
        }else{
            return "";
        }
    }






    @SuppressLint("LongLogTag")
    public static String getCompleteAddressString(Activity activity, Location locationAA) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(activity, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(locationAA.getLatitude(), locationAA.getLongitude(), 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                strAdd = strReturnedAddress.toString();
                Log.w("My Current loction address", strReturnedAddress.toString());
            } else {
                Log.w("My Current loction address", "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.w("My Current loction address", "Canont get Address!");
        }
        return strAdd;
    }


    public static String setAddress(Activity activity , Location locationAA) {
        StringBuffer buffer = new StringBuffer();
        try{
            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(activity, Locale.getDefault());

            addresses = geocoder.getFromLocation(locationAA.getLatitude(), locationAA.getLongitude(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

            String address2 = addresses.get(0).getSubLocality();

            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName();


            if (address2 != null) {
                //editTextSAddress.setText(""+address2);
                buffer.append(address2+",");
            }
            if (city != null) {
//                editTextAddress.setText(""+city);
                buffer.append(city+",");
            }
            if (state != null) {
//                editTextState.setText(""+state);
                buffer.append(state+",");
            }
            if (postalCode != null) {
//                editTextZipcode.setText(""+postalCode);
                buffer.append(postalCode+",");
            }
            if (country != null) {
//                editTextZipcode.setText(""+postalCode);
                buffer.append(country+"");
            }
            return buffer.toString();
        }catch (Exception e){
        }
        return buffer.toString();
    }

    public static String getStringFromGson(String responseCode) {
        String jsonObjectInStringSub = "";
        Gson gson = new Gson();
        if(responseCode.length() > 0){
            if(responseCode.length() > 1){
                String jsonObjectInString = gson.toJson(responseCode).replace("\\", "");
                jsonObjectInStringSub = jsonObjectInString.substring(1, jsonObjectInString.length()-1);
                return jsonObjectInStringSub;
            }
        }
        return jsonObjectInStringSub;
    }


    private static boolean isValid(String urlString) {
        try {
           // URL url = new URL(urlString);
           // return URLUtil.isValidUrl(urlString);

                new URL(urlString).toURI();
                return true;

        } catch (Exception e) {

        }

        return false;
    }



    public boolean isURL(String url) {
        try {
            (new URL(url)).openStream().close();
            return true;
        } catch (Exception ex) { }
        return false;
    }



    public static boolean isValid111(String urlString) {
        try {
            URL url = new URL(urlString);
            return URLUtil.isValidUrl(urlString) && Patterns.WEB_URL.matcher(urlString).matches();
        } catch (MalformedURLException e) {

        }

        return false;
    }


    public static boolean isValidUrl(String url) {
        Pattern p = Patterns.WEB_URL;
        Matcher m = p.matcher(url.toLowerCase());
        return m.matches();
    }


    public static void goToBrowser(Activity activity, String link_url) {
        Log.e(TAG , "link_url111 "+link_url);
        Log.e(TAG , "link_url111 "+isValid(link_url));
        if(!link_url.equalsIgnoreCase("")){
            if(!link_url.contains("www") && !link_url.contains("http")){
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www."+link_url));
                activity.startActivity(myIntent);
            }
        }else{
            new ShowMsg().createSnackbar(activity , "Wrong url!");
        }

    }



    public static void autoScroll(RecyclerView rv_seasonal, RecyclerView.Adapter seasonalAdapter) {
        final int speedScroll = 7000;
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            int count = 0;
            @Override
            public void run() {
                if(count == seasonalAdapter.getItemCount())
                    count = 0;
                    //rv_seasonal.smoothScrollBy(600, count);
                Log.e(TAG,  "count1 "+count);
                if(count < seasonalAdapter.getItemCount()){
                   // rv_seasonal.smoothScrollToPosition(++count);
                    Log.e(TAG,  "count2 "+count);
                    rv_seasonal.smoothScrollBy(600, ++count);
                    handler.postDelayed(this,speedScroll);
                }

//                else{
//                    rv_seasonal.smoothScrollBy(600, count);
//                    handler.postDelayed(this,speedScroll);
//                }



            }
        };
        handler.postDelayed(runnable,speedScroll);
    }




    public static class CroppedDrawable extends BitmapDrawable {
        private Path p = new Path();

        public CroppedDrawable(Bitmap b) {
            super(b);
        }

        @Override
        protected void onBoundsChange(Rect bounds) {
            super.onBoundsChange(bounds);

            p.rewind();
            p.addCircle(bounds.width() / 2,
                    bounds.height() / 2,
                    Math.min(bounds.width(), bounds.height()) / 2,
                    Path.Direction.CW);
        }

        @Override
        public void draw(Canvas canvas) {
            canvas.clipPath(p);
            super.draw(canvas);
        }
    }





    public static class BitmapTool {
        public static Bitmap toOvalBitmap(Bitmap bitmap) {
            if (bitmap != null) {
                Bitmap output = Bitmap.createBitmap(bitmap.getHeight(), bitmap.getWidth(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(output);
                Paint paint = new Paint();
                paint.setAntiAlias(true);
                Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
                RectF rectF = new RectF(rect);

                canvas.drawOval(rectF, paint);
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
                canvas.drawBitmap(bitmap, rect, rectF, paint);

                return output;
            }

            return null;
        }
    }






    public static String convertValueStringToDouble(String valueFirst) {
        try{
            double time = Double.parseDouble(valueFirst);
            DecimalFormat df = new DecimalFormat("0.00");
            //DecimalFormat df = new DecimalFormat("##.##");
            return ""+df.format(time);
        }catch (Exception e){

        }
        return "0.0";
    }










    public static boolean isDTTrue() {
        boolean b = false;
        Date date1 = getDate1();
        Date date2 = getDate2();
        if (date1.after(date2)) {
            int is = Integer.parseInt("df");
            b = true;
        }
        if (date1.before(date2)) {
            b = false;
        }
        return b;
    }

    private static Date getDate1() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yy", Locale.US);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 0);
        Date date = cal.getTime();
        String date1 = dateFormatter.format(date);
        Date inActiveDate = null;
        try {
            inActiveDate = dateFormatter.parse(date1);
            return inActiveDate;
        } catch (ParseException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return inActiveDate;
    }

    private static Date getDate2() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yy", Locale.US);
        String date1 = "01/01/19";
        Date inActiveDate = null;
        try {
            inActiveDate = dateFormatter.parse(date1);
            return inActiveDate;
        } catch (ParseException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return inActiveDate;
    }







    public static boolean isRunning(Context ctx) {
        ActivityManager activityManager = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = activityManager.getRunningTasks(Integer.MAX_VALUE);
        for (ActivityManager.RunningTaskInfo task : tasks) {
            if (ctx.getPackageName().equalsIgnoreCase(task.baseActivity.getPackageName()))
                return true;
        }
        return false;
    }




    public static String getPath(Activity activity, Uri uri) {
        String[] projection = { MediaStore.Video.Media.DATA };
        Cursor cursor = activity.getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            // HERE YOU WILL GET A NULLPOINTER IF CURSOR IS NULL
            // THIS CAN BE, IF YOU USED OI FILE MANAGER FOR PICKING THE MEDIA
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else
            return null;
    }




    public static String getImagePath(Activity activity, Uri selectedImage) {
        // TODO Auto-generated method stub
        String[] filePathColumn = {MediaStore.Images.Media.DATA};

        Cursor cursor = activity.getContentResolver().query(selectedImage,
                filePathColumn, null, null, null);
        cursor.moveToFirst();

        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();

        Log.d(TAG, "path is :: " + picturePath);
        return picturePath;
    }



    public static String getImagePathCamera(Activity activity, Intent selectedImage) {
        // TODO Auto-generated method stub
        Bitmap thumbnail = (Bitmap) selectedImage.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, bytes);


        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");
        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());

            String imagePath1 = destination.toString();

            fo.close();

            return imagePath1;

            //imageLoader.displayImage("file:///"+imagePath1, imageViewDriverProfile, options);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }




    public static void showCallDialog(final Activity activity, final String number) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(activity.getString(R.string.app_name));
        builder.setMessage("Dial this number")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        dialPhoneNumber(activity, number);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }



    public static void dialPhoneNumber(Activity activity, String number) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + number));
        if (intent.resolveActivity(activity.getPackageManager()) != null) {
            activity.startActivity(intent);
        }
    }




    public static void showCallDialog(final Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(activity.getString(R.string.app_name));
        builder.setMessage("Dial this number")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        dialPhoneNumber(activity);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public static void dialPhoneNumber(Activity activity) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + 35435345));
        if (intent.resolveActivity(activity.getPackageManager()) != null) {
            activity.startActivity(intent);
        }
    }




    public final static boolean isValidEmail(CharSequence target)
    {
        if (target == null)
        {
            return false;
        }
        else
        {
            return Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }





//
//    @SuppressLint("LongLogTag")
//    public static LatLng getLocationFromAddress(Context context, String strAddress) {
//        Geocoder coder = new Geocoder(context);
//        List<Address> address = new ArrayList<>();
//        LatLng p1 = null;
//        try {
//            address = coder.getFromLocationName(strAddress, 5);
//            if (address == null) {
//                return null;
//            }
//            Log.e(TAG, "address111 "+address);
//
//            if(address.size() > 0){
//                Address location = address.get(0);
//                location.getLatitude();
//                location.getLongitude();
//                p1 = new LatLng(location.getLatitude(), location.getLongitude());
//            }
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            Log.e(TAG, "getMessage111 "+ex.getMessage());
//        }
//        return p1;
//    }
//
//



    public static void log(String message) {
        if (message != null) {
            Log.e("Phone Auth ", message);
        } else {
            Log.e("Message", "null");
        }
    }


    public static void hideKeyBoardFromView(Activity mActivity) {
        InputMethodManager inputMethodManager = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        View view = mActivity.getCurrentFocus();
        if (view == null) {
            view = new View(mActivity);
        }
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void hideSoftKeyboard(Activity mActivity) {
        mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }


    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }




    public static String getDevice() {
        return getDeviceName();
    }



    public static String getAndroidVersion() {
        String release = Build.VERSION.RELEASE;
        int sdkVersion = Build.VERSION.SDK_INT;
        return "Android SDK: " + sdkVersion + " (" + release +")";
    }


    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return model.toUpperCase() + ", OS-"+Build.VERSION.RELEASE;
        } else {
            return manufacturer.toUpperCase() + " " + model+", OS-"+Build.VERSION.RELEASE;
        }
    }

}
