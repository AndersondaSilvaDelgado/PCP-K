package br.com.usinasantafe.pcpk.features.presenter.viewmodel.proprio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.pcpk.common.utils.StatusMovEquipProprio
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.DeleteEquipProprioSeg
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.RecoverListEquipProprioSeg
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.SetStatusMov
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VeiculoSegProprioListViewModel @Inject constructor (
    private val deleteEquipProprioSeg: DeleteEquipProprioSeg,
    private val recoverListEquipProprioSeg: RecoverListEquipProprioSeg,
    private val setStatusMov: SetStatusMov,
): ViewModel() {

    private val _uiLiveData = MutableLiveData<VeiculoSegProprioListFragmentState>()
    val uiLiveData: LiveData<VeiculoSegProprioListFragmentState> = _uiLiveData

    private fun checkDeleteEquipProprioSeg(check: Boolean) {
        _uiLiveData.value = VeiculoSegProprioListFragmentState.CheckDeleteEquipProprioSeg(check)
    }

    private fun checkSetStatusMovEquipAddMotorista(check: Boolean) {
        _uiLiveData.value = VeiculoSegProprioListFragmentState.CheckSetStatusMovAddMotorista(check)
    }

    private fun checkSetStatusMovEquipAddVeicSeg(check: Boolean) {
        _uiLiveData.value = VeiculoSegProprioListFragmentState.CheckSetStatusMovAddVeicSeg(check)
    }

    private fun checkSetStatusMovEquipAddVeic(check: Boolean) {
        _uiLiveData.value = VeiculoSegProprioListFragmentState.CheckSetStatusMovAddVeic(check)
    }

    private fun setListEquipSeg(equipSegList: List<String>) {
        _uiLiveData.value = VeiculoSegProprioListFragmentState.ListEquipSeg(equipSegList)
    }

    fun deleteEquipSeg(pos: Int) = viewModelScope.launch {
        checkDeleteEquipProprioSeg(deleteEquipProprioSeg(pos))
    }

    fun setStatusMovEquipAddVeicSeg() = viewModelScope.launch {
        checkSetStatusMovEquipAddVeicSeg(setStatusMov(StatusMovEquipProprio.ADDVEICULOSEG))
    }

    fun setStatusMovEquipAddMotorista() = viewModelScope.launch {
        checkSetStatusMovEquipAddMotorista(setStatusMov(StatusMovEquipProprio.ADDMOTORISTA))
    }

    fun setStatusMovEquipAddVeic() = viewModelScope.launch {
        checkSetStatusMovEquipAddVeic(setStatusMov(StatusMovEquipProprio.ADDVEICULO))
    }

    fun recoverListEquipSeg() = viewModelScope.launch {
        setListEquipSeg(recoverListEquipProprioSeg())
    }

}

sealed class VeiculoSegProprioListFragmentState {
    data class CheckDeleteEquipProprioSeg(val check: Boolean) : VeiculoSegProprioListFragmentState()
    data class CheckSetStatusMovAddVeicSeg(val check: Boolean) : VeiculoSegProprioListFragmentState()
    data class CheckSetStatusMovAddMotorista(val check: Boolean) : VeiculoSegProprioListFragmentState()
    data class CheckSetStatusMovAddVeic(val check: Boolean) : VeiculoSegProprioListFragmentState()
    data class ListEquipSeg(val equipSegList: List<String>) : VeiculoSegProprioListFragmentState()
}