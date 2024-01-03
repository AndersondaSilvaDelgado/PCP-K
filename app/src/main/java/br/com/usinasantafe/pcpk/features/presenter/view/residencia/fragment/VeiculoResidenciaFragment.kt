package br.com.usinasantafe.pcpk.features.presenter.view.residencia.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.residencia.VeiculoResidenciaViewModel

class VeiculoResidenciaFragment : Fragment() {

    companion object {
        fun newInstance() = VeiculoResidenciaFragment()
    }

    private lateinit var viewModel: VeiculoResidenciaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_veiculo_residencia, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(VeiculoResidenciaViewModel::class.java)
        // TODO: Use the ViewModel
    }

}