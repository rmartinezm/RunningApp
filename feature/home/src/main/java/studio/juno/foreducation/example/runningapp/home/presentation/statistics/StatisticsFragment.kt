package studio.juno.foreducation.example.runningapp.home.presentation.statistics

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import studio.juno.foreducation.example.runningapp.home.R
import studio.juno.foreducation.example.runningapp.home.databinding.FragmentStatisticsBinding

/* */
class StatisticsFragment : Fragment() {

    /* */
    private val binding: FragmentStatisticsBinding
            by lazy { FragmentStatisticsBinding.inflate(layoutInflater) }

    /**
     *
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

}