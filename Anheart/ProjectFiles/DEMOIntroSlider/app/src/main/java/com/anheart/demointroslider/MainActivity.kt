package com.anheart.demointroslider

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val introSliderAdapter = IntroSliderAdapter(
        listOf(
            IntroSlide(
                "UNIQUE ID VERIFICATION",
                "One time secure login with unique ID verification.",
                R.drawable.icon_one
            ),
            IntroSlide(
                "QR CODE SCANNING",
                "Quick & Efficient way to scan while keeping a safe distance.",
                R.drawable.icon_two
            ),
            IntroSlide(
                "ATTENDANCE TIME STAMP",
                "Check in & Check out time is marked as soon as the code is scanned.",
                R.drawable.icon_three
            ),
            IntroSlide(
                "GEO-LOCATION TAGGING",
                "Check in & Check out location is also marked as soon as the code is scanned.",
                R.drawable.icon_four
            ),
            IntroSlide(
                "AUTOMATED SELFIES",
                "Automatic selfie camera activates as soon as the code is scanned.",
                R.drawable.icon_five
            )
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        introSliderViewPager.adapter = introSliderAdapter
        setupIndicators()
        setCurrentIndicator(0)
        introSliderViewPager.registerOnPageChangeCallback(object:
            ViewPager2.OnPageChangeCallback(){

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)

                    setCurrentIndicator(position)

//                    if(position == introSliderAdapter.itemCount){
//                        Intent(applicationContext,AnotherActivity::class.java).also {
//                            startActivity(it)
//                        }
//                    }
                }
            }
        )

        buttonNext.setOnClickListener{
            if(introSliderViewPager.currentItem + 1 < introSliderAdapter.itemCount){
                introSliderViewPager.currentItem += 1
            }
            else{
                Intent(applicationContext,AnotherActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        }

        textSkipIntro.setOnClickListener {
            Intent(applicationContext,AnotherActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }
    }

    private fun setupIndicators(){
        val indicators = arrayOfNulls<ImageView>(introSliderAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)

        for(i in indicators.indices){
            indicators[i] = ImageView(applicationContext)
            indicators[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
                this?.layoutParams = layoutParams
            }
            indicatorsContainer.addView(indicators[i])
        }
    }

    private fun setCurrentIndicator(index: Int){
        val childCount = indicatorsContainer.childCount

        for(i in 0 until childCount){
            val imageView = indicatorsContainer[i] as ImageView
            if(i == index){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
            }
            else{
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
            }
        }

    }

}