package br.com.usinasantafe.pcpk.features.presenter.view.proprio

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.proprio.VeiculoProprioViewModel

class VeiculoProprioFragment : Fragment() {

    companion object {
        fun newInstance() = VeiculoProprioFragment()
    }

    private lateinit var viewModel: VeiculoProprioViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_veiculo_proprio, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(VeiculoProprioViewModel::class.java)
        // TODO: Use the ViewModel
    }

}