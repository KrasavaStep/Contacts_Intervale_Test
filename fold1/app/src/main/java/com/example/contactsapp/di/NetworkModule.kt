package com.example.contactsapp.di

import com.example.contactsapp.BuildConfig
import com.example.contactsapp.data.contacts_api.ContactsAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single<HttpLoggingInterceptor> {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    single<OkHttpClient> {
        val httpClient = OkHttpClient.Builder()

        if (BuildConfig.DEBUG){
            httpClient.addInterceptor((get<HttpLoggingInterceptor>())).build()
        }
        else{
            httpClient.build()
        }
    }

    single<Retrofit>(named("ContactsRetrofit")) {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ContactsAPI.CONTACTS_API_URL)
            .client(get<OkHttpClient>())
            .build()
    }

    single<ContactsAPI> {
        (get<Retrofit>(named("ContactsRetrofit")).create(ContactsAPI::class.java))
    }


}