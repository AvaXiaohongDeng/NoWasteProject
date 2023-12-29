/*
 * Name: Xiaohong Deng
 * Student ID: 991517517
 * Assignment: Final Group Project - NoWasteFinalProject APP
 * Nov 18, 2021
 *
 * Description of ItemListFragment class:
 * This is to create a fragment displaying details for all items in the database.
 *
 * @author dengxiao
* */
package project.st991517517.xiaohong


import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import project.st991517517.xiaohong.Intro.IntroActivity
import project.st991517517.xiaohong.data.ItemListAdapter
import project.st991517517.xiaohong.databinding.ItemListFragmentBinding
import project.st991517517.xiaohong.notification.SearchActivity


class ItemListFragment : Fragment() {

    private val viewModel: InventoryViewModel by activityViewModels {
        InventoryViewModelFactory(
            (activity?.application as InventoryApplication).database.itemDao()
        )
    }

    private var _binding: ItemListFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ItemListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ItemListAdapter {
            val action =    ItemListFragmentDirections.actionItemListFragmentToItemDetailFragment(it.id)
            this.findNavController().navigate(action)
        }
        binding.recyclerView.adapter = adapter

        viewModel.allItems.observe(this.viewLifecycleOwner) { items ->
            items.let {
                adapter.submitList(it)
            }
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.addItemButton.setOnClickListener {
            binding.addItemButton.visibility = View.GONE
            val action = ItemListFragmentDirections.actionItemListFragmentToAddItemFragment(
                getString(R.string.add_fragment_title)
            )
            this.findNavController().navigate(action)
        }

        binding.expiryButton.setOnClickListener {
            activity?.let{
                val intent2 = Intent (it, SearchActivity::class.java)
                it.startActivity(intent2)
            }
        }

        //Show the options menu in this fragment
        setHasOptionsMenu(true)
    }

    //Create options menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_exit -> {
                showConfirmationDialog()
            }
            R.id.action_intropage ->{
                activity?.let{
                    val intent = Intent (it, IntroActivity::class.java)
                    it.startActivity(intent)
                }
            }
            R.id.action_about ->{
                activity?.let{
                    val intent = Intent (it, AboutActivity::class.java)
                    it.startActivity(intent)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * Displays an alert dialog to get the user's confirmation before exit.
     */
    private fun showConfirmationDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(android.R.string.dialog_alert_title))
            .setMessage(getString(R.string.exit_question))
            .setCancelable(false)
            .setNegativeButton(getString(R.string.no)) { _, _ -> }
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                //System.exit(0)
                getActivity()?.moveTaskToBack(true)
                getActivity()?.finish()
            }
            .show()
    }
}
