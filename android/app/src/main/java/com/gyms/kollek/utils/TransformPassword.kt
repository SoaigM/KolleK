package com.gyms.kollek.utils

class TransformPassword {

    companion object {
        fun transform(text : String) : String{
            return "*".repeat(text.length)
        }
    }
}