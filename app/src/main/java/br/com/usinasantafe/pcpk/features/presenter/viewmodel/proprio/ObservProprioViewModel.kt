package br.com.usinasantafe.pcpk.features.presenter.viewmodel.proprio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    fun setObserv(observ: String?) = viewModelScope.launch {
        checkSetObserv(setObservProprio(observ))
    }

}

sealed class ObservProprioFragmentState {
    data class CheckSetObserv(val check: Boolean) : ObservProprioFragmentState()
}