package com.testbyexample

import com.intellij.execution.lineMarker.ExecutorAction
import com.intellij.execution.lineMarker.RunLineMarkerContributor
import com.intellij.icons.AllIcons
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtAnnotationEntry

class ExampleRunLineMarkerContributor: RunLineMarkerContributor() {
    override fun getInfo(element: PsiElement): Info? {
        val annotation = element as? KtAnnotationEntry ?: return null
        if (annotation.shortName?.asString() != "Example") {
            return null
        }

        val actions = ExecutorAction.getActions(Int.MAX_VALUE)
        return object: Info(AllIcons.RunConfigurations.TestState.Run, actions, {
            "Run Example"
        }) {

        }
    }
}