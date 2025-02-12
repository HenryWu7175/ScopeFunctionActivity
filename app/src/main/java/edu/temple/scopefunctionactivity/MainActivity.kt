package edu.temple.scopefunctionactivity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // You can test your helper functions by  calling them from onCreate() and
        // printing their output to the Log, which is visible in the LogCat:
        // eg. Log.d("function output", getTestDataArray().toString())
        var numbersarray = getTestDataArray()
        Log.d("function output", numbersarray.toString())

        val doublearray: MutableList<Double> = mutableListOf(1.0, 50.0, 1.0, 55.0, 100.0)
        var trueifavgless = averageLessThanMedian(doublearray)
        Log.d("function output", trueifavgless.toString())

        val collection1 = listOf(10, 20, 30, 40, 50)
        val position1 = 2 // Should display "30"
        val view1 = getView(position1, null, collection1, this)
        Log.d("function output", "View text (new): ${(view1 as TextView).text}")

        val collection2 = listOf(100, 200, 300, 400, 500)
        val position2 = 1 // Should display "200"
        val recycledView = TextView(this).apply { text = "Old Value" }
        val view2 = getView(position2, recycledView, collection2, this)

        Log.d("function output", "View text (recycled): ${(view2 as TextView).text}")
        Log.d("function output", "Is recycled view same? ${recycledView === view2}")
    }


    /* Convert all the helper functions below to Single-Expression Functions using Scope Functions */
    // eg. private fun getTestDataArray() = ...

    // HINT when constructing elaborate scope functions:
    // Look at the final/return value and build the function "working backwards"

    // Return a list of random, sorted integers
    private fun getTestDataArray(): List<Int> =
        MutableList(10) { Random.nextInt() }.apply { sort() }


    // Return true if average value in list is less than median value, false otherwise
    private fun averageLessThanMedian(listOfNumbers: List<Double>) =
        listOfNumbers.sorted().run {
            val median = if (size % 2 == 0)
                (this[size / 2] + this[(size - 1) / 2]) / 2
            else
                this[size / 2]
            listOfNumbers.average() < median
        }


    // Create a view from an item in a collection, but recycle if possible
    private fun getView(position: Int, recycledView: View?, collection: List<Int>, context: Context): View =
        (recycledView as? TextView ?: TextView(context).apply {
            setPadding(5, 10, 10, 0)
            textSize = 22f
        }).apply {
            text = collection[position].toString()
        }
}