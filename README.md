# ZxingSimplify
    一个精简的Zxing扫码库。（A Zxing simplify library）

![ZxingSimplify](https://github.com/shenyuanqing/ZxingSimplify/blob/master/images/zxingscan.png)

![ZxingSimplify](https://github.com/shenyuanqing/ZxingSimplify/blob/master/images/zxingsimplify.png)

Gradle
------
```
dependencies {
    compile 'com.github.shenyuanqing.zxing:zxingsimplify:1.0.0'
}
```

Usage
-----
* Android 6.0以上在跳转到扫码页之前先取得运行时权限，具体参考MainActivity。

* 去除了ViewfinderView,使用XML布局。

* 可暂停及恢复扫码。

```
    /**
     * 跳转到扫码页
     */
    private void jumpScanPage() {
        startActivityForResult(new Intent(MainActivity.this, CaptureActivity.class),REQUEST_SCAN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_SCAN && resultCode == RESULT_OK){
            Toast.makeText(mContext,data.getStringExtra("barCode"),Toast.LENGTH_LONG).show();
        }
    }
```

