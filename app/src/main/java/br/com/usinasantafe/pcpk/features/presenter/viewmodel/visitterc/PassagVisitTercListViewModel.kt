package br.com.usinasantafe.pcpk.features.presenter.viewmodel.visitterc

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.DeletePassagColab
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.visitterc.DeletePassagVisitTerc
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.visitterc.RecoverListPassagVisitTerc
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PassagVisitTercListViewModel @Inject constructor (
    private val recoverListPassagVisitTerc: RecoverListPassagVisitTerc,
    private val deletePassagVisitTerc: DeletePassagVisitTerc,
): ViewModel() {

    private val _uiLiveData = MutableLiveData<PassagVisitTercListFragmentState>()
    val uiLiveData: LiveData<PassagVisitTercListFragmentState> = _uiLiveData

    private fun checkDeleteVisitTercPassag(check: Boolean) {
        _uiLiveData.value = PassagVisitTercListFragmentState.CheckDeleteVisitTercPassag(check)
    }

    private fun setListPassag(passagList: List<String>) {
        _uiLiveData.value = PassagVisitTercListFragmentState.ListVisitTercPassag(passagList)
    }

    fun deletePassag(pos: Int) = viewModelScope.launch {
        checkDeleteVisitTercPassag(deletePassagVisitTerc(pos))
    }

    fun recoverListPassag() = viewModelScope.launch {
        setListPassag(recoverListPassagVisitTerc())
    }

}

sealed class PassagVisitTercListFragmentState {
    data class CheckDeleteVisitTercPassag(val check: Boolean) : PassagVisitTercListFragmentState()
    data class ListVisitTercPassag(val passagList: List<String>) : PassagVisitTercListFragmentState()
}