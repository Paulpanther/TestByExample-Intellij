package com.testbyexample

import com.intellij.execution.actions.ConfigurationContext
import com.intellij.execution.actions.LazyRunConfigurationProducer
import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.openapi.util.Ref
import com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtAnnotationEntry

class ExampleRunConfigurationProducer: LazyRunConfigurationProducer<GradleConfig>() {

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