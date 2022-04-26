package com.testbyexample

import com.intellij.codeInsight.hints.*
import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import org.jetbrains.kotlin.psi.KtAnnotationEntry
import javax.swing.JComponent
import javax.swing.JPanel

@Suppress("UnstableApiUsage")
class ExampleCodeHighlighter: InlayHintsProvider<NoSettings> {
    override val key = SettingsKey<NoSettings>("testByExample")
    override val name = "Example Editor"
    override val previewText = "Loading Highlighting"

    override fun createSettings() = NoSettings()
    override fun createConfigurable(settings: NoSettings) = ExampleIConfigurable()

    override fun getCollectorFor(
        file: PsiFile,
        editor: Editor,
        settings: NoSettings,
        sink: InlayHintsSink
    ) = object: FactoryInlayHintsCollector(editor) {
        override fun collect(element: PsiElement, editor: Editor, sink: InlayHintsSink): Boolean {
            val annotation = element as? KtAnnotationEntry ?: return true

            return true
        }
    }
}