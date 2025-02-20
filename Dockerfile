
# Utiliser une image Java officielle comme base
FROM openjdk:17-jdk-slim

# Définir le répertoire de travail
WORKDIR /app

# Copier le fichier JAR généré par Maven dans le conteneur
COPY target/*.jar app.jar

# Exposer le port utilisé par l'application
EXPOSE 8080

# Commande pour exécuter l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
