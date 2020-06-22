package com.example.final2

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    var now = 1
    var over = false
    var total = 0
    var winO = 0
    var winX = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun gameDoneDetect(){
        var b1 = button1.text
        var b2 = button2.text
        var b3 = button3.text
        var b4 = button4.text
        var b5 = button5.text
        var b6 = button6.text
        var b7 = button7.text
        var b8 = button8.text
        var b9 = button9.text
        var done = false
        var winner = "平手"
        if((b1==b5 && b5==b9) || (b2==b5 && b5==b8) || (b3==b5 && b5==b7) || (b4==b5 && b5==b6)){
            if(b5 == "O"){
                winO += 1
                winner = "恭喜「O」獲勝"
                done = true
            }
            else if(b5 == "X"){
                winX += 1
                winner = "恭喜「X」獲勝"
                done = true
            }

        }
        if((b1==b2 && b1==b3) || (b1==b4 && b1==b7)){
            if(b1 == "O"){
                winO += 1
                winner = "恭喜「O」獲勝"
                done = true
            }
            else if(b1 == "X"){
                winX += 1
                winner = "恭喜「X」獲勝"
                done = true
            }
        }
        if((b9==b8 && b9==b7) || (b9==b6 && b9==b3)){
            if(b9 == "O"){
                winO += 1
                winner = "恭喜「O」獲勝"
                done = true
            }
            else if(b9 == "X"){
                winX += 1
                winner = "恭喜「X」獲勝"
                done = true
            }
        }
        if(b1!="" && b2!="" && b3!="" && b4!="" && b5!="" && b6!="" && b7!="" && b8!="" && b9!=""){
            done = true
        }
        if(done){
            total += 1
            over = true
            AlertDialog.Builder(this)
                .setMessage(winner + "\n目前「O」共獲勝" + winO + "場，「X」共獲勝" + winX + "場，\n平手" + (total - winX - winO) + "場。")
                .setPositiveButton("確定", null)
                .show()
        }
    }
    fun playAgain(view: View){
        button1.text = ""
        button2.text = ""
        button3.text = ""
        button4.text = ""
        button5.text = ""
        button6.text = ""
        button7.text = ""
        button8.text = ""
        button9.text = ""
        over = false
        now = 1
        val context = applicationContext
        val text = "遊戲已重置^_^\n請「O」先下"
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(context, text, duration)
        toast.show()
    }

    fun clickButton1(view: View){
        if(button1.text != "" || over) return
        if(now == 1) button1.text = "O"
        else button1.text = "X"
        now = now xor 1
        gameDoneDetect()
    }
    fun clickButton2(view: View){
        if(button2.text != "" || over) return
        if(now == 1) button2.text = "O"
        else button2.text = "X"
        now = now xor 1
        gameDoneDetect()
    }
    fun clickButton3(view: View){
        if(button3.text != "" || over) return
        if(now == 1) button3.text = "O"
        else button3.text = "X"
        now = now xor 1
        gameDoneDetect()
    }
    fun clickButton4(view: View){
        if(button4.text != "" || over) return
        if(now == 1) button4.text = "O"
        else button4.text = "X"
        now = now xor 1
        gameDoneDetect()
    }
    fun clickButton5(view: View){
        if(button5.text != "" || over) return
        if(now == 1) button5.text = "O"
        else button5.text = "X"
        now = now xor 1
        gameDoneDetect()
    }
    fun clickButton6(view: View){
        if(button6.text != "" || over) return
        if(now == 1) button6.text = "O"
        else button6.text = "X"
        now = now xor 1
        gameDoneDetect()
    }
    fun clickButton7(view: View){
        if(button7.text != "" || over) return
        if(now == 1) button7.text = "O"
        else button7.text = "X"
        now = now xor 1
        gameDoneDetect()
    }
    fun clickButton8(view: View){
        if(button8.text != "" || over) return
        if(now == 1) button8.text = "O"
        else button8.text = "X"
        now = now xor 1
        gameDoneDetect()
    }
    fun clickButton9(view: View){
        if(button9.text != "" || over) return
        if(now == 1) button9.text = "O"
        else button9.text = "X"
        now = now xor 1
        gameDoneDetect()
    }
}