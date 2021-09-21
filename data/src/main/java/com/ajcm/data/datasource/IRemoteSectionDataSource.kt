package com.ajcm.data.datasource

import com.ajcm.domain.Section
import com.ajcm.domain.SectionSummary

interface IRemoteSectionDataSource {
    suspend fun getSectionsFromBook(bibleId: String, bookId: String): List<SectionSummary>
    suspend fun getSectionsFromChapter(bibleId: String, chapterId: String): List<SectionSummary>
    suspend fun getSection(bibleId: String, sectionId: String): Section
}
