# StarWarsAPI

Api rest para armazenar dados de planetas, com a quantidade de aparições de cada planeta nos filmes de Star Wars.
As informações sobre os planetas do universo Star Wars, é resgatada através da API aberta https://swapi.co/

__Funcionalidades:__

- Adicionar um planeta (com nome, clima e terreno)
- Listar planetas do banco de dados
- Listar planetas da API do Star Wars
- Buscar por nome no banco de dados
- Buscar por ID no banco de dados
- Remover planeta

O projeto foi desenvolvido em Java 8 e spring boot. Para persistência foi utilzado o MongoDB. 

Para rodar o projeto, primeiramente o MongoDB deve estar instalado e em execuação, e instalado o Java na versão 8.

__Eclipse__

Para rodar o projeto no eclipse, importe no eclipse e rode a aplicação.

__Maven__

Para rodar no maven, deve ter instalado o Apache Maven.
Dentro da pasta onde está o arquivo pom.xml, executar o comando: mvn clean install
Na pasta target, executar o comando: java -jar planetas-starwars-0.0.1-SNAPSHOT.jar

A aplicação estará rodando e pode ser utilzada pelo caminho http://localhost:8080/api

Obs.: Caso deseje alterar a porta, altere no arquivo application.properties.
 

__Requisições:__

- Para adicionar um planeta, faça uma requisição post em json para o endpoint "/planeta".
  
  __[POST]__
  Ex.: http://localhost:8080/api/planeta
  
  Os campos nome, clima e terreno são obrigatórios.
  
  Caso o planeta pertença ao universo Star Wars, a quantidade de aparições em filmes é obtida pela api do Star Wars na inserção do planeta.
  
  

  
  ```json
  {
	"nome": "Alderaan",
	"clima":"temperate",
	"terreno":"mountains"
  }
  ```
 
- Para listar os planetas do banco de dados, faça uma requisição get para o endpoint "/planeta".
  
  __[GET]__
  Ex.: http://localhost:8080/api/planeta
  

- Buscar um planeta por id no banco de dados, faça uma requisição get para o endpoint "/planeta/{id}", com o id que deseja pesquisar.
  
  __[GET]__
  Ex.: http://localhost:8080/api/planeta/5e62593222504422ae9254ab
  

- Buscar um planeta por nome no banco de dados, faça uma requisição get para o endpoint "/planeta/buscarPorNome/{nome}", com o nome que deseja pesquisar.
  
  __[GET]__
  Ex.: http://localhost:8080/api/planeta/buscarPorNome/Alderaan


- Para remover um planeta do banco de dados, faça uma requisição delete para o endpoint "/planeta/{id}, com o id do registro que deseja deletar".
  
  __[DELETE]__
  Ex.: http://localhost:8080/api/planeta/5e62593222504422ae9254ab


- Para listar os planetas do universo Star Wars, faça uma requisição get para o endpoint "/planetastarwars".
  
  __[GET]__
  Ex.: http://localhost:8080/api//planetastarwars


