package br.com.usinasantafe.pcpk.features.presenter.view.common

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.common.NomeVigiaViewModel

class NomeVigiaFragment : Fragment() {

    companion object {
        fun newInstance() = NomeVigiaFragment()
    }

    private lateinit var viewModel: NomeVigiaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_nome_vigia, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NomeVigiaViewModel::class.java)
        // TODO: Use the ViewModel
    }

}