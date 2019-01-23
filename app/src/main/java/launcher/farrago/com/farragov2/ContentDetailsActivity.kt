package launcher.farrago.com.farragov2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_content_details.content_image_large
import retrofit2.Retrofit
import java.io.InvalidObjectException
import javax.inject.Inject

class ContentDetailsActivity : AppCompatActivity() {
    @Inject lateinit var gson: Gson
    @Inject lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState)
        val url:String? = intent?.extras?.getString("poster")
        setContentView(R.layout.activity_content_details)

        if (gson == null) {
            throw InvalidObjectException("gson null exec")
        }

        if (retrofit == null) {
            throw InvalidObjectException("retrofit null exec")
        }

        Picasso.with(applicationContext).load(url).into(content_image_large)

    }
}
