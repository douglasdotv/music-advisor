# music-advisor

[![en](https://img.shields.io/badge/lang-en-red.svg)](https://github.com/douglasdotv/music-advisor/blob/master/README.md)
[![pt-br](https://img.shields.io/badge/lang-pt--br-green.svg)](https://github.com/douglasdotv/music-advisor/blob/master/README.pt-br.md)

Este é um aplicativo  que fornece aos usuários acesso a recomendações de diferentes álbuns e playlists 
por meio da [API do Spotify](https://developer.spotify.com/documentation/web-api/).

Este projeto foi criado como parte da trilha de Desenvolvedor Java da [JetBrains Academy](https://hyperskill.org/).

### Funcionalidades
- Visualizar playlists em destaque
- Visualizar novos lançamentos (álbuns)
- Buscar categorias
- Exibir playlists por categoria

### Uso
Se você quiser testar o aplicativo, siga os passos abaixo:

1 - Acesse o site [Spotify for Developers](https://developer.spotify.com/dashboard/login) e crie um aplicativo teste.

Na página dele, você poderá obter o CLIENT_ID e o CLIENT_SECRET que você precisará para testar o music-advisor.

Além disso, você precisará adicionar a seguinte redirect URI ao seu aplicativo: http://localhost:8080.

Para fazer isso, acesse a seção Edit Settings do aplicativo e adicione http://localhost:8080 ao campo _Redirect URIs_.

2 - Clone o repositório e abra-o em um IDE.

Em seguida, acesse a classe MusicAdvisorConfig (dentro do pacote config) e atualize o CLIENT_ID e o CLIENT_SECRET com valores válidos.

3 - Execute o programa a partir da classe Main e digite o comando _auth_ para obter um link de autorização.

Uma vez que você tenha autorizado o aplicativo, obterá acesso aos os seguintes comandos:

- featured - Visualizar as playlists em destaque
- new - Visualizar os novos lançamentos
- categories - Visualizar playlists por categoria
- playlists [playlist_name] - Buscar playlists específicas. Substitua [playlist_name] pelo nome da playlist que você está procurando.
- prev/next - Você pode usar esses comandos para navegar pelas diferentes páginas de conteúdo (visto que cada comando exibe uma quantidade limitada de itens por página).
- exit - Sair do aplicativo

### O que eu aprendi ao fazer este projeto
Ao fazer este projeto, aprendi a trabalhar com OAuth2 para interagir com a API do Spotify e apliquei alguns padrões de projeto.