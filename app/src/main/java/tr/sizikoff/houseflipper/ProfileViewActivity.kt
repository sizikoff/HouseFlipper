package tr.sizikoff.houseflipper

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProfileViewActivity() : AppCompatActivity(){

    private lateinit var arr: ArrayList<HouseData>
    private var pos: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.info)

        arr = intent.getSerializableExtra("EXTRA_ARR") as ArrayList<HouseData>
        pos = intent.getIntExtra("EXTRA_POS", 0)
        var item = arr[pos]


        val name: TextView = findViewById(R.id.tvName)
        name.text = item.name

        val price: TextView = findViewById(R.id.tvPrice)
        price.text = item.price.toString()

        val floor: TextView = findViewById(R.id.tvFloor)
        floor.text = item.floor

        val company: TextView = findViewById(R.id.tvCompany)
        company.text = item.company

    }
}