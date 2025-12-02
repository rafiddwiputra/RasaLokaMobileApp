// Top-level build file where you can add configuration options common to all sub-projects/modules.
@file:Suppress("DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    // Menambahkan Plugins untuk navigation Home ke DetailResepFragment
    alias(libs.plugins.androidx.navigation.safeargs) apply false
}
