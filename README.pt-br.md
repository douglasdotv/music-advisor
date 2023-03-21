# music-advisor

[![en](https://img.shields.io/badge/lang-en-red.svg)](https://github.com/douglasdotv/music-advisor/blob/master/README.md)
[![pt-br](https://img.shields.io/badge/lang-pt--br-green.svg)](https://github.com/douglasdotv/music-advisor/blob/master/README.pt-br.md)

Este é um aplicativo que fornece aos usuários acesso a recomendações de diferentes álbuns e playlists por meio da [API do Spotify](https://developer.spotify.com/documentation/web-api/).

### Funcionalidades
- Autenticação via OAuth2
- Obter autorização do Spotify para acessar a API
- Ver playlists em destaque, playlists por categoria e novos lançamentos de álbuns com base nas preferências do usuário

**Exemplo:**
![-](./screenshots/musicadvisor.jpg)

### Tecnologias utilizadas
- Java 17
- Gradle
- [Spotify Web API](https://developer.spotify.com/documentation/web-api/)

### Habilidades desenvolvidas ao longo do projeto
- Utilizar o protocolo OAuth2 para autenticar usuários
- Utilizar a API do Spotify Web para obter informações sobre playlists, álbuns, artistas e categorias com base nas preferências do usuário
- Utilizar a classe `HttpClient` para fazer requisições HTTP
- Realizar o parsing de JSONs obtidos via requisições HTTP
- Aplicar o padrão MVC para separar a aplicação em três camadas: model, view e controller
- Aplicar o padrão _strategy_ para lidar com diferentes tipos de _inputs_ do usuário
- Paginar resultados de requisições HTTP feitas à API do Spotify

### Como rodar o projeto
Para rodar a aplicação, é necessário:

- JDK, JRE e Gradle instalados
- Uma conta de usuário do Spotify

Em seguida, siga os passos abaixo:

##### **1**)
Acesse o site [Spotify for Developers](https://developer.spotify.com/dashboard/login) e crie um aplicativo teste.

Na página dele, é possível obter um Client ID e um Client Secret, necessários para testar o projeto.

Além disso, será necessário adicionar a seguinte redirect URI ao seu aplicativo: http://localhost:8080.

Para fazer isso, acesse a seção _Edit Settings_ do aplicativo e adicione http://localhost:8080 ao campo _Redirect URIs_.

##### **2**)
Abra o terminal e clone este repositório:
```
git clone https://github.com/douglasdotv/music-advisor.git
```

##### **3**)
Na pasta raiz do projeto, navegue até o seguinte local:

_\src\main\java\br\com\dv\advisor\config_

Em seguida, abra o arquivo _MusicAdvisorConfig.java_ com o seu editor de texto preferido e atualize CLIENT_ID e CLIENT_SECRET com os valores obtidos no site _Spotify for Developers_ ao criar um aplicativo teste. (Observe que os valores inicialmente presentes no arquivo são apenas exemplos e não funcionam.)

##### **4**)
Navegue até a pasta raiz do projeto, gere a build e rode o projeto:
```
./gradlew build
java -cp build/libs/music-advisor-1.0-SNAPSHOT.jar br.com.dv.advisor.Main
```

##### **5**)
Digite o comando _auth_ e pressione Enter para obter um link de autorização. Em seguida, acesse o link e autorize o aplicativo.
Concedida a autorização, será possível utilizar os comandos abaixo para interagir com o aplicativo:

- featured – Visualizar as playlists em destaque
- new – Visualizar os novos lançamentos
- categories – Visualizar playlists por categoria
- playlists [playlist_name] – Buscar playlists específicas (substitua [playlist_name] pelo nome da playlist que você está procurando)
- prev/next – Esses comandos permitem a navegação pelas diferentes páginas de conteúdo (cada um dos comandos anteriores exibe uma quantidade limitada de itens por página)
- exit – Sair do aplicativo

### Contato
Se houver quaisquer dúvidas ou sugestões, sinta-se à vontade para entrar em contato comigo através do [LinkedIn](https://www.linkedin.com/in/douglasdotv/) ou via e-mail (douglas16722@gmail.com).
