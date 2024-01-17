package br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.residencia

interface CheckDataSendMovEquipResidencia {

    suspend operator fun invoke(): Boolean

}