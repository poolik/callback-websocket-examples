performance-comparision
===========================

Performance comparision between using the standard ```$http``` service and the callback-websocket provided
```WebSocketService```

# Running
To run the example simply

1. build the .jar:
```bash
    git clone git@github.com:poolik/callback-websocket-examples.git
    cd callback-websocket-examples/performance-comparision
    mvn clean install
```

2. run it
```bash
    java -jar target/performance-comparision-jar-with-dependencies.jar
```

3. Open your browser @ [http://localhost:8080/websocket/](http://localhost:8080/websocket/)

You should see something similar after a few runs:
![performance-comparision application](http://f.cl.ly/items/1d0q1m3L1S0m0K3m2a2r/Screen%20Shot%202014-05-02%20at%2014.10.36.png)

Feel free to modify the nr of requests and test with different browsers
