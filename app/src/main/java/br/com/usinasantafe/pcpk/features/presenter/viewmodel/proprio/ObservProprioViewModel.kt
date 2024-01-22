package br.com.usinasantafe.pcpk.features.presenter.viewmodel.proprio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.pcpk.common.utils.FlowApp
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.SetObservProprio
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ObservProprioViewModel @Inject constructor(
    private val setObservProprio: SetObservProprio,
) : ViewModel() {

    private val _uiLiveData = MutableLiveData<ObservProprioFragmentState>()
    val uiLiveData: LiveData<ObservProprioFragmentState> = _uiLiveData

    private fun checkSetObserv(check: Boolean) {
        _uiLiveData.value = ObservProprioFragmentState.CheckSetObserv(check)
    }

    fun setObserv(observ: String?, flowApp: FlowApp, pos: Int) = viewModelScope.launch {
        checkSetObserv(setObservProprio(observ, flowApp, pos))
    }

}

sealed class ObservProprioFragmentState {
    data class CheckSetObserv(val check: Boolean) : ObservProprioFragmentState()
}