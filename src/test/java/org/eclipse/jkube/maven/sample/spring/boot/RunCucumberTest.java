package org.eclipse.jkube.maven.sample.spring.boot;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

import org.junit.runner.RunWith;

@io.cucumber.junit.platform.engine.Cucumber
@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"})
public class RunCucumberTest {
}
