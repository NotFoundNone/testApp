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

### Create a directory for the project, clone the repository, and build the project:

```
mkdir -p /home/{yourusername}/app
cd /home/{yourusername}/app
git clone https://github.com/notfoundnone/testApp.git
cd testApp
sudo mvn clean package
```

## Moving the Jar File

### Move the compiled jar file to the desired directory and set the appropriate permissions:

```
sudo mkdir -p /var/www/app
sudo cp target/*.jar /var/www/app/testapp.jar
sudo chmod 755 /var/www/app/testapp.jar
```

## Configuring the systemd Service

### Create the service file:

```
sudo nano /etc/systemd/system/testapp.service
```

### Add the following content:

```
[Unit]
After=network.target

[Service]
Type=simple
User=notfoundnone
ExecStart=/usr/bin/java -jar /var/www/app/testapp.jar

[Install]
WantedBy=multi-user.target
```

### Reload systemd and start the service:

```
sudo systemctl daemon-reload
sudo systemctl start testapp
sudo systemctl enable testapp
```

## Configuring Nginx

### Edit the Nginx configuration:

```
sudo cp /etc/nginx/sites-available/default /etc/nginx/sites-available/testapp
sudo nano /etc/nginx/sites-available/testapp
```

### Add the following content:

```
server {
    listen 80;
    server_name _;

    location / {
        proxy_pass http://localhost:8080;
    }
}
```

### Сreating a symbolic link:

```
sudo ln -s /etc/nginx/sites-available/testapp /etc/nginx/sites-enabled/
sudo rm /etc/nginx/sites-enabled/default
```

### Restart Nginx:

```
sudo systemctl restart nginx
```

### Setting up a firewall:

```
sudo ufw allow 80
sudo ufw allow 8080
sudo ufw enable
sudo ufw status

```
