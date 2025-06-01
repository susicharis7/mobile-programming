package com.example.studyflow.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.studyflow.model.Exam
import com.example.studyflow.model.ExamWithSubject

@Dao
interface ExamDao : BaseDao<Exam> {
    @Query("SELECT * FROM exams WHERE userId = :userId AND isActive = 1")
    suspend fun getExamsByUserId(userId: Long): List<Exam>

    // get by date

    // exam card also contains subject name so we might need some table joins
    @Query("""
        SELECT exams.name examName, exams.examDate, subjects.name subjectName, subjects.color colorStripe 
        FROM exams
        INNER JOIN subjects ON exams.subjectId=subjects.id
        WHERE exams.userId = :userId AND exams.isActive = 1
        ORDER BY exams.examDate
    """)
    suspend fun getExamsWithSubjectByUserId(userId: Long): List<ExamWithSubject>
}