package br.com.usinasantafe.pcpk.features.presenter.viewmodel.proprio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.pcpk.common.utils.StatusMovEquipProprio
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.DeleteColabPassag
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.RecoverListColabPassag
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.SetStatusMov
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PassagColabListViewModel @Inject constructor (
    private val recoverListColabPassag: RecoverListColabPassag,
    private val deleteColabPassag: DeleteColabPassag,
    private val setStatusMov: SetStatusMov,
): ViewModel() {

    private val _uiLiveData = MutableLiveData<PassagColabListFragmentState>()
    val uiLiveData: LiveData<PassagColabListFragmentState> = _uiLiveData

    private fun checkDeleteColabPassag(check: Boolean) {
        _uiLiveData.value = PassagColabListFragmentState.CheckDeleteColabPassag(check)
    }

    private fun checkSetStatusMovEquipAddMotorista(check: Boolean) {
        _uiLiveData.value = PassagColabListFragmentState.CheckSetStatusMovAddMotorista(check)
    }

    private fun checkSetStatusMovEquipAddPassag(check: Boolean) {
        _uiLiveData.value = PassagColabListFragmentState.CheckSetStatusMovAddPassag(check)
    }

    private fun setListPassag(passagList: List<String>) {
        _uiLiveData.value = PassagColabListFragmentState.ListColabPassag(passagList)
    }

    fun deletePassag(pos: Int) = viewModelScope.launch {
        checkDeleteColabPassag(deleteColabPassag(pos))
    }

    fun setStatusMovEquipAddMotorista() = viewModelScope.launch {
        checkSetStatusMovEquipAddMotorista(setStatusMov(StatusMovEquipProprio.ADDMOTORISTA))
    }

    fun setStatusMovEquipAddPassag() = viewModelScope.launch {
        checkSetStatusMovEquipAddPassag(setStatusMov(StatusMovEquipProprio.ADDPASSAGEIRO))
    }

    fun recoverListPassag() = viewModelScope.launch {
        setListPassag(recoverListColabPassag())
    }

}

sealed class PassagColabListFragmentState {
    data class CheckDeleteColabPassag(val check: Boolean) : PassagColabListFragmentState()
    data class CheckSetStatusMovAddPassag(val check: Boolean) : PassagColabListFragmentState()
    data class CheckSetStatusMovAddMotorista(val check: Boolean) : PassagColabListFragmentState()
    data class ListColabPassag(val passagList: List<String>) : PassagColabListFragmentState()
}