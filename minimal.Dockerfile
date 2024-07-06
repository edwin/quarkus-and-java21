FROM registry.access.redhat.com/ubi9/ubi-minimal:9.4

LABEL BASE_IMAGE="registry.access.redhat.com/ubi9/ubi-minimal:9.4"
LABEL JAVA_VERSION="21"

ENV LANGUAGE='en_US:en'
ENV TZ='Asia/Jakarta'

RUN microdnf install -y --nodocs java-21-openjdk-headless  \
    && microdnf clean all  \
    && echo "securerandom.source=file:/dev/urandom" >> /etc/alternatives/jre/lib/security/java.security

WORKDIR /work/

COPY --chown=185 target/quarkus-app/lib/ /work/lib/
COPY --chown=185 target/quarkus-app/*.jar /work/application.jar
COPY --chown=185 target/quarkus-app/app/ /work/app/
COPY --chown=185 target/quarkus-app/quarkus/ /work/quarkus/

ENV JAVA_OPTS="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager -XX:TieredStopAtLevel=1 -noverify -XX:+UseShenandoahGC -XX:+AlwaysPreTouch -XX:+UseNUMA -Xlog:gc*,safepoint=debug:file=/tmp/gc.log.%p:time,uptime:filecount=5,filesize=50M -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/tmp/"

EXPOSE 8080
USER 185

CMD java $JAVA_OPTS -jar application.jar