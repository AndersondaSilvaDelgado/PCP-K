package br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.visitterc.ObservVisitTercViewModel

class ObservVisitTercFragment : Fragment() {

    companion object {
        fun newInstance() = ObservVisitTercFragment()
    }

    private lateinit var viewModel: ObservVisitTercViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_observ_visit_terc, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ObservVisitTercViewModel::class.java)
        // TODO: Use the ViewModel
    }

}