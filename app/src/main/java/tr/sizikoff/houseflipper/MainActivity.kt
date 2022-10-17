package tr.sizikoff.houseflipper

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.getbase.floatingactionbutton.FloatingActionButton
import com.getbase.floatingactionbutton.FloatingActionsMenu
import java.util.*


class MainActivity : AppCompatActivity(),ItemClickListener {

    lateinit var houseList: ArrayList<HouseData>
    lateinit var recycler: RecyclerView
    lateinit var tex:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler = findViewById(R.id.recycler_view)
        tex = findViewById(R.id.tex)
        houseList = ArrayList()
        recycler.adapter = MyAdapter(houseList,this)
        recycler.layoutManager = LinearLayoutManager(this)

        val actionB = findViewById<View>(R.id.action_b) as FloatingActionButton
        val actionC = findViewById<View>(R.id.action_c) as FloatingActionButton


        actionB.setOnClickListener {
            val editDialog = Dialog(this@MainActivity)
            editDialog.setContentView(R.layout.alert_dialog)

            val etName = editDialog.findViewById<EditText>(R.id.etName)
            val etDesignation = editDialog.findViewById<EditText>(R.id.etPrice)
            val etDepartment = editDialog.findViewById<EditText>(R.id.etFloor)
            val etStation = editDialog.findViewById<EditText>(R.id.etComp)
            val btnModify = editDialog.findViewById<Button>(R.id.btnModify)
            val btnDecline = editDialog.findViewById<Button>(R.id.btnDecline)


            btnModify.setOnClickListener {

                if (etName.getText().isEmpty() || etDesignation.getText().isEmpty()||etDepartment.getText().isEmpty()||etStation.getText().isEmpty()) {
                    Toast.makeText(this@MainActivity,"Поля не заполнены",Toast.LENGTH_LONG).show()
                } else {
                    houseList.add(
                        0, HouseData(
                            etName.text.toString(),
                            Integer.parseInt(etDesignation.text.toString()), etDepartment.text.toString(),
                            etStation.text.toString()
                        )
                    )
                    recycler.adapter?.notifyDataSetChanged()
                    editDialog.dismiss()
                    tex.visibility = View.GONE
                }

            }
            editDialog.show()
            btnDecline.setOnClickListener {
                editDialog.hide()
            }
        }

        actionC.setOnClickListener {
            houseList.sortBy { it.price }
            recycler.adapter?.notifyDataSetChanged()
        }
    }


    override fun onClick(position: Int) {
        Intent(this, ProfileViewActivity()::class.java).also {
            it.putExtra("EXTRA_ARR", houseList)
            it.putExtra("EXTRA_POS", position)
            startActivity(it)
        }
    }

    override fun onEdit(position: Int) {
        val editDialog = Dialog(this)
        editDialog.setContentView(R.layout.alert_dialog)

        val etName = editDialog.findViewById<EditText>(R.id.etName)
        val etDesignation = editDialog.findViewById<EditText>(R.id.etPrice)
        val etDepartment = editDialog.findViewById<EditText>(R.id.etFloor)
        val etStation = editDialog.findViewById<EditText>(R.id.etComp)
        val btnModify = editDialog.findViewById<Button>(R.id.btnModify)
        val btnDecline = editDialog.findViewById<Button>(R.id.btnDecline)

        etName.setText(houseList[position].name)
        etDesignation.setText(houseList[position].price.toString())
        etDepartment.setText(houseList[position].floor)
        etStation.setText(houseList[position].company)

        btnModify.setOnClickListener{

            if (etName.getText().isEmpty() || etDesignation.getText().isEmpty()||etDepartment.getText().isEmpty()||etStation.getText().isEmpty()) {
                Toast.makeText(this@MainActivity,"Поля не заполнены",Toast.LENGTH_LONG).show()
            }else{
                houseList[position] =
                    HouseData(etName.text.toString(),
                        Integer.parseInt(etDesignation.text.toString()), etDepartment.text.toString(),
                        etStation.text.toString())
                recycler.adapter?.notifyDataSetChanged()
                editDialog.dismiss()
            }

        }
        editDialog.show()
        btnDecline.setOnClickListener{
            editDialog.hide();
        }

    }

}