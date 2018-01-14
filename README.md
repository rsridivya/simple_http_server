# simple_http_server

This is a repo with maven project and concourse ci pipelines.

This repo has a simple web application with unit tests and code coverage tests included in it. 

Instructions to run the app with mvn:
go to repo directory: 

Run the below commands: 
>mvn install

This will run the build, unit tests, code coverage to the project. You can check the code coverage results by opening target/jacoco-ut/index.html

>java -cp target/simple_http_server-0.0.1-SNAPSHOT.jar pkg.gannet.simple_http_server.SimpleHTTPServer

This will run the application. you can go to the browser and enter the url: http://<ipaddress or hostname:8004>/

You can find a docker container which run this web app: https://hub.docker.com/r/rsridivya/java-web-app-docker/
The repo description has instructions to how to run it. 

Running the pipeline:
fly -t lite set-pipeline -c pipeline.yml  -p web-app

This will create a pipeline with name web-app and once you run this will build and test the web application and then run integration test with dgoss.
