package br.com.usinasantafe.pcpk.features.presenter.view.proprio

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.proprio.ObservProprioViewModel

class ObservProprioFragment : Fragment() {

    companion object {
        fun newInstance() = ObservProprioFragment()
    }

    private lateinit var viewModel: ObservProprioViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_observ_proprio, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ObservProprioViewModel::class.java)
        // TODO: Use the ViewModel
    }

}