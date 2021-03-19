package pakiet.arkadiuszzimny.recruitmentweatherapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import pakiet.arkadiuszzimny.recruitmentweatherapp.R
import pakiet.arkadiuszzimny.recruitmentweatherapp.databinding.ActivityMainBinding
import pakiet.arkadiuszzimny.recruitmentweatherapp.ui.fragments.MainFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var fragment = MainFragment::class.java.newInstance()
        setFragment(fragment)
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}