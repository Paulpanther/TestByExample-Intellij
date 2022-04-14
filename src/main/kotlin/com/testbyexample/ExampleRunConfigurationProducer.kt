package com.testbyexample

import com.intellij.execution.actions.ConfigurationContext
import com.intellij.execution.actions.LazyRunConfigurationProducer
import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.openapi.util.Ref
import com.intellij.psi.PsiElement
import com.intellij.psi.util.parentOfType
import org.jetbrains.kotlin.psi.KtAnnotationEntry
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.plugins.gradle.service.execution.GradleExternalTaskConfigurationType
import org.jetbrains.plugins.gradle.service.execution.GradleRunConfiguration

class ExampleRunConfigurationProducer: LazyRunConfigurationProducer<GradleRunConfiguration>() {

    override fun setupConfigurationFromContext(
        configuration: GradleRunConfiguration,
        context: ConfigurationContext,
        sourceElement: Ref<PsiElement>
    ): Boolean {
        val path = getFunctionPath(sourceElement.get()) ?: return false

        configuration.name = path
        configuration.settings.taskNames = listOf("test")
        configuration.settings.scriptParameters = "--tests $path"
        configuration.settings.externalProjectPath = context.project.basePath
        return true
    }

    override fun isConfigurationFromContext(configuration: GradleRunConfiguration, context: ConfigurationContext): Boolean {
        val element = context.location?.psiElement ?: return false
        val path = getFunctionPath(element) ?: return false
        return configuration.name == path
    }

    private fun getFunctionPath(element: PsiElement): String? {
        if (element.text != "Example") return null
        val funParent = element.parentOfType<KtNamedFunction>()
        return funParent?.fqName?.asString()
    }

    override fun getConfigurationFactory(): ConfigurationFactory = GradleExternalTaskConfigurationType.getInstance().factory
}
