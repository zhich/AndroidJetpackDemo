package com.zch.demo.viewmodel

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zch.demo.R
import android.arch.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_detail.*


/**
 * Created by zch on 2018/10/27.
 */
class DetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var model: SharedViewModel = activity?.run {
            ViewModelProviders.of(this).get(SharedViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        model.selected.observe(this, Observer<User> { item ->
            tvDetail.setText("${item?.id} : ${item?.name}")
        })
    }
}