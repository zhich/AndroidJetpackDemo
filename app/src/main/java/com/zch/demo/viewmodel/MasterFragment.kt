package com.zch.demo.viewmodel

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zch.demo.R
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_master.*


/**
 * Created by zch on 2018/10/26.
 */
class MasterFragment : Fragment() {

    private val dataList = listOf(User("1", "张三"), User("2", "李四"), User("3", "王五"))

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_master, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var model = activity?.run {
            ViewModelProviders.of(this).get(SharedViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        lvMaster.adapter = ArrayAdapter<User>(
                activity,
                android.R.layout.simple_expandable_list_item_1,
                dataList)

        lvMaster.setOnItemClickListener { _, _, position, _ ->
            model.select(dataList[position])
        }
    }
}