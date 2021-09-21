package com.ajcm.data.datasource

import com.ajcm.domain.AudioChapter
import com.ajcm.domain.Chapter
import com.ajcm.domain.ChapterSummary

interface ILocalChapterDataSource {
    suspend fun saveChapters(chapters: List<ChapterSummary>)
    suspend fun saveChapter(chapter: Chapter)
    suspend fun saveAudioChapter(audioChapter: AudioChapter)

    suspend fun getChapters(bibleId: String, bookId: String): List<ChapterSummary>
    suspend fun getChapter(bibleId: String, chapterId: String): Chapter
    suspend fun getAudioChapter(bibleId: String, chapterId: String): AudioChapter
}
