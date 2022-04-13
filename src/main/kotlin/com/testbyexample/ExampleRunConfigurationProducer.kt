package com.testbyexample

import com.intellij.execution.actions.ConfigurationContext
import com.intellij.execution.actions.LazyRunConfigurationProducer
import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.execution.junit.JUnitConfiguration
import com.intellij.execution.junit.JUnitConfigurationType
import com.intellij.openapi.util.Ref
import com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtAnnotationEntry

class ExampleRunConfigurationProducer: LazyRunConfigurationProducer<JUnitConfiguration>() {

    override fun setupConfigurationFromContext(
        configuration: JUnitConfiguration,
        context: ConfigurationContext,
        sourceElement: Ref<PsiElement>
    ): Boolean {
        val element = sourceElement.get()
        val annotation = element as? KtAnnotationEntry ?: return false

        return true
    }

    override fun isConfigurationFromContext(configuration: JUnitConfiguration, context: ConfigurationContext): Boolean {
        return false
    }

    override fun getConfigurationFactory(): ConfigurationFactory = JUnitConfigurationType().configurationFactories.first()
}