war-example
===========================

# Running
To run the example simply

1. build the .jar:
    ```bash
    git clone git@github.com:poolik/callback-websocket-examples.git
    cd callback-websocket-examples/war-example
    mvn clean install
    ```

2. deploy the resulting **websocket-callback-example.war**
    ```bash
    cp target/websocket-callback-example.war /path/to/appServer/deployment/folder
    ```

3. Open your browser @ [http://localhost:8080/websocket-callback-example/](http://localhost:8080/websocket-callback-example/)

You should see the following:

![callback-websocket example](http://f.cl.ly/items/1t3Q382x2F0f0r0S182x/Screen%20Shot%202014-05-02%20at%2015.49.29.png)