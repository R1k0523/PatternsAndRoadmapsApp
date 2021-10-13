package ru.boringowl.parapp.presentation.viewmodel.news

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.boringowl.parapp.domain.model.news.Post
import ru.boringowl.parapp.presentation.repository.Repository
import ru.boringowl.parapp.presentation.repository.network.itnews.paged.NewsPagingSource

class NewsViewModel : ViewModel() {
    val posts: Flow<PagingData<Post>> = getNewsListStream()
        .map { pagingData -> pagingData.map { it } }


    private fun getNewsListStream(): Flow<PagingData<Post>> {
        return Pager(PagingConfig(20)) {
            NewsPagingSource(Repository.newsRepository)
        }.flow
    }
}