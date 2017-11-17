package io.pestakit.email.api.spec;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.junit.Test;

/**
 * @author Olivier Liechti (olivier.liechti@heig-vd.ch)
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/scenarios/", plugin = {"pretty", "html:target/cucumber"})
public class SpecificationTest {


}