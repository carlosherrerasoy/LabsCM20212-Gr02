package co.edu.udea.compumovil.labs20212_gr02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.EditText

class ContactDataActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_data)
        emailFocusListener()

    }
    private fun emailFocusListener() {

        findViewById<EditText>(R.id.emailEditText).setOnFocusChangeListener { _, focused ->
            if(!focused)
            {

            }
        }
    }

    private fun validEmail(): String?
    {
        val emailText = findViewById<EditText>(R.id.emailEditText).text.toString()
        if(!Patterns.EMAIL_ADDRESS.matcher(emailText).matches())
        {
            return "Invalid Email Address"
        }
        return null
    }

}