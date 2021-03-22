package pakiet.arkadiuszzimny.recruitmentweatherapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pakiet.arkadiuszzimny.recruitmentweatherapp.databinding.InfoCardBinding

class ListDataAdapter(
    private val cityArray: Array<String>,
    private val weatherArray: Array<String>,
    private val smallestArray: Array<Double>,
    private val highestArray: Array<Double>
) : RecyclerView.Adapter<ListDataAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(InfoCardBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(cityArray[position], weatherArray[position], smallestArray[position], highestArray[position])
    }

    override fun getItemCount(): Int {
        return cityArray.size
    }

    inner class ViewHolder(private val itemBinding: InfoCardBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(cityName: String, cityWeather: String, smallestTemp: Double, highestTemp: Double) {
            itemBinding.textViewCity.text = cityName
            itemBinding.weatherTextView.text = cityWeather
            itemBinding.textViewTempS.text = smallestTemp.toString()
            itemBinding.textViewTempH.text = highestTemp.toString()
            itemBinding.root.setOnClickListener {
                //val
            }
        }

    }

}