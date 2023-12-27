package br.com.usinasantafe.pcpk.features.presenter.view.proprio

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.proprio.NomeColabViewModel

class NomeColabFragment : Fragment() {

    companion object {
        fun newInstance() = NomeColabFragment()
    }

    private lateinit var viewModel: NomeColabViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_nome_colab, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NomeColabViewModel::class.java)
        // TODO: Use the ViewModel
    }

}