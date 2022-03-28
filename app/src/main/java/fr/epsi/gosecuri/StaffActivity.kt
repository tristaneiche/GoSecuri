package fr.epsi.gosecuri

import android.os.Bundle
import android.util.Log
import android.webkit.HttpAuthHandler
import android.webkit.WebView
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.squareup.picasso.Picasso
import kotlinx.coroutines.joinAll
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class StaffActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_staff)

        val imagebuttonback = findViewById<ImageButton>(R.id.back_arrow)
        imagebuttonback.setOnClickListener( {
            finish()
        })

        val textviewNom = findViewById<TextView>(R.id.NomStaff)
        val textviewPrenom = findViewById<TextView>(R.id.PrenomStaff)
        val textviewjob = findViewById<TextView>(R.id.JobStaff)
        val textViewequipement1 = findViewById<TextView>(R.id.Equipement1)
        val textViewequipement2 = findViewById<TextView>(R.id.Equipement2)
        val textViewequipement3 = findViewById<TextView>(R.id.Equipement3)
        val textViewequipement4 = findViewById<TextView>(R.id.Equipement4)
        val textViewequipement5 = findViewById<TextView>(R.id.Equipement5)
        val textViewequipement6 = findViewById<TextView>(R.id.Equipement6)
        val imageStaff = findViewById<ImageView>(R.id.ImageStaff)

        val intentValue = intent.getStringExtra("Data")
        val urlsup = "https://raw.githubusercontent.com/GO-Securi/save_GOSecuri/main/" + intentValue + ".txt"
        val urlimage = "https://raw.githubusercontent.com/GO-Securi/save_GOSecuri/main/" + intentValue + ".png"

        Log.d("response", urlsup)


        Thread {
            val urls = ArrayList<String>() //to read each line
            //TextView t; //to show the result, please declare and find it inside onCreate()
            try {
                // Create a URL for the desired page
                val url = URL(urlsup) //My text file location
                //First open the connection
                val conn: HttpURLConnection = url.openConnection() as HttpURLConnection
                conn.setConnectTimeout(60000) // timing out in a minute
                val `in` = BufferedReader(InputStreamReader(conn.getInputStream()))

                //t=(TextView)findViewById(R.id.TextView1); // ideally do this in onCreate()
                var str: String
                while (`in`.readLine().also { str = it } != null) {
                    urls.add(str)
                }
                `in`.close()
            } catch (e: Exception) {
                Log.d("MyTag", e.toString())
            }

            //since we are in background thread, to post results we have to go back to ui thread. do the following for that

            this.runOnUiThread(Runnable {

                val nligne = urls.size
                Log.d("response", nligne.toString())

                textviewNom.text = urls.get(0)
                textviewPrenom.text = urls.get(1)
                textviewjob.text = urls.get(2)

                if (urls.size >= 7) {
                    textViewequipement1.text = urls.get(5)
                } else {
                    textViewequipement1.text = "Aucun equipement"
                }

                if (urls.size >= 8) {
                    textViewequipement2.text = urls.get(6)
                } else {
                    textViewequipement2.isVisible = false
                }

                if (urls.size >= 9) {
                    textViewequipement3.text = urls.get(7)
                } else {
                    textViewequipement3.isVisible = false
                }

                if (urls.size >= 10) {
                    textViewequipement4.text = urls.get(8)
                } else {
                    textViewequipement4.isVisible = false
                }

                if (urls.size >= 11) {
                    textViewequipement5.text = urls.get(9)
                } else {
                    textViewequipement5.isVisible = false
                }

                if (urls.size == 12) {
                    textViewequipement6.text = urls.get(10)
                } else {
                    textViewequipement6.isVisible = false
                }


                // My TextFile has 3 lines
            })
        }.start()

        Picasso.get().load(urlimage).into(imageStaff)
    }
}