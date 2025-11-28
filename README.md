# GoalD Framework & Tele Assistance System (TAS) Case Study

Este repositório contém o ambiente completo para execução do framework **GoalD** (Goal-Driven Deployment) e o estudo de caso **TAS** (Tele Assistance System), configurado para rodar em sistemas Linux modernos.

O projeto integra componentes legados (Java 8, OSGi, Eclipse Neon) com ferramentas de build (Maven), incluindo correções manuais de dependências.

---

## 📋 Pré-requisitos

Para executar este projeto, seu ambiente **deve** atender aos seguintes requisitos estritos:

* **Sistema Operacional:** Linux (Testado no Ubuntu 22.04+).
* **Java JDK:** Versão **1.8 (Java 8)**.
    * *Atenção:* Versões superiores (11, 17, 21) **não funcionarão**.
* **Apache Maven:** Instalado e configurado para usar o Java 8.
* **IDE:** Eclipse IDE (Recomendado: Neon ou Oxygen, mas versões recentes funcionam com ajustes de GTK).

---

## 🛠️ Instalação e Configuração

### 1. Preparação do Sistema (Terminal)

Instale o Java 8 e o Maven, e garanta que o Maven use a versão correta do Java.

```bash
# Instalar Java 8 e Maven
sudo apt update
sudo apt install openjdk-8-jdk maven git -y

# Verificar versão (Deve retornar 1.8.x)
java -version
mvn -version
```
### 2. Clonar o Repositório

```bash
git clone https://github.com/sr-bauducco/goald-osgi-study-session.git
cd goald-osgi-study-session
```
### 3. Build Inicial (Maven)
```bash
Antes de abrir o Eclipse, force a compilação dos projetos principais para gerar os arquivos .jar necessários.
Bash

# Navegue até a pasta do GoalD
cd goald

# Compile o núcleo ignorando testes (para evitar erros de ambiente)
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
mvn clean install -DskipTests
```
## 💻 Configuração da IDE (Eclipse)

Devido à natureza híbrida do projeto (Maven + Plugins OSGi antigos), a importação exige passos manuais.

### Passo 1: Importar Projetos

    Abra o Eclipse.

    Vá em File > Import > Maven > Existing Maven Projects.

        Selecione a pasta raiz goald.

        Importe todos os módulos (incluindo tele-assistance-case-study).

    Vá em File > Import > General > Existing Projects into Workspace.

        Selecione a pasta raiz OSGi.

        Marque a opção Search for nested projects.

        Importe todos os subprojetos encontrados.

### Passo 2: Configurar Dependências Manuais (Libs)

O projeto org.goald.osgi.intelligence possui dependências externas que foram incluídas na pasta libs deste repositório para facilitar.

    No Eclipse, localize o projeto org.goald.osgi.intelligence.

    Verifique se a pasta libs contém dois arquivos .jar (goald-core...jar e evaluation-commons...jar).

    Selecione ambos os arquivos .jar, clique com o botão direito > Build Path > Add to Build Path.

### Passo 3: Ajustes Finais

    Erro no Filling Station: Se o projeto filling-station-case-study apresentar erros, clique com o botão direito e selecione Close Project. Ele não é necessário para o TAS.

    Compliance Level: Se houver erros de @Override, vá nas propriedades dos projetos afetados (ex: SendSMS-impl) e mude o Java Compiler para 1.8.

## 🚀 Como Executar a Simulação

O ponto de entrada da simulação é a classe TASMain.

    No Eclipse, localize o projeto tele-assistance-case-study.

    Navegue até: src/main/java > goald.evaluation.tas > TASMain.java.

    Criar pasta de resultados:

        O código exige uma pasta para salvar os logs.

        Clique com botão direito no projeto tele-assistance... > New > Folder > Nomeie como result.

    Executar:

        Clique com botão direito em TASMain.java > Run As > Java Application.

        Se solicitado, escolha a configuração Timeline.

### Verificando os Resultados

Após a execução, atualize a pasta result (F5). Um arquivo de dataset será gerado contendo a evolução da Utilidade e Custo do sistema ao longo do tempo.

### 📂 Estrutura do Repositório

    /goald: Código fonte do Framework GoalD (Maven).

        goald-core: Núcleo do sistema de adaptação.

        tele-assistance-case-study: Simulação médica (Main).

    /OSGi: Bundles e componentes simulados (Sensores, Atuadores).

        org.goald.osgi.intelligence: Módulo de inteligência com as bibliotecas manuais (libs).

Autor do Backup: Israel Teles Bandeira, graduando em Engenharia da Computação - Universidade de Brasilia/UnB. Baseado nos trabalhos originais do Laboratório de Engenharia de Software da UnB.
