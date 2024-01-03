package br.com.usinasantafe.pcpk.features.presenter.view.visitterc.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.visitterc.PassagVisitTercListViewModel

class PassagVisitTercListFragment : Fragment() {

    companion object {
        fun newInstance() = PassagVisitTercListFragment()
    }

    private lateinit var viewModel: PassagVisitTercListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_passag_visit_terc_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PassagVisitTercListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}