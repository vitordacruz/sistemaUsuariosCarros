# Sistema de Usuarios de Carros

# ESTÓRIAS DE USUÁRIO
## # 1. Cadastro Usuário
Eu como usuário, quero cadastrar usuários no sistema, para que eu possa gerenciar os usuários. O sistema deve inserir, listar, buscar, atualizar e remover usuários.  
Para inserir ou atualizar um usuário devo informar nome, sobrenome, email, data de nascimento, login, senha, telefone.  
Todos essas informações devem ser preenchidas.  
Não poderá haver mais de um usuário com o mesmo e-mail.  
Não poderá haver mais de um usuário com o mesmo login.  
Não será necessário estar logado para cadastrar usuários.  
Exemplo de JSON para a criação de usuário:
```json
{ 
   "firstName":"Hello",
   "lastName":"World",
   "email":"hello@world3.com",
   "birthday":"1990-05-01",
   "login":"hello.world",
   "password":"h3ll0",
   "phone":"988888888",
   "cars":[ 
      { 
         "year":2018,
         "licensePlate":"PDV-0265",
         "model":"Audi",
         "color":"White"
      }
   ]
}
```
#### Critérios de aceitação:
- Não é preciso estar cadastrado previamente
- A rota para a consulta de usuários deve ser a `api/users`
- A rota para a consulta de um usuário deve ser a `api/users/{id}`
- A rota para a atualização de usuário deve ser a `api/users/{id}`
- A rota para a remoção de usuário deve ser a `api/users/{id}`

## #  2. Cadastro Carros
Eu como usuário logado, quero cadastrar meus carros, para que eu possa gerenciar meus carros.  
O sistema deve inserir, listar, buscar, atualizar e remover meus carros.  
Para inserir ou atualizar um carro devo informar ano de fabricação, placa do carro, modelo e cor predominante do carro.  
Não poderá haver mais de um carro com a mesma placa.  
Todas as informações do carro devem estar preenchidas na hora de inserir ou atualizar os dados do carro.  
## # 3. Retornar minhas informações cadastrais
Eu como usuário logado, quero que o sistema exiba minhas informações cadastrais como: nome, sobrenome, email, data de nascimento, login, telefone e carros, além disso deve exibir a data de criação do meu usuário no sistema e meu último login.  
#### Critérios de aceitação:
- É preciso estar cadastrado previamente
- É preciso estar logado
- A rota para o acesso deve ser a `api/me`

## # 4. Logar no sistema
Eu como usuário quero logar no sistema para executar as ESTÓRIAS DE USUÁRIO 2 e 3  
Exemplo de JSON para logar no sistema:
```json
{
    "login": "meu_login",
    "password": "minha_senha"
}
```
#### Critérios de aceitação:
- A rota para executar o login deve ser a api/signin
- Os campos login e password devem ser preenchidos  

## SOLUÇÃO
Foi usado Spring Boot na solução da API para facilitar o uso da configuração e de suas dependências. O Spring Boot tem muitos componentes para o uso do desenvolvimento, diminuindo o retrabalho e facilitando a produtividade do desenvolvedor. O Spring foi testado e usado por muitos desenvolvedores, trazendo confiabilidade ao projeto de quem o utiliza.

## Build
Por ser um projeto *MAVEN*, qualquer *IDE* que tenha suporte para tal, pode ser utilizada. Após o projeto já ter sido carregado na ide, os comandos abaixos devem ser utilizados:
```sh
$ mvn clean install
```
Acessar a aplicação no endereço: `http://localhost:8080` e suas rotas descritas nas estórias.

## Deploy
O deploy foi feito utilizando o [Heroku](https://www.heroku.com/) e a aplicação pode ser acessada no seguinte endereço: `https://api-usuarios-carros.herokuapp.com/api`
