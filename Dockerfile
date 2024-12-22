# Estágio de build
FROM eclipse-temurin:17-jdk-alpine as builder
WORKDIR /application

# Copiar os arquivos necessários para o build
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src ./src

# Construir o projeto Maven
RUN ./mvnw clean package -DskipTests

# Extrair o conteúdo do JAR gerado
ARG JAR_FILE=target/*.jar
RUN java -Djarmode=layertools -jar ${JAR_FILE} extract

# Estágio de execução
FROM eclipse-temurin:17-jre-alpine
WORKDIR /application

# Copiar os arquivos extraídos do build
COPY --from=builder /application/dependencies/ ./
COPY --from=builder /application/spring-boot-loader/ ./
COPY --from=builder /application/snapshot-dependencies/ ./
COPY --from=builder /application/application/ ./

# Definir o ponto de entrada
# Definir o ponto de entrada como o JAR executável
#ENTRYPOINT ["java", "-jar", "application.jar"]
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher", "--spring.profiles.active=prod"]
