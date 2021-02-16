# Desenvolvedor Android - Processo seletivo Hotmart

O projeto em questão foi desenvolvido seguindo conceitos de Clean Architecture + MVVM, utilizando a linguagem Kotlin. Cada módulo do aplicativo é designado para sua respectiva função, facilitando assim a testabilidade e manutenibilidade do projeto. 


## Instalando / Getting started

Clonar o projeto e abrir utilizando a IDE de preferência. (recomendação: Android Studio)
Sincronizar as dependências necessárias. As dependências podem ser visualizadas no arquivo "dependencies.gradle" que está presente no diretório raíz do projeto.
Compilar e executar o projeto em um emulador ou dispositivo físico Android.


## Arquitetura

A imagem abaixo exemplifica a estrutura adotada no projeto.

![alt text](https://github.com/thomasmarquesbr/hotmart-mobile-app/blob/main/architecture.png?raw=true)

- **Presentation:** Todo o módulo responsavel pelas views, controllers e manipulação de componentes visíveis aos usuários.
- **Domain:** Módulo responsável por concentrar as regras de negócio do App, bem como os modelos e UseCases de interação com API REST. O Presentation se comunicará apenas com o módulo Domain que irá abstrair a complexidade de lidar com serviços REST e banco de dados no App.
- **DataRepository:** Módulo responsável por fornecer os dados ao módulo Presentation. O Presentation não deve ter conhecimento de onde vem os dados, pois quem irá decidir onde buscar será o DataRepository. O Domain solicitará os dados para o Data Repository que por sua vez irá decidir entre requisitar os dados para o RemoteRepository ou StorageRepository (ou ambos) e entregará esses dados para o Domain ao final do processo.
- **RemoteRepository:** Módulo responsável por realizar as requisições a API. O módulo RemoteRepository concentra a complexidade de lidar com APIs remotas, bem como suas rotas e definições de parâmetros, headers e corpo nas requisiçôes.
- **StorageRepository:** Módulo responsável por concentrar as operações com o banco de dados interno do App ou suas estruturas de armazenamento em cache. O módulo irá expor ao DataRepository os tipos de operações permitidas e entregará tais dados para uso para os níveis mais elevados da aplicação.

## Demo

![](demo.gif)

## Autor

Thomás Marques Brandão Reis - 
[Site](https://thomasmarques.com.br), 
[Linkedin](https://www.linkedin.com/in/thomasmarquesbr/)
