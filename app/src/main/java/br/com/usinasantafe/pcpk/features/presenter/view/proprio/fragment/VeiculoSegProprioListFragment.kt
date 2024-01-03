package br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.common.adapter.CustomAdapter
import br.com.usinasantafe.pcpk.common.base.BaseFragment
import br.com.usinasantafe.pcpk.common.extension.showGenericAlertDialog
import br.com.usinasantafe.pcpk.databinding.FragmentVeiculoSegProprioListBinding
import br.com.usinasantafe.pcpk.features.presenter.view.proprio.FragmentAttachListenerProprio
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.proprio.VeiculoSegProprioListFragmentState
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.proprio.VeiculoSegProprioListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VeiculoSegProprioListFragment : BaseFragment<FragmentVeiculoSegProprioListBinding>(
    R.layout.fragment_veiculo_seg_proprio_list,
    FragmentVeiculoSegProprioListBinding::bind,
) {

    private val viewModel: VeiculoSegProprioListViewModel by viewModels()
    private var fragmentAttachListenerProprio: FragmentAttachListenerProprio? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeState()
        startEvents()
        setListener()

    }

    private fun observeState(){
        viewModel.uiLiveData.observe(viewLifecycleOwner) {
            state -> handleStateChange(state)
        }
    }

    private fun startEvents() {
        viewModel.recoverListEquipSeg()
    }

    private fun setListener() {
        with(binding) {
            buttonInserirVeiculoSeg.setOnClickListener {
                viewModel.setStatusMovEquipAddVeicSeg()
            }
            buttonOkVeiculoSeg.setOnClickListener {
                viewModel.setStatusMovEquipAddMotorista()
            }
            buttonCancVeiculoSeg.setOnClickListener {
                viewModel.setStatusMovEquipAddVeic()
            }
        }
    }

    private fun handleStateChange(state: VeiculoSegProprioListFragmentState){
        when(state){
            is VeiculoSegProprioListFragmentState.ListEquipSeg -> handleListEquipSeg(state.equipSegList)
            is VeiculoSegProprioListFragmentState.CheckDeleteEquipProprioSeg -> handleCheckDeleteEquipSeg(state.check)
            is VeiculoSegProprioListFragmentState.CheckSetStatusMovAddVeicSeg -> handleCheckStatusMovEquipAddVeicSeg(state.check)
            is VeiculoSegProprioListFragmentState.CheckSetStatusMovAddMotorista -> handleCheckStatusMovEquipAddMotorista(state.check)
            is VeiculoSegProprioListFragmentState.CheckSetStatusMovAddVeic -> handleCheckStatusMovEquipAddVeic(state.check)
        }
    }

    private fun handleListEquipSeg(equipSegList: List<String>) {
        viewList(equipSegList)
    }

    private fun handleCheckDeleteEquipSeg(check: Boolean) {
        if(check) {
            viewModel.recoverListEquipSeg()
            return
        }
        showGenericAlertDialog(getString(R.string.texto_failure_delete, "VEÍCULO SECUNDÁRIO"), requireContext())
    }

    private fun handleCheckStatusMovEquipAddVeicSeg(check: Boolean) {
        if(check) {
            fragmentAttachListenerProprio?.goVeiculoProprio()
            return
        }
        showGenericAlertDialog(getString(R.string.texto_failure_app), requireContext())
    }

    private fun handleCheckStatusMovEquipAddMotorista(check: Boolean) {
        if(check) {
            fragmentAttachListenerProprio?.goMatricColab()
            return
        }
        showGenericAlertDialog(getString(R.string.texto_failure_app), requireContext())
    }

    private fun handleCheckStatusMovEquipAddVeic(check: Boolean) {
        if(check) {
            fragmentAttachListenerProprio?.goVeiculoProprio()
            return
        }
        showGenericAlertDialog(getString(R.string.texto_failure_app), requireContext())
    }

    private fun viewList(equipSegList: List<String>) {

        val localListView = equipSegList.map { it }

        val listAdapter = CustomAdapter(localListView).apply {
            onItemClick = { _, pos ->
                showMessage(pos)
            }
        }
        binding.listViewVeiculoSeg.run {
            setHasFixedSize(true)
            adapter = listAdapter
        }
    }

    private fun showMessage(pos: Int){
        AlertDialog.Builder(requireContext())
            .setMessage("DESEJA EXCLUIR O VEÍCULO?")
            .setPositiveButton("SIM") { _, _ ->
                viewModel.deleteEquipSeg(pos)
            }
            .setNegativeButton("NÃO", null)
            .create()
            .show()
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is FragmentAttachListenerProprio){
            fragmentAttachListenerProprio = context
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentAttachListenerProprio = null
    }

}