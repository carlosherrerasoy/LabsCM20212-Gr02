package co.edu.udea.compumovil.labs20212_gr02

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import java.text.SimpleDateFormat
import java.util.*

class PersonalDataActivity : AppCompatActivity() {


    val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_data)
        nextClickButtonListener()
        fechaNacimientoEditTextListener()
    }

    private fun tryToGenerateLogsAndOpenActivity() {
        if (areFieldsRequiredFilled()) {
            generatePersonalInfoLogs()
            openContactActivity()
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

        val fechaNacimientoTextView = findViewById<TextView>(R.id.fechaNacimientoTextView)
        Log.i(getString(R.string.fechaNacimientoLabelText), fechaNacimientoTextView.text.toString())


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
            tryToGenerateLogsAndOpenActivity()
        }
    }

    private fun fechaNacimientoEditTextListener() {
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                   dayOfMonth: Int) {
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView(getFechaNacimientoSeleccionado())
            }
        }


        val cambiarFechaNacimientoBoton = findViewById<Button>(R.id.cambiarFechaBoton)

        cambiarFechaNacimientoBoton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                DatePickerDialog(this@PersonalDataActivity,
                    dateSetListener,
                    // set DatePickerDialog to point to today's date when it loads up
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)).show()
            }

        })
    }

    private fun openContactActivity() {
        val contactActivity = Intent(this, ContactDataActivity::class.java)
        startActivity(contactActivity)
    }

    private fun areFieldsRequiredFilled(): Boolean {
        val nombresEditTextId = R.id.nombreEditText
        val apellidosEditTextId = R.id.apellidosEditText
        val nacimientoTextViewId = R.id.fechaNacimientoTextView


        val validatedNombres: Boolean = validateIsEmptyEditText(nombresEditTextId)
        val validatedApellidosEditTextId: Boolean = validateIsEmptyEditText(apellidosEditTextId)
        val validatedNacimientoTextViewId: Boolean = validateFechaNacimiento(nacimientoTextViewId)
        if (validatedNombres && validatedApellidosEditTextId && validatedNacimientoTextViewId) {
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
    private fun validateFechaNacimiento(fieldId: Int): Boolean {
        val textView = findViewById<TextView>(fieldId)
        if (textView.text.toString().equals(getString(R.string.fechaNacimientoLabelText))) {
            textView.error = getString(R.string.required)
            return false
        }
        return true
    }

    private fun updateDateInView(fechaNacimiento: String) {
        val fechaNacimientoView = findViewById<TextView>(R.id.fechaNacimientoTextView)
        fechaNacimientoView!!.text = fechaNacimiento
    }

    private fun getFechaNacimientoSeleccionado (): String {
        val myFormat = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(myFormat)
        return sdf.format(calendar.getTime())
    }

    private fun getFechaNacimientoTextView(): String {
        val fechaNacimientoView = findViewById<TextView>(R.id.fechaNacimientoTextView)
        return fechaNacimientoView.text.toString()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("fecha_nacimiento", getFechaNacimientoTextView())

        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        if (savedInstanceState.containsKey("fecha_nacimiento"))
            updateDateInView(savedInstanceState.getString("fecha_nacimiento").toString())

        super.onRestoreInstanceState(savedInstanceState)
    }
}