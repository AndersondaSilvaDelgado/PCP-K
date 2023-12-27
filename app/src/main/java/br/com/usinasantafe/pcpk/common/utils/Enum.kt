package br.com.usinasantafe.pcpk.common.utils

enum class StatusData { INITIATE, OPEN, CLOSE  }
enum class StatusSend { EMPTY, SENT, SENDING, SEND }
enum class TypeMov { INPUT, OUTPUT }
enum class StatusUpdate { UPDATED, FAILURE }
enum class StatusRecover { SUCCESS, EMPTY, FAILURE }
enum class PointerStart { MENUINICIAL, MENUAPONT, IMPLEMENTO, CHECKLIST }
enum class FlagUpdate { OUTDATED, UPDATED }