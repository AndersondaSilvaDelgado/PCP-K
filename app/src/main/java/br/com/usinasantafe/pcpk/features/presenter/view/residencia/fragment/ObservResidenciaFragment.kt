package br.com.usinasantafe.pcpk.features.presenter.view.residencia.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.residencia.ObservResidenciaViewModel

class ObservResidenciaFragment : Fragment() {

    companion object {
        fun newInstance() = ObservResidenciaFragment()
    }

    private lateinit var viewModel: ObservResidenciaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_observ_residencia, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ObservResidenciaViewModel::class.java)
        // TODO: Use the ViewModel
    }

}