package com.main.fragment

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dinh.service.APIService
import com.dinh.service.APIUntil
import com.main.activity.R
import com.main.adapter.ListHomeAdapter
import com.main.adapter.SlideHomeAdapter
import com.main.model.Film
import com.main.model.Song
import com.smarteist.autoimageslider.IndicatorAnimations.SCALE_DOWN
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Response
import java.util.*


class HomeFragment : Fragment() {
    private lateinit var apiService: APIService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        apiService = APIUntil.server
        addSlideHome() //slide top fragment home
        addLayout()
    }
    private fun addLayout(){
        var productList: List<Film>
        var adapterFilm: ListHomeAdapter
        rc_home.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        apiService.getAllFilm().enqueue(object :retrofit2.Callback<List<Film>>{
            override fun onFailure(call: Call<List<Film>>, t: Throwable) {
                Log.e("onFailure",t.message)
            }

            override fun onResponse(call: Call<List<Film>>, response: Response<List<Film>>) {
                try {
                    if (response.isSuccessful){
                        productList = response.body() as List<Film>
                        adapterFilm = ListHomeAdapter(context, productList)
                        rc_home?.adapter = adapterFilm
                        rc_home?.setHasFixedSize(true)
                        adapterFilm.notifyDataSetChanged()
                    }
                    else{
                        Log.e("HAHA","NO DATA")
                    }
                }catch (e:Exception){
                    Log.e("ERROR",e.message)
                }
            }

        })
    }

    private fun addSlideHome() {
        val adapter = SlideHomeAdapter()
        imgSlider.sliderAdapter = adapter
        imgSlider.setIndicatorAnimation(SCALE_DOWN) //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        imgSlider.setSliderTransformAnimation(SliderAnimations.CUBEINSCALINGTRANSFORMATION)
        imgSlider.autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH
        imgSlider.indicatorSelectedColor = Color.WHITE
        imgSlider.indicatorUnselectedColor = Color.argb(1, 248, 249, 249)
        imgSlider.scrollTimeInSec = 2 //set scroll delay in seconds :
        imgSlider.startAutoCycle()
    }
}
