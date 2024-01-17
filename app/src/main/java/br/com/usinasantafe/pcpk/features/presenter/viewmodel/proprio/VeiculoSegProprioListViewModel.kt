package br.com.usinasantafe.pcpk.features.presenter.viewmodel.proprio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.DeleteEquipSeg
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.RecoverListEquipProprioSeg
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VeiculoSegProprioListViewModel @Inject constructor (
    private val deleteEquipSeg: DeleteEquipSeg,
    private val recoverListEquipProprioSeg: RecoverListEquipProprioSeg,
): ViewModel() {

    private val _uiLiveData = MutableLiveData<VeiculoSegProprioListFragmentState>()
    val uiLiveData: LiveData<VeiculoSegProprioListFragmentState> = _uiLiveData

    private fun checkDeleteEquipProprioSeg(check: Boolean) {
        _uiLiveData.value = VeiculoSegProprioListFragmentState.CheckDeleteEquipProprioSeg(check)
    }

    private fun setListEquipSeg(equipSegList: List<String>) {
        _uiLiveData.value = VeiculoSegProprioListFragmentState.ListEquipSeg(equipSegList)
    }

    fun deleteEquip(pos: Int) = viewModelScope.launch {
        checkDeleteEquipProprioSeg(deleteEquipSeg(pos))
    }

    fun recoverListEquipSeg() = viewModelScope.launch {
        setListEquipSeg(recoverListEquipProprioSeg())
    }

}

sealed class VeiculoSegProprioListFragmentState {
    data class CheckDeleteEquipProprioSeg(val check: Boolean) : VeiculoSegProprioListFragmentState()
    data class ListEquipSeg(val equipSegList: List<String>) : VeiculoSegProprioListFragmentState()
}