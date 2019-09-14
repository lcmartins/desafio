# Este proejeto consiste da leitura de um arquivo no formato de log e nas seguintes funcionalidades (usecases):

1. exibir o log de movimentações de forma ordenada;
2. informar o total de gastos por categoria;
3. informar qual categoria cliente gastou mais;
4. informar qual foi o mês que cliente mais gastou;
5. quanto de dinheiro o cliente gastou;
6. quanto de dinheiro o cliente recebeu;
7. saldo total de movimentações do cliente.

A idéia foi criar Comands específicos para cada uma das funcionalidades, selecionando o command adequado através do input do usuário na chamada ao jar.

Cada command chama o usecase adequado que aplica as regras e devolve o dado a ser exibido.

A funcionalidade 2 (informar o total de gastos por categoria) e a funcionalidade 4 (informar qual foi o mês que cliente mais gastou) fazem uso do mesmo usecase mudando apenas o agrupamento (mes|categoria).

Para a leitura do arquivo foi criado um repository (FileRepository) que implementa a interface IFileRepository (mesma usada nos testes).

Nos testes criei uma interface de leitura de arquivo que implementa a mesma interface utilizada no FileRepository

Para executar o projeto fazer uma chamada ao jar no console da seguinte forma:
   java -jar movimentacao_bancaria-1.0-SNAPSHOT-shaded.jar 7
   
7 é uma das opções que podem ser passadas para o programa (pode-se passar de 1 a 7).
