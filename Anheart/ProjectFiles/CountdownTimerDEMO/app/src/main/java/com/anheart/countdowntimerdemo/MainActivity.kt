package com.anheart.countdowntimerdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var countDownTimer: CountDownTimer? = null;
    private var timerDuration: Long = 60000;
    private var pauseOffset: Long = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_timer.text = "${(timerDuration/1000).toString()}"

        btn_start.setOnClickListener {
            startTimer(pauseOffset);
        }

        btn_pause.setOnClickListener {
            pauseTimer()
        }

        btn_reset.setOnClickListener {
            resetTimer()
        }
    }

    private fun startTimer(pauseOffsetL: Long){
        countDownTimer = object: CountDownTimer(timerDuration - pauseOffsetL,1000){
            override fun onTick(millisUntilFinished: Long) {
                pauseOffset = timerDuration - millisUntilFinished;
                tv_timer.text = "${(millisUntilFinished/1000).toString()}";
            }

            override fun onFinish() {
                Toast.makeText(this@MainActivity,"Timer is completed",Toast.LENGTH_SHORT).show();
            }
        }.start()
    }

    private fun pauseTimer(){
        if(countDownTimer!=null){
            countDownTimer!!.cancel()
        }
    }

    private fun resetTimer(){
        if(countDownTimer!=null){
            countDownTimer!!.cancel()
            tv_timer.text = "${(timerDuration/1000).toString()}"
            pauseOffset = 0;
            countDownTimer = null;
        }
    }
}
