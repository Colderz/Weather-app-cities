package pakiet.arkadiuszzimny.recruitmentweatherapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
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

        val animation = AnimationUtils.loadAnimation(context, R.anim.circle_scale_enter_from).apply {
            duration = 700
            interpolator = AccelerateDecelerateInterpolator()
        }

        binding.floatingButtonList.setOnClickListener {
            binding.floatingButtonList.isVisible = false
            binding.circleView.isVisible = true
            binding.circleView.startAnimation(animation) {
                binding.root.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.colorPrimary))
                binding.circleView.isVisible = false
                binding.botttomListLayout.isVisible = false
            }
        }

        return binding.root
    }
}