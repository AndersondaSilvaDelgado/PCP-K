package br.com.usinasantafe.pcpk.features.presenter.viewmodel.proprio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.pcpk.common.utils.ResultUpdateDatabase
import br.com.usinasantafe.pcpk.common.utils.StatusUpdate
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.common.CheckNroEquip
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.database.update.UpdateEquip
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.SetNroVeiculoEquipSeg
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VeiculoProprioViewModel @Inject constructor(
    private val checkNroEquip: CheckNroEquip,
    private val setNroVeiculoEquipSeg: SetNroVeiculoEquipSeg,
    private val updateEquip: UpdateEquip,
) : ViewModel() {

    private val _uiLiveData = MutableLiveData<VeiculoProprioFragmentState>()
    val uiLiveData: LiveData<VeiculoProprioFragmentState> = _uiLiveData

    private fun checkVeiculo(checkMatric: Boolean) {
        _uiLiveData.value = VeiculoProprioFragmentState.CheckEquip(checkMatric)
    }

    private fun checkSetMatricColab(checkSetMatricOperador: Boolean) {
        _uiLiveData.value = VeiculoProprioFragmentState.CheckSetVeicEquipSeg(checkSetMatricOperador)
    }

    private fun setStatusUpdate(statusUpdate: StatusUpdate) {
        _uiLiveData.value = VeiculoProprioFragmentState.FeedbackUpdate(statusUpdate)
    }

    private fun setResultUpdate(resultUpdateDatabase: ResultUpdateDatabase){
        _uiLiveData.value = VeiculoProprioFragmentState.SetResultUpdate(resultUpdateDatabase)
    }

    fun checkNroVeiculo(nroEquip: String) = viewModelScope.launch {
        checkVeiculo(checkNroEquip(nroEquip))
    }

    fun setMatricMotorista(nroEquip: String) = viewModelScope.launch {
        checkSetMatricColab(setNroVeiculoEquipSeg(nroEquip))
    }

    fun updateDataEquip() =
        viewModelScope.launch {
            updateEquip()
                .catch { catch ->
                    setResultUpdate(ResultUpdateDatabase(1, "Erro: $catch", 100, 100))
                    setStatusUpdate(StatusUpdate.FAILURE)
                }
                .collect { result ->
                    result.fold(
                        onSuccess = { resultUpdateDatabase ->
                            setResultUpdate(resultUpdateDatabase)
                            if (resultUpdateDatabase.percentage == 100) {
                                setStatusUpdate(StatusUpdate.UPDATED)
                            }
                        },
                        onFailure = { catch ->
                            setResultUpdate(ResultUpdateDatabase(100, "Erro: $catch", 100))
                            setStatusUpdate(StatusUpdate.FAILURE)
                        })
                }
        }

}

sealed class VeiculoProprioFragmentState {
    data class CheckEquip(val checkNroEquip: Boolean) : VeiculoProprioFragmentState()
    data class CheckSetVeicEquipSeg(val checkSetNroVeiculo: Boolean) : VeiculoProprioFragmentState()
    data class FeedbackUpdate(val statusUpdate: StatusUpdate) : VeiculoProprioFragmentState()
    data class SetResultUpdate(val resultUpdateDatabase: ResultUpdateDatabase) : VeiculoProprioFragmentState()
}