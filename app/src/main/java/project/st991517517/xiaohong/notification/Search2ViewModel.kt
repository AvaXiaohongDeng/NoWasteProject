/*
 * Name: Xiaohong Deng
 * Student ID: 991517517
 * Assignment: Final Group Project - NoWasteFinalProject APP
 * Nov 28, 2021
 *
 * Description of Search2ViewModel class:
 * This is to create a ViewModel to provide a string information to UI
 *
 * @author dengxiao
* */
package project.st991517517.xiaohong.notification

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Search2ViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Display \nFoods Will Expire Tomorrow"
    }
    val text: LiveData<String> = _text
}