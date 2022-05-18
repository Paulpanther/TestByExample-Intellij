package com.testbyexample;

import com.intellij.codeInsight.hints.ChangeListener;
import com.intellij.codeInsight.hints.ImmediateConfigurable;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

@SuppressWarnings("UnstableApiUsage")
public class ExampleIConfigurable implements ImmediateConfigurable {
	@NotNull
	@Override
	public JComponent createComponent(@NotNull ChangeListener changeListener) {
		return new JPanel();
	}
}
