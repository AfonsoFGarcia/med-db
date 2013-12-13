SIRS 2013/14 - Medical Database Records - 28
======

O projecto tem como objectivo a criação de um sistema electrónico de armazenamento de registos médicos com base nos seguintes princípios:

- Criação/estruturação de uma base de dados médica;
- Definição de politicas de acesso com base em autenticação;
- Garantia de Confidencialidade e Integridade da informação;
- Definição de políticas de acesso aos registos com base em roles personalizáveis/dinâmicos;

O Med-DB usa a Fénix Framework como abstracção entre o mundo relacional e o mundo dos objectos.

**IMPORTANTE - LER**
-----

**13/12/2013 - Por questoes de optimizacao, o modo como as passwords sao guardadas foi alterado, e *obrigatorio* apagar a base de dados.**

11/12/2013 - A forma como as permissoes sao serializadas foi alterada, e *obrigatorio* apagar a base de dados!

09/12/2013 - Devido a alteração no modelo de domínio é necessário limpar a base de dados!

Começo Rápido
-----

Ciclo rápido: `mvn clean package exec:java -Dexec.mainClass="pt.ist.sirs.application.MedDBApp" -Dexec.args="<pasta da chave>"`

Compilar domínio: `mvn dml:generate-domain` (apenas é necessário depois de alterar a dml)

Compilar projecto: `mvn package`

Preencher base de dados: `mvn exec:java -Dexec.mainClass="pt.ist.sirs.application.PopulateDB"`

Correr projecto: `mvn exec:java -Dexec.mainClass="pt.ist.sirs.application.MedDBApp" -Dexec.args="<pasta da chave>"`

Limpar base de dados: `mvn sql:execute`

Software Necessário
-----

Maven 3.0.5 - http://maven.apache.org/download.cgi

Java 7 SDK - http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html

MySQL 5 - http://dev.mysql.com/downloads/mysql/

### Instalação do software em Ubuntu/Debian

	wget --quiet https://raw.github.com/davidmartinho/pillow/develop/pillow.sh && sudo bash pillow.sh && rm -rf pillow.sh && source .bashrc
	
Software Recomendado
-----

Eclipse Kepler - http://www.eclipse.org/downloads/packages/eclipse-ide-java-developers/keplersr1

MySQL Workbench - http://dev.mysql.com/downloads/tools/workbench/
