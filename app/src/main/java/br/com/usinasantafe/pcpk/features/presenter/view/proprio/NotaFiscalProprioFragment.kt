package br.com.usinasantafe.pcpk.features.presenter.view.proprio

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.proprio.NotaFiscalProprioViewModel

class NotaFiscalProprioFragment : Fragment() {

    companion object {
        fun newInstance() = NotaFiscalProprioFragment()
    }

    private lateinit var viewModel: NotaFiscalProprioViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_nota_fiscal_proprio, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NotaFiscalProprioViewModel::class.java)
        // TODO: Use the ViewModel
    }

}