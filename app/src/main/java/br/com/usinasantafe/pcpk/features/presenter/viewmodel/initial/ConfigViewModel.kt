package br.com.usinasantafe.pcpk.features.presenter.viewmodel.initial

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.pcpk.common.utils.StatusRecover
import br.com.usinasantafe.pcpk.common.utils.StatusUpdate
import br.com.usinasantafe.pcpk.common.utils.WEB_RETURN_CLEAR_EQUIP
import br.com.usinasantafe.pcpk.features.domain.entities.variable.Config
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.common.CheckUpdate
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.database.UpdateAllDataBase
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.initial.RecoverConfig
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.initial.SaveConfig
import br.com.usinasantafe.pcpk.features.presenter.model.ResultUpdateDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConfigViewModel @Inject constructor(
    private val recoverConfig: RecoverConfig,
    private val updateAllDataBase: UpdateAllDataBase,
    private val saveConfig: SaveConfig,
    private val checkUpdate: CheckUpdate,
) : ViewModel() {

    private val _uiLiveData = MutableLiveData<ConfigFragmentState>()
    val uiLiveData: LiveData<ConfigFragmentState> = _uiLiveData

    private fun setLoadingDataBase(statusUpdate: StatusUpdate) {
        _uiLiveData.value = ConfigFragmentState.FeedbackLoadingDataBase(statusUpdate)
    }

    private fun setLoadingEquip(statusRecover: StatusRecover) {
        _uiLiveData.value = ConfigFragmentState.FeedbackLoadingEquip(statusRecover)
    }

    private fun setCheckUpdate(isCheckUpdate: Boolean) {
        _uiLiveData.value = ConfigFragmentState.IsCheckUpdate(isCheckUpdate)
    }

    private fun setConfig(config: Config) {
        _uiLiveData.value = ConfigFragmentState.RecoverConfig(config)
    }

    private fun setResultUpdate(resultUpdateDatabase: ResultUpdateDatabase){
        _uiLiveData.value = ConfigFragmentState.SetResultUpdate(resultUpdateDatabase)
    }

    fun recoverDataConfig() = viewModelScope.launch {
        var config = recoverConfig()
        config?.let { setConfig(it) }
    }

    fun checkUpdateData() = viewModelScope.launch {
        setCheckUpdate(checkUpdate())
    }

    fun saveDataConfig(nroAparelho: String, senha: String) =
        viewModelScope.launch {
            saveConfig(nroAparelho, senha)
                .catch { catch ->
                    setResultUpdate(ResultUpdateDatabase(100, "Erro: $catch", 100))
                    setLoadingEquip(StatusRecover.FAILURE)
                }
                .collect { resultUpdateDatabase ->
                    setResultUpdate(resultUpdateDatabase)
                    if (resultUpdateDatabase.percentage == 100) {
                        if (resultUpdateDatabase.describe == WEB_RETURN_CLEAR_EQUIP) {
                            setLoadingEquip(StatusRecover.EMPTY)
                        } else {
                            setLoadingEquip(StatusRecover.SUCCESS)
                        }
                    }
                }
        }

    fun updateDataBaseInitial() =
        viewModelScope.launch {
            updateAllDataBase()
                .catch { catch ->
                    setResultUpdate(ResultUpdateDatabase(100, "Erro: $catch", 100))
                    setLoadingDataBase(StatusUpdate.FAILURE)
                }
                .collect { resultUpdateDatabase ->
                    setResultUpdate(resultUpdateDatabase)
                    if (resultUpdateDatabase.percentage == 100) {
                        setLoadingDataBase(StatusUpdate.UPDATED)
                    }
                }
        }
}

sealed class ConfigFragmentState {
    data class RecoverConfig(val config: Config) : ConfigFragmentState()
    data class FeedbackLoadingDataBase(val statusUpdateDataBase: StatusUpdate) :
        ConfigFragmentState()
    data class FeedbackLoadingEquip(val statusUpdateEquip: StatusRecover) : ConfigFragmentState()
    data class IsCheckUpdate(val isCheckUpdate: Boolean) : ConfigFragmentState()
    data class SetResultUpdate(val resultUpdateDatabase: ResultUpdateDatabase) : ConfigFragmentState()
}