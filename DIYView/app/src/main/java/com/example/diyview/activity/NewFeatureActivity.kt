package com.example.diyview.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.diyview.R
import kotlinx.android.synthetic.main.activity_newfeature.*

/**
 * @author 王阳
 *
 * @time 2020/12/6  0:38
 *
 * @desc
 *
 */
class NewFeatureActivity : AppCompatActivity(),View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_newfeature)

        //kotlin设置点击事件的几种写法
        tvFour.setOnClickListener(this)
        //lambda
//        tvFour.setOnClickListener{
//            startActivity()
//        }
//        tvFour.setOnClickListener(fun (v:View):Unit{
//
//        })
    }

    override fun onClick(v: View?) {
        if (v is View) when(v.id){
            R.id.tvFour ->{
                startActivity(Intent(this@NewFeatureActivity,FeatureFourActivity::class.java))
            }
            R.id.tvFive ->{

            }
            R.id.tvSix ->{

            }
            R.id.tvSeven ->{

            }
        }

    }
}