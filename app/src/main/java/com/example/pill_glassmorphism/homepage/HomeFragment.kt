package com.example.pill_glassmorphism.homepage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.aminography.primecalendar.civil.CivilCalendar
import com.aminography.primedatepicker.picker.PrimeDatePicker
import com.aminography.primedatepicker.picker.callback.MultipleDaysPickCallback
import com.aminography.primedatepicker.picker.theme.DarkThemeFactory
import com.example.pill_glassmorphism.R
import com.example.pill_glassmorphism.auth.AuthListener
import com.example.pill_glassmorphism.databinding.FragmentHomeBinding
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


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentHomeBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_home, container, false)
        viewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.homeListener = this
        // Inflate the layout for this fragment
        return binding.root


    }


    override fun singleDay(){
        val themeFactory = object: DarkThemeFactory() {
        }

        val callback = MultipleDaysPickCallback {it ->
//            textView.text = it.joinToString {it.longDateString}
        }
        val today = CivilCalendar()
        val datePicker = PrimeDatePicker
            .bottomSheetWith(today)
            .pickMultipleDays(callback).applyTheme(themeFactory)
            .build()

        getActivity()?.let { datePicker.show(it.supportFragmentManager, "Void_Tracker") }
    }

}