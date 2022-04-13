package com.github.paulpanther.testbyexampleintellij.services

import com.intellij.openapi.project.Project
import com.github.paulpanther.testbyexampleintellij.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
