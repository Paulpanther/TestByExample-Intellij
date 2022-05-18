package com.testbyexample

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType

class ExampleSyntaxHighlighter: SyntaxHighlighterBase() {
    override fun getHighlightingLexer(): Lexer {
        TODO("Not yet implemented")
    }

    override fun getTokenHighlights(tokenType: IElementType?): Array<TextAttributesKey> {
        TODO("Not yet implemented")
    }
}
