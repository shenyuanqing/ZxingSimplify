# ZxingSimplify
    一个精简的Zxing扫码库。（A Zxing simplify libaray）

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

License
-------

    Copyright 2017 shenyuanqing

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
