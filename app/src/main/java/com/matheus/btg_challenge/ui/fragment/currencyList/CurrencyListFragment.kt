package com.matheus.btg_challenge.ui.fragment.currencyList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.matheus.btg_challenge.R
import org.koin.android.viewmodel.ext.android.viewModel
import com.google.android.material.snackbar.Snackbar
import android.widget.Toast
import androidx.lifecycle.Observer
import com.matheus.btg_challenge.database.dao.CurrencyDao
import com.matheus.btg_challenge.database.entity.Currency
import com.matheus.btg_challenge.databinding.FragmentCurrencyListBinding

class CurrencyListFragment : Fragment() {

    private val listViewModel : CurrencyListViewModel by viewModel()
    private var binding : FragmentCurrencyListBinding?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCurrencyListBinding.inflate(inflater, container, false)

        context ?: return binding?.root
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observerError()
        observerList()
    }

    private fun observerList() {
        listViewModel.getCurrencyList().observe(viewLifecycleOwner, Observer { currencies ->
            currencies.let {
                println("DATA $it")
            }
        })
    }

    private fun observerError() {
        listViewModel.error.observe(viewLifecycleOwner, Observer {
            val snackbar: Snackbar = Snackbar.make(requireView(), it, Snackbar.LENGTH_SHORT)

            snackbar.setAction("OK") {
                snackbar.dismiss()
            }

            snackbar.show()
        })
    }

}