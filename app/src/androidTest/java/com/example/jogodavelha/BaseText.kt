package com.example.jogodavelha

import android.app.Activity
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.jogodavelha.ui.game.GameActivity
import org.junit.Rule

open class BaseText(val activity: Class<Activity>) {
    @Rule
    @JvmField
    var activityRule: ActivityScenarioRule<GameActivity> = ActivityScenarioRule(GameActivity::class.java)

}