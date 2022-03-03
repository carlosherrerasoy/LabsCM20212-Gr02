package co.edu.udea.compumovil.labs20212_gr02.listCountries

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import org.json.JSONTokener

class Utils {

    companion object {

        fun getAllCountries(context: Context): ArrayList<String> {

            val countriesNames = ArrayList<String>();
            val url = "https://restcountries.com/v3.1/region/america/"

            val postResquest = StringRequest(
                Request.Method.GET, url,
                { response ->
                    try {
                        val array = JSONTokener(response).nextValue() as JSONArray
                        for (i in 0 until array.length()) {
                            val countryName = JSONObject(
                                array.getJSONObject(i).get("name").toString()
                            ).get("common")
                            countriesNames.add(countryName.toString())
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            ) { error ->
                Log.e("Error", "")
            }
            Volley.newRequestQueue(context).add(postResquest)
            return countriesNames;
        }
        fun getAllCitys(context: Context): ArrayList<String> {

            val cityNames = ArrayList<String>();
            val url = "https://www.datos.gov.co/resource/xdk5-pm3f.json"

            val postResquest = StringRequest(Request.Method.GET, url,
                { response ->
                    try {
                        val array = JSONTokener(response).nextValue() as JSONArray
                        for (i in 0 until array.length()) {
                            val cityName = array.getJSONObject(i).get("municipio").toString()
                            cityNames.add(cityName.toString())
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            ) { error -> Log.e("Error", "") }
            Volley.newRequestQueue(context).add(postResquest)
            return cityNames;
        }

    }
}