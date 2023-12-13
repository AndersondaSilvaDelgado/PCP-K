package br.com.usinasantafe.cmm.common.utils

enum class StatusData { INITIATE, OPEN, CLOSE  }
enum class StatusConnection { OFFLINE, ONLINE }
enum class TypeNote { FAILURE, TRABALHANDO, PARADA }
enum class FlowNote { BOLETIM, APONTAMENTO }
enum class StatusSend { EMPTY, SENT, SENDING, SEND }
enum class StatusUpdate { UPDATED, FAILURE }
enum class StatusRecover { SUCCESS, EMPTY, FAILURE }
enum class PointerStart { MENUINICIAL, MENUAPONT, IMPLEMENTO, CHECKLIST }
enum class FlagUpdate { OUTDATED, UPDATED }
enum class ChoiceHorimetro { FAILURE, APONTAMENTO, CHECKLIST, IMPLEMENTO }
enum class ChoiceCheckList { CONFORME, NAOCONFORME, REPARO }
enum class StatusImplemento{ OK, INEXISTENTE, REPETIDO }
enum class TypeApp { PMM, ECM, PCOMP }
enum class TypeEquipSeg { CARRETEL, TRANSBORDO, IMPLEMENTO, MOTOBOMBA, CARREGADORA, REBOQUE }
