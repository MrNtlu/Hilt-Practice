package com.mrntlu.hiltpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //Field Injection
    @Inject
    lateinit var someClass: SomeClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println(someClass.testFunc())
        println(someClass.otherTestFunc())
    }
}

class SomeClass
@Inject
constructor( //Constructor Injection
        private val someOtherClass: SomeOtherClass
){
    fun testFunc() = "This is a test"

    fun otherTestFunc() = someOtherClass.otherTestFunc()
}

class SomeOtherClass
@Inject
constructor(){
    fun otherTestFunc() = "This is test but the second one."
}