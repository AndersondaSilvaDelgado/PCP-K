package br.com.usinasantafe.pcpk.presenter.visitterc.nome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.pcpk.domain.usecases.visitterc.RecoverDataVisitTerc
import br.com.usinasantafe.pcpk.domain.usecases.visitterc.SetCPFMotoristaPassagVisitTerc
import br.com.usinasantafe.pcpk.utils.TypeAddOcupante
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NomeVisitTercViewModel @Inject constructor(
    private val recoverDataVisitTerc: RecoverDataVisitTerc,
    private val setCPFMotoristaPassagVisitTerc: SetCPFMotoristaPassagVisitTerc,
): ViewModel() {

    private val _uiLiveData = MutableLiveData<NomeVisitTercFragmentState>()
    val uiLiveData: LiveData<NomeVisitTercFragmentState> = _uiLiveData

    private fun checkSetMatricColab(checkSetCPF: Boolean) {
        _uiLiveData.value = NomeVisitTercFragmentState.CheckSetCPF(checkSetCPF)
    }

    private fun setDataVisitTerc(display: DisplayDataVisitTercModel) {
        _uiLiveData.value = NomeVisitTercFragmentState.GetDataVisitTerc(display)
    }

    fun recoverData(cpf: String, typeAddOcupante: TypeAddOcupante, pos: Int) = viewModelScope.launch {
        setDataVisitTerc(recoverDataVisitTerc(cpf, typeAddOcupante, pos))
    }

    fun setCPFVisitTerc(matricVigia: String, typeAddOcupante: TypeAddOcupante, pos: Int) = viewModelScope.launch {
        checkSetMatricColab(setCPFMotoristaPassagVisitTerc(matricVigia, typeAddOcupante, pos))
    }

}

sealed class NomeVisitTercFragmentState {
    data class CheckSetCPF(val checkSetCPF: Boolean) : NomeVisitTercFragmentState()
    data class GetDataVisitTerc(val display: DisplayDataVisitTercModel) : NomeVisitTercFragmentState()
}