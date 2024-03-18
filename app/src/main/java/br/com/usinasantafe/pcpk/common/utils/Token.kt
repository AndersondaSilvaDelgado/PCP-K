package br.com.usinasantafe.pcpk.common.utils

import br.com.usinasantafe.pcpk.BuildConfig
import com.google.common.base.Strings
import java.math.BigInteger

import java.security.MessageDigest
import java.util.Locale


fun token(nroAparelho: Long): String {
    var token = "PCP-VERSAO_${BuildConfig.VERSION_NAME}-$nroAparelho"
    val m = MessageDigest.getInstance("MD5")
    m.update(token.toByteArray(), 0, token.length)
    val bigInteger = BigInteger(1, m.digest())
    val str = bigInteger.toString(16).uppercase(Locale.getDefault())
    token = Strings.padStart(str, 32, '0')
    return "Bearer $token"
}