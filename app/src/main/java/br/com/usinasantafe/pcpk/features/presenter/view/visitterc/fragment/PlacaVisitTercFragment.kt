package br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.visitterc.PlacaVisitTercViewModel

class PlacaVisitTercFragment : Fragment() {

    companion object {
        fun newInstance() = PlacaVisitTercFragment()
    }

    private lateinit var viewModel: PlacaVisitTercViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_placa_visit_terc, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PlacaVisitTercViewModel::class.java)
        // TODO: Use the ViewModel
    }

}