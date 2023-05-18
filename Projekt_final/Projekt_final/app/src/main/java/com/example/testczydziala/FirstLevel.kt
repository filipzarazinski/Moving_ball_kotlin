package com.example.testczydziala

import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import java.util.*
import kotlin.math.sqrt

class FirstLevel : AppCompatActivity(), SensorEventListener {
    lateinit var sensorManager: SensorManager
    lateinit var text: TextView
    lateinit var text2: TextView
    lateinit var imageV: ImageView
    var x_acc = 0f  //variables to the circle x
    var y_acc = 0f   //variables to the circle y
    var xVelocity =0f  //variables to the Velocity x
    var yVelocity =0f  //variables to the Velocity y
    var frameTime = 0f //set speed
    var xPosition = 0f  //position x circle
    var yPosition = 0f  // position y circle
    var xmax = 620f   // max width
    var ymax = 970f   // max heigh
    var yS = 0f  //variables to Calculate distance travelled in specific time () - x
    var xS = 0f   //variables to Calculate distance travelled in specific time () - y
    var check_lose = false  //variables check lose
    var check_win = false  ///variables check win

    var radius = 30f //radius circle
    var xmin = radius  //min width
    var ymin = radius  // min heigh
    val bitmap = Bitmap.createBitmap(650, 1000, Bitmap.Config.ARGB_4444)

    val canvas = Canvas(bitmap)
    var paint = Paint()
    var paint2= Paint()
    var paint3= Paint()





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_level)
        frameTime=intent.getFloatExtra("frameTime",0.5f)
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // wymuszenie orientacji telefonu
        setUpSensorStaff()

        imageV = findViewById(R.id.imageV)

        canvas.drawARGB(255, 78, 168, 186);


        paint.isFilterBitmap = true
        paint.isAntiAlias = true
        paint.color = Color.YELLOW

        paint2.isFilterBitmap = true
        paint2.isAntiAlias = true
        paint2.color = Color.RED

        paint3.isFilterBitmap = true
        paint3.isAntiAlias = true
        paint3.color = Color.GREEN

        text = findViewById(R.id.textView)

        // ustawienie bitmapy jako tlo w imageview
        imageV.background = BitmapDrawable(getResources(), bitmap)

    }
        /*setup akcelerometru*/
    private fun setUpSensorStaff() {
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)?.also {
            sensorManager.registerListener(this,
                it,
                SensorManager.SENSOR_DELAY_GAME, //ustawienie dokladnosci sensora
                SensorManager.SENSOR_DELAY_GAME)
        }
    }

    override fun onSensorChanged(event: SensorEvent) {
        if(event?.sensor?.type == Sensor.TYPE_ACCELEROMETER){
            x_acc = -event.values[0].toFloat()   // przechwytywanie nachylenia telefonu w plaszczyznie x
            y_acc = event.values[1].toFloat()    // przechwytywanie nachylenia telefonu w plaszczyznie y
            updateBall() //funkcja poruszajaca pilka
            /*Rysowanie zoltego kolka, kolek czerownych, zielonych*/
            canvas.drawARGB(255, 78, 168, 186);
            canvas.drawCircle(xPosition,yPosition,radius,paint)
            canvas.drawCircle(100f,300f,75f,paint2) //1
            canvas.drawCircle(275f,300f,75f,paint2) //2
            canvas.drawCircle(500f,300f,75f,paint2) //3
            canvas.drawCircle(300f,600f,125f,paint2) //4
            canvas.drawCircle(100f,600f,30f,paint2) //5
            canvas.drawCircle(550f,600f,30f,paint2) //6
            canvas.drawCircle(125f,850f,50f,paint3) //7
            canvas.drawCircle(425f,850f,50f,paint2) //8
            canvas.drawCircle(350f,125f,50f,paint2) //9


            checkLose()
            if(check_lose== true)
            {

                sensorManager.unregisterListener(this)
                lose()
            }
            if (check_win == true)
            {

                sensorManager.unregisterListener(this)
                win()
            }


            text.text = "  "


        }

    }

    private fun checkLose() {
        var odleglosc1 = 0f
        var odleglosc2 = 0f
        var odleglosc3 = 0f
        var odleglosc4 = 0f
        var odleglosc5 = 0f
        var odleglosc6 = 0f
        var odleglosc7 = 0f
        var odleglosc8 = 0f
        var odleglosc9 = 0f

        odleglosc1 = sqrt(((xPosition-100f)*(xPosition-100f))+((yPosition-300f)*(yPosition-300f)))
        odleglosc2 = sqrt(((xPosition-275f)*(xPosition-275f))+((yPosition-300f)*(yPosition-300f)))
        odleglosc3 = sqrt(((xPosition-500f)*(xPosition-500f))+((yPosition-300f)*(yPosition-300f)))
        odleglosc4 = sqrt(((xPosition-300f)*(xPosition-300f))+((yPosition-600f)*(yPosition-600f)))
        odleglosc5 = sqrt(((xPosition-100f)*(xPosition-100f))+((yPosition-600f)*(yPosition-600f)))
        odleglosc6 = sqrt(((xPosition-550f)*(xPosition-550f))+((yPosition-600f)*(yPosition-600f)))
        odleglosc7 = sqrt(((xPosition-125f)*(xPosition-125f))+((yPosition-850f)*(yPosition-850f)))
        odleglosc8 = sqrt(((xPosition-425f)*(xPosition-425f))+((yPosition-850f)*(yPosition-850f)))
        odleglosc9 = sqrt(((xPosition-350f)*(xPosition-350f))+((yPosition-125f)*(yPosition-125f)))
        if (odleglosc1 <= 75f+radius ||
            odleglosc2 <= 75f+radius ||
            odleglosc3 <= 75f+radius ||
            odleglosc4 <= 125f+radius ||
            odleglosc5 <= 30f+radius ||
            odleglosc6 <= 30f+radius ||
            odleglosc9 <= 50f+radius ||
            odleglosc8 <= 50f+radius
        )
        {
            check_lose   = true

        }
        if(odleglosc7 <= 50f+radius)
        {
            check_win = true
        }
    }

    private fun lose()
    {
        val intent = Intent(this,Lose::class.java)
        intent.putExtra("frameTime",frameTime)
        startActivity(intent)
    }

    private fun win()
    {
        val intent = Intent(this,Win::class.java)
        intent.putExtra("frameTime",frameTime)
        startActivity(intent)
    }

    private fun start()
    {
        val intent = Intent(this,StartGame::class.java)
        startActivity(intent)
    }
    private fun updateBall() {


        //Obliczanie predkosci
        xVelocity += x_acc * frameTime
        yVelocity += y_acc * frameTime

        //Obliczanie dystansu w tym czasie
        xS = xVelocity / 2 * frameTime
        yS = yVelocity / 2 * frameTime
        if(xS < xmin){xS = xmin}
        if(xS > xmax){xS = xmax}
        if(yS < ymin){yS = xmin}
        if(yS > ymax){yS = ymax}



        xPosition = xS
        yPosition = yS

    }
    override fun onAccuracyChanged(event: Sensor?, accuracy: Int) {
        return;    }

    override fun onDestroy() {
        super.onDestroy()
    }

}