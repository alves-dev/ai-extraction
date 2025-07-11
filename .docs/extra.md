O objetivo do projeto é entender os possíveis potências dos LLM.

### Caso de uso
Dado um áudio, o transformar em texto, e depois extrair dele um `json`
contendo as informações; logicamente com o json em mãos sua criatividade é o limite.

### Como foi feito
Para auxiliar no processo de desenvolvimento decidi usar o projeto [Spring AI](https://spring.io/projects/spring-ai)
que disponibiliza uma série de classes prontas para lidar com vários modelos, dentre eles os modelos da [OpenAI](https://openai.com/).

Para a transcrição do áudio estou usando o modelo `whisper-1`, e para a análise do texto uso o `gpt-3.5-turbo-0125`.

### Detalhes de implementação
Nao tem segredo para realizar a transcrição, basta enviar o áudio para a API utilizando a classe `OpenAiAudioTranscriptionClient`
e voce ira obter o texto de retorno.

Já para a extração do `json` voce deve usar a `OpenAiChatClient`; e funciona assim:

Primeiramente crie o seu prompt, como algo assim:
```java
private static final String POMPT_STRING = """
            Quero que você extraia do texto: {text}
            as seguintes intenções: {intents}
            apenas caso encontre elas no texto.
                        
            {format}
            """;
```
Injete o texto extraído e suas `intents`.

Com a ajuda do spring você cria um `BeanOutputParser`, esta é a classe que representa
qual o tipo de retorno você desejá, então no lugar de `{format}`
o spring injeta uma descrição informando que o retorno dado pelo LLM deve ser apenas um `json` valido
e nada mais do que isso, depois é só converter a string em json para o seu objeto.

### Possíveis evoluções
Como evolução do projeto você poderia rodar algum modelo disponível na sua própria infra (eu até tentei, mas minha maquina pediu para sair).

O projeto foi construído de uma forma que os modelos utilizados possam ser substituídos facilmente,
que tal tentar outros modelos no mercado?

E que tal treinar o seu próprio modelo?
O projeto salva todas as interações no banco de dados [MongoDB](https://www.mongodb.com/pt-br),
assim voce tem os dados que podem ser utilizados para tal.