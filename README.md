# BestTools

O BestTools é um plugin Spigot que seleciona automaticamente a melhor ferramenta para quebrar um determinado bloco, considerando que o mesmo bloco possa ser quebrado por diferentes ferramentas. Ele oferece uma solução prática e eficiente para os jogadores, otimizando a sua experiência de mineração e coleta de recursos.

## Download
[Download BestTools](blob:https://github.com/baed8d3b-9beb-4db5-88a7-9c5fe4cb338c)

## Comandos

O plugin BestTools possui os seguintes comandos:

### addBlock

O comando "addBlock" permite adicionar um bloco à lista dos blocos que podem ser quebrados por uma determinada ferramenta. Esse comando deve ser executado enquanto o jogador estiver segurando a ferramenta desejada e olhando para o bloco. Após a execução bem-sucedida deste comando, sempre que o jogador tentar quebrar esse tipo de bloco, o plugin selecionará automaticamente a melhor ferramenta disponível.
Comando só pode ser executado pelo administrador do BestTools.

**Uso:** /addBlock

### Comando "removeBlock"

O comando "removeBlock" permite remover um bloco da lista dos blocos que podem ser quebrados por uma determinada ferramenta. Isso significa que, caso o jogador esteja segurando essa ferramenta, o plugin não a selecionará automaticamente para quebrar o bloco específico.
Para remover o bloco da lista olhe para o bloco e digite o comando.
**Comando só pode ser executado pelo administrador do BestTools.**

**Uso:** /removeBlock

### Comando "addadm"

O comando "addadm" permite adicionar um jogador como administrador do plugin BestTools. Os administradores têm permissão para executar todos os comandos do plugin e gerenciar as configurações.
**Para adicionar o administrador precisa ser Operador do servidor.**

**Uso:** /addadm <nome do jogador>

### Comando "removeadm"

O comando "removeadm" permite remover um jogador da lista de administradores do plugin BestTools. Isso restringirá o acesso do jogador aos comandos e configurações do plugin.
**Para remover o administrador precisa ser Operador do servidor.**

**Uso:** /removeadm <nome do jogador>

### Comando "activated"

O comando "activated" permite ativar ou desativar o funcionamento do plugin BestTools. Quando definido como "true", o plugin selecionará automaticamente a melhor ferramenta para quebrar o bloco. Quando definido como "false", o plugin não fará a seleção automática e os jogadores precisarão usar as ferramentas manualmente.

**Uso:** /activated <true|false>


## Conclusão

O plugin BestTools é uma solução eficiente para automatizar a seleção da melhor ferramenta ao quebrar diferentes blocos. Com seus comandos intuitivos, configurações personalizáveis e lista de administradores, o plugin melhora a jogabilidade e a experiência de mineração dos jogadores em seu servidor.