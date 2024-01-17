package br.com.usinasantafe.pcpk.features.presenter.view.proprio.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.common.adapter.CustomAdapter
import br.com.usinasantafe.pcpk.common.base.BaseFragment
import br.com.usinasantafe.pcpk.common.extension.showGenericAlertDialog
import br.com.usinasantafe.pcpk.common.extension.showGenericAlertDialogCheck
import br.com.usinasantafe.pcpk.common.utils.TypeAddOcupante
import br.com.usinasantafe.pcpk.common.utils.TypeAddEquip
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
    private lateinit var typeAddEquip : TypeAddEquip
    private var pos: Int = 0

    companion object {
        const val KEY_TYPE_VEIC_PROPRIO_SEG_LIST = "key_type_veic_proprio_seg_list";
        const val KEY_POS_EQUIP_SEG_PROPRIO = "key_pos_equip_seg_proprio";
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        typeAddEquip = TypeAddEquip.values()[arguments?.getInt(KEY_TYPE_VEIC_PROPRIO_SEG_LIST)!!]
        pos = arguments?.getInt(KEY_POS_EQUIP_SEG_PROPRIO)!!
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
                fragmentAttachListenerProprio?.goVeiculoProprio(typeAddEquip)
            }
            buttonOkVeiculoSeg.setOnClickListener {
                when(typeAddEquip) {
                    TypeAddEquip.ADDVEICULO,
                    TypeAddEquip.ADDVEICULOSEG -> fragmentAttachListenerProprio?.goMatricColab(TypeAddOcupante.ADDMOTORISTA)
                    TypeAddEquip.CHANGEVEICULO,
                    TypeAddEquip.CHANGEVEICULOSEG -> fragmentAttachListenerProprio?.goDetalhe(pos)
                }
            }
            buttonCancVeiculoSeg.setOnClickListener {
                when(typeAddEquip) {
                    TypeAddEquip.ADDVEICULO,
                    TypeAddEquip.ADDVEICULOSEG -> fragmentAttachListenerProprio?.goVeiculoProprio(TypeAddEquip.ADDVEICULO)
                    TypeAddEquip.CHANGEVEICULO,
                    TypeAddEquip.CHANGEVEICULOSEG -> fragmentAttachListenerProprio?.goDetalhe(pos)
                }
            }
        }
    }

    private fun handleStateChange(state: VeiculoSegProprioListFragmentState){
        when(state){
            is VeiculoSegProprioListFragmentState.ListEquipSeg -> handleListEquipSeg(state.equipSegList)
            is VeiculoSegProprioListFragmentState.CheckDeleteEquipProprioSeg -> handleCheckDeleteEquipSeg(state.check)
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
        showGenericAlertDialogCheck("DESEJA EXCLUIR O VEÍCULO?", requireContext()) {
            viewModel.deleteEquip(pos)
        }
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