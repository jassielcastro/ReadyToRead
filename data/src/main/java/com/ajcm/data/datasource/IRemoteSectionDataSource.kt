package com.ajcm.data.datasource

import com.ajcm.domain.entity.Section

interface IRemoteSectionDataSource {
    suspend fun getSectionsFromBook(bibleId: String, bookId: String): List<Section>
    suspend fun getSectionsFromChapter(bibleId: String, chapterId: String): List<Section>
    suspend fun getSection(bibleId: String, sectionId: String): Section
}
