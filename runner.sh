#!/usr/bin/env bash

mvn gatling:test -Dgatling.simulationClass=org.gatling.test.ExampleTest -Dsettings.env=prod