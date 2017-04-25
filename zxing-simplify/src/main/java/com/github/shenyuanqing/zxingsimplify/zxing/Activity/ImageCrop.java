package com.github.shenyuanqing.zxingsimplify.zxing.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.github.shenyuanqing.zxingsimplify.R;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by shenyuanqing on 2015/4/6.
 */
public class ImageCrop {

    public static int SELECT_PIC = 2001;

    public static int SELECT_PIC_KITKAT = 2002;

    public static int TAKE_PIC = 2003;

    public static int CROP = 2004;

    public static int SELECT_LOCAL_PIC = 2005;

    public String uploadType;

    private Activity activity;

    private Fragment fragment;

//    public static File TEMP_PIC = new File(CacheContext.getTempPath(), "temp.jpg");
//
//    public static File UPLOAD_PIC = new File(CacheContext.getTempPath(), "upload.jpg");

    public boolean anyScale = false;//任意截取比例

    public void setAnyScale(boolean anyScale) {
        this.anyScale = anyScale;
    }

    public ImageCrop(Activity activity) {
        this.activity = activity;
    }

    public ImageCrop(Fragment fragment) {
        this.fragment = fragment;
    }

    public ImageCrop(Activity activity, boolean anyScale) {
        this.activity = activity;
        this.anyScale = anyScale;
    }

    public ImageCrop(Fragment fragment, boolean anyScale) {
        this.fragment = fragment;
        this.anyScale = anyScale;
    }

//    public void selectLocalPhoto(int max, ArrayList<String> selectedPhoto) {
//        if (activity != null) {
//            Intent intent = new Intent(activity, PicSelectActivity.class);
//            intent.putExtra("maxSize", max);
//            if (selectedPhoto != null)
//                intent.putStringArrayListExtra("selectedPhoto", selectedPhoto);
//            activity.startActivityForResult(intent, SELECT_LOCAL_PIC);
//        } else {
//            Intent intent = new Intent(fragment.getActivity(), PicSelectActivity.class);
//            intent.putExtra("maxSize", max);
//            if (selectedPhoto != null)
//                intent.putStringArrayListExtra("selectedPhoto", selectedPhoto);
//            fragment.startActivityForResult(intent, SELECT_LOCAL_PIC);
//        }
//    }

//    public void showImageSelector() {
//        final Dialog mDialog = new Dialog(activity == null ? fragment.getActivity() : activity, R.style.ShareDialogStyle);
//        mDialog.setContentView(R.layout.pubblico_take_photo);
//        Window dialogWindow = mDialog.getWindow();
//        dialogWindow.getDecorView().setPadding(0, 0, 0, 0);
//        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//        dialogWindow.setGravity(Gravity.BOTTOM);
//        dialogWindow.setWindowAnimations(R.style.mypopwindow_anim_style);
//        dialogWindow.setAttributes(lp);
//
//        dialogWindow.getDecorView().findViewById(R.id.take_photo).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                if (listener != null)
////                    listener.takePhoto(mDialog, v);
//                takePhoto();
//                mDialog.dismiss();
//            }
//        });
//
//        dialogWindow.getDecorView().findViewById(R.id.toAlbum).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                if (listener != null)
////                    listener.toAlbum(mDialog, v);
//                goAlbum();
//                mDialog.dismiss();
//            }
//        });
//        dialogWindow.getDecorView().findViewById(R.id.cancle).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mDialog.dismiss();
//            }
//        });
//        mDialog.show();
//    }

    /**
     * 跳转到图片选择
     */
    public void openAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            start(intent, SELECT_PIC_KITKAT);
        } else {
            start(intent, SELECT_PIC);
        }


    }

    /**
     * 拍照
     */
