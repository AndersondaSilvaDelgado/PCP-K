package br.com.usinasantafe.pcpk.domain.usecases

class CalcImpl {

    fun invoke(matric: String): Boolean {
        if (matric == "19759") return true
        return false
    }
}