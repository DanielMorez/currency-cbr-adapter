<h1>Settings</h1>

<h3>ActiveMQ Artemis on Docker</h3>

Images for downloading: vromero/activemq-artemis

```` 
docker run -it --rm \
-p 8161:8161 \
-p 61616:61616 \
-e ARTEMIS_USERNAME=admin \
-e ARTEMIS_PASSWORD=admin vromero/activemq-artemis
````
