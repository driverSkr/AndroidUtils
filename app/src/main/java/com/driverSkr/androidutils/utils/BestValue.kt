package com.driverSkr.androidutils.utils

/**
 * @Author: driverSkr
 * @Time: 2023/11/4 17:42
 * @Description: 求N个数的最大最小值
 */

/*fun max(vararg nums: Int): Int {
    *//**记录所有数的最大值，并在一开始将它赋值成了整型范围的最小值**//*
    var maxNum = Int.MIN_VALUE
    //for-in循环遍历nums参数列表
    for (num in nums) {
        *//**如果发现当前遍历的数字比maxNum更大，就将maxNum的值更新成这个数**//*
        maxNum = kotlin.math.max(maxNum,num)
    }
    return maxNum
}*/

/**
 * Description: 求最大值
 *
 * @param nums 双精度浮点型、单精度浮点型，还是短整型、整型、长整型
 * @return 双精度浮点型、单精度浮点型，还是短整型、整型、长整型
 */
fun <T: Comparable<T>> max(vararg nums: T): T {
    if (nums.isEmpty()) throw RuntimeException("Params can not be empty")
    var maxNum = nums[0]
    for (num in nums) {
        if (num > maxNum) {
            maxNum = num
        }
    }
    return maxNum
}

/**
 * Description: 求最小值
 *
 * @param nums 双精度浮点型、单精度浮点型，还是短整型、整型、长整型
 * @return 双精度浮点型、单精度浮点型，还是短整型、整型、长整型
 */
fun <T: Comparable<T>> min(vararg nums: T): T {
    if (nums.isEmpty()) throw RuntimeException("Params can not be empty")
    var minNum = nums[0]
    for (num in nums) {
        if (num < minNum) {
            minNum = num
        }
    }
    return minNum
}

fun main() {
    val a = 3.5
    val b = 3.8
    val c = 4.1
    val d = 5.2

    val largest = max(a, b, c, d)
    val least = min(a, b, c, d)

    println("最大值：$largest；最小值：$least")
}
