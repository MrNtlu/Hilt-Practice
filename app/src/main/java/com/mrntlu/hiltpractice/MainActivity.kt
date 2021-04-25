package com.mrntlu.hiltpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var someClass: SomeClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println(someClass.testFunc())
    }
}

@AndroidEntryPoint
class TestFragment: Fragment(){

    @Inject
    lateinit var someClass: SomeClass

}

class SomeClass
@Inject
constructor(
        private val someInterfaceImpl: SomeInterface
){
    fun testFunc() = "This is a test ${someInterfaceImpl.getSomething()}"
}

class SomeDependency
@Inject
constructor(){
    fun testFunc() = "for dependency"
}

class SomeInterfaceImpl
@Inject
constructor(
        private val someDependency: SomeDependency
): SomeInterface{
    override fun getSomething() = "You got it ${someDependency.testFunc()}."
}

interface SomeInterface{
    fun getSomething(): String
}

//Provides way
@InstallIn(SingletonComponent::class)
@Module
class TestModuleAlt{

    @Singleton
    @Provides
    fun provideSomeDependency(): SomeDependency{
        return SomeDependency()
    }

    @Singleton
    @Provides
    fun provideSomeInterface(
            someDependency: SomeDependency
    ): SomeInterface{
        return SomeInterfaceImpl(someDependency)
    }
}


//Binds way
@InstallIn(SingletonComponent::class)
@Module
abstract class TestModule{
    @Singleton
    @Binds
    abstract fun bindSomeDependency(
            someImpl: SomeInterfaceImpl
    ): SomeInterface
}
