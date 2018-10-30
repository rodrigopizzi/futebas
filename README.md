[![Coverage Status](https://coveralls.io/repos/github/rodrigopizzi/futebas/badge.svg?branch=master)](https://coveralls.io/github/rodrigopizzi/futebas?branch=master)

# Projeto :: Futebas

Este projeto é responsavel por gerenciar partidas de futebol.

O backend foi construido com SpringBoot + Swagger. Assim tenho uma API Rest para ser consumida.

O frontend foi contruido com VueJs e bootstrap. Desta maneira consigo ter um unico backend que dará suporte para Android + Web. 

O planejamento e monitoramento das funcionalidades estão disponiveis na ferramenta [YourTrack](https://futebas.myjetbrains.com)

### Pré-requisitos

#### MacOSX

```
brew install node
npm install -g @vue/cli
```

#### Linux

```
sudo apt update
sudo apt install node
npm install -g @vue/cli
```

#### Windows

```
choco install npm
npm install -g @vue/cli
```

## Setup do Projeto

```
Futebas
├─┬ back        → backend module with Spring Boot code
│ ├── src
│ └── pom.xml
├─┬ front       → frontend module with Vue.js code
│ ├── src
│ └── pom.xml
└── pom.xml     → Maven parent pom managing both modules
```

## Rodando pela primeira vez

Dentro do diretório raiz do Futebas: 

```
mvn clean install
```

Para rodar aplicação toda em SpringBoot:

```
mvn --projects backend spring-boot:run
```

Agora abra o navegador na URL > http://localhost:8088/ e veja a aplicação rodando.



## Rodando o frontend em modo de desenvolvimento

Para ter feedback de alterações no frontend imediatamente, entre no diretório do `front` e rode:

```
npm run serve
```
