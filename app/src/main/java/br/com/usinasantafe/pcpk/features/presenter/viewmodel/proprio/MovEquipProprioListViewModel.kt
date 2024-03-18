package br.com.usinasantafe.pcpk.features.presenter.viewmodel.proprio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.pcpk.common.utils.TypeMov
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.common.RecoverHeader
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.CloseSendAllMovProprio
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.RecoverListMovEquipProprioOpen
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.StartMovEquipProprio
import br.com.usinasantafe.pcpk.features.presenter.model.HeaderModel
import br.com.usinasantafe.pcpk.features.presenter.model.MovEquipProprioModel
import br.com.usinasantafe.pcpk.features.presenter.viewmodel.visitterc.MovEquipVisitTercStartedListFragmentState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovEquipProprioListViewModel @Inject constructor (
    private val closeSendAllMovProprio: CloseSendAllMovProprio,
    private val recoverHeader: RecoverHeader,
    private val startMovEquipProprio: StartMovEquipProprio,
    private val recoverListMovEquipProprioOpen: RecoverListMovEquipProprioOpen,
): ViewModel() {

    private val _uiLiveData = MutableLiveData<MovEquipProprioListFragmentState>()
    val uiLiveData: LiveData<MovEquipProprioListFragmentState> = _uiLiveData

    private fun checkSetInitialMov(check: Boolean) {
        _uiLiveData.value = MovEquipProprioListFragmentState.CheckInitialMovEquip(check)
    }

    private fun setHeader(header: HeaderModel) {
        _uiLiveData.value = MovEquipProprioListFragmentState.RecoverHeader(header)
    }

    private fun setListMovEquip(movEquipList: List<MovEquipProprioModel>) {
        _uiLiveData.value = MovEquipProprioListFragmentState.ListMovEquip(movEquipList)
    }

    private fun checkCloseAllMov(check: Boolean) {
        _uiLiveData.value = MovEquipProprioListFragmentState.CheckCloseAllMov(check)
    }

    fun checkSetInitialMov(typeMov: TypeMov) = viewModelScope.launch {
        checkSetInitialMov(startMovEquipProprio(typeMov))
    }

    fun recoverDataHeader() = viewModelScope.launch {
        setHeader(recoverHeader())
    }

    fun recoverListMov() = viewModelScope.launch {
        setListMovEquip(recoverListMovEquipProprioOpen())
    }

    fun closeAllMov() = viewModelScope.launch {
        checkCloseAllMov(closeSendAllMovProprio())
    }

}

sealed class MovEquipProprioListFragmentState {
    data class RecoverHeader(val header: HeaderModel) : MovEquipProprioListFragmentState()
    data class ListMovEquip(val movEquipProprioList: List<MovEquipProprioModel>) : MovEquipProprioListFragmentState()
    data class CheckInitialMovEquip(val check: Boolean) : MovEquipProprioListFragmentState()
    data class CheckCloseAllMov(val check: Boolean) : MovEquipProprioListFragmentState()
}