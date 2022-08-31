package net.coremotion.challenge1.domain.reposoitory


import android.util.Log.d
import net.coremotion.challenge1.data.remote.ApiService
import net.coremotion.challenge1.common.Resource
import net.coremotion.challenge1.domain.model.UserDetail
import net.coremotion.challenge1.domain.model.Users
import javax.inject.Inject
import kotlin.system.exitProcess

class UserRepository @Inject constructor(
    private val api: ApiService
) {
    suspend fun getUsers(page: Int): Resource<Users> {
        return try {
            Resource.loading(null)
            val response = api.getUsers(page)
            val result = response.body()
            if (response.isSuccessful && result != null) {
                Resource.success(result)
            } else {
                Resource.error(response.message())
            }
        } catch (e: Exception) {
            Resource.error(e.message.toString())
        }
    }

    suspend fun getUserDetail(id: Int): Resource<UserDetail> {
        return try {
            val response = api.getUserDetail(id)
            val result = response.body()
            if (response.isSuccessful && result != null) {
                d("check12", result.toString())
                Resource.success(result)
            } else {
                Resource.error(response.message())
            }
        } catch (e: Exception) {
            Resource.success(UserDetail(UserDetail.Data("","sdsf","afsa",5,"asfaf")))
        }
    }
}