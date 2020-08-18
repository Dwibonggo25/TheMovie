package com.example.jetpack

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.jetpack.databinding.ActivityMainBinding
import com.example.jetpack.ui.detailmovies.DetailMoviesFragment
import com.example.jetpack.utils.Menu
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector, DetailMoviesFragment.OnSetTitleToollbar {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<androidx.fragment.app.Fragment>

    private lateinit var toolbar: Toolbar

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        initBinding()

        val navHostFragment= supportFragmentManager.findFragmentById(R.id.main_nav_fragment) as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.navigation)
        navHostFragment.navController.graph = graph
        navController = navHostFragment.navController

        setupToolbar()
        setupBottomNavigation()

        handleDeeplinkData()

    }

    private fun handleDeeplinkData() {
        val extras = intent?.extras
        if (extras?.getString(KEY_NEXT_ACTION_MENU_ID) != null) {
            val nextActionMenuId = extras.getString(KEY_NEXT_ACTION_MENU_ID)
            handleNotificationData(nextActionMenuId!! )
        }
    }

    private fun handleNotificationData(menuId: String) {
        when(menuId){
            Menu.DETAIL_MOVIES.id -> navController.navigate(R.id.detailMoviesFragment)
        }
    }


    private fun setupBottomNavigation() {
        bottomNavigationView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener(navigationListener)
    }

    private fun setupToolbar() {
        toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        setupActionBarWithNavController(navController)
    }

    private val navigationListener =
        NavController.OnDestinationChangedListener { controller, destination, bundle ->
            when (destination.id) {
                R.id.popularMoviesFragment -> {
                    showBottomNavigation(false)
                    showToolbar(true)
                    showToolbarBackArrow(true)
                }
                R.id.homeFragment -> {
                    showBottomNavigation(true)
                    showToolbar(false)
                    showToolbarBackArrow(false)
                }
                R.id.topRatedMoviesFragment -> {
                    showBottomNavigation(false)
                    showToolbar(true)
                    showToolbarBackArrow(true)
                }
                R.id.proffileFragment -> {
                    showBottomNavigation(true)
                    showToolbar(false)
                    showToolbarBackArrow(false)
                }
                R.id.detailMoviesFragment -> {
                    showBottomNavigation(true)
                    showToolbar(true)
                    showToolbarBackArrow(true)
                }
                R.id.favoriteFragment -> {
                    showBottomNavigation(true)
                    showToolbar(true)
                    showToolbarBackArrow(true)
                }
                else -> {
                    showBottomNavigation(true)
                    showToolbar(true)
                    showToolbarBackArrow(false)
                }
            }
        }


    private fun showToolbar(shouldShow: Boolean) {
        if (shouldShow) toolbar.visibility = View.VISIBLE else toolbar.visibility = View.GONE
    }

    private fun showBottomNavigation(shouldShow: Boolean) {
        if (shouldShow) binding.bottomNavigationView.visibility =
            View.VISIBLE else binding.bottomNavigationView.visibility = View.GONE
    }

    private fun showToolbarBackArrow(shouldShow: Boolean) {
        if (shouldShow) supportActionBar?.setDisplayHomeAsUpEnabled(true) else supportActionBar?.setDisplayHomeAsUpEnabled(
            false
        )
    }

    private fun changeToolbarSubtitle(subtitle: String?) {
        supportActionBar?.title = subtitle
    }

    private fun initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.executePendingBindings()
    }

    override fun onBackPressed() {

        when (navController.currentDestination?.id) {
            R.id.homeFragment -> showExitAppConfirmation()
            else -> {
                navController.navigateUp()
            }
        }

    }

    private fun showExitAppConfirmation() {
        AlertDialog.Builder(this)
            .setTitle("Keluar")
            .setMessage("Mau yang mana")
            .setPositiveButton(android.R.string.yes) { dialogInterface, i ->
                dialogInterface.dismiss()
                finish()
            }
            .setNegativeButton(android.R.string.no) { dialogInterface, i -> dialogInterface.dismiss()  }
            .show()
    }

    override fun onSupportNavigateUp() = navController.navigateUp()

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    override fun getTitleToollbar(title: String) {
        changeToolbarSubtitle(title)
    }

}

