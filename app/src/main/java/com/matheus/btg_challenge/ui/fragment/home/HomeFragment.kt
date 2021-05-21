package com.matheus.btg_challenge.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.matheus.btg_challenge.R
import com.matheus.btg_challenge.network.service.ApiList
import com.matheus.btg_challenge.repository.ListRepository
import org.koin.android.ext.android.inject


class HomeFragment : Fragment() {
    private val remoteCurrencyList : ApiList by inject()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ListRepository(remoteCurrencyList).getCurrencyListFromApi()
    }


}
