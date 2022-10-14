package com.example.diyview.activity

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.doyou.cv.utils.ToastUtils
import com.example.diyview.R
import com.example.diyview.annotaion.*

/**
 * @author 王阳
 *
 * @time 2020/12/6  0:38
 *
 * @desc
 *
 */
@InjectSetContentView(R.layout.activity_annotation)
class AnnotationActivity : AppCompatActivity(){

    @InjectFbi(R.id.tv_click)
    var tv_click: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_annotation)
        AnnotationManager.getInstance().inject(this)
        if (tv_click !=null){
            Toast.makeText(this, "tv has value", Toast.LENGTH_LONG).show()
        }
    }

    @InjectOnClick(R.id.tv_click,R.id.tv_click2)
    public fun onClick(view: View) {
        if (view is TextView){
            ToastUtils.showLongToast(this,"onClick:"+view.text.toString())
//            view.setOnClickListener(object :View.OnClickListener{
//                override fun onClick(p0: View?) {
//                }
//            })
        }
    }

    @InjectOnLongClick(R.id.tv_longclick)
    public fun onLongClick(view: View): Boolean {
        if (view is TextView){
            ToastUtils.showLongToast(this,"onLongClick:"+view.text.toString())
//            view.setOnLongClickListener(object :View.OnLongClickListener{
//                override fun onLongClick(p0: View?): Boolean {
//                    return false
//                }
//            })
        }
        return true

    }

}