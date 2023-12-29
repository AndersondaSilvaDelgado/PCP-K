package br.com.usinasantafe.pcpk.features.presenter.viewmodel.proprio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.pcpk.common.utils.TypeMov
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.common.RecoverBase
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.StartMovEquipProprio
import br.com.usinasantafe.pcpk.features.presenter.model.ConfigModelOutput
import br.com.usinasantafe.pcpk.features.presenter.model.MovEquipProprioModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovEquipProprioListViewModel @Inject constructor (
    private val recoverBase: RecoverBase,
    private val startMovEquipProprio: StartMovEquipProprio,
//    private val recoverListMovEquipProprio: RecoverListMovEquipProprio,
): ViewModel() {

    private val _uiLiveData = MutableLiveData<MovEquipProprioListFragmentState>()
    val uiLiveData: LiveData<MovEquipProprioListFragmentState> = _uiLiveData

    private fun setConfig(config: ConfigModelOutput) {
        _uiLiveData.value = MovEquipProprioListFragmentState.RecoverConfig(config)
    }

    private fun setInitialMov(check: Boolean) {
        _uiLiveData.value = MovEquipProprioListFragmentState.CheckInitialMovEquip(check)
    }

    private fun setListMovEquip(movEquipList: List<MovEquipProprioModel>) {
        _uiLiveData.value = MovEquipProprioListFragmentState.ListMovEquip(movEquipList)
    }

    fun recoverDataConfig() = viewModelScope.launch {
        setConfig(recoverBase())
    }

    fun setInitialMov(typeMov: TypeMov) = viewModelScope.launch {
        setInitialMov(startMovEquipProprio(typeMov))
    }

    fun recoverListMov() = viewModelScope.launch {
        TODO()
    }

}

sealed class MovEquipProprioListFragmentState {
    data class RecoverConfig(val config: ConfigModelOutput) : MovEquipProprioListFragmentState()
    data class ListMovEquip(val movEquipProprioList: List<MovEquipProprioModel>) : MovEquipProprioListFragmentState()
    data class CheckInitialMovEquip(val check: Boolean) : MovEquipProprioListFragmentState()

}