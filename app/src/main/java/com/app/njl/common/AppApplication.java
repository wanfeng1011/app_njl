package com.app.njl.common;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.app.njl.utils.BuildConfig;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.socks.library.KLog;
import com.squareup.leakcanary.LeakCanary;
import com.yolanda.nohttp.NoHttp;

import java.io.File;

public class AppApplication extends Application {

    private static AppApplication app;

    public AppApplication() {
        app = this;
    }

    public static synchronized AppApplication getInstance() {
        if (app == null) {
            app = new AppApplication();
        }
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        NoHttp.init(this);
        //日志初始化
        KLog.init(BuildConfig.LOG_DEBUG);
        LeakCanary.install(this);
        registerUncaughtExceptionHandler();
        initImageLoader(this);
    }

    // 注册App异常崩溃处理器
    private void registerUncaughtExceptionHandler() {
        Thread.setDefaultUncaughtExceptionHandler(AppException.getAppExceptionHandler());
    }

    /** 初始化ImageLoader */
    public static void initImageLoader(Context context) {
        String filePath = Environment.getExternalStorageDirectory() +
                "/Android/data/" + context.getPackageName() + "/cache/";

        File cacheDir = StorageUtils.getOwnCacheDirectory(context, filePath);// 获取到缓存的目录地址
        Log.d("cacheDir", cacheDir.getPath());
        // 创建配置ImageLoader(所有的选项都是可选的,只使用那些你真的想定制)，这个可以设定在APPLACATION里面，设置为全局的配置参数
        ImageLoaderConfiguration config = new ImageLoaderConfiguration
                .Builder(context)
                // .memoryCacheExtraOptions(480, 800) // max width, max
                // height，即保存的每个缓存文件的最大长宽
                // .discCacheExtraOptions(480, 800, CompressFormat.JPEG,
                // 75, null) // Can slow ImageLoader, use it carefully
                // (Better don't use it)设置缓存的详细信息，最好不要设置这个
                .threadPoolSize(3)// 线程池内加载的数量
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new WeakMemoryCache())
                        // .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024
                        // * 1024)) // You can pass your own memory cache
                        // implementation你可以通过自己的内存缓存实现
                .memoryCacheSize(2 * 1024 * 1024)
                        // /.discCacheSize(50 * 1024 * 1024)
                .discCacheFileNameGenerator(new Md5FileNameGenerator())// 将保存的时候的URI名称用MD5
                        // 加密
                        // .discCacheFileNameGenerator(new
                        // HashCodeFileNameGenerator())//将保存的时候的URI名称用HASHCODE加密
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                        // .discCacheFileCount(100) //缓存的File数量
                .discCache(new UnlimitedDiscCache(cacheDir))// 自定义缓存路径
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                .imageDownloader(new BaseImageDownloader(context, 5 *
                        1000, 30 * 1000)) // connectTimeout (5 s),
                        // readTimeout(30)// 超时时间
                .writeDebugLogs() // Remove for release app 打印log信息
                .build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);// 全局初始化此配置
    }

}