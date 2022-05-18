package com.testbyexample

import com.intellij.execution.TestStateStorage
import com.intellij.execution.lineMarker.ExecutorAction
import com.intellij.execution.lineMarker.RunLineMarkerContributor
import com.intellij.icons.AllIcons
import com.intellij.psi.PsiElement
import com.intellij.psi.impl.source.tree.LeafPsiElement
import com.intellij.psi.util.parentOfType
import org.jetbrains.kotlin.psi.KtAnnotationEntry

class ExampleRunLineMarkerContributor: RunLineMarkerContributor() {
    override fun getInfo(element: PsiElement): Info? {
        val annotation = AnnotationPsiFinder.find(element) ?: return null

//        val state = TestStateStorage.getInstance(e.getProject()).getState(url)
        val actions = ExecutorAction.getActions(Int.MAX_VALUE)
        return object: Info(AllIcons.RunConfigurations.TestState.Run, actions, {
            "Run Example"
        }) {

        }
    }
}
