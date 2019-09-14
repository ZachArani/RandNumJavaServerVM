## How to install [Java server](https://github.com/ZachArani/RandNumJavaServerVM)
### Installation
On an Ubuntu system run: `sudo apt-get install tomcat8`. If you are on another **nix* based system use your package manager of choice or download the source code from [Apache's website](https://tomcat.apache.org/download-80.cgi). This will all install Java and other dependencies if not already installed.

### Install Directories
Tomcat then creates several directories of note:
`/etc/tomcat8/`: Stores all configuration files, namely `server.xml`
`/usr/share/tomcat8/`: Runtime directory, also known as *CATALINA_HOME*
`/var/lib/tomcat8/webapps`: For webapp installations

### Modify Configurations
First, we will modify `/etc/tomcat8/server.xml` to change our port and allow external connections.
Open the file using a text editor and find this block of code (i.e.)
`sudo nano /etc/tomcat8/server.xml`
```
<Connector port="8080" protocol="HTTP/1.1"
               connectionTimeout="20000"
               redirectPort="8443" />
```

Modify the port number to either be 80 or your port of choice. Also add the line `address="0.0.0.0"` or your address of choice for external connections, if desired.
With the port 80, and the default address, our new configuration should look like this:

```
<Connector port="80" protocol="HTTP/1.1"
               address="0.0.0.0"
               connectionTimeout="20000"
               redirectPort="8443" />
```

Now exit the file (`ctrl+x, y, enter` on nano) and save. 

If you set your port number to something below 1023 you must also edit `/etc/default/tomcat8` and add the line `AUTHBIND=yes` to the bottom of the file. 

### Installing the Web Application (randNum)
In order to install web application, the file `randNum.war` simply needs to be copied to `/var/lib/tomcat8/webapps` and the server needs to be restarted. You can get this war from either compiling the [source code yourself or using the pre-compiled WAR file in the repo.](https://github.com/ZachArani/RandNumJavaServerVM)

For example, if our given application is located at `~/randNum.war` then we need to simply run `sudo cp ~/randNum.war /var/lib/tomcat8/webapps/` to copy the file to the proper destination.

In order to restart the server, run `sudo service tomcat8 restart`. 

### Usage
Depending on the port listed, You can access the server like so `http://<IP>:<PORT>/randNum`. If your port is set to 80, then it shall be `http://<IP>/randNum`. For example: `http://http://35.202.141.106/randNum` Connecting to the web address will bring about a random single numerical output between 1 and 1 million, with a guarantee that at least 750 out of 1000 numbers will be unique. 

**IMPORTANT NOTE:** The *first* connection to the server will take quite some time, due to a combination of limitations with both tomcat itself and the google cloud platform. It is recommended to run the `lynx`  (`sudo apt-get install lynx` to install) web browser and run a connection to the server after launching (i.e. `lynx http://localhost:<PORT>`) and wait for it to connect before considering the app 'launched.' The first connection may take several minutes but every consecutive connection following (regardless of device) will be at a regular speed.

### Useful commands
The server can be controlled via the ubuntu `service` command. Here are some useful commands
`sudo service tomcat8 start`: Startup server
`sudo service tomcat8  stop`: Shutdown server
`sudo service tomcat8 restart`: Restart server
`sudo service tomcat8 status`: Get current server status
