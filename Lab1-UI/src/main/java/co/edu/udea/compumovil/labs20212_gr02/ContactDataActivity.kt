package co.edu.udea.compumovil.labs20212_gr02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.EditText
import android.content.Intent
import android.util.Log
import android.widget.Button


class ContactDataActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_data)
        emailFocusListener()
        nextClickButtonListener()
    }

    private fun nextClickButtonListener() {
        findViewById<Button>(R.id.contactNextButton).setOnClickListener {
            validateFields()
            val i=0
        }
    }

    private fun validateFields() {

        val phoneEditTextId=R.id.phoneEditText
        val emailEditTextId = R.id.emailEditText
        val countryEditTextId=R.id.countryEditText

        val  validEmail: Boolean?=validEmail()
        val validatedPhone: Boolean?=validateIsEmptyEditText(phoneEditTextId)
        val validatedEmailEditTextId: Boolean?=validateIsEmptyEditText(emailEditTextId)
        val validatedCountryEditTextId: Boolean?=validateIsEmptyEditText(countryEditTextId)


         if(validatedPhone==true && validatedEmailEditTextId==true && validatedCountryEditTextId==true && validEmail==true){

         }


    }


    //On email editText focus change
    private fun emailFocusListener() {

        findViewById<EditText>(R.id.emailEditText).setOnFocusChangeListener { _, focused ->
            if (!focused) {
               validEmail()
            }

        }
    }

    private fun validateIsEmptyEditText(fieldId:Int): Boolean?{
        val editText=findViewById<EditText>(fieldId)
        if(editText.text.toString().isEmpty()){
          editText.error=getString(R.string.required)
            return false
        }
        return true
    }

    private fun validEmail(): Boolean? {
        val emailText = findViewById<EditText>(R.id.emailEditText)
         if (!Patterns.EMAIL_ADDRESS.matcher(emailText.text.toString()).matches()) {
             emailText.error=getString(R.string.incorrectEmailMessage)
             return false
        }
        return true
    }


}