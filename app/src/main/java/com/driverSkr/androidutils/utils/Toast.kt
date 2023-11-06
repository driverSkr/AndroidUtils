package com.driverSkr.androidutils.utils

import android.content.Context
import android.widget.Toast

/**
 * @Author: driverSkr
 * @Time: 2023/11/6 11:02
 * @Description: String类和Int类添加扩展函数，简化Toast
 */

fun String.showToast(context: Context, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, this, duration).show()
}

fun Int.showToast(context: Context, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, this, duration).show()
}