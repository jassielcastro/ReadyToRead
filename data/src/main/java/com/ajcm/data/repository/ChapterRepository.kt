package com.ajcm.data.repository

import com.ajcm.data.datasource.ILocalChapterDataSource
import com.ajcm.data.datasource.IRemoteChapterDataSource
import com.ajcm.domain.entity.Chapter
import com.ajcm.domain.repository.IChapterRepository
import javax.inject.Inject

class ChapterRepository @Inject constructor(
    private val localDataSource: ILocalChapterDataSource,
    private val remoteDataSource: IRemoteChapterDataSource
) : IChapterRepository {

    override suspend fun getChapters(bibleId: String, bookId: String): List<Chapter> {
        if (localDataSource.getChapters(bibleId, bookId).isEmpty()) {
            val chapters = remoteDataSource.getChapters(bibleId, bookId)
            localDataSource.saveChapters(chapters)
        }

        return localDataSource.getChapters(bibleId, bookId)
    }

    override suspend fun getChapter(bibleId: String, chapterId: String): Chapter {
        val chapter = try {
            localDataSource.getChapter(bibleId, chapterId)
        } catch (e: Exception) {
            remoteDataSource.getChapter(bibleId, chapterId)
        }

        if (chapter.content.isEmpty()) {
            localDataSource.saveChapter(remoteDataSource.getChapter(bibleId, chapterId))
        }

        return localDataSource.getChapter(bibleId, chapterId)
    }

}
