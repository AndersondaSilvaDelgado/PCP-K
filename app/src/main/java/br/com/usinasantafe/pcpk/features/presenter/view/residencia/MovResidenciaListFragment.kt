package br.com.usinasantafe.pcpk.features.presenter.view.residencia

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.residencia.MovResidenciaListViewModel

class MovResidenciaListFragment : Fragment() {

    companion object {
        fun newInstance() = MovResidenciaListFragment()
    }

    private lateinit var viewModel: MovResidenciaListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mov_equip_residencia_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MovResidenciaListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}