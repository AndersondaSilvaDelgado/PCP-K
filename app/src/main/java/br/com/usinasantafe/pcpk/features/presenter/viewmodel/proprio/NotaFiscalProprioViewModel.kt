package br.com.usinasantafe.pcpk.features.presenter.viewmodel.proprio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.pcpk.common.utils.FlowApp
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.GetTipoMov
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.SetDestinoProprio
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.SetNotaFiscalProprio
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotaFiscalProprioViewModel @Inject constructor(
    private val setNotaFiscalProprio: SetNotaFiscalProprio,
) : ViewModel() {

    private val _uiLiveData = MutableLiveData<NotaFiscalProprioFragmentState>()
    val uiLiveData: LiveData<NotaFiscalProprioFragmentState> = _uiLiveData

    private fun checkSetNotaFiscal(check: Boolean) {
        _uiLiveData.value = NotaFiscalProprioFragmentState.CheckSetNotaFiscal(check)
    }

    fun setNotaFiscal(notaFiscal: String, flowApp: FlowApp, pos: Int) = viewModelScope.launch {
        checkSetNotaFiscal(setNotaFiscalProprio(notaFiscal, flowApp, pos))
    }

}

sealed class NotaFiscalProprioFragmentState {
    data class CheckSetNotaFiscal(val check: Boolean) : NotaFiscalProprioFragmentState()
}