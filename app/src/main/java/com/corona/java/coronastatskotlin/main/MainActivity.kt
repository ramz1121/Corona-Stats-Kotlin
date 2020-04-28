package com.corona.java.coronastatskotlin.main

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.corona.java.coronastatskotlin.R
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.formatter.ValueFormatter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.graph_layout.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity(), CaseNumbersInteractor.mainView {
    private var presenter: MainPresenter? = null
    //Part1
    val entries = ArrayList<Entry>()
    var dateArray = mutableListOf<String>()
    var caseArray = mutableListOf<Float>()
    var dateArrayConverted = mutableListOf<Float>()
    var caseArrayFinalConverted  = mutableListOf<Float>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
        llProgressBar?.visibility = View.VISIBLE
        presenter?.networkcall()
        //swipe to refresh listener
        swipe_refresh_layout.setOnRefreshListener {
            presenter?.networkcall()
            swipe_refresh_layout.isRefreshing = true
        }
    }

    fun initialize() {
        presenter = MainPresenter(this)
        // enable touch gestures

        // enable touch gestures
        chart!!.setTouchEnabled(true)

        chart!!.dragDecelerationFrictionCoef = 0.9f

        // enable scaling and dragging

        // enable scaling and dragging
        chart!!.isDragEnabled = true
        chart!!.setScaleEnabled(true)
        chart!!.setDrawGridBackground(false)
        chart!!.isHighlightPerDragEnabled = true

        // set an alternative background color

        // set an alternative background color
        chart!!.setBackgroundColor(Color.WHITE)
        chart!!.setViewPortOffsets(0f, 0f, 0f, 0f)

        // add data


        // get the legend (only possible after setting data)

        // get the legend (only possible after setting data)
        val l = chart!!.legend
        l.isEnabled = false

        val xAxis = chart!!.xAxis
        xAxis.position = XAxis.XAxisPosition.TOP_INSIDE
//        xAxis.typeface = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");
        xAxis.textSize = 10f
        xAxis.textColor = Color.WHITE
        xAxis.setDrawAxisLine(false)
        xAxis.setDrawGridLines(true)
        xAxis.textColor = Color.rgb(255, 192, 56)
        xAxis.setCenterAxisLabels(true)
        xAxis.valueFormatter = object : ValueFormatter() {
            private val mFormat =
                SimpleDateFormat("dd MMM", Locale.ENGLISH)

            override fun getFormattedValue(value: Float): String {
                val millis =
                    TimeUnit.HOURS.toMillis(value.toLong())
                return mFormat.format(Date(millis))
            }
        }
        dateArray= presenter!!.showArrayOfDates()
        caseArray=presenter!!.showArrayOfTotalCases()
        try {
            val sdf = SimpleDateFormat("dd/MM/yyyy")
            for (n in 0 until dateArray.size) {
                val date = sdf.parse(dateArray[n])
                dateArrayConverted.add(date.time.toFloat())

            }
        } catch (e: ParseException) {
            e.printStackTrace()
        }

    }

    override fun updateViewData() {
        tv_currentcases?.setText(presenter?.showTotalConfirmed())
    tv_recoveredcases?.setText(presenter?.showTotalRecovered())
    tv_deaths?.setText(presenter?.showTotalDeaths())
    tv_active_cases?.setText(presenter?.showActiveCases())
    tv_closed_cases?.setText(presenter?.showClosedCases())
    llProgressBar?.visibility = View.GONE
    swipe_refresh_layout.isRefreshing = false
}
}