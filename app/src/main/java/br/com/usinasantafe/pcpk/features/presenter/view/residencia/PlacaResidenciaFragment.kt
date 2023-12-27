package br.com.usinasantafe.pcpk.features.presenter.view.residencia

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.residencia.PlacaResidenciaViewModel

class PlacaResidenciaFragment : Fragment() {

    companion object {
        fun newInstance() = PlacaResidenciaFragment()
    }

    private lateinit var viewModel: PlacaResidenciaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_placa_residencia, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PlacaResidenciaViewModel::class.java)
        // TODO: Use the ViewModel
    }

}