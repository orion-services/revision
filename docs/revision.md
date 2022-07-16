# Revision Web Service 
## Serviço para revisão de exercício de programação realizado no Github e registo de nota no Moodle.
### Alexandre de Mesquita Fabian, Antonio Paulo Serpa Antunes
### Orientador: Dr. Rodrigo Prestes Machado

## 1. Introdução
A crescente demanda para utilização de tecnologia na área da educação é algo que esbarra na utilização de ferramentas ultrapassadas para o ensino ou que não são utilizadas pelo mercado de trabalho. É necessário mais do que transferir conteúdo do espaço presencial para o virtual. (CORBELLINI, 2021) O Revision Web Service foi criado para integrar a ferramenta Moodle, amplamente utilizada por instituições de ensino, com a plataforma Github, o maior repositório de código fonte do mundo.

O Moodle é um software de código aberto utilizado como ambiente de aprendizagem, possibilitando interação entre os usuários e realização de atividades. Foi desenvolvido em 1999 e desde então recebe constante desenvolvimento. (WERKA, 2021)

O Github é uma plataforma de desenvolvimento utilizada por milhões de desenvolvedores e empresas para fazer, divulgar e manter seus softwares. É baseado na ferramenta Git que é um sistema de controle de versões distribuído de código aberto e livre criado inicialmente pelo desenvolvedor Linus Torvalds para o desenvolvimento do Linux.

Web Service é uma solução utilizada na integração de sistemas e na comunicação entre aplicações diferentes. Com esta tecnologia é possível que novas aplicações possam interagir com aquelas que já existem e que sistemas desenvolvidos em plataformas diferentes sejam compatíveis entre si.

A motivação para a criação do Revision Web Service advém da necessidade de facilitar a correção e aplicação da nota no Moodle dos exercícios de programação realizados pelos alunos utilizando a plataforma Github. Através de ferramenta específica do Github é possível criar tarefas e automatizar a avaliação das mesmas e utilizando-se do Revision Web Service será possível publicar o resultado desta avaliação na plataforma de ensino Moodle.

A linguagem de programação JAVA e o framework Quarkus são utilizados para o desenvolvimento do serviço web, bem como disponibilizado seu código fonte na plataforma Github

## 2. Fundamentação Teórica
O ambiente virtual de aprendizagem Moodle foi escolhido por ser amplamente utilizado em instituições de ensino e possuir ferramentas que possibilitam sua integração com o Revision Web Service, como seu Web Server REST.
O repositório de projetos Github é uma escolha natural para a disponibilização de atividades por parte dos educadores, pois possui ferramentas para verificação do resultado de testes, controle de versões e uma API pública que disponibiliza todas as informações necessárias sobre os projetos armazenados em seu serviço. 

**2.1. Moodle**

De acordo com Dascalu et al. (2021), o início do COVID-19 mudou a educação on-line de um complemento ou alternativa para uma necessidade. Muitos, se não a maioria dos educadores, foram forçados a transformar suas atividades de aprendizagem presencial e se adaptar às modalidades de aprendizagem online. Consequentemente, os ambientes virtuais de aprendizagem se tornaram indispensáveis para alunos e professores durante a pandemia de COVID-19.
    Avaliar alunos em ambientes online é uma tarefa demorada e  desafiadora, especialmente em disciplinas de programação em cursos da área de computação, que costumam ser numerosas, exigindo bastante do docente, que muitas vezes não consegue acompanhar individualmente os alunos de maneira eficiente.
Por ser um ambiente extensível e completo em termos de recursos para gerenciamento de atividades educacionais, o Moodle apresenta-se como ambiente propício para integrar ferramentas que dêem suporte ao processo de ensino e aprendizagem em disciplinas de programação. (MACIEL, 2013)

**2.2. GIT e Github**

