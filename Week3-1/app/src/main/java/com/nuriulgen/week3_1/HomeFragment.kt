package com.nuriulgen.week3_1

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nuriulgen.week3_1.databinding.FragmentHomeBinding
import android.os.Handler
import android.os.Looper


class HomeFragment : Fragment() {

    // DataBinding
    private lateinit var binding : FragmentHomeBinding

    // variables
    private var runnable : Runnable = Runnable{}
    private var handler = Handler(Looper.myLooper()!!)
    private var countValue : Int = 0
    private var timeWhenStopped: Int = 0

    companion object {
        // Singleton
        const val Count_KEY = "count_key"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        /*
         keeps the last stopwatch value when exiting the app
         */
        outState.getInt(Count_KEY,countValue)
    }
    @SuppressLint("SetTextI18n")
    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        binding.start.setOnClickListener{
            startButton()
        }

        binding.restartButton.setOnClickListener {
            restartButton()
        }

        binding.pause.setOnClickListener {
            //pauses the stopwatch
            if (savedInstanceState != null) {
                countValue = savedInstanceState.getInt(Count_KEY,0)
                timeWhenStopped = countValue - timeWhenStopped
                binding.counterText.text = "Pause: $timeWhenStopped"
            }
        }
    }

   @SuppressLint("SetTextI18n")

   private fun startButton(){
       // starts the stopwatch

        countValue = 0

        runnable = Runnable {
            countValue += 1
            binding.counterText.text = "Counter: $countValue"
            handler.postDelayed(runnable,1000)
        }
        handler.post(runnable)
    }

  @SuppressLint("SetTextI18n")
  private fun restartButton(){
      //reset the stopwatch

      handler.removeCallbacks(runnable)
      countValue = 0
      binding.counterText.text = "Counter: $countValue"
  }

}