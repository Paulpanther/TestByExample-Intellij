package com.testbyexample

import com.intellij.psi.PsiElement
import com.intellij.psi.impl.source.tree.LeafPsiElement
import com.intellij.psi.util.parentOfType
import org.jetbrains.kotlin.psi.KtAnnotationEntry

object AnnotationPsiFinder {
    fun find(element: PsiElement): KtAnnotationEntry? {
        val leaf = element as? LeafPsiElement ?: return null
        if (leaf.text != "Example") return null
        return leaf.parentOfType<KtAnnotationEntry>()
    }
}