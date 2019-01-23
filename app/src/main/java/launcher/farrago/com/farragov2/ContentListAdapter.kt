package launcher.farrago.com.farragov2

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.squareup.picasso.Picasso
import launcher.farrago.com.domain.models.Content

class ContentListAdapter(
    onClick: (String) -> Unit
) : RecyclerView.Adapter<ContentListAdapter.ViewHolder>() {

    private var recyclerList: ArrayList<Content?>? = null
    private val itemClick = onClick

    fun setItems(items: List<Content?>) {
        if (this.recyclerList == items) {
            return
        }
        this.recyclerList = items as ArrayList<Content?>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContentListAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.content_repo_detailed, parent, false))
    }

    override fun getItemCount(): Int {
        recyclerList?.let {
            return it.size
        } ?: run {
            return 0
        }
    }

    override fun onBindViewHolder(
        viewHolder: ViewHolder,
        pos: Int
    ) {

        var url:String = ""
        recyclerList?.get(pos)?.urlPoster?.let {
            url = it
            Picasso.with(viewHolder.image.context).load(it).into(viewHolder.image)
        }

        recyclerList?.get(pos)?.titleMovie?.let {
            viewHolder.contentTitle.text = it
        }

        recyclerList?.get(pos)?.genres?.let {
            viewHolder.genres.text = it
        }

        recyclerList?.get(pos)?.yearReleased?.let {
            viewHolder.yearReleased.text = it
        }

        viewHolder.container.setOnClickListener {
            itemClick(url)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var container: LinearLayout = itemView.findViewById(R.id.crd_container)
        var image: ImageView = itemView.findViewById(R.id.content_image)
        var yearReleased: TextView = itemView.findViewById(R.id.content_released_year)
        var contentTitle: TextView = itemView.findViewById(R.id.content_title)
        var genres: TextView = itemView.findViewById(R.id.content_genres)
    }
}
