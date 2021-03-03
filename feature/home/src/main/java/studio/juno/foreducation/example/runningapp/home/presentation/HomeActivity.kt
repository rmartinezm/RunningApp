package studio.juno.foreducation.example.runningapp.home.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import studio.juno.foreducation.example.runningapp.home.R
import studio.juno.foreducation.example.runningapp.home.databinding.ActivityHomeBinding

/* */
class HomeActivity : AppCompatActivity() {

    /* */
    private val binding: ActivityHomeBinding
            by lazy { ActivityHomeBinding.inflate(layoutInflater) }

    /* */
    private val navController: NavController by lazy {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navHostFragment.navController
    }

    /**
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupNavController()
    }

    /**
     *
     */
    private fun setupNavController() {
        navController.setGraph(R.navigation.navigation_graph_home, intent.extras)
        binding.bottomNavigationView.setupWithNavController(navController)
    }

    /**
     *
     */
    override fun onSupportNavigateUp(): Boolean =
        navController.navigateUp()

}