package ru.boringowl.parapp.presentation.repository.network.itnews.paged

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.boringowl.parapp.domain.model.news.Post
import ru.boringowl.parapp.presentation.repository.NewsRepository

class NewsPagingSource(
    private val repository: NewsRepository
) : PagingSource<Int, Post>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {

        return try {
            val nextPage = params.key ?: 1
            val movieListResponse = repository.getPopularNews(nextPage)

            LoadResult.Page(
                data = movieListResponse.articles,
                prevKey = if (nextPage == 1) null else nextPage -  1 ,
                nextKey = if (nextPage < movieListResponse.totalResults)
                    nextPage.plus(1) else null
            )
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Post>): Int? {
        return state.anchorPosition
    }
}
