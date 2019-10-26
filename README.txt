
- Todas as funcionalidades Pedidas foram implementadas. 

Porém na função roteamento que a classe Roteador implenta, 
eu tive que adicionar mais argumentos para passar
a matriz de roteadores, pois se eu não fizesse isso, seria 
praticamente impossível de saber os roteadores vizinhos daquele roteador,
e assim dizer sua porta de saída. 



- O que eu faria diferente seria a Topologia de Rede, ao invés de ser uma Matriz,
eu utilizaria uma topoplogia Estrela por exemplo, que seria imensamente mais fácil
e prático de implementar a passagem dos pacotes de um roteador para outro, até chegar
no roteador final.

- Não foi utilizada nenhuma biblioteca externa.

No diretorio run/ existe o .jar da aplicação e 
os arquivos necessários para rodar essa aplicação.

Como eu gerei o .jar através da IDE "intellij Idea"
você pode compilar o projeto através de Build -> Build Artifacts... -> build ou
utilizar o .jar do diretório.


- Autor:
	 Marcos Vinicio Araújo Delgado Junior - Tudo.
