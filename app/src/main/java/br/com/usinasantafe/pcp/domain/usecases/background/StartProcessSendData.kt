package br.com.usinasantafe.pcp.domain.usecases.background

import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import br.com.usinasantafe.pcp.domain.usecases.common.CheckStatusSend
import java.util.concurrent.TimeUnit
import javax.inject.Inject

interface StartProcessSendData {
    suspend operator fun invoke()
}

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