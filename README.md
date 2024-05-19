# Comands for deploy:

## Installing JDK, Maven, Git and Nginx

```
sudo apt update
sudo apt install openjdk-17-jdk
sudo apt install maven
sudo apt install git
sudo apt istall nginx
```

## Cloning and Building the Project

# Create a directory for the project, clone the repository, and build the project:

```
mkdir -p /home/{yourusername}/app
cd /home/{yourusername}/app
git clone https://github.com/notfoundnone/testApp.git
cd testApp
./mvnw package
```

# Moving the Jar File

## Move the compiled jar file to the desired directory and set the appropriate permissions:

```
sudo mkdir -p /var/www/app
sudo cp target/TestApp-0.0.1-SNAPSHOT.jar /var/www/app/testapp.jar
sudo chmod 755 /var/www/app/testapp.jar
```

# Configuring the systemd Service

## Create the service file:

```
sudo nano /etc/systemd/system/testapp.service
```

## Add the following content:

```
[Unit]
Description=Simple API for Test
After=network.target

[Service]
User=www-data
WorkingDirectory=/var/www/app
ExecStart=/usr/bin/java -jar /var/www/app/testapp.jar
Restart=always
RestartSec=10
SyslogIdentifier=testapp
Environment=SPRING_PROFILES_ACTIVE=prod

[Install]
WantedBy=multi-user.target
```

## Reload systemd and start the service:

```
sudo systemctl daemon-reload
sudo systemctl start testapp
sudo systemctl enable testapp
```

# Configuring Nginx

## Edit the Nginx configuration:

```
sudo nano /etc/nginx/sites-available/default
```

## Add the following content:

```
server {
    listen 80;
    server_name _;

    location / {
        proxy_pass http://localhost:8080;
        proxy_http_version 1.1;
        proxy_set_header Upgrade $http_upgrade;
        proxy_set_header Connection keep-alive;
        proxy_set_header Host $host;
        proxy_cache_bypass $http_upgrade;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
    }
}
```

## Restart Nginx:

```
sudo systemctl restart nginx
```
