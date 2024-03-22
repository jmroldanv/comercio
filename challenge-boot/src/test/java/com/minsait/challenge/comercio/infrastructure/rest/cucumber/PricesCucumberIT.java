package com.minsait.challenge.comercio.infrastructure.rest.cucumber;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@ConfigurationParameter(
        key = GLUE_PROPERTY_NAME,
        value = "com.minsait.challenge.comercio.infrastructure.rest.cucumber.steps")
@SelectClasspathResource("features")
public class PricesCucumberIT {}
