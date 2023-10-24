FROM eclipse-temurin:20-jdk

  # Копируем директорию из основной файловой системы внутрь образа

COPY /app/build/libs/simple-application.jar simple-application.jar

# Определяет действие, которое будет выполнено при старте контейнера (docker run)
# Используется только в случае, если контейнер был запущен без указания команды
# [команда, аргумент]

# Сообщаем, что контейнер использует 80 порт
EXPOSE 3000

ENTRYPOINT ["java","-jar","simple-application.jar"]