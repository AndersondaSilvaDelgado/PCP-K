package br.com.usinasantafe.pcpk.common.utils

enum class StatusData { OPEN, CLOSE  }
enum class StatusSend { STARTED, SENT, SENDING, SEND }
enum class TypeMov { INPUT, OUTPUT }
enum class StatusUpdate { UPDATED, FAILURE }
enum class StatusRecover { SUCCESS, EMPTY, FAILURE }
enum class PointerStart { MENUINICIAL, MENUAPONT, IMPLEMENTO, CHECKLIST }
enum class FlagUpdate { OUTDATED, UPDATED }
enum class TypeAddEquip { ADDVEICULO, ADDVEICULOSEG, CHANGEVEICULO, CHANGEVEICULOSEG }
enum class TypeAddOcupante { ADDMOTORISTA, ADDPASSAGEIRO, CHANGEMOTORISTA, CHANGEPASSAGEIRO }
enum class TypeVisitTerc { VISITANTE, TERCEIRO }
enum class FlowApp { ADD, CHANGE }
