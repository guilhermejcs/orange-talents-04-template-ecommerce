# Mercado Livre

## Tecnologias:

- Java 11

- Sprigboot 2.4.5

- Spring Cloud Feign

- Maven

- MySQL

- Spring JPA


------

## Cadastro de novo usuário

#### Tag: v0.1.1

### Necessidades

- precisamos saber o instante do cadastro, login e senha.

### Restrições

- O instante não pode ser nulo e não pode ser no futuro
- O login não pode ser em branco ou nulo
- O login precisa ter o formato do email
- A senha não pode ser branca ou nula
- A senha precisa ter no mínimo 6 caracteres
- A senha deve ser guardada usando algum algoritmo de hash da sua escolha.

### Resultado esperado

- O usuário precisa estar criado no sistema
- O cliente que fez a requisição precisa saber que o usuário foi criado. Apenas um retorno com status 200 está suficente.
- Em caso de falha de validação status 400

------

### Não podemos ter dois usuários com o mesmo email

#### Tag: v1.1.1

#### Necessidades

- Só pode existir um usuário com o mesmo email.

#### Resultado esperado

- Status 400 informando que não foi possível realizar um cadastro com este email.

------

### Cadastro de categorias

#### Tag: v.1.1.2

#### Necessidades

No mercado livre você pode criar hierarquias de categorias livres. Ex: Tecnologia -> Celulares -> Smartphones -> Android,Ios etc. Perceba que o sistema precisa ser flexível o suficiente para que essas sequências sejam criadas.

- Toda categoria tem um nome
- A categoria pode ter uma categoria mãe

#### Restrições

- O nome da categoria é obrigatório
- O nome da categoria precisa ser único

#### Resultado esperado

- categoria criada e status 200 retornado pelo endpoint.
- caso exista erros de validação, o endpoint deve retornar 400 e o json dos erros.

------

### Trabalhando com o usuário logado

#### Tag: 1.2.2

Você precisa configurar um mecanismo de autenticação via token, provavelmente com o Spring Security, para permitir o login.

------

