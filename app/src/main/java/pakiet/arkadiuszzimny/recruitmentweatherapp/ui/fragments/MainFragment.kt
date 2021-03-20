package pakiet.arkadiuszzimny.recruitmentweatherapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import pakiet.arkadiuszzimny.recruitmentweatherapp.*
import pakiet.arkadiuszzimny.recruitmentweatherapp.databinding.MainFragmentLayoutBinding
import pakiet.arkadiuszzimny.recruitmentweatherapp.ui.MainViewModel

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: MainFragmentLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainFragmentLayoutBinding.inflate(inflater, container, false)

        val animation =
            AnimationUtils.loadAnimation(context, R.anim.circle_scale_enter_from).apply {
                duration = 700
                interpolator = AccelerateDecelerateInterpolator()
            }

        binding.floatingButtonList.setOnClickListener {
            binding.floatingButtonList.isVisible = false
            binding.circleView.isVisible = true
            binding.circleView.startAnimation(animation) {
                binding.root.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.colorPrimary
                    )
                )
                binding.circleView.isVisible = false
                binding.botttomListLayout.isVisible = false
            }
        }
        setMinimumTempAcrossTextView(viewModel.getSmallestAcross())


        return binding.root
    }

    private fun setMinimumTempAcrossTextView(temp: Double) {
        binding.textViewMinAcross.text = temp.toString()
    }
}