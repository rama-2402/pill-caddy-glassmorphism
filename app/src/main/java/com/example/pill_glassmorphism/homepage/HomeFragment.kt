package com.example.pill_glassmorphism.homepage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.aminography.primecalendar.civil.CivilCalendar
import com.aminography.primedatepicker.picker.PrimeDatePicker
import com.aminography.primedatepicker.picker.callback.MultipleDaysPickCallback
import com.aminography.primedatepicker.picker.callback.RangeDaysPickCallback
import com.aminography.primedatepicker.picker.callback.SingleDayPickCallback
import com.example.pill_glassmorphism.R
import com.example.pill_glassmorphism.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance


class HomeFragment : Fragment(), KodeinAware, HomeListener {
    /**
     * A Kodein Aware class must be within reach of a [Kodein] object.
     */
    override val kodein by kodein()
    private val factory: HomeViewModelFactory by instance()
    private lateinit var viewModel: HomeViewModel
    var progress: Int = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentHomeBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home, container, false
        )
        viewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.homeListener = this
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fab()
        coordLayout()
        waterCardFill()
    }

    fun waterCardFill() {
        imageView.setOnClickListener {
            progress += 10
            imageView.beerProgress = progress
        }
    }

    override fun singleDay() {
//        val themeFactory = object: DarkThemeFactory() {
//        }
        val callback = SingleDayPickCallback { it ->
            stat_for_the_day.text = it.longDateString
            fab()
        }
        val today = CivilCalendar()
        val datePicker = PrimeDatePicker
            .bottomSheetWith(today)
            .pickSingleDay(callback)
//            .applyTheme(themeFactory)
            .build()

        getActivity()?.let { datePicker.show(it.supportFragmentManager, "Void_Tracker") }
    }

    override fun multipleDays() {

        val callback = MultipleDaysPickCallback { it ->
            stat_for_the_day.text = it.joinToString {it.longDateString}
            fab()
        }
        val today = CivilCalendar()
        val datePicker = PrimeDatePicker
            .bottomSheetWith(today)
            .pickMultipleDays(callback)
//                .applyTheme(themeFactory)
            .build()

        getActivity()?.let { datePicker.show(it.supportFragmentManager, "Void_Tracker") }
    }

    override fun rangeOfDays() {

        val callback = RangeDaysPickCallback { start, end ->
            stat_for_the_day.text = ("From: ${start.longDateString}\nTo: ${end.longDateString}")
            fab()
        }
        val today = CivilCalendar()
        val datePicker = PrimeDatePicker
            .bottomSheetWith(today)
            .pickRangeDays(callback)
//                .applyTheme(themeFactory)
            .build()

        getActivity()?.let { datePicker.show(it.supportFragmentManager, "Void_Tracker") }
    }

    override fun fab() {
        two_fab.visibility = View.GONE
        two_fab_txt.visibility = View.GONE
        three_fab.visibility = View.GONE
        three_fab_txt.visibility = View.GONE

        add_fab.shrink()

    }

    override fun expandFAB() {
        if (two_fab.visibility == View.GONE) {
            two_fab.show()
            two_fab_txt.visibility = View.VISIBLE
            three_fab.show()
            three_fab_txt.visibility = View.VISIBLE
            add_fab.extend()
        } else {
            singleDay()
        }
    }

    fun coordLayout() {
        coordinator.setOnClickListener {
            fab()
        }
    }


}