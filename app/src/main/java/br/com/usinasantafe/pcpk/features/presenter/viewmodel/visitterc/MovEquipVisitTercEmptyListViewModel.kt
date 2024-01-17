package br.com.usinasantafe.pcpk.features.presenter.viewmodel.visitterc

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.visitterc.RecoverListMovEquipVisitTercEmpty
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.visitterc.SetStatusSendCloseAllMovVisitTerc
import br.com.usinasantafe.pcpk.features.presenter.model.MovEquipVisitTercModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovEquipVisitTercEmptyListViewModel @Inject constructor (
    private val setStatusSendCloseAllMovVisitTerc: SetStatusSendCloseAllMovVisitTerc,
    private val recoverListMovEquipVisitTercEmpty: RecoverListMovEquipVisitTercEmpty,
): ViewModel() {

    private val _uiLiveData = MutableLiveData<MovEquipVisitTercEmptyListFragmentState>()
    val uiLiveData: LiveData<MovEquipVisitTercEmptyListFragmentState> = _uiLiveData

    private fun checkCloseAllMov(check: Boolean) {
        _uiLiveData.value = MovEquipVisitTercEmptyListFragmentState.CheckCloseAllMov(check)
    }

    private fun setListMovEquip(movEquipList: List<MovEquipVisitTercModel>) {
        _uiLiveData.value = MovEquipVisitTercEmptyListFragmentState.ListMovEquip(movEquipList)
    }

    fun closeAllMov() = viewModelScope.launch {
        checkCloseAllMov(setStatusSendCloseAllMovVisitTerc())
    }

    fun recoverListMov() = viewModelScope.launch {
        setListMovEquip(recoverListMovEquipVisitTercEmpty())
    }

}

sealed class MovEquipVisitTercEmptyListFragmentState {
    data class ListMovEquip(val movEquipVisitTercList: List<MovEquipVisitTercModel>) : MovEquipVisitTercEmptyListFragmentState()
    data class CheckCloseAllMov(val check: Boolean) : MovEquipVisitTercEmptyListFragmentState()
}