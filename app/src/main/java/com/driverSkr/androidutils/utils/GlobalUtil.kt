package com.driverSkr.androidutils.utils

import android.annotation.SuppressLint
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.os.Build
import android.provider.Settings
import android.text.TextUtils
import com.driverSkr.androidutils.MyApplication
import java.lang.Exception
import java.util.*

/**
 * @Author: driverSkr
 * @Time: 2023/11/6 11:35
 * @Description: 应用程序全局的通用工具类，功能比较单一，经常被复用的功能，应该封装到此工具类当中，从而给全局代码提供方面的操作。
 */
object GlobalUtil {

    private var TAG = "GlobalUtil"

    /**
     * 获取当前应用程序的包名。
     *
     * @return 当前应用程序的包名。
     */
    val appPackage: String get() = MyApplication.context.packageName

    /**
     * 获取当前应用程序的名称。
     * @return 当前应用程序的名称。
     */
    val appName: String get() = MyApplication.context.resources.getString(MyApplication.context.applicationInfo.labelRes)

    /**
     * 获取当前应用程序的版本名。
     * @return 当前应用程序的版本名。
     */
    val appVersionName: String get() = MyApplication.context.packageManager.getPackageInfo(appPackage,0).versionName

    /**
     * 获取当前应用程序的版本号。
     * @return 当前应用程序的版本号。
     */
    val appVersionCode: Long get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
        MyApplication.context.packageManager.getPackageInfo(appPackage,0).longVersionCode
    } else {
        MyApplication.context.packageManager.getPackageInfo(appPackage,0).versionCode.toLong()
    }

    /**
     * 获取设备的的型号，如果无法获取到，则返回Unknown。
     * @return 设备型号。
     */
    val deviceModel: String get() {
        var deviceModel = Build.MODEL
        if (TextUtils.isEmpty(deviceModel)){
            deviceModel = "unknown"
        }
        return deviceModel
    }

    /**
     * 获取设备的品牌，如果无法获取到，则返回Unknown。
     * @return 设备品牌，全部转换为小写格式。
     */
    val deviceBrand: String get() {
        var deviceBrand = Build.BRAND
        if (TextUtils.isEmpty(deviceBrand)){
            deviceBrand = "unknown"
        }
        return deviceBrand.toLowerCase(Locale.getDefault())
    }

    private var deviceSerial: String? = null

    /**
     * 获取资源文件中定义的字符串。
     *
     * @param resId
     * 字符串资源id
     * @return 字符串资源id对应的字符串内容。
     */
    fun getString(resId: Int): String = MyApplication.context.resources.getString(resId)

    /**
     * 获取资源文件中定义的字符串。
     *
     * @param resId
     * 字符串资源id
     * @return 字符串资源id对应的字符串内容。
     */
    fun getDimension(resId: Int): Int = MyApplication.context.resources.getDimensionPixelOffset(resId)

    /**
     * 获取指定资源名的资源id。
     *
     * @param name
     * 资源名
     * @param type
     * 资源类型
     * @return 指定资源名的资源id。
     */
    fun getResourceId(name: String,type: String): Int = MyApplication.context.resources.getIdentifier(name,type, appPackage)

    /**
     * 获取AndroidManifest.xml文件中，<application>标签下的meta-data值。
     *
     * @param key
     *  <application>标签下的meta-data健
     */
    fun getApplicationMetaData(key: String): String?{
        var applicationInfo: ApplicationInfo? = null
        try {
            applicationInfo = MyApplication.context.packageManager.getApplicationInfo(
                appPackage, PackageManager.GET_META_DATA)
        } catch (e: PackageManager.NameNotFoundException){
            logW(TAG,e.message,e)
        }
        if (applicationInfo == null) return ""
        return applicationInfo.metaData.getString(key)
    }

    /**
     * 判断某个应用是否安装。
     * @param packageName
     * 要检查是否安装的应用包名
     * @return 安装返回true，否则返回false。
     */
    fun isInstalled(packageName: String): Boolean{
        val packageInfo: PackageInfo? = try {
            MyApplication.context.packageManager.getPackageInfo(packageName,0)
        } catch (e: PackageManager.NameNotFoundException){
            null
        }
        return packageInfo != null
    }

    /**
     * 获取当前应用程序的图标。
     */
    fun getAppIcon(): Drawable {
        val packageManager = MyApplication.context.packageManager
        val applicationInfo = packageManager.getApplicationInfo(appPackage,0)
        return packageManager.getApplicationIcon(applicationInfo)
    }

    /**
     * 判断手机是否安装了QQ。
     */
    fun isQQInstalled() = isInstalled("com.tencent.mobileqq")

    /**
     * 判断手机是否安装了微信。
     */
    fun isWechatInstalled() = isInstalled("com.tencent.mm")

    /**
     * 判断手机是否安装了微博。
     * */
    fun isWeiboInstalled() = isInstalled("com.sina.weibo")
}