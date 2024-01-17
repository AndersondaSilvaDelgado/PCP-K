package br.com.usinasantafe.pcpk.features.presenter.view.visitterc.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.usinasantafe.pcpk.databinding.ItemRowMovEquipVisitTercEmptyBinding
import br.com.usinasantafe.pcpk.features.presenter.model.MovEquipVisitTercModel

class MovEquipVisitTercEmptyAdapter(
    private val dataSet: List<MovEquipVisitTercModel>
) : RecyclerView.Adapter<MovEquipVisitTercEmptyAdapter.ViewHolder>() {

    var onItemClick: ((position: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemRowMovEquipVisitTercEmptyBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bindView(dataSet[position], position)
    }

    override fun getItemCount() = dataSet.size

    inner class ViewHolder(
        itemRowMovEquipVisitTercEmptyBinding: ItemRowMovEquipVisitTercEmptyBinding
    ) : RecyclerView.ViewHolder(itemRowMovEquipVisitTercEmptyBinding.root) {

        private val textViewDthrMov = itemRowMovEquipVisitTercEmptyBinding.textViewDthrMov
        private val textViewMotoristaMov = itemRowMovEquipVisitTercEmptyBinding.textViewMotoristaMov
        private val textViewVeiculoMov = itemRowMovEquipVisitTercEmptyBinding.textViewVeiculoMov
        private val textViewPlacaMov = itemRowMovEquipVisitTercEmptyBinding.textViewPlacaMov
        private val textViewTipoMov = itemRowMovEquipVisitTercEmptyBinding.textViewTipoMov

        fun bindView(movEquipVisitTercModel: MovEquipVisitTercModel, position: Int) {

            textViewDthrMov.text = movEquipVisitTercModel.dthr
            textViewMotoristaMov.text = movEquipVisitTercModel.motorista
            textViewVeiculoMov.text = movEquipVisitTercModel.placa
            textViewPlacaMov.text = movEquipVisitTercModel.veiculo
            if(movEquipVisitTercModel.tipo == "ENTRADA"){
                textViewTipoMov.setTextColor(Color.BLUE)
            } else {
                textViewTipoMov.setTextColor(Color.RED)
            }
            itemView.setOnClickListener {
                onItemClick?.invoke(position)
            }

        }

    }

}