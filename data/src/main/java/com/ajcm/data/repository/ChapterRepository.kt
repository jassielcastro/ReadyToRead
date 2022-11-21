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
        var chapters = localDataSource.getChapters(bibleId, bookId)
        if (chapters.isEmpty()) {
            localDataSource.saveChapters(remoteDataSource.getChapters(bibleId, bookId))
            chapters = localDataSource.getChapters(bibleId, bookId)
        }

        return chapters
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
