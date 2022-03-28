package fr.epsi.gosecuri

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button0 = findViewById<TextView>(R.id.ButtonStaff0)
        val button1 = findViewById<TextView>(R.id.ButtonStaff1)
        val button2 = findViewById<TextView>(R.id.ButtonStaff2)
        val button3 = findViewById<TextView>(R.id.ButtonStaff3)
        val button4 = findViewById<TextView>(R.id.ButtonStaff4)
        val button5 = findViewById<TextView>(R.id.ButtonStaff5)
        val button6 = findViewById<TextView>(R.id.ButtonStaff6)
        val button7 = findViewById<TextView>(R.id.ButtonStaff7)

        Thread {
            val urls = ArrayList<String>() //to read each line
            //TextView t; //to show the result, please declare and find it inside onCreate()
            try {
                // Create a URL for the desired page
                val url = URL("https://raw.githubusercontent.com/GO-Securi/save_GOSecuri/main/staff.txt") //My text file location
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

                button0.text = urls.get(0)
                button1.text = urls.get(1)
                button2.text = urls.get(2)
                button3.text = urls.get(3)
                button4.text = urls.get(4)
                button5.text = urls.get(5)
                button6.text = urls.get(6)
                button7.text = urls.get(7)

                Log.d("response", urls.get(1))
                Log.d("response", urls.get(2))
                Log.d("response", urls.get(3))
                Log.d("response", urls.get(4))
                Log.d("response", urls.get(5))
                Log.d("response", urls.get(6))
                Log.d("response", urls.get(7))


            // My TextFile has 3 lines
            })
        }.start()

        button0.setOnClickListener {
            val i = Intent(this, StaffActivity::class.java).apply {
                putExtra("Data", button0.text.toString())
            }

            startActivity(i)
        }
        button1.setOnClickListener {
            val i = Intent(this, StaffActivity::class.java).apply {
                putExtra("Data", button1.text.toString())
            }

            startActivity(i)
        }
        button2.setOnClickListener {
            val i = Intent(this, StaffActivity::class.java).apply {
                putExtra("Data", button2.text.toString())
            }

            startActivity(i)
        }
        button3.setOnClickListener {
            val i = Intent(this, StaffActivity::class.java).apply {
                putExtra("Data", button3.text.toString())
            }

            startActivity(i)
        }
        button4.setOnClickListener {
            val i = Intent(this, StaffActivity::class.java).apply {
                putExtra("Data", button4.text.toString())
            }

            startActivity(i)
        }
        button5.setOnClickListener {
            val i = Intent(this, StaffActivity::class.java).apply {
                putExtra("Data", button5.text.toString())
            }

            startActivity(i)
        }
        button6.setOnClickListener {
            val i = Intent(this, StaffActivity::class.java).apply {
                putExtra("Data", button6.text.toString())
            }

            startActivity(i)
        }
        button7.setOnClickListener {
            val i = Intent(this, StaffActivity::class.java).apply {
                putExtra("Data", button7.text.toString())
            }

            startActivity(i)
        }

    }

}