package br.com.usinasantafe.pcpk.features.presenter.view.visitterc.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.usinasantafe.pcpk.databinding.ItemRowMovEquipProprioBinding
import br.com.usinasantafe.pcpk.databinding.ItemRowTextBinding
import br.com.usinasantafe.pcpk.features.presenter.model.MovEquipProprioModel

class MovEquipVisitTercAdapter(
    private val dataSet: List<MovEquipProprioModel>
) : RecyclerView.Adapter<MovEquipVisitTercAdapter.ViewHolder>() {

    var onItemClick: ((position: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemRowMovEquipProprioBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bindView(dataSet[position], position)
    }

    override fun getItemCount() = dataSet.size

    inner class ViewHolder(
        itemRowMovEquipProprioBinding: ItemRowMovEquipProprioBinding
    ) : RecyclerView.ViewHolder(itemRowMovEquipProprioBinding.root) {

        private val textViewDthrMov = itemRowMovEquipProprioBinding.textViewDthrMov
        private val textViewTipoMov = itemRowMovEquipProprioBinding.textViewTipoMov
        private val textViewEquipMov = itemRowMovEquipProprioBinding.textViewEquipMov
        private val textViewMotoristaMov = itemRowMovEquipProprioBinding.textViewMotoristaMov

        fun bindView(movEquipProprioModel: MovEquipProprioModel, position: Int) {

            textViewDthrMov.text = movEquipProprioModel.dthr
            textViewTipoMov.text = movEquipProprioModel.tipo
            textViewEquipMov.text = movEquipProprioModel.equip
            textViewMotoristaMov.text = movEquipProprioModel.colab

            itemView.setOnClickListener {
                onItemClick?.invoke(position)
            }

        }

    }

}