package com.crys.shoppinglist20.ui

import androidx.lifecycle.ViewModel
import com.crys.shoppinglist20.api.PhotoResponse
import com.crys.shoppinglist20.data.db.entities.ShoppingItem
import com.crys.shoppinglist20.data.repositories.ShoppingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.internal.wait
import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class ShoppingViewModel @Inject constructor(
    private val repository: ShoppingRepository
) : ViewModel() {

    fun upsert(item: ShoppingItem) = GlobalScope.launch {
            repository.upsert(item)
        }

    fun delete(item: ShoppingItem) = GlobalScope.launch {
        repository.delete(item)
    }

    fun getAllShoppingItems() = repository.getAllShoppingItems()

    fun provideUrl(tag: String) : String {
        var url = ""
        val job = GlobalScope.launch {
            val response = try {
                repository.providePhoto(tag)
            } catch(e: IOException) {
                Timber.d(e)
                return@launch
            } catch (e: HttpException) {
                Timber.d(e)
                return@launch
            }

            if(response.isSuccessful && response.body() != null) {
                val body = response.body()!!
                url = "https://live.staticflickr.com/${body.photos.photo[0].server}/${body.photos.photo[0].id}_${body.photos.photo[0].secret}_m.jpg"
                Timber.d("Setting the url into:$url")
            } else {
                Timber.d("Error")
            }
        }
        runBlocking {
            job.join()
        }
        return url
    }
}