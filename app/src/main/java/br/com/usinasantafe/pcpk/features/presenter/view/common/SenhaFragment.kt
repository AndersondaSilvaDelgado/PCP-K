package br.com.usinasantafe.pcpk.features.presenter.view.common

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.common.SenhaViewModel

class SenhaFragment : Fragment() {

    companion object {
        fun newInstance() = SenhaFragment()
    }

    private lateinit var viewModel: SenhaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_senha, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SenhaViewModel::class.java)
        // TODO: Use the ViewModel
    }

}