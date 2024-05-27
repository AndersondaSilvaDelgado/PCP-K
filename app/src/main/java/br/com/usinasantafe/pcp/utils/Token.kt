package br.com.usinasantafe.pcp.utils

import com.google.common.base.Strings
import java.math.BigInteger

import java.security.MessageDigest
import java.util.Locale


fun token(nroAparelho: Long, version: String): String {
    var token = "PCP-VERSAO_${version}-$nroAparelho"
    val messageDigest = MessageDigest.getInstance("MD5")
    messageDigest.update(token.toByteArray(), 0, token.length)
    val bigInteger = BigInteger(1, messageDigest.digest())
    val str = bigInteger.toString(16).uppercase(Locale.getDefault())
    token = Strings.padStart(str, 32, '0')
    return "Bearer $token"
}