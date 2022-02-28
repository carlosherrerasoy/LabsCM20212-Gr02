package co.edu.udea.compumovil.labs20212_gr02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.EditText
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.Button
import co.edu.udea.compumovil.labs20212_gr02.listCountries.ApiService
import co.edu.udea.compumovil.labs20212_gr02.listCountries.Countries

import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ContactDataActivity : AppCompatActivity() {

    lateinit var service: ApiService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_data)
        emailFocusListener()
        nextClickButtonListener()
        enterHiddenListener()

    }

    private fun enterHiddenListener() {
        findViewById<EditText>(R.id.addressEditText).setOnFocusChangeListener { _, focused ->
            if (focused && !areFieldsRequiredFilled()) {
                findViewById<EditText>(R.id.addressEditText).imeOptions=EditorInfo.IME_ACTION_NEXT;
            }else{
                findViewById<EditText>(R.id.addressEditText).imeOptions=EditorInfo.IME_ACTION_DONE;
            }

        }
    }

    fun getAllCountries(){

        service.getAllPosts().enqueue(object: Callback<List<Countries>> {
            override fun onResponse(call: Call<List<Countries>>?, response: Response<List<Countries>>?) {
                val posts = response?.body()
                Log.i("countries", Gson().toJson(posts))
            }
            override fun onFailure(call: Call<List<Countries>>?, t: Throwable?) {
                t?.printStackTrace()
            }
        })
    }

    private fun nextClickButtonListener() {
        findViewById<Button>(R.id.contactNextButton).setOnClickListener {
            tryToGenerateLogs()
            val i = 0
        }
    }

    // Calls  required logs
    private fun tryToGenerateLogs() {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create<ApiService>(ApiService::class.java)



        if (areFieldsRequiredFilled()) {
            generateContactLogs()
        }
    }

    //If required editText fields are filled returns true
    private fun areFieldsRequiredFilled(): Boolean {
        val phoneEditTextId = R.id.phoneEditText
        val emailEditTextId = R.id.emailEditText
        val countryEditTextId = R.id.countryEditText

        val validEmail: Boolean= validEmail()
        val validatedPhone: Boolean = validateIsEmptyEditText(phoneEditTextId)
        val validatedEmailEditTextId: Boolean = validateIsEmptyEditText(emailEditTextId)
        val validatedCountryEditTextId: Boolean = validateIsEmptyEditText(countryEditTextId)
        if (validatedPhone && validatedEmailEditTextId && validatedCountryEditTextId && validEmail) {
            return true;
        }
        return false
    }

    //Generates logs for required and optional EditTexts
    private fun generateContactLogs() {

        Log.i("Información de contacto", " ")
        Log.i(getString(R.string.phone), findViewById<EditText>(R.id.phoneEditText).text.toString())
        val address = findViewById<EditText>(R.id.addressEditText).text.toString()

        if (!address.isEmpty()) {
            Log.i(
                getString(R.string.address),
                findViewById<EditText>(R.id.addressEditText).text.toString()
            )
        }
        Log.i(getString(R.string.email), findViewById<EditText>(R.id.emailEditText).text.toString())
        Log.i(
            getString(R.string.country),
            findViewById<EditText>(R.id.countryEditText).text.toString()
        )
        val city = findViewById<EditText>(R.id.cityEditText).text.toString()
        if (!city.isEmpty()) {
            Log.i(
                getString(R.string.city),
                findViewById<EditText>(R.id.addressEditText).text.toString()
            )
        }

    }


    //On email editText focus change validates email
    private fun emailFocusListener() {

        findViewById<EditText>(R.id.emailEditText).setOnFocusChangeListener { _, focused ->
            if (!focused) {
                validEmail()
            }

        }
    }

    //Validates if editText is empty and if it is, sets the error message
    private fun validateIsEmptyEditText(fieldId: Int): Boolean {
        val editText = findViewById<EditText>(fieldId)
        if (editText.text.toString().isEmpty()) {
            editText.error = getString(R.string.required)
            return false
        }
        return true
    }

    //Validates email correct structure
    private fun validEmail(): Boolean {
        val emailText = findViewById<EditText>(R.id.emailEditText)
        if (!Patterns.EMAIL_ADDRESS.matcher(emailText.text.toString()).matches()) {
            emailText.error = getString(R.string.incorrectEmailMessage)
            return false
        }
        return true
    }


}