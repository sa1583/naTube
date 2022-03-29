package fastcampus.aop.part2.natube.service

import fastcampus.aop.part2.natube.dto.VideoDto
import retrofit2.Call
import retrofit2.http.GET

interface VideoService {
    @GET("/v3/6a1c6710-ea1e-4668-b499-0bf1f727932d")
    fun listVideos(): Call<VideoDto>
}