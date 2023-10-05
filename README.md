# Gerenciamento de Dependências e Build em Java com Maven

## Introdução

**O que é Maven**

- Gerenciamento de build e dependências de projetos
- Auxilia na compilação, execução de testes e empacotamento de projetos
- Endereça como o sw foi construído e suas dependências
- Fornece informações de qualidade

**Instalação e configuração**


    Download
    Instalação:

```
$ tar -xvf apache-maven-...
# mv apache-maven-... /opt/maven3
```

Adicione ao final do ~/.bashrc

```
export MVN_HOME="/opt/maven3/"
export PATH="$PATH:${MVN_HOME}/bin"
```


## Primeiro projeto e conceitos

**Criando um projeto via linha de comando**

```
mvn archetype:generate -DgroupId=io.github.lucianodacunha -DartifactId=quick-start-maven -Darchetype=maven-archetype-quickstart -DinteractiveMode=false

```

**Comandos que auxiliam no dia-a-dia**

- mvn compile: compila os códigos e insere na pasta target
- mvn test: encontra classes de test e executa as validações
- mvn package: empacota o jar da app.
- mvn clean: limpa a pasta de trabalho, `target`.

**Criando diferentes tipos de projeto**

- Maven archetype: possibilita a configuração de um projeto e como será construído.
- procurar por maven archetype list: 
  - [Maven Archetypes](https://maven.apache.org/archetypes/index.html)
  - [MVN Repository](https://mvnrepository.com/)

## POM, dependências e repositórios

**POM**

- Project object model
- formato xml
- detalha o projeto, como construir e empacotar

```

```
- geralmente, inciamos o uso com arquivos pom.xml simples, mas é importante lembrar, que para todos os casos, o Maven irá extender automaticamente as funcionalidades de um Super POM. Esse super POM, possui todas as configurações avançadas de um projeto.

**Repositórios**

- São locais onde podemos encontrar plugins e libs que o Maven provê.
- Locais e remoto
- Remoto é por padrão o [Maven Central(https://central.sonatype.com/)], definido no super POM.
- Utilizando repositório no POM.

```
<project>
    <repositories>
        <repository>
...

```
- Local: na máquina local, onde o Maven mantém um repositório de tudo que já foi baixado, e será dali que será baixado novamente caso já exista.

**Dependências**

- no POM?

```
<dependencies>
    <dependency>
        <groupId>io.github.lucianodacunha</groupId>
        <artifactId>app-start</artifactId>
        <version>0.0.1</version>
    <dependency>
...
```
- groupId: id da organização
- artifactId: nome do projeto
- version: versão do seu projeto

## Gerenciando dependências

**Tipo de dependências**

- dependências diretas: declaradas no xml diretamente
- dependências transitivas: declaradas no xml da dependência.
- mvn install 
- copie o POM e adicione no novo projeto

```
  <groupId>io.github.lucianodacunha</groupId>
  <artifactId>webapp</artifactId>
  <version>1.0-SNAPSHOT</version>
```

**Transitividade e Escopos**

- dependências transitivas
- classpath: runtime, test e compile
- o maven prevê escopos que limitam a transitividade das dependências, sendo elas:
- default: padrão, compile
- provider: compilação, test, mas não em runtime e não é transitiva.
- runtime: para execução e não compilação.
- test: compilação e execução dos tests e não é transitiva.
- system: similar ao provided, não sendo transitiva.
- import: permite importar dependências de outro projeto.

**Dica sobre escopos, dependências opcionais e exclusion**

- ver o classpath

```
mvn dependency:build-classpath -DincludeScope=compile
mvn dependency:build-classpath -DincludeScope=test
mvn dependency:build-classpath -DincludeScope=runtime
```

- Dependências opcionais: utilizado quando uma dependência não é necessária para os projetos que irão reutilizar seu componente.

```
    <dependency>
        <groupId>io.github.lucianodacunha</groupId>
        <artifactId>app-start</artifactId>
        <version>0.0.1</version>
        <optional>true</optional>
    <dependency>
```

-exclusions: utilizado quando se usa ou compartilha uma lib que já não temos ou não queremos ter disponível.

```
<dependency>
    <groupId>io.github.lucianodacunha</groupId>
    <artifactId>app-start</artifactId>
    <version>0.0.1</version>
    <exclusions>
        <exclusion>
            <groupId>com.google.code.gson</groupId>
            <artifactId>gson</artifactId>
        </exclusion>
    </exclusions>
<dependency>
```

## Maven Build Lifecycle

- Conceito de ciclo de vida de construção
- conceito e os comando da ferramenta
- composto por 3 ciclos de vida
  - default lifecycle: responsável pelo deploy local, composto por 23 fases
    - validate, compile, test-compile, test, integration-test, package, install, deploy
  - clean lifecycle: limpeza do projeto, composto por pre, clean e post-clean
  - site-lifecycle: ciclo final, de publicação do projeto, composto por 4 fases:
    - pre, site, post-silte e site-deploy.
- cada ciclo possui fases (Maven phases)
- cada fase possui objetivos (maven goals)

## Projetos multi-módulos

- projeto com 4 módulos separados: core, service, webapp, controller
- projeto agregador e vários módulos
- crie o projeto principal
- define em seu pom, a tag packaging com o valor pom.
- crie os demais módulos dentro do diretório do projeto parent.

## Plugins

- a maioria das funcionalidades são providas por plugins
- pensado em extensibilidade
- escritos na maioria em java/jars.
- mais utilizados:
  - eclipse, jacoco, ear, war, compile, clean, checkstyle, javadoc
- configuração: 

```
  <build>
    <plugins>
      <plugin>
        <artifactId>maven-myquery-plugin</artifactId>
        <version>1.0</version>
        <configuration>
          <url>http://www.foobar.com/query</url>
          <timeout>10</timeout>
        </configuration>
      </plugin>
    </plugins>
  </build>
```
- plugin javadoc

```
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-javadoc-plugin</artifactId>
    <version>3.3.1</version>
</plugin>
```

- No terminal

```
mvn javadoc:javadoc
```
## Para saber mais

- [Slides](https://docs.google.com/presentation/d/1wudqWaBDK40QnBAYjuh4Q65dcC2wqLW_/edit?usp=sharing&ouid=105300330738120646134&rtpof=true&sd=true)
- Apache Maven 3 Cookbook, Srirangan, 2011, Packt Publishing