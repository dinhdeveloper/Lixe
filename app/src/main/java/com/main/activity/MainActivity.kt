package com.main.activity

import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.main.fragment.FilmFragment
import com.main.fragment.HomeFragment
import com.main.fragment.MusicFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    var doubleBackToExitPressed: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        //fullscreen
//        requestWindowFeature(Window.FEATURE_NO_TITLE)
//        this.window.setFlags(
//            WindowManager.LayoutParams.FLAG_FULLSCREEN,
//            WindowManager.LayoutParams.FLAG_FULLSCREEN
//        )
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navBottom.setOnNavigationItemSelectedListener(this)
        navBottom.menu.getItem(1).isChecked = true
        loadFragment(HomeFragment()) //load fragment in mainActivity

    }

    private fun loadFragment(fragment: Fragment): Boolean {
        if (fragment != null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.mainActivity, fragment)
                .commit()
            return true
        }
        return false
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment?
        fragment = when (item.itemId) {
            R.id.item_home -> {
                HomeFragment()
            }
            R.id.item_music -> {
                MusicFragment()
            }
            R.id.item_film -> {
                FilmFragment()
            }

            else -> HomeFragment()
        }
        return loadFragment(fragment)
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressed) {
            super.onBackPressed()
            return
        }
        this.doubleBackToExitPressed = true
        Toast.makeText(this, "Nhấn lần nữa để thoát", Toast.LENGTH_SHORT).show()
        Handler().postDelayed({ doubleBackToExitPressed = false }, 2000)
    }
}
