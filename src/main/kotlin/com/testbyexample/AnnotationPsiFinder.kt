package com.testbyexample

import com.intellij.psi.PsiElement
import com.intellij.psi.impl.source.tree.LeafPsiElement
import com.intellij.psi.util.parentOfType
import org.jetbrains.kotlin.psi.KtAnnotationEntry
import org.jetbrains.kotlin.psi.KtStringTemplateExpression
import org.jetbrains.kotlin.psi.KtValueArgument
import org.jetbrains.kotlin.psi.KtValueArgumentName
import org.jetbrains.kotlin.psi.psiUtil.getChildOfType

object AnnotationPsiFinder {
    fun find(element: PsiElement): KtAnnotationEntry? {
        val leaf = element as? LeafPsiElement ?: return null
        if (leaf.text != "Example") return null
        return leaf.parentOfType<KtAnnotationEntry>()
    }

    fun findValue(element: PsiElement): PsiElement? {
        element.parentOfType<KtAnnotationEntry>() ?: return null
        val valArg = element.parentOfType<KtValueArgument>() ?: return null
        val valName = valArg.children.getOrNull(0) as? KtValueArgumentName ?: return null
        val valValue = valArg.children.getOrNull(1) as? KtStringTemplateExpression ?: return null
        return valValue.entries.firstOrNull()
    }
}
