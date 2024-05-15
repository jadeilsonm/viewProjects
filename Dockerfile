FROM gelecex/openjdk-curl
LABEL authors="jadeilson"

WORKDIR app

COPY . .

# Expõe a porta que a aplicação utiliza
EXPOSE 8080

# Comando para executar a aplicação quando o contêiner for iniciado
CMD ["java", "-jar", "target/ViewProjects-0.0.1-SNAPSHOT.jar"]

ENTRYPOINT ["top", "-b"]