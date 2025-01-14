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

### Adicione uma opinião sobre um produto

#### Tag: v.2.3.3

#### Explicação

Um usuário logado pode opinar sobre um produto. Claro que o melhor era que isso só pudesse ser feito depois da compra, mas podemos lidar com isso depois.

#### Necessidades

- Tem uma nota que vai de 1 a 5
- Tem um título. Ex: espetacular, horrível...
- Tem uma descrição
- O usuário logado que fez a pergunta (aqui pode usar usar o approach de definir um usuário na primeira linha do controller e depois trabalhar com o logado de verdade)
- O produto que para o qual a pergunta foi direcionada

#### Restrições

- A restrição óbvia é que a nota é no mínimo 1 e no máximo 5
- Título é obrigatório
- Descrição é obrigatório e tem no máximo 500 caracteres
- usuário é obrigatório
- o produto relacionado é obrigatório

#### Resultado esperado

- Uma nova opinião é criada e status 200 é retornado
- Em caso de erro de validação, retorne 400 e o json com erros.

------

### Faça uma pergunta

#### Tag: v.3.3.3

#### Explicação

Um usuário logado pode fazer uma pergunta sobre o produto

#### Necessidades

- A pergunta tem um título
- Tem instante de criação
- O usuário que fez a pergunta
- O produto relacionado a pergunta
- O vendedor recebe um email com a pergunta nova
- o email não precisa ser de verdade. Pode ser apenas um print no console do servidor com o corpo.

#### Restrições

- O título é obrigatório
- O produto é obrigatório
- O usuário é obrigatório

#### Resultado esperado

- Uma nova pergunta é criada e é retornada. Status 200
- Em caso de erro de validação, retorne 400 e o json com erros.

------

### Escreva o código necessário para montar a página de detalhe

#### Tag: v3.3.4

#### Explicação

O front precisa montar essa página =>  [Exemplo mercado livre]( https://produto.mercadolivre.com.br/MLB-1279370191-bebedouro-bomba-eletrica-p-garrafo-galo-agua-recarregavel-_JM?quantity=1&variation=49037204722&onAttributesExp=true)

Não temos todas as informações, mas já temos bastante coisa. Faça, do jeito que achar melhor o código necessário para que o endpoint retorne as informações para que o front monte a página.

#### Informações que já temos como retornar

- Links para imagens
- Nome do produto
- Preço do produto
- Características do produto
- Descrição do produto
- Média de notas do produto
- Número total de notas do produto
- Opiniões sobre o produto
- Perguntas para aquele produto

------

### Realmente finaliza compra - parte 1

#### Tag: v3.4.4

#### Contexto

Aqui a gente vai simular uma integração com um gateway como paypal, pagseguro etc. O fluxo geralmente é o seguinte:

- O sistema registra uma nova compra e gera um identificador de compra que pode ser passado como argumento para o gateway.
- O cliente efetua o pagamento no gateway
- O gateway invoca uma url do sistema passando o identificador de compra do próprio sistema e as informações relativas a transação em si.

Então essa é a parte 1 do processo de finalização de compra. Onde apenas geramos a compra no sistema. Não precisamos da noção de um carrinho compra. Apenas temos o usuário logado comprando um produto.

#### Necessidades

- A pessoa pode escolher a quantidade de itens daquele produto que ela quer comprar
- O estoque do produto é abatido 
- Um email é enviado para a pessoa que é dona(o) do produto informando que um usuário realmente disse que queria comprar seu produto.
- Uma compra é gerada informando o status INICIADA e com as seguintes informações:
  - gateway escolhido para pagamento
  - produto escolhido
  - quantidade
  - comprador(a)
  - Valor do produto naquele momento
- Suponha que o cliente pode escolher entre pagar com o Paypal ou Pagseguro.

#### Restrições

- A quantidade é obrigatória
- A quantidade é positiva
- Precisa ter estoque para realizar a compra

#### Resultado esperado

- Caso a pessoa escolha o paypal seu endpoint deve gerar o seguinte redirect(302):
  - Retorne o endereço da seguinte maneira: paypal.com?buyerId={idGeradoDaCompra}&redirectUrl={urlRetornoAppPosPagamento}
- Caso a pessoa escolha o pagseguro o seu endpoint deve gerar o seguinte redirect(302):
  - Retorne o endereço da seguinte maneira: pagseguro.com?returnId={idGeradoDaCompra}&redirectUrl={urlRetornoAppPosPagamento}
- Caso aconteça alguma restrição retorne um status 400 informando os problemas.

------

### Realmente finaliza a compra - parte 2

#### Tag: v.4.4.4

#### Necessidades

O meio de pagamento(pagseguro ou paypal) redireciona para a aplicação passando no mínimo 3 argumentos:

- id da compra no sistema de origem
- id do pagamento na plataforma escolhida
- status da compra. Para o status vamos assumir os dois básicos(Sucesso, Falha). Os gateways de pagamento informam isso de maneira distinta.
- Paypal retorna o número 1 para sucesso e o número 0 para erro.
- Pagseguro retorna a string SUCESSO ou ERRO.

Temos alguns passos aqui.

- Precisamos registrar a tentativa de pagamento com todas as informações envolvidas. Além das citadas, é necessário registrar o exato instante do processamento do retorno do pagamento.
- Caso a compra tenha sido concluída com sucesso:
  - precisamos nos comunicar com o setor de nota fiscal que é um outro sistema. Ele precisa receber apenas receber o id da compra e o id do usuário que fez a compra.
  - Neste momento você não precisa criar outro projeto para simular isso. Crie um controller com um endpoint fake e faça uma chamada local mesmo.
  - também precisamos nos comunicar com o sistema de ranking dos vendedores. Esse sistema recebe o id da compra e o id do vendedor.
  - Neste momento você não precisa criar outro projeto para simular isso. Faça uma chamada local mesmo.
- para fechar precisamos mandar um email para quem comprou avisando da conclusão com sucesso. Pode colocar o máximo de informações da compra que puder.
- Caso a compra não tenha sido concluída com sucesso, precisamos:
  - enviar um email para o usuário informando que o pagamento falhou com o link para que a pessoa possa tentar de novo.

#### Restrição

- id de compra, id de transação e status são obrigatórios para todas urls de retorno de dentro da aplicação.
- O id de uma transação que vem de alguma plataforma de pagamento só pode ser processado com sucesso uma vez.
- Uma transação que foi concluída com sucesso não pode ter seu status alterado para qualquer coisa outra coisa.
- Uma compra não pode ter mais de duas transações concluídas com sucesso associada a ela.