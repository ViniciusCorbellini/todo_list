# 📋 Sistema de Gerenciamento de Tarefas

Este sistema Java com interface gráfica (Swing) permite o gerenciamento de tarefas, grupos de tarefas e desafios, com geração de relatórios em PDF e controle por perfil de usuário.

---

## ✅ Funcionalidades Principais

- Cadastro e login de usuários
- Criação, edição e exclusão de tarefas
- Organização de tarefas por grupo
- Atribuição de tarefas a desafios
- Geração de relatórios em PDF (tarefas e desafios)
- Controle de acesso por perfil (usuário comum e administrador)
- Interface gráfica amigável desenvolvida com Swing

---

## 🧩 Estrutura de Pacotes

- `model` – Classes de domínio: `Usuario`, `Tarefa`, `GrupoTarefas`, `Desafio`
- `dao` – Camada de persistência: `UserDAO`, `TarefaDAO`, `GrupoDAO`, `DesafioDAO`, `TarefaDesafioDAO`, `ConnectionDAO`
- `controller` – Camada de controle: `TodoController`
- `view` – Telas da aplicação (Swing): `Login`, `Dashboard`, `Desafios`, `Grupos`, `Tarefas`, etc.
- `report` – Geração de relatórios PDF
- `exceptions` – Exceções personalizadas
- `images` – Ícones e elementos visuais da interface

---

## ⚙️ Requisitos e Execução

### Requisitos
- Java JDK 8 ou superior
- MySQL Server
- Driver JDBC, FlatLaf, ItextPDF, MySQL-Connector  (incluso no classpath via NetBeans)
- IDE recomendada: **NetBeans**

### Como executar
1. Abra o projeto `Trabalho3` no NetBeans.
2. Verifique as configurações do banco de dados em `ConnectionDAO.java`.
3. Compile o projeto (F11).
4. Execute a aplicação (F6).
5. Faça login e utilize as funcionalidades conforme seu perfil.

---

## 💾 Configuração da Base de Dados

A conexão com o banco está configurada em:

```java
dao/ConnectionDAO.java
