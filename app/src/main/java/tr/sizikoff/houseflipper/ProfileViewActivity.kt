package tr.sizikoff.houseflipper

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ProfileViewActivity() : AppCompatActivity(){

    private lateinit var arr: ArrayList<HouseData>
    private var pos: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.info)

        // Make sure to make your data class as Serializable
        arr = intent.getSerializableExtra("EXTRA_ARR") as ArrayList<HouseData>
        pos = intent.getIntExtra("EXTRA_POS", 0)
        var item = arr[pos]


        val tvDesignation: TextView = findViewById(R.id.tvName)
        tvDesignation.text = item.name

        val tvDepartment: TextView = findViewById(R.id.tvPrice)
        tvDepartment.text = item.price.toString()

        val tvStation: TextView = findViewById(R.id.tvFloor)
        tvStation.text = item.floor

        val tvCompany: TextView = findViewById(R.id.tvCompany)
        tvCompany.text = item.company

    }
}