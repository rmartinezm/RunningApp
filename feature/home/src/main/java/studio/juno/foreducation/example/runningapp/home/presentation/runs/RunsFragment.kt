package studio.juno.foreducation.example.runningapp.home.presentation.runs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.android.ext.android.get
import studio.juno.foreducation.example.runningapp.home.R
import studio.juno.foreducation.example.runningapp.home.databinding.FragmentRunsBinding
import studio.juno.foreducation.example.runningapp.navigation.FeatureFlow
import studio.juno.foreducation.example.runningapp.navigation.feature_flow.race_tracking.RaceTrackingFeatureFlow

/* */
class RunsFragment : Fragment() {

    /* */
    private val binding: FragmentRunsBinding
            by lazy { FragmentRunsBinding.inflate(layoutInflater) }

    /**
     *
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    /**
     *
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViews()
    }

    /**
     *
     */
    private fun setupViews() {
        setupViewsClickListeners()
    }

    /**
     *
     */
    private fun setupViewsClickListeners() {
        binding.fabNewRace.setOnClickListener(::onNewRaceClickListener)
    }

    /**
     *
     */
    private fun onNewRaceClickListener(view: View) {
        val featureFlow: FeatureFlow = get<RaceTrackingFeatureFlow>()
        featureFlow.execute(requireContext())
    }

}