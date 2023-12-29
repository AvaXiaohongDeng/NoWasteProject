/*
 * Name: Xiaohong Deng
 * Student ID: 991517517
 * Assignment: Final Group Project - NoWasteFinalProject APP
 * Nov 28, 2021
 *
 * Description of IntroActivity class:
 * This activity is to use ViewPager2 to slide the APP tutorial pages.
 *
 * @author dengxiao
* */
package project.st991517517.xiaohong.Intro

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_intro.*
import project.st991517517.xiaohong.MainActivity
import project.st991517517.xiaohong.R
import project.st991517517.xiaohong.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity(R.layout.activity_intro){

    lateinit var introView: List<IntroView>
    private lateinit var binding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityIntroBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        addToIntroView()

        binding.viewPager2.adapter = ViewPagerIntroAdapter(introView)
        binding.viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        binding.circleIndicator.setViewPager(viewPager2)

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                if (position == 3) {
                    animationButton()
                }
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }
        })
    }

    private fun animationButton() {
        btn_start_app.visibility = View.VISIBLE

        btn_start_app.animate().apply {
            duration = 1400
            alpha(1f)

            btn_start_app.setOnClickListener {
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }.start()
    }

    private fun addToIntroView() {

        //Create some items that you want to add to your viewpager
        introView = listOf(
            IntroView(getString(R.string.intro1), R.drawable.nowaste),
            IntroView(getString(R.string.intro2), R.drawable.ic_food_bank),
            IntroView(getString(R.string.intro3), R.drawable.ic_list),
            IntroView(getString(R.string.intro4), R.drawable.ic_app),
        )
    }
}
