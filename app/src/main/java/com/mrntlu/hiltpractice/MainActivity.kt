package com.mrntlu.hiltpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject
import javax.inject.Singleton

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var someClass: SomeClass

    @Inject
    lateinit var someOtherClass: SomeOtherClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println(someClass.testFunc())
        println(someOtherClass.testFunc())
    }
}

@AndroidEntryPoint
class TestFragment: Fragment(){

    @Inject
    lateinit var someClass: SomeClass

    @Inject
    lateinit var someOtherClass: SomeOtherClass

    @Inject
    lateinit var someAnotherClass: SomeAnotherClass
}

@Singleton
class SomeClass
@Inject
constructor(){
    fun testFunc() = "This is a test"
}

@ActivityScoped
class SomeOtherClass
@Inject
constructor(){
    fun testFunc() = "This is a test for other"
}

@FragmentScoped
class SomeAnotherClass
@Inject
constructor(){
    fun testFunc() = "This is a test for another"
}