package com.ajcm.chapter.datasource

import com.ajcm.data.datasource.IRemoteChapterDataSource
import com.ajcm.domain.entity.Chapter
import com.ajcm.chapter.service.ChapterService
import javax.inject.Inject

class RemoteChapterDataSource @Inject constructor(
    private val chapterService: ChapterService
) : IRemoteChapterDataSource {

    override suspend fun getChapters(bibleId: String, bookId: String): List<Chapter> {
        return chapterService.getChapters(bibleId, bookId).data
    }

    override suspend fun getChapter(bibleId: String, chapterId: String): Chapter {
        return chapterService.getChapter(bibleId, chapterId).data
    }

}
