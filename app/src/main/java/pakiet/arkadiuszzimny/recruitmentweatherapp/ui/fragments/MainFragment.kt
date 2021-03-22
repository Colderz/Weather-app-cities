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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import pakiet.arkadiuszzimny.recruitmentweatherapp.*
import pakiet.arkadiuszzimny.recruitmentweatherapp.databinding.MainFragmentLayoutBinding
import pakiet.arkadiuszzimny.recruitmentweatherapp.ui.MainViewModel
import pakiet.arkadiuszzimny.recruitmentweatherapp.ui.adapters.HighTempAdapter
import pakiet.arkadiuszzimny.recruitmentweatherapp.ui.adapters.ListDataAdapter

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: MainFragmentLayoutBinding
    private lateinit var recyclerViewHigh: RecyclerView
    private lateinit var recyclerViewInfo: RecyclerView
    private var counter: Int = 0

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
            if (counter == 0) {
                binding.floatingButtonList.isVisible = true
                binding.circleView.isVisible = true
                binding.circleView.setBackgroundResource(R.drawable.circle)
                binding.circleView.startAnimation(animation) {
                    binding.root.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.colorPrimarySecond
                        )
                    )
                    binding.circleView.isVisible = false
                    binding.mainLL.isVisible = false
                    binding.recyclerViewInfo.isVisible = true
                    counter = 1
                }
            } else {
                binding.floatingButtonList.isVisible = true
                binding.circleView.isVisible = true
                binding.circleView.setBackgroundResource(R.drawable.circle_second)
                binding.circleView.startAnimation(animation) {
                    binding.root.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.colorPrimary
                        )
                    )
                    binding.circleView.isVisible = false
                    binding.mainLL.isVisible = true
                    binding.recyclerViewInfo.isVisible = false
                    counter = 0
                }

            }
        }
        setSmallestTempAcrossTextView(viewModel.getSmallestAcross(viewModel.weatherItems))
        setSmallestAverageCityTextView(viewModel.getCityWithSmallestAverageFun(viewModel.weatherItems))
        recyclerViewHigh = binding.rv
        recyclerViewHigh.layoutManager =
            LinearLayoutManager(requireContext().applicationContext, RecyclerView.HORIZONTAL, false)
        recyclerViewHigh.adapter =
            HighTempAdapter(
                viewModel.getArrayWithCity(viewModel.weatherItems),
                viewModel.getArrayWithMaxTemp(viewModel.weatherItems)
            )
        recyclerViewInfo = binding.recyclerViewInfo
        recyclerViewInfo.layoutManager = LinearLayoutManager(requireContext().applicationContext)
        recyclerViewInfo.adapter = ListDataAdapter(
            viewModel.getArrayWithCity(viewModel.weatherItems),
            viewModel.getArrayWithWeather(viewModel.weatherItems),
            viewModel.getArrayWithSmallestTemp(viewModel.weatherItems),
            viewModel.getArrayWithMaxTemp(viewModel.weatherItems)
        )

        viewModel.printSmallestTemp(viewModel.weatherItems)
        viewModel.printMaxTempForEachCity(viewModel.weatherItems)
        viewModel.printCityWithSmallestAvg(viewModel.weatherItems)
        return binding.root
    }

    private fun setSmallestTempAcrossTextView(temp: Double) {
        binding.textViewMinAcross.text = temp.toString()
    }

    private fun setSmallestAverageCityTextView(city: String) {
        binding.textCityAverage.text = city
    }
}