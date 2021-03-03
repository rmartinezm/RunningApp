package studio.juno.foreducation.example.runningapp.home.presentation.about

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import studio.juno.foreducation.example.runningapp.home.R
import studio.juno.foreducation.example.runningapp.home.databinding.FragmentAboutBinding

/* */
class AboutFragment : Fragment() {

    /* */
    private val binding: FragmentAboutBinding
            by lazy { FragmentAboutBinding.inflate(layoutInflater) }

    /**
     *
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

}