Git é um sistema de controle de versão, isto é, um software projetado para acompanhar as alterações feitas nos arquivos ao longo do tempo. Além disso, o controle de versão é distribuído, o que significa que todos os usuários têm uma cópia do histórico completo do projeto, não apenas o estado atual dos arquivos. 
Existem vários benefícios em usar o Git, mesmo para quem não irá utilizar a ferramenta sozinho ou apenas para editar arquivos de texto, tais como a capacidade de desfazer alterações, ter um histórico completo de todas as alterações, poder documentar o por que as alterações foram realizadas e a confiança para alterar qualquer coisa, pois em caso de erro pode-se facilmente retornar para um estado válido.
De acordo com Bell e Beer (2014), GitHub é um site onde é possível fazer upload de uma cópia do seu repositório Git. Ele permite que você colabore com outras pessoas em um mesmo projeto. O Github fornece um local centralizado para compartilhar o projeto, uma interface baseada na web para visualizá-lo e recursos como bifurcação, pull requests, issues e wikis, que ajudam no gerenciamento de alterações, correções de bug, implementações de novos recursos e divulgação do trabalho realizado entre todos os envolvidos.



## 3. Escolhas metodológicas
Como padrão de desenvolvimento, foi escolhido o Chain of Responsability. E para a implementação, um Restful Web Service na linguagem JAVA juntamente com o framework Quarkus e suas diversas extensões.

**3.1. Chain of Responsability**

É um padrão de projeto que permite que se passe requisições por uma corrente de handlers. Em cada um desses handlers, após receber a requisição, é decidido se passa adiante o pedido, para o próximo membro da corrente, ou se ela é quebrada. (Shvets, 2019)
Cada handler é responsável por uma checagem, assim, fazendo uma pequena parte do trabalho, é possível quebrar a complexidade do problema e desenvolver partes menores, mais simples e fáceis de implementar e manter.

**3.2. Restful Web Service**

Restful Web Services são serviços web que implementam toda a especificação REST, que é uma arquitetura que especifica recursos e um número limitado de operações que são acessíveis através de  links na web. Tornando os serviços web mais simples, leves e rápidos.
    
**3.3. Linguagem de Programação JAVA**

Java é uma linguagem de programação orientada a objetos, criada na Sun Microsystem, atualmente Oracle, na década de 1990. Desde 2007 possui seu código fonte sob licença livre.
A linguagem Java é uma das escolhas naturais para projetos web, tendo sido desenvolvida e pensada com recursos que facilitam o desenvolvimento de aplicações web, como um Restful Web Service.

**3.4 Framework Quarkus**

Framework rápido e leve desenvolvido pela Red Hat, atualmente parte da IBM, com foco em novas e atualizadas tecnologias, que oferece baixo uso de memória e inicializações rápidas. Também oferece otimizações em tempo de desenvolvimento, diminuindo o tempo gasto nas configurações e na criação de aplicações web.


## 4. Referências
CORBELLINI, Silvana. Aprendizagens e tecnologias. In: CORBELLINI, Silvana (org.). Orientação educacional: registros de um percurso de formação. Porto Alegre: Forma Diagramação, 2021. p. 27-42.

WERKA, Hellen Meiry Grosskopf. Moodle acadêmico. In: NIENOV, Otto Henrique; CAPP, Edison. Estratégias didáticas para atividades remotas. Porto Alegre: Universidade Federal do Rio Grande do Sul, Programa de Pós-Graduação em Ciências da Saúde: Ginecologia e Obstetrícia, 2021. p. 77-92.
DASCALU, Maria-Dorinela et al. Before and during COVID-19: a cohesion network analysis of students’ online participation in moodle courses. Computers In Human Behavior, [S.L.], v. 121, n. 106780, p. 1-19, ago. 2021. Elsevier BV. Doi: http://dx.doi.org/10.1016/j.chb.2021.106780. Disponível em: https://www.sciencedirect.com/science/article/pii/S0747563221001035. Acesso em: 23 jun. 2022.

MACIEL, Danilo Leal; FRANÇA, Allyson Bonetti; SOARES, José Marques. Sistema de apoio a atividades de laboratório de programação via Moodle com suporte ao balanceamento de carga e análise de similaridade de código. Revista Brasileira de Informática na Educação, [S.l.], v. 21, n. 01, p. 91, abr. 2013. ISSN 2317-6121. Disponível em: http://ojs.sector3.com.br/index.php/rbie/article/view/1435/2122. Acesso em: 22 jun. 2022. doi: http://dx.doi.org/10.5753/rbie.2013.21.01.91.

BELL, Peter; BEER, Brent. Introducing GitHub: a non-technical guide. Sebastopol, California: O'Reilly Media, 2014. 142 p.

SHVETS, Alexander. Dive Into Design Patterns. Kamianets-Podilskyi, Ucrânia. Refactoring.Guru, 2019. 409 p.
