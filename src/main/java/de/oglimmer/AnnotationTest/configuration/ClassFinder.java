package de.oglimmer.AnnotationTest.configuration;


import lombok.Getter;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Spring specific code to extract all Classes in the de.oglimmer package.
 */
@Service
public class ClassFinder {

    @Getter
    private final List<Class<?>> dtoClasses;

    {
        try {
            final ClassPathScanningCandidateComponentProvider classPathScanningCandidateComponentProvider = new ClassPathScanningCandidateComponentProvider(false);
            classPathScanningCandidateComponentProvider.addIncludeFilter(new RegexPatternTypeFilter(Pattern.compile(".*")));
            final Set<BeanDefinition> candidateComponents = classPathScanningCandidateComponentProvider.findCandidateComponents("de.oglimmer");
            dtoClasses = new ArrayList<>();
            for (BeanDefinition bean : candidateComponents) {
                Class<?> clazz = Class.forName(bean.getBeanClassName());
                dtoClasses.add(clazz);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
