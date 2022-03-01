package com.example.bookreader.data_class

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class BookData(@PrimaryKey(autoGenerate = false)var id: Long, var name: String, @SerializedName("writer") var author: String = "测试作者",
                    @SerializedName("picture_path") var coverUrl: String, var intro: String, @SerializedName("marks") val rate: Float, @SerializedName("collected") var isCollected: Boolean,
                    var content: String = "null", @SerializedName("last_read_time") var lastReadTime: String = ""): Serializable