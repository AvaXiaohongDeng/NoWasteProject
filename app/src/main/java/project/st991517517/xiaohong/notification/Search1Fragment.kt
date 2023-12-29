/*
 * Name: Xiaohong Deng
 * Student ID: 991517517
 * Assignment: Final Group Project - NoWasteFinalProject APP
 * Nov 28, 2021
 *
 * Description of Search1Fragment class:
 * This is to create a fragment displaying all expired foods.
 *
 * @author dengxiao
* */
package project.st991517517.xiaohong.notification

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.view.*
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import project.st991517517.xiaohong.*
import project.st991517517.xiaohong.Intro.IntroActivity
import project.st991517517.xiaohong.databinding.FragmentSearch1Binding

class Search1Fragment : Fragment() {

    private lateinit var search1ViewModel: Search1ViewModel
    private var _binding: FragmentSearch1Binding? = null
    private lateinit var tVDisplay: TextView
    private lateinit var imageView: ImageView

    private val viewModel: InventoryViewModel by activityViewModels {
        InventoryViewModelFactory(
            (activity?.application as InventoryApplication).database.itemDao()
        )
    }

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        search1ViewModel =
            ViewModelProvider(this).get(Search1ViewModel::class.java)

        _binding = FragmentSearch1Binding.inflate(inflater, container, false)
        val root: View = binding.root

        val tVTitle: TextView = binding.tVTitle
        search1ViewModel.text.observe(viewLifecycleOwner, Observer {
            tVTitle.text = it
        })

        //Initialize
        tVDisplay = binding.tVDisplay
        imageView = binding.imageView
        tVDisplay.text = ""

        viewModel.expiredItems.observe(this.viewLifecycleOwner){ items ->
            items.let{
                for (item in it){
                    tVDisplay.append("\n\t\t${item.itemName}\t\t\t\t\t${item.quantityInStock}\t\t\t\t\t${item.expiry_date}\n")
                }
            }
        }

        // call animationText to dynamically display the textView
        animationText()
        animationImage()

        //Show the options menu in this fragment
        setHasOptionsMenu(true)
        return root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //Create options menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.searchmenu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_exit -> {
                //System.exit(0)
                getActivity()?.moveTaskToBack(true)
                getActivity()?.finish()
            }
            R.id.action_homepage ->{
                activity?.let{
                    val intent = Intent (it, MainActivity::class.java)
                    it.startActivity(intent)
                }
            }
            R.id.action_intropage ->{
                activity?.let{
                    val intent = Intent (it, IntroActivity::class.java)
                    it.startActivity(intent)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun animationText() {
        tVDisplay.visibility = View.VISIBLE

        val colorDrawables = arrayOf(
            ColorDrawable(Color.GREEN),
            ColorDrawable(Color.RED),
            ColorDrawable(Color.YELLOW))
        val transitionDrawable = TransitionDrawable(colorDrawables)
        tVDisplay.background = transitionDrawable
        transitionDrawable.startTransition(3000)
    }

    fun animationImage(){
        imageView.visibility = View.VISIBLE
        activity?.let{
           // val animation = AnimationUtils.loadAnimation(it, R.anim.rotate)
            val animation = AnimationUtils.loadAnimation(it, R.anim.rotate)
            imageView.startAnimation(animation)
        }
    }
}
