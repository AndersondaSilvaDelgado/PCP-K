package br.com.usinasantafe.pcpk.features.presenter.viewmodel.visitterc

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.pcpk.common.utils.TypeAddOcupante
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.visitterc.RecoverDataVisitTerc
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.visitterc.SetMotoristaPassagVisitTerc
import br.com.usinasantafe.pcpk.features.presenter.model.DisplayDataVisitTercModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NomeVisitTercViewModel @Inject constructor(
    private val recoverDataVisitTerc: RecoverDataVisitTerc,
    private val setMotoristaPassagVisitTerc: SetMotoristaPassagVisitTerc,
): ViewModel() {

    private val _uiLiveData = MutableLiveData<NomeVisitTercFragmentState>()
    val uiLiveData: LiveData<NomeVisitTercFragmentState> = _uiLiveData

    private fun checkSetMatricColab(checkSetCPF: Boolean) {
        _uiLiveData.value = NomeVisitTercFragmentState.CheckSetCPF(checkSetCPF)
    }

    private fun setDataVisitTerc(display: DisplayDataVisitTercModel) {
        _uiLiveData.value = NomeVisitTercFragmentState.GetDataVisitTerc(display)
    }

    fun recoverData(cpf: String) = viewModelScope.launch {
        setDataVisitTerc(recoverDataVisitTerc(cpf))
    }

    fun setCPFVisitTerc(matricVigia: String, typeAddOcupante: TypeAddOcupante) = viewModelScope.launch {
        checkSetMatricColab(setMotoristaPassagVisitTerc(matricVigia, typeAddOcupante))
    }

}

sealed class NomeVisitTercFragmentState {
    data class CheckSetCPF(val checkSetCPF: Boolean) : NomeVisitTercFragmentState()
    data class GetDataVisitTerc(val display: DisplayDataVisitTercModel) : NomeVisitTercFragmentState()
}