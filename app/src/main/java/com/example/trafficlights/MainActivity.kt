package com.example.trafficlights

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import java.util.*


class MainActivity : Activity() {
    //всегда нулл если присваивание к переменной будет позже

    //обозначаем переменные вью активти
    var imageViewSemafor: ImageView? = null

    //массив присваиваем для присваивания картинку номера
    var imageArray: IntArray =
        intArrayOf(R.drawable.semafor_red, R.drawable.semafor_yellow, R.drawable.semafor_green)

    //переменная для счетчика
    var counter: Int = 0

    //создаём таймер и переменную для определеия запуска
    var timer: Timer? = null
    var isRun: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //связываем
        imageViewSemafor = findViewById(R.id.imageViewSemafor)


    }


    //клик кнопки
    fun onStartStop(view: View) {

        //даём возможность устанавливаеть вью как кнопку
        view as ImageButton

        //делаем проверку переменной на запуск таймера
        if (!isRun) {

            //апускаем метод для таймера
            timerStartStop()

            //устанавливаем другую картинку по нажатию
            view.setImageResource(R.drawable.button_stop)

            //устанавливаем что таймер запущен
            isRun = true


        } else {
            //останавливаем таймер
            timer?.cancel()

            //устанавливаем каждый раз по нажатию на стоп начальное значение красное цвета
            counter = 0

            //устанавливаем другую картинку по нажатию
            view.setImageResource(R.drawable.button_start)

            //устанавливаем начальное значение картинки
            imageViewSemafor?.setImageResource(R.drawable.semafor_grey)

            //устанавливаем что таймер закончился
            isRun = false
        }
    }


    //метод для таймера
    fun timerStartStop() {

        //создаём таймер
        timer = Timer()

        //время запуска таймера
        timer?.schedule(object : TimerTask() {

            //метод который запускается каждую секунду
            override fun run() {

                //делаем запуск в основном потоке иначе элементы интерфеса не будут менять ов второстепенном
                runOnUiThread{

                    //устанавливаем картинку из массива определяя элемент в зависимости от значения счетчика коунтер
                    imageViewSemafor?.setImageResource(imageArray[counter])

                    //увеличваем переменную на один
                    counter++

                    //проверяем что бы не переходило больше значений и картинки могли меняться в правильном порядке
                    if (counter == 3) counter = 0
                }
            }
            //задержка и период
        }, 0,1000)
    }

}


