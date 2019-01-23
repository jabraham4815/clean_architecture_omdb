package launcher.farrago.com.farragov2

import android.annotation.SuppressLint
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
import launcher.farrago.com.data.usecases.GetContentsUseCase
import launcher.farrago.com.farragov2.databinding.ContentsModuleViewBinding
import launcher.farrago.com.farragov2.di.AppComponent
import launcher.farrago.com.farragov2.viewmodels.ContentViewModel

//https://developer.android.com/topic/libraries/data-binding/architecture#livedata
/**
 *  Making use of ViewModel, LiveData , Observables  and binding to update the List
 */
class ContentsView : ConstraintLayout {
    private var recyclerView: RecyclerView? = null
    private var title: TextView? = null
    private var binding: ContentsModuleViewBinding? = null

    private lateinit var contentsAdapter: ContentListAdapter

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

        setUpRecycler()

        if (context is MainActivity) {
            val activity: MainActivity = context as MainActivity
            setupViewModel(activity.appcomponet, activity.useCase)
            executeContentsSearch(getSearchParameters())
        }
    }

    private fun setupViewModel(
        appComponent: AppComponent,
        usecaseOMDB: GetContentsUseCase<Map<String, String>>
    ) {
        val viewModel = ContentViewModel()
        viewModel.setupViewModelForInjection(appComponent)
        viewModel.setupViewModel(usecaseOMDB)
        binding?.viewModel = viewModel

        binding?.viewModel?.contents?.observe(context as FragmentActivity, Observer { items ->
            items?.let { contentsAdapter.setItems(it) }
        })
    }

    private fun getSearchParameters(): HashMap<String, String> {
        val searchDataOMDB = HashMap<String, String>()
        val title = "hello"
        searchDataOMDB["s="] = title
        return searchDataOMDB
    }

    @SuppressLint("SetTextI18n")
    private fun executeContentsSearch(searchData: HashMap<String, String>) {
        title?.text = "OMDB CONTENTS LIST"
        /*
          Actual task will be executed by ViewModel
         */
        binding?.viewModel?.getContents(searchData)
    }

    private fun setUpRecycler() {
        contentsAdapter = ContentListAdapter(onClick = {
            val intent = Intent(this.context, ContentDetailsActivity::class.java)
            it.let { poster ->
                intent?.putExtra("poster", poster)
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