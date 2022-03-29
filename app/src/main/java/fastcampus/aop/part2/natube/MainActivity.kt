package fastcampus.aop.part2.natube

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import fastcampus.aop.part2.natube.adapter.VideoAdapter
import fastcampus.aop.part2.natube.databinding.ActivityMainBinding
import fastcampus.aop.part2.natube.dto.VideoDto
import fastcampus.aop.part2.natube.service.VideoService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var videoAdapter: VideoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, PlayerFragment())
            .commit()
        videoAdapter = VideoAdapter()

        binding.mainRecyclerView.apply {
            adapter = videoAdapter
            layoutManager = LinearLayoutManager(context)
        }

        getVideoList()
    }

    private fun getVideoList() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://run.mocky.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(VideoService::class.java).also {
            it.listVideos()
                .enqueue(object: Callback<VideoDto> {
                    override fun onResponse(call: Call<VideoDto>, response: Response<VideoDto>) {
                        if (response.isSuccessful.not()) {
                            Log.d("MainActivity", "response fail")
                            return
                        }
                        response.body()?.let { videoDto ->
                            videoAdapter.submitList(videoDto.videos)
                        }

                    }

                    override fun onFailure(call: Call<VideoDto>, t: Throwable) {
                        Toast.makeText(this@MainActivity, "비디오 목록 받아오기 실패!", Toast.LENGTH_SHORT).show()
                    }
                })
        }
    }
}