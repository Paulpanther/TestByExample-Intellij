package com.testbyexample

import com.intellij.openapi.fileEditor.FileEditor
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.FileEditorManagerListener
import com.intellij.openapi.fileEditor.FileEditorProvider
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Pair
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiManager
import org.jetbrains.kotlin.idea.util.application.invokeLater

class FileOpenedHandler: FileEditorManagerListener {

    companion object {
        fun register(project: Project) {
            project.messageBus.connect().subscribe(FileEditorManagerListener.FILE_EDITOR_MANAGER, FileOpenedHandler())
        }
    }

    override fun fileOpenedSync(
        source: FileEditorManager,
        file: VirtualFile,
        editors: Pair<Array<FileEditor>, Array<FileEditorProvider>>
    ) {
        val psiFile = PsiManager.getInstance(source.project).findFile(file) ?: return
    }
}