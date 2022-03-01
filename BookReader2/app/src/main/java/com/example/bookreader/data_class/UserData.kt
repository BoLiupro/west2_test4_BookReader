package com.example.bookreader.data_class

import java.io.Serializable

data class UserData(val id: Long, var code: Int = 9999): Serializable{

    override fun toString(): String {
        return "id: $id, code: $code"
    }

}
