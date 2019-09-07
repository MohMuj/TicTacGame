package com.example.tictacgame

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    //to reset the game
    var clickable = false
    protected fun resetgame(view:View){
        val reset = view as Button
        if (clickable){
            reset.isClickable=true
            finish()
            startActivity(this.intent)
        }
    }
    var stop = true
    protected fun buclick (view:View){
        val selectedbu = view as Button
        var cellid = 0
        when(selectedbu.id){
            R.id.bu1 -> cellid=1
            R.id.bu2 -> cellid=2
            R.id.bu3 -> cellid=3
            R.id.bu4 -> cellid=4
            R.id.bu5 -> cellid=5
            R.id.bu6 -> cellid=6
            R.id.bu7 -> cellid=7
            R.id.bu8 -> cellid=8
            R.id.bu9 -> cellid=9

        }
       // Toast.makeText(this,"ID : $cellid",Toast.LENGTH_SHORT).show()
        var emptycell = ArrayList<Int>()

        for (it in 1..9){ if(!(p1.contains(it) || p2.contains(it))){ emptycell.add(it) } }
        if (emptycell.size!=0 && stop) {

            Playgame(cellid, selectedbu)
            checkwinner()

        }
    }
    var p1 = ArrayList<Int>()
    var p2 = ArrayList<Int>()
    var r = Random().nextInt(2)
    var activePlayer =1

    fun Playgame (cellid:Int , selectedbu :Button){


        if (activePlayer==1){
            selectedbu.setText("X")
            selectedbu.setBackgroundResource(R.color.material_blue400)
            p1.add(cellid)
            activePlayer = 2
            var emptycell = ArrayList<Int>()
            for (it in 1..9){ if(!(p1.contains(it) || p2.contains(it))){ emptycell.add(it) } }
            if (emptycell.size!=0 && stop) {
                bot()
            }else{
                Toast.makeText(this,"GameOver",Toast.LENGTH_SHORT).show()
                stop = false
                clickable=true
            }


        }else if(activePlayer==2 && stop){

            selectedbu.setText("O")
            selectedbu.setBackgroundResource(R.color.material_orange400)
            p2.add(cellid)
            activePlayer = 1
        }
        selectedbu.isEnabled=false
    }
    fun checkwinner(){
        var winner = 0

        // ROW
        // ROW 1
        if (p1.contains(1) && p1.contains(2) && p1.contains(3)){ winner = 1 }
        if (p2.contains(1) && p2.contains(2) && p2.contains(3)){  winner = 2 }
        // ROW 2
        if (p1.contains(4) && p1.contains(5) && p1.contains(6)){ winner = 1 }
        if (p2.contains(4) && p2.contains(5) && p2.contains(6)){  winner = 2 }
        // ROW 3
        if (p1.contains(7) && p1.contains(8) && p1.contains(9)){ winner = 1 }
        if (p2.contains(7) && p2.contains(8) && p2.contains(9)){  winner = 2 }

        // Column
        // Column 1
        if (p1.contains(1) && p1.contains(4) && p1.contains(7)){ winner = 1 }
        if (p2.contains(1) && p2.contains(4) && p2.contains(7)){  winner = 2 }
        // Column 2
        if (p1.contains(2) && p1.contains(5) && p1.contains(8)){ winner = 1 }
        if (p2.contains(2) && p2.contains(5) && p2.contains(8)){  winner = 2 }
        // Column 3
        if (p1.contains(3) && p1.contains(6) && p1.contains(9)){ winner = 1 }
        if (p2.contains(3) && p2.contains(6) && p2.contains(9)){  winner = 2 }


        // Column and row X
        // case 1
        if (p1.contains(1) && p1.contains(5) && p1.contains(9)){ winner = 1 }
        if (p2.contains(1) && p2.contains(5) && p2.contains(9)){  winner = 2 }
        // case 2
        if (p1.contains(3) && p1.contains(5) && p1.contains(7)){ winner = 1 }
        if (p2.contains(3) && p2.contains(5) && p2.contains(7)){  winner = 2 }


        if (winner==1){
            Toast.makeText(this,"You are the Winner",Toast.LENGTH_LONG).show()
            stop = false
            clickable=true
        }else if (winner==2){
            Toast.makeText(this,"GameOver",Toast.LENGTH_LONG).show()
            stop = false
            clickable=true
        }


    }

    fun bot(){
       var emptycell = ArrayList<Int>()

        // to add all emptycells
        for (it in 1..9){ if(!(p1.contains(it) || p2.contains(it))){ emptycell.add(it) } }
        var r=Random()
        val i = emptycell.size
        var cellid =0
        for (id in 0..i ){
            //AI to not let another player to win
            if (p1.contains(2) && p1.contains(3) || p1.contains(5) && p1.contains(9) || p1.contains(4) && p1.contains(7)){ if (emptycell.contains(1)){ cellid=1 } }
            if (p1.contains(1) && p1.contains(3) || p1.contains(5) && p1.contains(8) ){ if (emptycell.contains(2)){ cellid=2 } }
            if (p1.contains(1) && p1.contains(2) || p1.contains(5) && p1.contains(7) || p1.contains(6) && p1.contains(9)){ if (emptycell.contains(3)){ cellid=3 } }
            if (p1.contains(1) && p1.contains(7) || p1.contains(5) && p1.contains(6) ){ if (emptycell.contains(4)){ cellid=4 } }
            if (p1.contains(1) && p1.contains(9) || p1.contains(4) && p1.contains(6) || p1.contains(2) || p1.contains(8) || p1.contains(7) || p1.contains(3)){ if (emptycell.contains(5)){ cellid=5 } }
            if (p1.contains(4) && p1.contains(5) || p1.contains(3) && p1.contains(9) ){ if (emptycell.contains(6)){ cellid=6 } }
            if (p1.contains(1) && p1.contains(4) || p1.contains(5) && p1.contains(3) || p1.contains(8) && p1.contains(9)){ if (emptycell.contains(7)){ cellid=7 } }
            if (p1.contains(7) && p1.contains(9) || p1.contains(2) && p1.contains(5) ){ if (emptycell.contains(8)){ cellid=8 } }
            if (p1.contains(1) && p1.contains(5) || p1.contains(3) && p1.contains(6) || p1.contains(7) && p1.contains(8)){ if (emptycell.contains(9)){ cellid=9 } }

            // AI to win the game
            if (p2.contains(2) && p2.contains(3) || p2.contains(5) && p2.contains(9) || p2.contains(4) && p2.contains(7)){ if (emptycell.contains(1)){ cellid=1 } }
            if (p2.contains(1) && p2.contains(3) || p2.contains(5) && p2.contains(8) ){ if (emptycell.contains(2)){ cellid=2 } }
            if (p2.contains(1) && p2.contains(2) || p2.contains(5) && p2.contains(7) || p2.contains(6) && p2.contains(9)){ if (emptycell.contains(3)){ cellid=3 } }
            if (p2.contains(1) && p2.contains(7) || p2.contains(5) && p2.contains(6) ){ if (emptycell.contains(4)){ cellid=4 } }
            if (p2.contains(1) && p2.contains(9) || p2.contains(4) && p2.contains(6) || p2.contains(2) || p2.contains(8) || p2.contains(7) || p2.contains(3)){ if (emptycell.contains(5)){ cellid=5 } }
            if (p2.contains(4) && p2.contains(5) || p2.contains(3) && p2.contains(9) ){ if (emptycell.contains(6)){ cellid=6 } }
            if (p2.contains(1) && p2.contains(4) || p2.contains(5) && p2.contains(3) || p2.contains(8) && p2.contains(9)){ if (emptycell.contains(7)){ cellid=7 } }
            if (p2.contains(7) && p2.contains(9) || p2.contains(2) && p2.contains(5) ){ if (emptycell.contains(8)){ cellid=8 } }
            if (p2.contains(1) && p2.contains(5) || p2.contains(3) && p2.contains(6) || p2.contains(7) && p2.contains(8)){ if (emptycell.contains(9)){ cellid=9 } }

        }
        if(cellid==0) {
            val randindex = r.nextInt(emptycell.size)
            cellid = emptycell[randindex]
        }
        var buselect : Button?
        when(cellid){
            1 -> buselect=bu1
            2 -> buselect=bu2
            3 -> buselect=bu3
            4 -> buselect=bu4
            5 -> buselect=bu5
            6 -> buselect=bu6
            7 -> buselect=bu7
            8 -> buselect=bu8
            9 -> buselect=bu9
            else -> { buselect=bu1}
        }
        // To check the winner before player two play
        checkwinner()
        Playgame(cellid,buselect)




    }
}
