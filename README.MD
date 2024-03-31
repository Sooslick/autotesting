# Sooslick.Art's autotesting framework

This autotesting framework is custom JUnit5 x Selenium x Cucumber addon that introduces some useful features for testing
web-pages and related things. The primary goal of this framework is to provide flexible Page Model and highly
customizable steps for verifying various aspects of web-pages and pages elements, without overbloating steps classes
with repetitive gherkin phrases and implementations.

# Setting up test runner, launching tests

- [JUnit5 @Suite](#Setting-up-JUnit5-@Suite)
- [Custom runner class](#Setting-up-custom-runner-class)
- [runner.yaml](#Configuring-runner-properties-through-runner.yaml-file)

### Setting up JUnit5 @Suite

For launching any test using JUnit5 @Suite, you should create empty class annotated with @Suite, and then add other
annotations for configuring cucumber features and selecting tests for running.

To make things work you must include cucumber engine using `@IncludeEngines("cucumber")`, specify path to scenarios
directory using annotations `@SelectClasspathResource`
with selected tests to launch using `@IncludeTags`, and then configure in any way following cucumber properties:

- `cucumber.glue` - packages with cucumber steps implementations, comma separated. Add package `ru.sooslick.qa.steps` to
  include default framework steps and features.

- `cucumber.object-factory` - make sure you have this property with value `ru.sooslick.qa.core.StepsFactory`
  or another cucumber ObjectFactory with dependency injection implemented, since framework has a couple of steps classes
  that shares common variables through test execution.

To set up custom features besides cucumber, you should configure [runner.yaml](#runner.yaml) file.

Most popular IDEs have native support for launching JUnit5 Suites, perhaps you will need to configure your pom.xml in
order to launch tests using maven.

[Example class](src/main/java/ru/sooslick/qa/DebugRunner.java)

### Setting up custom runner class

We already have configured Runner class that creates and executes JUnit5 Discovery:

[Example class](src/main/java/ru/sooslick/qa/YamlRunner.java)

Use `mvn exec:java -Dexec.mainClass=ru.sooslick.qa.YamlRunner` to launch tests using maven.

Unlike JUnit5 @Suite, all runner properties are defined by [runner.yaml](#runner.yaml) configuration file. That makes
custom runner more flexible, however custom runners has some lack of integrity with IDEs.

### Configuring runner properties through runner.yaml file

For make things work you must properly configure your [runner.yaml](src/main/resources/properties/runner.yaml) file. To
keep git diff clean, you can use external runner.yaml file by setting environment variable `RUNNER_PROPERTIES`.

You should set properties related to cucumber (unless you use JUnit5 @Suite configured through annotations):

- `resources.scenarios` - list of classpath directories with cucumber scenarios;
- `resources.steps` - list of java packages with implementations for gherkin steps phrases;
- `resources.objects-factory` - implementation of cucumber object factory. Make sure you have this property with
  value `ru.sooslick.qa.core.StepsFactory`;
- `test-run.launch-tags` - list of test tags that will be launched during test execution.

You must set properties related to other framework's features:

- `resources.page-objects` - list of java packages with Page Model classes;
- `resources.preconditions` - list of java packages with implementations for preconditions steps;
- `resources.data-generators` - list of java packages with implementations for
  custom [Data Generators](#Using-Data-Generators)
- `resources.properties` - list of additional .property-files required for test run.

You also must configure your webdriver:

- `test-run.web-driver.path` - path to webdriver executable;
- `test-run.web-driver.binary-path` - path to browser main executable;
- `test-run.web-driver.configuration-class` - java class that instantiate Selenium WebDriver for tests. To create custom
  configuration classes you have to implement `WebDriverConfig` interface.

# Page Model

We use Selenium Page Model with some major additions that makes things easier to read and maintain. The basic principle
is the same, we create package in the project where we will create Page Objects.

##### Pages

After browser loads the page we should call the step `* "My Web Page" page opens`. Step will search Page Object,
annotated with `@PageName` with the value, corresponding to page's name
("My Web Page" in this example). So we have to create new Java Class with annotation, extend it from `AbstractPage`
and fill the newly created Page Object with elements.

##### Elements

We are referring to page elements using their human-readable names, so we have wrapper-class HtmlElement to link
WebElements with their names. To describe element within Page Object, we should declare java field of HtmlElement or any
HtmlElement child. Then, we should add following annotations:

- `@ElementName` - human-readable name of the element for referring from cucumber scenarios;
- `@FindBy` - Selenium annotation with locator to the element.

Additionally, you can add `@Required` annotation. This annotation marks element as required to be visible on the page
after page loaded - so instead of manually checking if all elements of the pages displayed correctly before proceeding,
you simply can put `@Required` on needed elements.

##### Inner elements

Framework supports nesting elements within various blocks. For example, if we have some kind of login form, we can
declare new class that will extend HtmlElement, and then declare all elements of this form within this class. So,
instead of having all inner elements in page class, we can simply declare one element for entire block.

So, if we have some Page class with element "Auth form", and this form contains inner element "Login input", we can
access inner element through chaining names using special separator `->`: `Auth form -> Login input`

##### Customizing actions

Some UI implementations requires a special approach. For example, page has complicated input field with extra validation
checks, and Selenium's `sendKeys` just doesn't work as intended. So, instead of implementing new gherkin-phrases for
that type of inputs, we can change behaviour of `sendKeys`.

To make this work, you have to complete following steps:

1) Create new java class that implements ActionPerformer with generic type corresponding to return type of required
   method;
2) Implement `perform` method for given WebElement on your own;
3) Jump to declared HtmlElement and add `@Action` annotation. Specify action type and put your performer class here.

##### Customizing components

TBA

# Implementing new steps and custom Data Types

We didn't mess with declaring new gherkin-phrases and their implementations, however we have some additions to make
development easier.

In addition to default cucumber types we added these:

- `{element}` - to use HtmlElement from loaded page with given name (or by given names chain);
- `{image}` - to use another type of HtmlElement related to `<img>` tags;
- `{dataGenerator}` - to use non-static values, depending on test environment or another special conditions; See
  more: [Using Data Generators](#Using Data Generators)
- `{variable}` - to use any variable from scenario context;
- `{numberComparisonMethod}` - to specify how to treat various number-related comparisons.

You also can examine other ParameterTypes by
inspecting [ParameterTypes class](src/main/java/ru/sooslick/qa/steps/ParameterTypes.java)

##### Dependency Injection

We have [ScenarioContext](src/main/java/ru/sooslick/qa/core/ScenarioContext.java) type for sharing test execution state
between various cucumber-related classes, such as Steps. To use it, you must have any ScenarioContext field, annotated
with `@Context`. ScenarioContext of current test execution will be injected into newly instantiated object and will be
able to use it in various steps.

# Using Data Generators

Some cucumber steps accepts `{dataGenerator}` parameter. Data generators are commonly used to convert some non-static
data from template. To convert values, you should specify a generator and a template in curly brackets, according to
template:

`{generator name: value to transform}`

Cucumber will search DataGenerator class annotated with `@GeneratorName`. If there is a generator with giver name, value
to transform will be converted by this generator, and resulting value will be used as expected value.

We provide following data generators by default:

- `current date in format` - to use current date formatted with given template;
- `variable` - to use context variable with given name;
- `random number` - to use randomly generated integer within given bounds (both inclusive);
- `property` - to use value from .properties file, enlisted in runner config.

To implement your own generator, you have to
implement [DataGenerator](src/main/java/ru/sooslick/qa/pagemodel/generator/DataGenerator.java)
interface. Make sure you listed your generators package in `resources.data-generators` runner property.

# Test reporting

In example runners we use Allure as reporter plugin.

`cucumber.plugin = io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm`

Allure generates `allure-results` directory after test execution.

# Other things to mention

TBA