package ru.sooslick.qa.pagemodel.generator;

import ru.sooslick.qa.core.ScenarioContext;
import ru.sooslick.qa.pagemodel.annotations.GeneratorName;

/**
 * Interface for various Data Generators to convert some non-static data from template.
 * To register new Data generator for cucumber scenarios, class must have {@link GeneratorName} annotation
 * with generator's name that will be used in cucumber scenarios as is.
 * <p>
 * Those steps which accepts DataGenerator as step parameter will transform values between curly brackets to
 * real ones, when these values are matches following pattern:
 * {generator name: value to transform}
 * If engine has generator with given name registered, then value to transform will be passed to its {@link DataGenerator#generate} method,
 * and returned value will replace curly brackets template.
 */
public interface DataGenerator {

    /**
     * Transforms value from curly brackets template to real value.
     *
     * @param source  value from template to transform.
     * @param context current scenario's context.
     * @return transformed value from given template.
     */
    // todo probably i should use dependency injection for simplifying things/
    String generate(String source, ScenarioContext context);
}
