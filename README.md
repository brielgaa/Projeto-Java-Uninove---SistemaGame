# 🎮 SistemaGame – Gerenciador de Jogos e Clientes  

![Java](https://img.shields.io/badge/Java-21-orange?logo=java&logoColor=white)
![Swing](https://img.shields.io/badge/Interface-Swing-blue?logo=appveyor)
![Status](https://img.shields.io/badge/Status-Completo-brightgreen)
![License](https://img.shields.io/badge/Licença-Acadêmica-lightgrey)

---

## 🖥️ Visão Geral  
**SistemaGame** é uma aplicação Java com interface gráfica moderna (Swing) que permite gerenciar **jogos, clientes e vendas** de forma simples e intuitiva.  
Possui **modo escuro**, **salvamento automático** e **descontos exclusivos** para clientes premium.  

> 💾 Todos os dados são armazenados localmente em arquivos `.dat` e carregados automaticamente ao iniciar o sistema.  

---

## 🧩 Funcionalidades  

### 🎮 Jogos  
- Cadastrar, editar, atualizar status e excluir jogos.  
- Exibição em tabela estilizada.  

### 👤 Clientes  
- Cadastrar clientes **regulares** e **premium (15% de desconto)**.  
- Atualizar saldo, editar dados e remover.  

### 💰 Vendas  
- Realizar vendas automáticas com cálculo de desconto.  
- Atualização instantânea do saldo e status do jogo.  

### 📊 Histórico  
- Consultar todas as vendas realizadas ou filtrar por cliente.  
- Exibição com data e hora formatadas.  

### 💵 Saldo  
- Adicionar saldo facilmente a qualquer cliente.  

---

## 🎨 Interface  

✨ **Modo escuro moderno** com detalhes azuis e textos brancos.  
🧭 **Menu lateral interativo** com animações de hover.  
💬 **Caixas de diálogo estilizadas** (JOptionPane com texto branco).  
🔴 **Botão de saída personalizado** com destaque em vermelho.  

---

## 🛠️ Tecnologias Utilizadas  

| Categoria | Ferramenta |
|------------|-------------|
| Linguagem | **Java SE 21** |
| Biblioteca Gráfica | **Swing (javax.swing)** |
| IDE recomendada | **Eclipse / IntelliJ IDEA** |
| Persistência | **Serialização com ObjectOutputStream** |
| Armazenamento | **Arquivos locais `.dat`** |

---

## 🚀 Como Executar  

1. **Clone o repositório:**  
   ```bash
   git clone https://github.com/seu-usuario/SistemaGame.git
2. **Abra o projeto no Eclipse ou IntelliJ IDEA.**

3. **Verifique o módulo:**
```
module SistemaGame {
    requires java.desktop;
    exports jogo;
}
```
4. **Execute a classe principal:**
```
SistemaGameUI.java (Caso for usar a interfcae)
Main.java (Caso for usar no Console)
```

📂 **Estrutura do Projeto**

```
📦 SistemaGame/
 ┣ 📁 src/
 ┃ ┗ 📂 jogo/
 ┃   ┣ 📄 Cliente.java
 ┃   ┣ 📄 ClienteRegular.java
 ┃   ┣ 📄 ClientePremium.java
 ┃   ┣ 📄 Jogo.java
 ┃   ┣ 📄 Venda.java
 ┃   ┣ 📄 Main.java
 ┃   ┗ 📄 SistemaGameUI.java
 ┗ 📄 module-info.java
```
👨‍💻 **Autor**
**Gabriel da Silva Bitencourt**

💡 Projeto acadêmico com foco em **POO, interfaces gráficas e persistência de dados.**

🧠 Desenvolvido para demonstrar boas práticas de design e usabilidade em Java.
