package com.ajcm.domain.repository

import com.ajcm.domain.entity.AudioChapter
import com.ajcm.domain.entity.Chapter
import com.ajcm.domain.entity.ChapterSummary

interface IChapterRepository {
    suspend fun getChapters(bibleId: String, bookId: String): List<ChapterSummary>
    suspend fun getChapter(bibleId: String, chapterId: String): Chapter
    suspend fun getAudioChapter(bibleId: String, chapterId: String): AudioChapter
}
