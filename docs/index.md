# Serviço de Revisão

Esse é um serviço utilizado para revisar se um estudante realizou um exercício no Github e, no caso de sucesso, permite registrar o resultado em um sistema Moodle.

## Sequência de revisão

Atualmente o serviço de revisão implementa uma cadeia de verificações (Checkers) antes de submeter uma avaliação no Moodle:

* Verifica se o nome e sobrenome do usuário do Github é o mesmo do Moodle (MoodleChecker)
* Confere se o repositório informado é o mesmo contigo na atividade e se esse repositório é um fork do original (RepositoryChecker)
* Analisa se a última execução dos testes passou com sucesso (RepositoryChecker)
* Verifica em todos os commits do usuário se em algum os testes foram alterados (TestChangeChecker)

## Configuração do Moodle

Para a integração será necessário criar um web service dentro do Moodle. Sugerimos que sejam seguidos os passos da página Site Administration > Server > Web services > Overview (disponível geralmente em http://endereco-do-moodle/admin/settings.php?section=webservicesoverview). Os passos são:

1. Enable web services - habilitar o web service
2. Enable protocols - habilitar o *REST protocol*
3. Create a Specific User - criar um usuário apenas para acessar o webservice. Preencher os campos obrigatórios (nome, e-mail) e selecionar como método de autenticação a opção *Web services authentication*
4. Check user capabilities - neste trecho é preciso interromper os passos da configuração do web service e ir na área de administração de usuários e vincular o web service a um perfil de usuário. É possível criar um perfil apenas para o webservice. **Importante:** o serviço precisa ter a permissão Use *REST Protocol* habilitada

5. Select a service - neste momento criamos o web service Clique em Add, defina o nome do serviço e marque as opções Enabled e Authorised users only
6. Add functions - aque iremos selecionar as funções do Moodle que o serviço terá acesso. Libere, uma a uma, as seguintes funções:

* core_webservice_get_site_info
* core_course_get_course_module
* core_course_get_enrolled_users_by_cmid
* core_user_get_users
* mod_assign_get_assignments
* mod_assign_save_grade

7. Select a specific user - No serviço que você criou você deverá vincular o usuário do web service criado anteriormente. Clique em Authorized Users e adicione apenas o usuário do web service na lista de usuário com acesso permitido.
8. Create a token for a user - Selecione o usuário do web service, o nome do serviço criado e clique em *Save Changes*. Anote o token. Se desejar ampliar a segurança do web service é possível nesta tela fazer a restrição do IP e definir um período de validade do token.
9. Enable developer documentation - Habilite a documentação do web service do Moodle.

## Configuração da aplicação

Edite o arquivo /src/main/resources/application.properties inserindo o endereço do webservice e seu respectivo token.

## Configuração de uma atividade no Moodle

**Importante:** Para que o web service consiga acessar as tarefas e lista de alunos de um determinado curso, o usuário do web service criado nos passos acima precisará estar inscrito no curso.

### Passo 1

O primeiro passo é configurar uma atividade (*Assign*) para que possa ser utilizada com o serviço de revisão. Existem três informações importantes que necessitam serem informadas pra o serviço, são elas: repo (repositório do exercício), workflow (o arquivo do Github Actions) e o arquivo que implementa os testes (nota: atualmente todos os testes devem ficar num único arquivo). Estas informações devem seguir o formato abaixo:

```html
<!--
    repo: "cpw2-web-storage"
    workflow: "npm-test.yml"
    test-file: "correcao.js"
-->
```

Esta configuração deve ser informada em uma atividade no Moodle como um comentário de HTML (use a opção Edit HTML Source) e o conteúdo acima precisa estar em formato YAML.

A seguir anote o número do assign, que consta na URL da tarefa. Por exemplo, se o link do assign fosse o endereço abaixo:

http://endereco-do-moodle/mod/assign/view.php?id=4135

Anote o código do assing.

### Passo 2

Configurada a tarefa, é preciso inserir no Moodle um link para que os alunos possam acessar o Revision e postarem os seus repositórios com a tarefa finalizada.

Crie uma nova atividade, do tipo URL. Ela deve apontar para o endereço do frontend do Revision. Após digitar a URL, coloque ainda um parâmeto assign com o número da tarefa, de forma que o link fique da seguinte forma:

http://endereco-do-revision/?assign=4135

Clique na opção URL Variables e insira ali um parâmetro. O nome do parâmetro precisa se chamar id. O valor deste parâmetro de ver ser o id do User. Desta forma o Moodle passará para o Revision automaticamente a id do estudante matriculado que submeter sua tarefa.

Salve o link.

O link gerado deverá ficar com o formato abaixo:

http://endereco-do-revision/?assign=4135&id=2123

Repare que para funcionar, o link precisará de dois parâmetros: o número do assign, que precisa ser inserido manualmente no cadastro da URL e o número do id do aluno, que é inserido automaticamente pelo Moodle, e será diferente para cada aluno.

Para cada tarefa que usar o Revision é preciso criar um nova URL.
