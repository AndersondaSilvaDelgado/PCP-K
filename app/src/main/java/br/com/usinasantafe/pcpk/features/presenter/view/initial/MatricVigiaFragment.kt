package br.com.usinasantafe.pcpk.features.presenter.view.initial

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.initial.MatricVigiaViewModel

class MatricVigiaFragment : Fragment() {

    companion object {
        fun newInstance() = MatricVigiaFragment()
    }

    private lateinit var viewModel: MatricVigiaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_matric_vigia, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MatricVigiaViewModel::class.java)
    }

}