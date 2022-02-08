package com.ajcm.data.repository

import com.ajcm.data.datasource.ILocalChapterDataSource
import com.ajcm.data.datasource.IRemoteChapterDataSource
import com.ajcm.domain.entity.AudioChapter
import com.ajcm.domain.entity.Chapter
import com.ajcm.domain.repository.IChapterRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class ChapterRepository @Inject constructor(
    private val localDataSource: ILocalChapterDataSource,
    private val remoteDataSource: IRemoteChapterDataSource
): IChapterRepository {

    override suspend fun getChapters(bibleId: String, bookId: String): List<Chapter> {
        if (localDataSource.getChapters(bibleId, bookId).isEmpty()) {
            val chapters = remoteDataSource.getChapters(bibleId, bookId)
            localDataSource.saveChapters(chapters)
        }

        return localDataSource.getChapters(bibleId, bookId)
    }

    override suspend fun getChapter(bibleId: String, chapterId: String): Chapter {
        return suspendCoroutine { continuation ->
            CoroutineScope(Dispatchers.IO).launch {
                runCatching {
                    localDataSource.getChapter(bibleId, chapterId)
                }.onSuccess {
                    continuation.resume(it)
                }.onFailure {
                    val chapter = remoteDataSource.getChapter(bibleId, chapterId)
                    localDataSource.saveChapter(chapter)
                    continuation.resume(localDataSource.getChapter(bibleId, chapterId))
                }
            }
        }
    }

    override suspend fun getAudioChapter(bibleId: String, chapterId: String): AudioChapter {
        TODO("Not yet implemented")
    }

}
