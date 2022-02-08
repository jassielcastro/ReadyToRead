package com.ajcm.domain.repository

import com.ajcm.domain.entity.Section

interface ISectionRepository {
    suspend fun getSectionsFromBook(bibleId: String, bookId: String): List<Section>
    suspend fun getSectionsFromChapter(bibleId: String, chapterId: String): List<Section>
    suspend fun getSection(bibleId: String, sectionId: String): Section
}
