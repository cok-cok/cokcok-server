package com.cokcok.backend;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.library.Architectures;

@AnalyzeClasses(packages = "com.cokcok.backend", importOptions = ImportOption.DoNotIncludeTests.class)
public class ArchitectureDependencyDirectionTest {

    @ArchTest
    void architectureDependencyDirectionTest(JavaClasses classes) {
        Architectures.layeredArchitecture()
                .consideringAllDependencies()
                .layer("domain").definedBy("com.cokcok.backend.domain..")
                .layer("application").definedBy("com.cokcok.backend.application..")
                .layer("adapter").definedBy("com.cokcok.backend.adapter..")
                .whereLayer("domain").mayOnlyBeAccessedByLayers("application", "adapter")
                .whereLayer("application").mayOnlyBeAccessedByLayers("adapter")
                .whereLayer("adapter").mayNotBeAccessedByAnyLayer()
                .check(classes);
    }
}