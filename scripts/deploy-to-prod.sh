#!/usr/bin/env bash

gradle clean build
heroku war:deploy build/libs/Prorg.war --app prorg