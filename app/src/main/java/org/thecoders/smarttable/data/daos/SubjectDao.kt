package org.thecoders.smarttable.data.daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import org.thecoders.smarttable.data.pojos.Subject

/**
 * Created by frenz on 24.06.2017.
 */

@Dao
interface SubjectDao {

    @Query("select * from subjects")
    fun loadSubjects(): LiveData<List<Subject>>

    @Query("select * from subjects where id = :id")
    fun loadSubjectById(id: Long): Subject

    @Query("select name from subjects")
    fun loadSubjectNames(): LiveData<List<String>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(subject: Subject)

    @Insert
    fun insertMany(subjects: List<Subject>)

    @Delete
    fun delete(subject: Subject)

}