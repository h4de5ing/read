# module app
## 读
- 读,取自阅读
- 业余练习项目

# module kanzhihu
## 看知乎
- 数据来源http://kanzhihu.com
- 学习架构MVP+ButterKnife+Okhttp

###　配置ButterKnife
Project build.gradle
```
dependencies {
      classpath 'com.android.tools.build:gradle:1.3.1'
      classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'
    }
```
module build.gradle
```
dependencies {
    compile 'com.jakewharton:butterknife:8.1.0'
    apt 'com.jakewharton:butterknife-compiler:8.1.0'
}
```

### 附
- 架构：MVP
- json:Gson         compile 'com.google.code.gson:gson:2.6.2'
- 网络：OkHttp       compile 'com.zhy:okhttputils:2.6.2'
- 缓存:okhttputils  compile 'com.lzy.net:okhttputils:1.4.0'
- 工具类：library  compile 'com.code19.library:library:0.1.0'

