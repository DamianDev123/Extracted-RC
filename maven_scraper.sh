#!/bin/bash

BASE_MAVEN_URL=https://repo1.maven.org/maven2/org/firstinspires/ftc
RELEASE_VERSION=10.3.0

OUTPUT_DIR=ftcmaven_scraped_$RELEASE_VERSION

if [ -d "$OUTPUT_DIR" ]; then
  rm -rf "$OUTPUT_DIR"
fi
mkdir "$OUTPUT_DIR"

declare -a modules=("Blocks" "FtcCommon" "Hardware" "Inspection" "OnBotJava" "RobotCore" "RobotServer" "Vision")

for module in "${modules[@]}"
do
   echo "Downloading $module-$RELEASE_VERSION.aar"
   curl -sSfL "$BASE_MAVEN_URL/$module/$RELEASE_VERSION/$module-$RELEASE_VERSION.aar" -o "$OUTPUT_DIR/$module.aar"

   echo "Downloading $module-$RELEASE_VERSION-sources.jar"
   curl -sSfL "$BASE_MAVEN_URL/$module/$RELEASE_VERSION/$module-$RELEASE_VERSION-sources.jar" -o "$OUTPUT_DIR/$module-sources.jar"
done
