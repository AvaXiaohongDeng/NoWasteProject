/*
 * Name: Xiaohong Deng
 * Student ID: 991517517
 * Assignment: Final Group Project - NoWasteFinalProject APP
 * Dec 5, 2021
 *
 * Description of AboutActivity class:
 * This activity is to display the developer information.
 *
 * @author dengxiao
* */
package project.st991517517.xiaohong

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import project.st991517517.xiaohong.Intro.IntroActivity
import project.st991517517.xiaohong.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity(R.layout.activity_about) {

    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        binding = ActivityAboutBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
    }

    //process menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.aboutmenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_homepage -> {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            true
        }

        R.id.action_intropage -> {
            val intent = Intent(this, IntroActivity::class.java)
            startActivity(intent)
            true
        }
        R.id.action_exit -> {
            //System.exit(0)
            moveTaskToBack(true)
            finish()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}