FROM container-registry.oracle.com/java/openjdk:21-oraclelinux8

ARG JAR_FILE                  

EXPOSE 8080                  

COPY ${JAR_FILE} issueDeviceAdministration.jar       
ENTRYPOINT ["java"]           
CMD ["-jar","app.jar"]  