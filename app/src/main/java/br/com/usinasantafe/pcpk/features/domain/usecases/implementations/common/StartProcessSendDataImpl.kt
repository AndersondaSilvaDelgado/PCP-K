package br.com.usinasantafe.pcpk.features.domain.usecases.implementations.common

import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import br.com.usinasantafe.pcpk.features.domain.usecases.background.ProcessWorkManager
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.common.CheckStatusSend
import br.com.usinasantafe.pcpk.features.domain.usecases.interfaces.common.StartProcessSendData
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class StartProcessSendDataImpl @Inject constructor(
    private val workManager: WorkManager,
    private val checkStatusSend: CheckStatusSend,
) : StartProcessSendData {

    override suspend fun invoke() {
        if(checkStatusSend()){
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresCharging(true)
                .build()
            val workRequest = OneTimeWorkRequest.Builder(ProcessWorkManager::class.java)
                .setConstraints(constraints)
                .setBackoffCriteria(
                    BackoffPolicy.LINEAR,
                    2, TimeUnit.MINUTES
                )
                .build()
            workManager.enqueue(workRequest)
        }
    }

}