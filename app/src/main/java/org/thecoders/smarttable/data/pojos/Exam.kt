package org.thecoders.smarttable.data.pojos

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import org.thecoders.smarttable.data.DateConverter
import java.util.*

/**
 * Created by Pascal on 27.03.2016.
 */

@Entity(tableName = "exam")
@TypeConverters(DateConverter::class)
class Exam(
        @PrimaryKey(autoGenerate = true) val id: Long,
        val subject: String = "NULL",
        val topic: String = "NULL",
        val date: Date = Date(),
        val grade: String = "NULL"
) {

    //Holds String-ID's for all fields
    companion object {
        const val ID = "exam_id"
        const val SUBJECT = "exam_subject"
        const val TOPIC = "exam_topic"
        const val DATE = "exam_date"
        const val GRADE = "exam_grade"
    }
}
