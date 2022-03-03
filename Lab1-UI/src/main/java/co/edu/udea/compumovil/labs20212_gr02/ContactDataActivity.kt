package co.edu.udea.compumovil.labs20212_gr02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.*
import co.edu.udea.compumovil.labs20212_gr02.listCountries.Utils
class ContactDataActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_data)
        emailFocusListener()
        nextClickButtonListener()
        enterHiddenListener()
        prepareCities()
        prepareCountries()

    }

    private fun prepareCountries() {


        findViewById<EditText>(R.id.countryAutoCompleteText).setOnFocusChangeListener { _, focused ->
            val autotextView = findViewById<AutoCompleteTextView>(R.id.countryAutoCompleteText)
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1, Utils.getAllCountries(this)
            )
            autotextView.setAdapter(adapter)

        }
    }

    private fun prepareCities() {

        findViewById<EditText>(R.id.cityAutoCompleteText).setOnFocusChangeListener { _, focused ->
            val autotextView = findViewById<AutoCompleteTextView>(R.id.cityAutoCompleteText)

            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1, Utils.getAllCitys(this)
            )
            autotextView.setAdapter(adapter)
        }
    }


    private fun enterHiddenListener() {
        findViewById<EditText>(R.id.addressEditText).setOnFocusChangeListener { _, focused ->
            if (focused && !areFieldsRequiredFilled()) {
                findViewById<EditText>(R.id.addressEditText).imeOptions =
                    EditorInfo.IME_ACTION_NEXT;
            } else {
                findViewById<EditText>(R.id.addressEditText).imeOptions =
                    EditorInfo.IME_ACTION_DONE;
            }

        }
    }


    private fun nextClickButtonListener() {
        findViewById<Button>(R.id.contactNextButton).setOnClickListener {
            tryToGenerateLogs()
        }
    }

    // Calls  required logs
    private fun tryToGenerateLogs() {


        if (areFieldsRequiredFilled()) {
            generateContactLogs()
        }
    }

    //If required editText fields are filled returns true
    private fun areFieldsRequiredFilled(): Boolean {
        val phoneEditTextId = R.id.phoneEditText
        val emailEditTextId = R.id.emailEditText
        val countryAutoCompleteText = R.id.countryAutoCompleteText

        val validEmail: Boolean = validEmail()
        val validatedPhone: Boolean = validateIsEmptyEditText(phoneEditTextId)
        val validatedEmailEditTextId: Boolean = validateIsEmptyEditText(emailEditTextId)
        val validatedCountryEditTextId: Boolean =
            validateIsEmptyAutoCompleteText(countryAutoCompleteText)
        if (validatedPhone && validatedEmailEditTextId && validatedCountryEditTextId && validEmail) {
            return true;
        }
        return false
    }

    //Generates logs for required and optional EditTexts
    private fun generateContactLogs() {

        Log.i(getString(R.string.contactInformation), " ")
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
            findViewById<AutoCompleteTextView>(R.id.countryAutoCompleteText).text.toString()
        )
        val city = findViewById<AutoCompleteTextView>(R.id.cityAutoCompleteText).text.toString()
        if (!city.isEmpty()) {
            Log.i(
                getString(R.string.city),
                findViewById<AutoCompleteTextView>(R.id.cityAutoCompleteText).text.toString()
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

    //Validates if AutoCompleteTextView is empty and if it is, sets the error message
    private fun validateIsEmptyAutoCompleteText(fieldId: Int): Boolean {
        val editText = findViewById<AutoCompleteTextView>(fieldId)
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
  private fun onSaveInstanceSate(outState:Bundle){
      super.onSaveInstanceState(outState)
  }

}