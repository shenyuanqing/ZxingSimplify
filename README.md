# ZxingSimplify
    一个精简的Zxing扫码库。（A Zxing simplify libaray）

Gradle
------
```
dependencies {
    compile 'com.github.shenyuanqing.zxing:zxingsimplify:1.0.0'
}
```

Usage
-----
*Android 6.0以上在跳转到扫码页之前先取得运行时权限，具体参考MainActivity。

*去除了ViewfinderView,使用XML布局。

*可暂停及恢复扫码。

```
    startActivityForResult(new Intent(MainActivity.this, CaptureActivity.class),REQUEST_SCAN);

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_SCAN && resultCode == RESULT_OK){
            Toast.makeText(mContext,data.getStringExtra("barCode"),Toast.LENGTH_LONG).show();
        }
    }
```

```
    /**
     * 开始扫码
     */
    private void startScan() {
        inactivityTimer = new InactivityTimer(this);
        beepManager = new BeepManager(this);

//        扫描线动画2(补间动画)
//        translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0.0f,
//                Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.9f);
//        translateAnimation.setDuration(4500);
//        translateAnimation.setRepeatCount(-1);
//        translateAnimation.setRepeatMode(Animation.RESTART);
//        scanLine.startAnimation(translateAnimation);


        if (isPause) {
            objectAnimator.resume();
            isPause = false;
        } else {
            objectAnimator.start();
        }

        // CameraManager must be initialized here, not in onCreate(). This is necessary because we don't
        // want to open the camera driver and measure the screen size if we're going to show the help on
        // first launch. That led to bugs where the scanning rectangle was the wrong size and partially
        // off screen.
        cameraManager = new CameraManager(getApplication());
        handler = null;
        if (isHasSurface) {
            // The activity was paused but not stopped, so the surface still exists. Therefore
            // surfaceCreated() won't be called, so init the camera here.
            initCamera(scanPreview.getHolder());
        } else {
            // Install the callback and wait for surfaceCreated() to init the camera.
            scanPreview.getHolder().addCallback(this);
        }
        inactivityTimer.onResume();
    }

    /**
     * 暂停扫码
     */
    private void pauseScan() {
        if (handler != null) {
            handler.quitSynchronously();
            handler = null;
        }
        inactivityTimer.onPause();
        beepManager.close();
        cameraManager.closeDriver();
        if (!isHasSurface) {
            scanPreview.getHolder().removeCallback(this);
        }
        objectAnimator.pause();
//        translateAnimation.cancel();
        isPause = true;
    }
```


