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
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlin.math.sqrt

class TestMove : AppCompatActivity(),SensorEventListener {
    lateinit var sensorManager: SensorManager
    lateinit var text: TextView
    lateinit var text2: TextView
    lateinit var imageV: ImageView
    lateinit var button5: Button

    var x_acc = 0f  //zmienne do akcelerometru
    var y_acc = 0f   //zmienne do akcelerometru
    var xVelocity =0f
    var yVelocity =0f
    var frameTime = 0f
    var xPosition = 0f
    var yPosition = 0f
    var xmax = 620f
    var ymax = 970f
    var yS = 0f
    var xS = 0f


    var radius = 30f
    var xmin = radius
    var ymin = radius
    val bitmap = Bitmap.createBitmap(650, 1000, Bitmap.Config.ARGB_4444)

    val canvas = Canvas(bitmap)
    var paint = Paint()
    var paint2= Paint()
    var paint3= Paint()





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_move)
        frameTime=intent.getFloatExtra("frameTime",0.5f)
        button5 = findViewById(R.id.button5)
        button5.setOnClickListener{
            sensorManager.unregisterListener(this)
            val intent = Intent (this,StartGame::class.java)
            intent.putExtra("frameTime",frameTime)
            startActivity(intent)
        }


        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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
        text2 = findViewById(R.id.textView2)


        // set bitmap as background to ImageView

        imageV.background = BitmapDrawable(getResources(), bitmap)

    }

    private fun setUpSensorStaff() {
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)?.also {
            sensorManager.registerListener(this,
                it,
                SensorManager.SENSOR_DELAY_GAME,
                SensorManager.SENSOR_DELAY_GAME)
        }
    }

    override fun onSensorChanged(event: SensorEvent) {
        if(event?.sensor?.type == Sensor.TYPE_ACCELEROMETER){
            x_acc = -event.values[0].toFloat()
            y_acc = event.values[1].toFloat()
            updateBall()
            canvas.drawARGB(255, 78, 168, 186);
            canvas.drawCircle(xPosition,yPosition,radius,paint)


            text.text = "x ${x_acc}, y ${y_acc.toInt()} \n xS = ${xS.toInt()} ,yS = ${yS.toInt()} \n" +
                    " wsp_x = ${xPosition.toInt()} ,wsp_y = ${yPosition.toInt()}\n " +
                    "pred_x = ${xVelocity.toInt()} ,pred_y = ${yVelocity.toInt()}"


        }

    }


    private fun updateBall() {


        //Calculate new speed

        xVelocity += x_acc * frameTime
        yVelocity += y_acc * frameTime


            //Calc distance travelled in that time
            xS = xVelocity / 2 * frameTime
            yS = yVelocity / 2 * frameTime
            if (xS < xmin) {
                xS = xmin

            }
            if (xS > xmax) {
                xS = xmax

            }
            if (yS < ymin) {
                yS = xmin

            }
            if (yS > ymax) {
                yS = ymax

            }



        xPosition = xS
        yPosition = yS

    }
    override fun onAccuracyChanged(event: Sensor?, accuracy: Int) {
        return;    }

    override fun onDestroy() {
        super.onDestroy()
    }

}