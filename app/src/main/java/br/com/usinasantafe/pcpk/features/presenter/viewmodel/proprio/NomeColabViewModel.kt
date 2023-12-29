package br.com.usinasantafe.pcpk.features.presenter.viewmodel.proprio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.proprio.RecoverNomeColabMotoristaPassag
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NomeColabViewModel @Inject constructor(
    private val recoverNomeColabMotoristaPassag: RecoverNomeColabMotoristaPassag,
): ViewModel() {

    private val _uiLiveData = MutableLiveData<NomeColabFragmentState>()
    val uiLiveData: LiveData<NomeColabFragmentState> = _uiLiveData

    private fun setNomeVigia(nome: String) {
        _uiLiveData.value = NomeColabFragmentState.GetNomeColab(nome)
    }

    fun recoverDataNomeVigia() = viewModelScope.launch {
        setNomeVigia(recoverNomeColabMotoristaPassag())
    }

}

sealed class NomeColabFragmentState {
    data class GetNomeColab(val nomeColab: String) : NomeColabFragmentState()
}