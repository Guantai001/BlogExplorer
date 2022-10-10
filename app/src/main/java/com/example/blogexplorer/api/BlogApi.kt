package com.example.blogexplorer.api

import com.example.blogexplorer.models.Post
import com.example.blogexplorer.models.User
import retrofit2.Call
import retrofit2.http.*

interface BlogApi {
    @GET("posts")
    suspend fun getPosts(@Query("_page")page:Int = 1,@Query("_limit")limit: Int = 10): List<Post>

    @Headers("platform:Android")
    @GET("posts/{id}")
    suspend fun getPost(@Path("id") postId: Int): Post

    @GET("users/{id}")
    suspend fun getUser(@Path("id") userId: Int): User


    // request body contains the complete new version
    @PUT("posts/{id}")
    suspend fun updatePost(@Path("id") postId: Int, @Body post: Post): Post

    //request body only needs to contain the specific changes to the resource
    @PATCH("posts/{id}")
    suspend fun patchPost(@Path("id") postId: Int, @Body params: Map<String,String>): Post

    @DELETE("posts/{id}")
    suspend fun deletePost(@Header("Auth-Token")auth:String,@Path("id") postId: Int)

    @POST("posts/{id}")
    suspend fun createPost(@Body post : Post): Post

    @GET("posts/{id}")
    fun getPostViaCallback(@Path("id") postId: Int): Call<Post>

    @GET("users/{id}")
    fun getUserViaCallback(@Path("id") userId: Int): Call<User>
}