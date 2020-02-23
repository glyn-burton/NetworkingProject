package com.example.networkingproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.networkingproject.datasource.remote.httpurl.HttpUrlHelper
import com.example.networkingproject.datasource.remote.okhttp.OkHttpHelper
import com.example.networkingproject.model.Repository.Owner
import com.example.networkingproject.model.Repository.RepositoryResponse
import com.example.networkingproject.model.User.UserResponse
import com.example.networkingproject.view.GitAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.git_item.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gitHubUserURL = "https://api.github.com/users/glyn-burton/repos"
        val gitHubProfileURL = "https://api.github.com/search/users?q=glyn-burton"
        val httpUrlHelper = HttpUrlHelper()
        var jsonString = ""
        var jsonProfileString = ""

        Thread(Runnable
        {
            jsonString = httpUrlHelper.getResponse(gitHubUserURL)
            jsonProfileString = httpUrlHelper.getResponse(gitHubProfileURL)

            if(jsonProfileString.isNotBlank())
            {
                runOnUiThread( {
                    val userResult = Gson().fromJson<UserResponse>(jsonProfileString, UserResponse::class.java)
                    tvUsername.text = "Profile Name: " + userResult.items[0].login
                    tvId.text = "ID: " + userResult.items[0].id.toString()
                    tvRepoUrl.text = "Repo Url: " + userResult.items[0].repos_url
                    tvAvatarUrl.text = "GitHub Type: " + userResult.items[0].login
                })
            }
            if(jsonString.isNotBlank())
            {
                val userArray: ArrayList<Owner> = Gson().fromJson(
                    jsonString,
                    object : TypeToken<List<Owner?>?>() {}.type
                )
                val yourArray: ArrayList<RepositoryResponse> = Gson().fromJson(
                    jsonString,
                    object : TypeToken<List<RepositoryResponse?>?>() {}.type
                )
                runOnUiThread( {
                    rvGitlist.layoutManager = LinearLayoutManager(this)
                    rvGitlist.adapter = GitAdapter(yourArray)
                })
            }

        }).start()
    }

    }






