package com.example.diyview.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.diyview.R

class KotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)
    }

    //函数
    //带有两个 Int 参数、返回 Int 的函数：
    fun plus(a: Int, b: Int): Int {
        return a + b
    }

    //将表达式作为函数体、返回值类型自动推断的函数：
    fun plus2(a: Int, b: Int) = a + b

    //函数返回无意义的值：
    fun plus3(a: String, b: Int): Unit {
        Toast.makeText(this, a + b, Toast.LENGTH_LONG).show()
    }

    //Unit 返回类型可以省略：
    fun plus4(a: String, b: Int) {
        Toast.makeText(this, a + b, Toast.LENGTH_LONG).show()
    }

    //变量
    //定义只读局部变量使用关键字 val 定义。只能为其赋值一次。
    fun value() {
        val a: Int = 1
        val d = "just test"
        val c: String
        c = "this si a test"
    }

    //字符串模板
    fun d(a: String, b: Int) {
        Log.e("d", "sum is $b,and print ${a + b}")
    }

    //在 Kotlin 中，if 也可以用作表达式：
    fun e(a: Int, b: Int): Int {
        return if (a > b) a else b
    }

    //空值与 null 检测
    fun f():String {
        val maybe12 = maybenull("12")
        val maybe13 = maybenull("13")
        if (maybe12==null) return ""
        if (maybe13==null) return ""
        return maybe12+maybe13
    }
    fun maybenull(something: String): String? {
        return something
    }
    //类型检测与自动类型转换

    //for 循环

    //while 循环

    //when 表达式

    //使用区间（range）

    //集合

    //创建基本类及其实例

    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
}