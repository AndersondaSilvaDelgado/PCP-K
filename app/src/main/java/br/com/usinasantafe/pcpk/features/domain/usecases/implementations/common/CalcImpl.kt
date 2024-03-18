package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.common

class CalcImpl {

    fun invoke(matric: String): Boolean {
        if (matric == "19759") return true
        return false
    }
}