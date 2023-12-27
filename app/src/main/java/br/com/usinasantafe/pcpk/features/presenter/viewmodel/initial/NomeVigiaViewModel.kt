package br.com.usinasantafe.pcpk.features.presenter.viewmodel.initial

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.initial.RecoverNomeVigia
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NomeVigiaViewModel @Inject constructor(
    private val recoverNomeVigia: RecoverNomeVigia,
): ViewModel() {

    private val _uiLiveData = MutableLiveData<NomeVigiaFragmentState>()
    val uiLiveData: LiveData<NomeVigiaFragmentState> = _uiLiveData

    private fun setNomeVigia(nome: String) {
        _uiLiveData.value = NomeVigiaFragmentState.GetNomeVigia(nome)
    }

    fun recoverDataNomeVigia() = viewModelScope.launch {
        setNomeVigia(recoverNomeVigia())
    }

}

sealed class NomeVigiaFragmentState {
    data class GetNomeVigia(val nomeVigia: String) : NomeVigiaFragmentState()
}