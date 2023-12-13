package br.com.usinasantafe.pcpk.features.presenter.view.common

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.common.MenuApontListViewModel

class MenuApontListFragment : Fragment() {

    companion object {
        fun newInstance() = MenuApontListFragment()
    }

    private lateinit var viewModel: MenuApontListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu_apont_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MenuApontListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}