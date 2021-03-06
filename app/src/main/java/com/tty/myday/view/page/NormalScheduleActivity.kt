package com.tty.myday.view.page

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tty.myday.R
import com.tty.myday.data.DataSource
import com.tty.myday.listener.OnRItemClickListener
import com.tty.myday.model.ScheduleTag
import com.tty.myday.model.ScheduleTemp
import com.tty.myday.util.TagConst
import com.tty.myday.util.TimeUtil
import com.tty.myday.view.adapter.ScheduleItemAdapter
import com.tty.myday.view.converter.ColorIconConverter
import com.tty.myday.view.helper.MyDecoration
import kotlinx.android.synthetic.main.activity_normal_schedule.*
import java.util.*

class NormalScheduleActivity : AppCompatActivity(), View.OnClickListener,OnRItemClickListener {

    //private var taskType:Int = 1
    private var scheduleTemp = ScheduleTemp()

    override fun onItemClick(v: View?, position: Int) {
        Log.d(TagConst.UI,"item:: 点击了第${position}项")
    }

    override fun onClick(v: View?) {
        when(v){
            img_back_normal_schedule->{
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            float_button_add_normal->{
                float_button_add_normal.hide()
                card_add_normal.visibility = View.VISIBLE

            }
            btn_type_normal->{
                scheduleTemp.isTask=!scheduleTemp.isTask
                tbx_type_normal.text = scheduleTemp.typeDisplay
                edt_title_normal.hint = "添加${scheduleTemp.typeDisplay}"
                img_add_normal.visibility = if (scheduleTemp.isTask){
                    View.VISIBLE
                } else{
                    View.INVISIBLE
                }
            }
            btn_time_normal->{
                onCreateMenu(btn_time_normal)
            }
            btn_close_time->{
                scheduleTemp.date = null
                btn_close_time.visibility = View.GONE
                tbx_time_normal.text = scheduleTemp.dateDisplay
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_normal_schedule)

        img_back_normal_schedule.setOnClickListener(this)
        float_button_add_normal.setOnClickListener(this)
        btn_type_normal.setOnClickListener(this)
        btn_time_normal.setOnClickListener(this)
        btn_close_time.setOnClickListener(this)

        //防止回收后出现空指针异常的问题
        DataSource.initIfNotLoadAsync(this)

        if (intent.extras!=null){
            val bundle:Bundle = intent.extras!!
            val id = bundle.getLong("ID")
            val tag: ScheduleTag = DataSource.tags.find { scheduleTag -> scheduleTag.id == id  }!!

            //初始化
            img_icon_normal.setImageResource(ColorIconConverter().getIconRes(tag.icon!!))
            tbx_title_normal.text = tag.title
            //TODO("获取用户人数")
            val userCount = 0
            if (userCount == 0){
                tbx_user_normal.visibility = View.GONE
            }else{
                tbx_user_normal.text = userCount.toString()
                tbx_user_normal.visibility = View.VISIBLE
            }
        } else{
            Log.d(TagConst.UI,"scheduleActivity::不存在bundle")
        }

        //TODO("重写读取数据集的逻辑")
        val adapter = ScheduleItemAdapter(DataSource.scheduleMap["item1"]!!)

        recyclerView_item.overScrollMode =RecyclerView.OVER_SCROLL_NEVER
        recyclerView_item.adapter = adapter;
        recyclerView_item.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        //recyclerView_item.addItemDecoration(MItemDecoration(this,1, Color.GRAY))
        recyclerView_item.addItemDecoration(MyDecoration(this,RecyclerView.VERTICAL,40))


        adapter.setOnRItemClickListener(this)

    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
    }

    private fun onCreateMenu(v:View)
    {
        Log.d(TagConst.UI,"创建menu$v")
        val popupMenu:PopupMenu = PopupMenu(this,v)
        menuInflater.inflate(
            when(v){
                //btn_type_normal->R.menu.type_normal
                btn_time_normal->
                {
                    R.menu.time_normal
                }
                else->R.menu.type_normal
            },
            popupMenu.menu
        )


        popupMenu.setOnMenuItemClickListener{item->
            when(v){
                btn_time_normal->{
                    val calendar = Calendar.getInstance()
                    calendar.time = Date()
                    when(item.itemId){
                        //group of time
                        R.id.time_normal_1->{
                            scheduleTemp.setDateNormal(0)
                        }
                        R.id.time_normal_2->{
                            scheduleTemp.setDateNormal(1)
                        }
                        R.id.time_normal_3->{
                            scheduleTemp.setDateNormal(7)
                        }
                        R.id.time_normal_4->{
                            //TODO("弹出时间选择框")
                            val fragment = DateSelectFragment()
                            fragment.show(this.supportFragmentManager,"选择日期")

                            scheduleTemp.setDateNormal(15)
                        }
                    }

                    tbx_time_normal.text = scheduleTemp.dateDisplay
                    btn_close_time.visibility = View.VISIBLE
                }

            }

            false
        }

        popupMenu.show()
    }

}
