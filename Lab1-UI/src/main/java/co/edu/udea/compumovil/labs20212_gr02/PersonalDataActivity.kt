package co.edu.udea.compumovil.labs20212_gr02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Spinner

class PersonalDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_data)
        nextClickButtonListener()
    }


    private fun tryToGenerateLogs() {
        if (areFieldsRequiredFilled()) {
            generatePersonalInfoLogs()
        }
    }

    private fun generatePersonalInfoLogs() {

        Log.i(getString(R.string.personalInformation), " ")
        Log.i(getString(R.string.nombreHelpText),findViewById<EditText>(R.id.nombreEditText).text.toString())

        Log.i(getString(R.string.apellidosHintValue),findViewById<EditText>(R.id.apellidosEditText).text.toString())

        val sexoMasculino = findViewById<RadioButton>(R.id.sexoHombreRadio).isChecked
        val sexoFemenino = findViewById<RadioButton>(R.id.sexoMujerRadio).isChecked

        var valorSexo = "N/A"
        if (sexoMasculino){
            valorSexo = getString(R.string.hombreRadioButtonText)
        } else if(sexoFemenino){
            valorSexo = getString(R.string.mujerRadioButtonText)
        }

        Log.i(
            getString(R.string.sexoTitulo),
            valorSexo
        )

        val escolaridadSpinner = findViewById<Spinner>(R.id.escolaridadSpinner)
        val itemPosition = escolaridadSpinner.selectedItemPosition

        var escolaridad = "N/A"
        if (itemPosition > 0)
            escolaridad = escolaridadSpinner.selectedItem.toString()

        Log.i(
            getString(R.string.escolaridad_titulo),
            escolaridad
        )
    }

    private fun nextClickButtonListener() {
        findViewById<Button>(R.id.seleccionarButton).setOnClickListener {
            tryToGenerateLogs()
        }
    }

    private fun areFieldsRequiredFilled(): Boolean {
        val nombresEditTextId = R.id.nombreEditText
        val apellidosEditTextId = R.id.apellidosEditText
        val nacimientoEditTextId = R.id.fechaNacimientoEditDate


        val validatedNombres: Boolean = validateIsEmptyEditText(nombresEditTextId)
        val validatedApellidosEditTextId: Boolean = validateIsEmptyEditText(apellidosEditTextId)
        val validatedNacimientoEditTextId: Boolean = validateIsEmptyEditText(nacimientoEditTextId)
        if (validatedNombres && validatedApellidosEditTextId && validatedNacimientoEditTextId) {
            return true;
        }
        return false
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
}