//    public void takePhoto() {
//        if (!CommonUtils.checkCamera()) {
//            LogUtils.e("???????????");
//            CommonDialog dialog = new CommonDialog(activity);
//            dialog.setMessage("请在权限管理中设置允许掌药访问你的相机");
//            dialog.hideNegativeBtn();
//            dialog.setPositiveBtnListener(new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.dismiss();
//                }
//            });
//            dialog.show();
//            return;
//        }
//        if (TEMP_PIC.exists() && TEMP_PIC.isFile()) {
//            TEMP_PIC.delete();
//        }
//        LogUtils.e("图片路径==" + TEMP_PIC.getPath());
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(TEMP_PIC));
//        start(intent, TAKE_PIC);
//    }
    private void start(Intent intent, int requestCode) {
        if (activity != null)
            activity.startActivityForResult(intent, requestCode);
        else if (fragment != null)
            fragment.startActivityForResult(intent, requestCode);
    }

    /**
     * 放在onActivityForResult中处理跳转
     *
     * @param requestCode
     * @param resultCode
     * @param intent
     * @param imageListener
     */
//    public void handlerImage(int requestCode, int resultCode, Intent intent, OnImageListener imageListener) {
//        if (requestCode == ImageCrop.SELECT_PIC && resultCode == Activity.RESULT_OK) { //4.4以下图库
//            Uri uri = intent.getData();
//            String path = getPath(activity == null ? fragment.getActivity() : activity, uri);
//            crop(Uri.parse("file://" + path), 1, 1);
//        }
//
//        if (requestCode == ImageCrop.SELECT_PIC_KITKAT && resultCode == Activity.RESULT_OK) { //4.4及以上酷图
//            Uri uri = intent.getData();
//            String path = getPath(activity == null ? fragment.getActivity() : activity, uri);
//            crop(Uri.parse("file://" + path), 1, 1);
//        }
////        if (requestCode == ImageCrop.TAKE_PIC && resultCode == Activity.RESULT_OK) { //拍照
////            crop(Uri.fromFile(TEMP_PIC), 1, 1);
//////            LogUtils.e("拍照=" + TEMP_PIC.getPath());
////        }
//
////        if (requestCode == ImageCrop.CROP && resultCode == Activity.RESULT_OK) { //裁剪
////            if (imageListener != null)
////                imageListener.hanlderImage(UPLOAD_PIC);
////        }
//    }

//    public void handlerImage2(int requestCode, int resultCode, Intent intent, OnImageListener2 imageListener) {
//        handlerImage(requestCode, resultCode, intent, null);
//        if (requestCode == ImageCrop.SELECT_LOCAL_PIC && resultCode == Activity.RESULT_OK) { //新的本地图片选择
//            ArrayList<String> selected = intent.getStringArrayListExtra("selectedPhoto");
//            imageListener.hanlderImage(selected);
//        }
//    }

    /**
     * 当选择一张图片时，支持剪裁操作
     *
     * @param requestCode
     * @param resultCode
     * @param intent
     * @param imageListener
     * @param width         长度
     * @param height        宽度
     */
//    public void handlerImage2(int requestCode, int resultCode, Intent intent, OnImageListener imageListener, int width, int height) {
//        handlerImage(requestCode, resultCode, intent, imageListener);
//        if (requestCode == ImageCrop.SELECT_LOCAL_PIC && resultCode == Activity.RESULT_OK) { //新的本地图片选择
//            ArrayList<String> selected = intent.getStringArrayListExtra("selectedPhoto");
//            if (selected.size() == 1) {
//                crop(Uri.parse("file://" + selected.get(0)), width, height);
//            } else {
////                YDToast.toastShort("图片太多，无法剪裁");
//            }
//        }
//    }

    /**
     * 收缩图片
     *
     * @param uri
     */
//    public void crop(Uri uri, int width, int height) {
//        Intent intent = new Intent("com.android.camera.action.CROP");
//        intent.setDataAndType(uri, "image/*");
//        intent.putExtra("crop", "true");
//        if (!anyScale) {
//            // aspectX aspectY 是宽高的比例
//            intent.putExtra("aspectX", width);
//            intent.putExtra("aspectY", height);
//        }
//        // outputX outputY 是裁剪图片宽高
////        intent.putExtra("outputX", 720);
////        intent.putExtra("outputY", 720);
//        intent.putExtra("return-data", false);
//
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(UPLOAD_PIC));
//        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
//        intent.putExtra("noFaceDetection", true); // no face detection
//        start(intent, CROP);
//    }


    public interface OnImageListener {

        public void hanlderImage(File file);

    }

    public interface OnImageListener2 {

        public void hanlderImage(ArrayList<String> files);

    }


    public String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {

            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }


    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
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

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

}
