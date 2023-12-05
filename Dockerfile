FROM tomcat:9.0.80-jdk11-corretto-al2
RUN rm -rf /usr/local/tomcat/webapps/ROOT
RUN rm -rf /usr/local/tomcat/webapps/examples
ADD Selling_Fashion_Website-1.0-SNAPSHOT /usr/local/tomcat/webapps/

EXPOSE 8080
CMD ["catalina.sh", "run"]