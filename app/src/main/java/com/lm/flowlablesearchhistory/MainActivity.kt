package com.lm.flowlablesearchhistory

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.lm.flowlablesearchhistory.cache.SearchHistoryDataCache
import com.lm.flowlablesearchhistory.configuration.Constants
import com.lm.flowlablesearchhistory.eventbus.BaseEvent
import com.lm.flowlablesearchhistory.eventbus.ConcreteFactory
import com.lm.flowlablesearchhistory.view.layouts.FlowLayout
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this)
        initData()
        updateLabelData()
        btn_add_content.setOnClickListener {
            val content = et_print_word.text ?: ""
            if (content.trim().isEmpty()) {
                Toast.makeText(this@MainActivity, "内容不能为空", Toast.LENGTH_LONG).show()
            } else {
                SearchHistoryDataCache.getInstance()
                    .addSearchHistoryContent(content.toString().trim())
                updateLabelData()
            }
            et_print_word.text.clear()
        }
        btn_V.setOnClickListener {
            flowlayout_history_search_5.visibility = View.VISIBLE
            flowlayout_history_search_2.visibility = View.GONE
            btn_V.visibility = View.GONE
        }
        ib_head_history_search_delete.setOnClickListener {
            AlertDialog.Builder(this@MainActivity).setTitle("是否清除历史数据")
                .setPositiveButton("okay") { _, _ ->
                    SearchHistoryDataCache.getInstance().removeAllHistoryContent()
                    updateLabelData()
                }
                .setNegativeButton("cancel") { dialog, _ ->
                    dialog.dismiss()
                }
                .create().show()
        }
    }

    private fun initData() {
        val data = SearchHistoryDataCache.getInstance().historyCacheData
        if (data.isEmpty()) {
            val cacheData = mutableListOf(
                "A.J.弗克斯",
                "岛上的书店",
                "左右男女",
                "盗墓笔记",
                "东野圭吾",
                "满秋",
                "X嫌疑人",
                "月亮与六便士",
                "平凡的世界"
            )
            for (content: String in cacheData) {
                SearchHistoryDataCache.getInstance().addSearchHistoryContent(content)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this)
    }

    /**
     * 更新Label数据
     */
    private fun updateLabelData() {
        flowlayout_history_search_2.removeAllViews()
        flowlayout_history_search_5.removeAllViews()

        val cacheData = SearchHistoryDataCache.getInstance().historyCacheData
        if (cacheData.isNotEmpty()) {
            ll_history_search_layout.visibility = View.VISIBLE
            if (flowlayout_history_search_5.visibility == View.GONE) {
                flowlayout_history_search_2.visibility = View.VISIBLE
                flowlayout_history_search_5.visibility = View.GONE
            } else {
                flowlayout_history_search_2.visibility = View.GONE
                flowlayout_history_search_5.visibility = View.VISIBLE
            }
            for (data in cacheData) {
                flowlayout_history_search_2.addView(
                    buildLayoutView(
                        data,
                        flowlayout_history_search_2
                    )
                )
                flowlayout_history_search_5.addView(
                    buildLayoutView(
                        data,
                        flowlayout_history_search_5
                    )
                )
            }
        } else {
            ll_history_search_layout.visibility = View.GONE
        }
    }

    /**
     * 构建历史搜索系那是控件
     */
    private fun buildLayoutView(text: String, layout: FlowLayout): RelativeLayout {
        var view: RelativeLayout = LayoutInflater.from(this@MainActivity)
            .inflate(R.layout.item_history_label_layout, layout, false) as RelativeLayout
        var tv: TextView = view.findViewById(R.id.tv_content)
        tv.tag = text
        tv.text = if (text.length > 15) "${text.substring(0, 14)}..." else text
        tv.setOnClickListener(labelClickListener)
        return view
    }

    @Subscribe
    fun onEventMainThread(event: BaseEvent) {
        when (event.what) {
            Constants.SEARCH_HISTORY_LABEL_MAX_LINE_CODE -> {
                if (flowlayout_history_search_5.visibility != View.VISIBLE) {
                    btn_V.visibility = View.VISIBLE
                }
            }
        }
    }

    //标签点击事件对象
    private val labelClickListener = FlowClickCallBackListener()

    /**
     * Label点击事件监听器
     */
    inner class FlowClickCallBackListener : View.OnClickListener {
        override fun onClick(v: View?) {
            if (v is TextView) {
                var _search_content = v.tag.toString()
                SearchHistoryDataCache.getInstance().addSearchHistoryContent(_search_content)
                updateLabelData()
            }
        }
    }
}
