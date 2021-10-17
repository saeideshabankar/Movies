package com.example.movies.utils

import android.app.Application
import com.yandex.metrica.YandexMetrica
import com.yandex.metrica.YandexMetricaConfig
import io.github.inflationx.calligraphy3.BuildConfig
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump
import nouri.`in`.goodprefslib.GoodPrefs


class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        GoodPrefs.init(applicationContext)
        ViewPump.init(
            ViewPump.builder()
                .addInterceptor(
                    CalligraphyInterceptor(
                        CalligraphyConfig.Builder()
                            .setDefaultFontPath("fonts/vazir.ttf")
                            .build()
                    )
                )
                .build()
        )

        val config = YandexMetricaConfig.newConfigBuilder(CRASH_API_KEY)
            .withAppVersion(BuildConfig.VERSION_NAME)
            .withCrashReporting(true)
            .withNativeCrashReporting(true)
            .build()
        YandexMetrica.activate(applicationContext, config)
        YandexMetrica.enableActivityAutoTracking(this)

    }
}