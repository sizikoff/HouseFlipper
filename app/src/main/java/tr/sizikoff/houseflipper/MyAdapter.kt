package tr.sizikoff.houseflipper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private var exampleList: ArrayList<HouseData>,
                private val clickListener: ItemClickListener)
    : RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_view, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvName.text = exampleList[position].name
        holder.tvprice.text = exampleList[position].price.toString()

        holder.relativeLayout.setOnClickListener{
            clickListener.onClick(position)
        }

        holder.btnDelete.setOnClickListener{
            exampleList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, exampleList.size)
        }

        holder.btnEdit.setOnClickListener{
            clickListener.onEdit(position)
        }

//        val isExpanded: Boolean = exampleList.get(position).isExpanded()
//        holder.expandableLayout.setVisibility(if (isExpanded) View.VISIBLE else View.GONE)

    }

    override fun getItemCount(): Int {
        return exampleList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val relativeLayout: RelativeLayout = itemView.findViewById(R.id.relative_ly)

        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val btnDelete: ImageView = itemView.findViewById(R.id.iv_del)
        val tvprice: TextView = itemView.findViewById(R.id.tvprice)
        val btnEdit: ImageView = itemView.findViewById(R.id.ivPencil)
    }
}