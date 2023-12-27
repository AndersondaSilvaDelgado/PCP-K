package br.com.usinasantafe.pcpk.features.presenter.view.visitterc

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.visitterc.CPFVisitTercViewModel

class CPFVisitTercFragment : Fragment() {

    companion object {
        fun newInstance() = CPFVisitTercFragment()
    }

    private lateinit var viewModel: CPFVisitTercViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cpf_visit_terc, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CPFVisitTercViewModel::class.java)
        // TODO: Use the ViewModel
    }

}