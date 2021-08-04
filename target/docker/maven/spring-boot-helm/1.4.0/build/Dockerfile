FROM quay.io/jkube/jkube-java-binary-s2i:0.0.9
ENV JAVA_APP_DIR=/deployments JAVA_OPTIONS=-Dspring.output.ansi.enabled=always
LABEL org.label-schema.description="Spring Boot application with a single REST endpoint.\
    Demonstrates how to generate Helm chart (YAML) files using Eclipse JKube's k8s:helm Maven goal." org.label-schema.version=1.4.0 org.label-schema.schema-version=1.0 org.label-schema.build-date=2021-07-28T13:56:37.451720 org.label-schema.name="Eclipse JKube :: Quickstarts :: Maven :: Spring Boot - Helm" org.label-schema.vcs-ref=2cdb581fd67f874a3d2d1323be7576455ffa01fa org.label-schema.vcs-url=https://github.com/eclipse/jkube.git
EXPOSE 8080 8778 9779
COPY /jkube-generated-layer-original/deployments /deployments/
