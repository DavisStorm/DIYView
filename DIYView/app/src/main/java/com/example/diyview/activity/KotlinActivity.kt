package com.example.diyview.activity

import android.app.Person
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.diyview.R
import com.example.diyview.bean.Student
import java.sql.DriverManager.println

annotation class Inject

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
    fun f(): String {
        val maybe12 = maybenull("12")
        val maybe13 = maybenull("13")
        if (maybe12 == null) return ""
        if (maybe13 == null) return ""
        return maybe12 + maybe13
    }

    fun maybenull(something: String): String? {
        return something
    }

    //类型检测与自动类型转换
    fun typeCheck(a: Any): Int? {
        if (a is String) {
            return a.length
        }

        return null
    }

    //for 循环
    fun forech() {
        val map = mapOf("a" to 1, "b" to 2, "c" to 3)//mapOf创建只读 map
        println(map["a"])    // 1

        var items = listOf("apple", "banana", "kiwifruit")//listOf创建只读 list
        for (item in items) {
            println(item)
        }
    }

    //while 循环
    fun whiletest(a: Int) {
        var index: Int = 0
        while (index < a) {
            println("this is position $index")
            index++
        }
    }

    //when 表达式    when 将它的参数与所有的分支条件顺序比较，直到某个分支满足条件
    fun whentest(a: Int) {
        when (a) {
            1 -> println("$a")
            2 -> Toast.makeText(this, "$a", Toast.LENGTH_LONG).show()
            else -> {
                println("a is neither 1 nor 2")
            }
        }
    }

    //使用区间（range）
    fun rangetest(b: Int): Boolean? {
        var c: Int = 20
        if (b in 1..c + 100) {
            return true
        }
        return null
    }

    //集合
    fun listTest() {
    }

    //控制流：if、when、for、while
    //If 表达式
    fun ifExpression(a: Int, b: Int) {
        val max1 = if (a > b) a else b
        val max2 = if (a > b) {
            println(a)
            a
        } else {
            println(b)
            b
        }
    }

    //When 表达式
    fun whenExpression(a: Any) {
        when (a) {
            1 -> println(a)
            2, 3 -> println(a)
            in 4..6 -> println(a)
            !in 10..20 -> println(a)
            is String -> println(a)
            else -> println(0)
        }
    }

    //For 循环
    fun forExpression(items: Array<Int>) {
        for (item in items) {
            println(item)
        }
        for (item in 1..10 step 2) {
            println(item)
        }
        for (item in 10 downTo 1 step 2) {
            println(item)
        }
        for ((index, value) in items.withIndex()) {
            println("items's element with index at $index is $value")
        }
    }

    //返回到标签
    fun returnExpression() {

        //加标签并用以限制 return
        listOf<Int>(1, 2, 3).forEach lit@{
            if (it == 2) return@lit // 局部返回到该 lambda 表达式的调用者，即 forEach 循环
            println(it)
        }

        //通常情况下使用隐式标签更方便。 该标签与接受该 lambda 的函数同名
        listOf(1, 2, 3, 4, 5).forEach {
            if (it == 3) return@forEach // 局部返回到该 lambda 表达式的调用者，即 forEach 循环
            print(it)
        }

        //匿名函数内部的 return 语句将从该匿名函数自身返回
        listOf(1, 2, 3, 4, 5).forEach(fun(value: Int) {
            if (value == 3) return  // 局部返回到匿名函数的调用者，即 forEach 循环
            print(value)
        })
    }

    //类和继承   类名、类头、类体   类头(构造器)与类体都是可选的
    fun classExpression(name: String) {
        //继承     覆盖方法   覆盖属性
        /**
         * 如果派生类有一个主构造函数，其基类可以（并且必须） 用派生类主构造函数的参数就地初始化。
        如果派生类没有主构造函数，那么每个次构造函数必须使用 super 关键字初始化其基类型，或委托给另一个构造函数做到这一点。
        注意，在这种情况下，不同的次构造函数可以调用基类型的不同的构造函数
         * */
        var student = Student("lisa", 3, false)
        student.code
        student.name

        // 抽象类  我们可以用一个抽象成员覆盖一个非抽象的开放成员
        open class Polygon {
            open fun draw() {}
        }

        abstract class Rectangle : Polygon() {
            abstract override fun draw()
        }


    }

    //    如果我们定义了一个自定义的 setter，那么每次给属性赋值时都会调用它。一个自定义的 setter 如下所示：
    var stringRepresentation: String
        get() = this.toString()
        set(value) {
            stringRepresentation = value // 解析字符串并赋值给其他属性
//            setDataFromString(value)
        }
    //一个只读属性的语法和一个可变的属性的语法有两方面的不同：1、只读属性的用 val开始代替var 2、只读属性不允许 setter
//    如果可以从 getter 推断出属性类型，则可以省略它
    val isEmpty get() = stringRepresentation.length == 0  // 具有类型 Boolean

    //声明对象
    class Person1(name: String) {
        val firstProperty = "First property: $name".also { println(name) }

        init {
            println("First initializer block that prints ${name}")
        }
    }

    //如果构造函数有注解或可见性修饰符，这个 constructor 关键字是必需的，并且这些修饰符在它前面：
    class Customer public @Inject constructor(name: String) { /*……*/ }

    class Person2 {
        constructor(name: String) {
            println(name)
        }
    }

    //如果类有一个主构造函数，每个次构造函数需要委托给主构造函数， 可以直接委托或者通过别的次构造函数间接委托。委托到同一个类的另一个构造函数用 this 关键字即可
    class Person3(val name: String) {
        var children: MutableList<Person3> = mutableListOf()

        constructor(name: String, parent: Person3) : this(name) {
            parent.children.add(this)
        }
    }

    //委托到同一个类的另一个构造函数用 this 关键字即可,   说人话就是，调用其他构造器
    class Person(val name: String) {
        var children: MutableList<Person> = mutableListOf()

        constructor(name: String, parent: Person) : this(name) {
            parent.children.add(this)
        }
    }

    //所有初始化块与属性初始化器中的代码都会在次构造函数体之前执行。即使该类没有主构造函数，这种委托仍会隐式发生，并且仍会执行初始化块
    class Constructors {
        init {
            println("Init block")
        }

        constructor(i: Int) {
            println("Constructor")
        }
    }
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

