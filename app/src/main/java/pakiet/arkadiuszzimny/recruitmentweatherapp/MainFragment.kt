package pakiet.arkadiuszzimny.recruitmentweatherapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import pakiet.arkadiuszzimny.recruitmentweatherapp.databinding.MainFragmentLayoutBinding

class MainFragment: Fragment() {

    lateinit var binding: MainFragmentLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainFragmentLayoutBinding.inflate(inflater, container, false)

        val animation = AnimationUtils.loadAnimation(context, R.anim.circle_scale_anim).apply {
            duration = 700
            interpolator = AccelerateDecelerateInterpolator()
        }


        return binding.root
    }
}