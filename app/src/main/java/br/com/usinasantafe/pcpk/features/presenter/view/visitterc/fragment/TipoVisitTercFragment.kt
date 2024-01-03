package br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.visitterc.TipoVisitTercViewModel

class TipoVisitTercFragment : Fragment() {

    companion object {
        fun newInstance() = TipoVisitTercFragment()
    }

    private lateinit var viewModel: TipoVisitTercViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tipo_visit_terc, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TipoVisitTercViewModel::class.java)
        // TODO: Use the ViewModel
    }

}