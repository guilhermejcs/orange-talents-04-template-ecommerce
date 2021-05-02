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

#### Tag: v1.2.2

Você precisa configurar um mecanismo de autenticação via token, provavelmente com o Spring Security, para permitir o login.

------

### Usuário logado cadastra novo produto

#### Tag: v2.2.2

#### Explicação

Aqui a gente vai permitir o cadastro de um produto por usuário logado.

#### Necessidades

- Tem um nome
- Tem um valor
- Tem quantidade disponível
- Características(cada produto pode ter um conjunto de características diferente).
- Cada característica tem um nome e uma descrição associada.
- Tem uma descrição
- Pertence a uma categoria
- Instante de cadastro

#### Restrições

- Nome é obrigatório
- Valor é obrigatório e maior que zero
- Quantidade é obrigatório e >= 0
- O produto possui pelo menos três características
- Descrição é obrigatória e tem máximo de 1000 caracteres.
- A categoria é obrigatória

#### Resultado esperado

- Um novo produto criado e status 200 retornado
- Caso dê erro de validação retorne 400 e o json dos erros

------

### Usuário logado adiciona imagem no seu produto

#### Explicação

Com um produto cadastrado, um usuário logado pode adicionar imagens ao seu produto. Não precisa salvar a imagem em algum cloud ou no próprio sistema de arquivos. Cada arquivo de imagem pode virar um link ficticio que pode ser adicionado ao produto. 

#### Tag: v.2.2.3

#### Necessidades

- Adicionar uma ou mais imagens a um determinado produto do próprio usuário

#### Restrições

- Tem uma ou mais fotos
- Só pode adicionar fotos ao produto que pertence ao próprio usuário

### Resultado esperado

- Imagens adicionadas e 200 como retorno
- Caso dê erro de validação retorne 400 e o json dos erros
- Caso tente adicionar imagens a um produto que não é seu retorne 403.

------

