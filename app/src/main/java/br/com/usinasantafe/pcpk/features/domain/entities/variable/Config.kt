package br.com.usinasantafe.pcpk.features.domain.entities.variable

import br.com.usinasantafe.pcpk.common.utils.FlagUpdate


data class Config(
    var nroAparelhoConfig: Long? = null,
    var passwordConfig: String? = null,
    var idBDConfig: Long? = null,
    var version: String? = null,
    var flagUpdate: FlagUpdate = FlagUpdate.OUTDATED,
    var matricVigia: Long? = null,
    var idLocal: Long? = null,
)
