package br.com.usinasantafe.pcpk.utils

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import br.com.usinasantafe.pcpk.databinding.DialogProgressBarGenericBinding
import br.com.usinasantafe.pcpk.utils.ResultUpdateDatabase

class GenericDialogProgressBar(
    context: Context
): Dialog(context) {

    private lateinit var binding: DialogProgressBarGenericBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DialogProgressBarGenericBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun setValue(resultUpdateDatabase: ResultUpdateDatabase){
        with(binding) {
            dialogProgressBarTitle.text = resultUpdateDatabase.describe
            progressBarGeneric.progress = resultUpdateDatabase.percentage
        }
    }

}