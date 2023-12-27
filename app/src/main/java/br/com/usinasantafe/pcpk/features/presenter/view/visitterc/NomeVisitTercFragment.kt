package br.com.usinasantafe.pcpk.features.presenter.view.visitterc

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.visitterc.NomeVisitTercViewModel

class NomeVisitTercFragment : Fragment() {

    companion object {
        fun newInstance() = NomeVisitTercFragment()
    }

    private lateinit var viewModel: NomeVisitTercViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_nome_visit_terc, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NomeVisitTercViewModel::class.java)
        // TODO: Use the ViewModel
    }

}