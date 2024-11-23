// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    // Apply the Android plugin
    alias(libs.plugins.android.application) apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        // Add the classpath for the Google services plugin
        classpath("com.google.gms:google-services:4.3.15") // Use the latest version
    }
}

allprojects {

}
