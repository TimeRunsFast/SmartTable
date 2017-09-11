package org.thecoders.smarttable.ui

import android.app.Dialog
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.widget.ArrayAdapter
import android.widget.Toast
import org.thecoders.smarttable.R
import org.thecoders.smarttable.data.*


/**
 * Created by Pascal on 03.09.2016.
 * This is the dialog interface which is shown to the user when he attempts to delete an entry
 * from the database. It makes sure the user did not toggle the action accidentally
 */
//Empty Constructor as required by Guidelines
class Dialog_ConfirmDelete : DialogFragment() {

    var objectToDelete: Any? = null
    var objectAdapter: ArrayAdapter<*>? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val db = AppDatabase.getPersistenceDatabase(context)

        when (objectToDelete) {
            is Homework -> {
                val homeworkToDelete = objectToDelete as Homework
                val homeworkAdapter = objectAdapter as Adapter_Homework
                builder.setMessage(R.string.title_confirm_delete_dialog)
                        .setPositiveButton(R.string.action_delete) { dialog, _ ->
                            AsyncTask.execute {
                                db.homeworkModel().delete(homeworkToDelete)     // Delete database entry
                            }
                            homeworkAdapter.remove(homeworkToDelete)            // Update Adapter
                            homeworkAdapter.notifyDataSetChanged()              // Update the ListView
                            dialog.dismiss()                                    // Dismiss the Dialog
                        }
                        .setNegativeButton(R.string.action_cancel) { dialog, _ ->
                            dialog.dismiss()                                    // dismiss dialog
                        }

            }
            is Exam -> {
                val examToDelete = objectToDelete as Exam
                val examAdapter = objectAdapter as Adapter_Exam
                builder.setMessage(R.string.title_confirm_delete_dialog)
                        .setPositiveButton(R.string.action_delete) { dialog, _ ->
                            AsyncTask.execute {
                                db.examModel().delete(examToDelete)
                            }
                            examAdapter.remove(examToDelete)
                            examAdapter.notifyDataSetChanged()
                            dialog.dismiss()
                        }
                        .setNegativeButton(R.string.action_cancel) { dialog, _ ->
                            dialog.dismiss()
                        }
            }
            is Subject -> {
                val subjectToDelete = objectToDelete as Subject
                val subjectAdapter = objectAdapter as Adapter_Subject
                builder.setMessage(R.string.title_confirm_delete_dialog)
                        .setPositiveButton(R.string.action_delete) { dialog, _ ->
                            AsyncTask.execute {
                                db.subjectModel().delete(subjectToDelete)
                            }
                            subjectAdapter.remove(subjectToDelete)
                            subjectAdapter.notifyDataSetChanged()
                            dialog.dismiss()
                        }
                        .setNegativeButton(R.string.action_cancel) { dialog, _ ->
                            dialog.dismiss()
                        }
            }
            is Lesson -> {
                val lessonToDelete = objectToDelete as Lesson
                val lessonAdapter = objectAdapter as Adapter_Lesson
                builder.setMessage(R.string.title_confirm_delete_dialog)
                        .setPositiveButton(R.string.action_delete) { dialog, _ ->
                            AsyncTask.execute {
                                if(lessonToDelete is LessonMon)
                                    db.timetableModel().updateMondayLesson(lessonToDelete.id, "NULL", "NULL")
                                else Toast.makeText(context, "Something went wrong!", Toast.LENGTH_LONG).show()
                                //TODO: Extend this until every day is covered!
                            }
                            lessonAdapter.remove(lessonToDelete)
                            lessonAdapter.notifyDataSetChanged()
                            dialog.dismiss()
                        }
                        .setNegativeButton(R.string.action_cancel) { dialog, _ ->
                            dialog.dismiss()
                        }
            }
        }
        return builder.create()
    }
}
