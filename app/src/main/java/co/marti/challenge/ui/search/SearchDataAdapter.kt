package co.marti.challenge.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import co.marti.challenge.R
import co.marti.challenge.databinding.ItemCustomBinding
import co.marti.challenge.network.model.place.Prediction
import co.marti.challenge.util.DebugLog


class SearchDataAdapter(callback: SearchAdapterCallback):RecyclerView.Adapter<SearchDataAdapter.PredictionsHolder>(),SearchDataHandlers {

    private val _callback : SearchAdapterCallback=callback
    private var _predictions : ArrayList<Prediction?>? =null


    //  private val predictions get() = _predictions!!

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PredictionsHolder {
         val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemCustomBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_custom,parent,false)
        return PredictionsHolder(binding)
    }

    override fun getItemCount(): Int {

        return if (_predictions != null) {
            _predictions!!.size
        }else {
            0
        }

    }
    override fun onBindViewHolder(holder: PredictionsHolder, position: Int) {
      holder.bind(_predictions?.get(position))
    }

    fun setPredictionsList(predictions:  ArrayList<Prediction?>) {
       _predictions = predictions
        notifyDataSetChanged()
    }

    inner class PredictionsHolder(binding: ItemCustomBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val mBinding: ItemCustomBinding = binding
        fun bind(item: Prediction?) {
            mBinding.prediction=item
            mBinding.handler=this@SearchDataAdapter
            mBinding.executePendingBindings()
        }

    }

    override fun onItemClick(view: View,selectedPlaceId : String) {
        DebugLog.write()
        _callback.getSelectedPlace(selectedPlaceId)
    }


}


interface SearchDataHandlers {
    fun onItemClick(view: View, selectedPlaceId : String)
}