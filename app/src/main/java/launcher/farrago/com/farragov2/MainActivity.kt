package launcher.farrago.com.farragov2

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.google.gson.Gson
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.navigation
import launcher.farrago.com.data.usecases.GetContentsUseCase
import launcher.farrago.com.farragov2.di.AppComponent
import retrofit2.Retrofit
import java.io.InvalidObjectException
import javax.inject.Inject
import launcher.farrago.com.farragov2.di.ViewModelFactory



class MainActivity : AppCompatActivity() {
    @Inject lateinit var appcomponet: AppComponent
    @Inject lateinit var gson: Gson
    @Inject lateinit var retrofit: Retrofit
    @Inject lateinit var useCase: GetContentsUseCase<Map<String, String>>
    @Inject lateinit var viewModelFactory: ViewModelFactory

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                return@OnNavigationItemSelectedListener true
            }
//            R.id.navigation_dashboard -> {
//                return@OnNavigationItemSelectedListener true
//            }
//            R.id.navigation_notifications -> {
//                return@OnNavigationItemSelectedListener true
//            }
        }
        false
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (viewModelFactory == null) {
            throw InvalidObjectException("viewModelFactory null exec")
        }
        if (appcomponet == null) {
            throw InvalidObjectException("appcomponet null exec")
        }
        if (retrofit == null) {
            throw InvalidObjectException("retrofit null exec")
        }

        if (useCase == null) {
            throw InvalidObjectException("useCaseOMDB null exec")
        }

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

    }
}
