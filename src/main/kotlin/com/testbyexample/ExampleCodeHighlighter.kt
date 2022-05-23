package com.testbyexample

import com.intellij.codeInsight.hints.*
import com.intellij.debugger.engine.evaluation.TextWithImports
import com.intellij.debugger.engine.evaluation.TextWithImportsImpl
import com.intellij.openapi.Disposable
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.EditorFactory
import com.intellij.openapi.editor.actions.IncrementalFindAction
import com.intellij.openapi.editor.ex.EditorEx
import com.intellij.openapi.editor.impl.EditorImpl
import com.intellij.openapi.fileTypes.FileTypes
import com.intellij.openapi.util.Ref
import com.intellij.psi.*
import com.intellij.ui.EditorTextField
import com.intellij.util.ui.UIUtil
import org.jetbrains.kotlin.idea.KotlinFileType
import org.jetbrains.kotlin.idea.debugger.evaluate.KotlinCodeFragmentFactory
import org.jetbrains.kotlin.idea.util.application.invokeLater
import org.jetbrains.kotlin.psi.KtAnnotationEntry
import java.awt.Cursor
import java.awt.event.ComponentEvent

@Suppress("UnstableApiUsage")
class ExampleCodeHighlighter: InlayHintsProvider<NoSettings> {
    override val key = SettingsKey<NoSettings>("testByExample")
    override val name = "Example Editor"
    override val previewText = "Loading Highlighting"

    override fun createSettings() = NoSettings()
    override fun createConfigurable(settings: NoSettings) = ExampleIConfigurable()

    val hasComponent = mutableSetOf<KtAnnotationEntry>()

    override fun getCollectorFor(
        file: PsiFile,
        editor: Editor,
        settings: NoSettings,
        sink: InlayHintsSink
    ) = object: FactoryInlayHintsCollector(editor) {
        override fun collect(element: PsiElement, editor: Editor, sink: InlayHintsSink): Boolean {
            val annotation = AnnotationPsiFinder.find(element) ?: return true
            if (annotation in hasComponent) return true

            val customEditor = EditorTextField()

            val line = editor.document.getLineNumber(annotation.textRange.startOffset)
//            val presentation = factory.inset(factory.text("Hello World"), top = 6, left = 3)
            val presentation = customEditor
//            sink.addBlockElement(annotation.textRange.startOffset, false, true, 0, presentation)

            invokeLater {
                hasComponent += annotation

                val manager = EditorComponentInlaysManager.from(editor)

                val project = file.project
//                val code: PsiCodeFragment = KotlinCodeFragmentFactory()
//                    .createCodeFragment(TextWithImportsImpl(element), element, project)
//                val editorDocument = PsiDocumentManager.getInstance(project).getDocument(code)
                val factory = EditorFactory.getInstance()
                val editorDocument = factory.createDocument("Hello")
                val editorComponent = object: EditorTextField(editorDocument, project, FileTypes.PLAIN_TEXT) {
                    override fun updateBorder(editor: EditorEx) = setupBorder(editor)

                    override fun createEditor(): EditorEx {
                        return super.createEditor().apply {
                            component.isOpaque = false
                            scrollPane.isOpaque = false
                        }
                    }

                }.apply {
                    component.putClientProperty(UIUtil.HIDE_EDITOR_FROM_DATA_CONTEXT_PROPERTY, true)
                    putClientProperty(UIUtil.HIDE_EDITOR_FROM_DATA_CONTEXT_PROPERTY, true)
                    setOneLineMode(false)
                    setPlaceholder(text)
                    addSettingsProvider {
                        it.putUserData(IncrementalFindAction.SEARCH_DISABLED, true)
                        it.colorsScheme.lineSpacing = 1f
                        it.settings.isUseSoftWraps = true
                    }
                    selectAll()
                    cursor = Cursor.getDefaultCursor()
                }

                val inlayRef = Ref<Disposable>()
                val inlay = manager.insertAfter(line, editorComponent)
                editorComponent.revalidate()
                inlayRef.set(inlay)

                val viewport = (editor as? EditorImpl)?.scrollPane?.viewport
                viewport?.dispatchEvent(ComponentEvent(viewport, ComponentEvent.COMPONENT_RESIZED))
            }

            return true
        }
    }
}
