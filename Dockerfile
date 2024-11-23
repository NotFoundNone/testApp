# Используем базовый образ с Java 17
FROM eclipse-temurin:17-jdk-alpine

# Создаем рабочую директорию
WORKDIR /app

# Копируем собранный JAR файл в контейнер
COPY target/TestApp-0.0.1-SNAPSHOT.jar app.jar

# Указываем команду для запуска приложения
ENTRYPOINT ["java", "-jar", "app.jar"]
