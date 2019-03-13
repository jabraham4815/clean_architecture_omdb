package launcher.farrago.com.farragov2

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.constraint.ConstraintLayout
import android.support.v4.app.FragmentActivity
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import com.google.gson.Gson
import launcher.farrago.com.farragov2.databinding.ContentsModuleViewBinding
import launcher.farrago.com.farragov2.viewmodels.ContentViewModel
import javax.inject.Inject

//https://developer.android.com/topic/libraries/data-binding/architecture#livedata
/**
 *  Making use of ViewModel, LiveData , Observables  and binding to update the List
 */
class ContentsView : ConstraintLayout {
    private var recyclerView: RecyclerView? = null
    private var title: TextView? = null
    private var binding: ContentsModuleViewBinding? = null

    private lateinit var contentsAdapter: ContentListAdapter

    //Added here to demo injection
    @Inject lateinit var gson: Gson
    /*
       Note we cannot inject anything provided by the subComponents attached to this AppComponent . i.e you won't be able to inject anything
       provided by Modules DataModule or ViewModelsProvider here
       However for subComponents like the one mentioned in ActivityBuilderModule has complete access to AppComponent.
     */

    constructor(context: Context) : this(context, null)
    constructor(
        context: Context,
        attrs: AttributeSet?
    ) : this(context, attrs, 0)

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr)

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = DataBindingUtil.inflate(inflater, R.layout.contents_module_view, this, true)
        title = binding?.wordsModuleTitle
        title?.text = "OMDB CONTENTS LIST"

        //setup for injection
        val appComponent = (context.applicationContext as MainApplication).provideAppComponent()
        appComponent.inject(this)

        setUpRecycler()
    }

    fun setupViewModel(
        viewModel: ContentViewModel?
    ) {
        val searchparams = getSearchParameters()

        binding?.viewModel = viewModel
        binding?.viewModel?.contents?.observe(context as FragmentActivity, Observer { items ->
            items?.let { contentsAdapter.setItems(it) }
        })

        searchparams.let {
            binding?.viewModel?.getContents(it)
        }
    }

    private fun getSearchParameters(): HashMap<String, String> {
        val searchDataOMDB = HashMap<String, String>()
        val title = "hello"
        searchDataOMDB["s="] = title
        return searchDataOMDB
    }

    private fun setUpRecycler() {
        contentsAdapter = ContentListAdapter(onClick = {
            val intent = Intent(this.context, ContentDetailsActivity::class.java)
            it.let { poster ->
                intent.putExtra("poster", poster)
            }
            startActivity(this.context, intent, null)

        })
        recyclerView = binding?.moduleRecyclerview
        recyclerView?.setHasFixedSize(true)
        recyclerView?.clipToPadding = false
        recyclerView?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView?.adapter = contentsAdapter
    }
}