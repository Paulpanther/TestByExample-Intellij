package com.testbyexample

import com.intellij.openapi.components.Service
import com.intellij.openapi.project.Project

@Service
class PluginService(
    val project: Project
) {

    init {

    }
}