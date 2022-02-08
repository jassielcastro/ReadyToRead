package com.ajcm.data.datasource

import com.ajcm.domain.entity.Section

interface ILocalSectionDataSource {
    suspend fun saveSections(sections: List<Section>)
    suspend fun saveSection(section: Section)

    suspend fun getSectionsFromBook(bibleId: String, bookId: String): List<Section>
    suspend fun getSectionsFromChapter(bibleId: String, chapterId: String): List<Section>
    suspend fun getSection(bibleId: String, sectionId: String): Section
}
