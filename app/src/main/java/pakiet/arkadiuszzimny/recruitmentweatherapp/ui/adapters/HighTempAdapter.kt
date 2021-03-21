package pakiet.arkadiuszzimny.recruitmentweatherapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pakiet.arkadiuszzimny.recruitmentweatherapp.databinding.CityMaxCardBinding


class HighTempAdapter(
    private val cityArray: Array<String>,
    private val highestArray: Array<Double>
): RecyclerView.Adapter<HighTempAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CityMaxCardBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(cityArray[position], highestArray[position])
    }

    override fun getItemCount(): Int {
        return cityArray.size
    }

    inner class ViewHolder(private val itemBinding: CityMaxCardBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(cityName: String, highestTemp: Double) {
            itemBinding.textViewCity.text = cityName
            itemBinding.textViewTemp.text = highestTemp.toString()
        }
    }
}