package com.ajcm.data.datasource

import com.ajcm.domain.entity.AudioChapter
import com.ajcm.domain.entity.Chapter

interface ILocalChapterDataSource {
    suspend fun saveChapters(chapters: List<Chapter>)
    suspend fun saveChapter(chapter: Chapter)
    suspend fun saveAudioChapter(audioChapter: AudioChapter)

    suspend fun getChapters(bibleId: String, bookId: String): List<Chapter>
    suspend fun getChapter(bibleId: String, chapterId: String): Chapter
    suspend fun getAudioChapter(bibleId: String, chapterId: String): AudioChapter
}
